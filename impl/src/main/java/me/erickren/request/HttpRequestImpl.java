package me.erickren.request;

import me.erickren.enums.RequestMethod;
import me.erickren.response.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;


public class HttpRequestImpl implements HttpRequest{
    private RequestLine requestLine;
    private RequestHeader requestHeader;
    private RequestData requestData;

    // If the source is false, it will automatically request the moved link.
    // eg: The status code is 301 and header has Location.It will automatically build a new request for the new link.
    private boolean source = false;

    public HttpRequestImpl(RequestLine requestLine, RequestHeader requestHeader, RequestData requestData) {
        this.requestLine = requestLine;
        this.requestHeader = requestHeader;
        this.requestData = requestData;
    }

    public HttpRequestImpl(RequestLine requestLine) {
        this.requestLine = requestLine;
    }

    public HttpRequestImpl(String requestLine) throws MalformedURLException, UnsupportedEncodingException {
        this(requestLine, false);
    }

    public HttpRequestImpl(String requestLine, boolean source) throws MalformedURLException, UnsupportedEncodingException {
        this.requestLine = new RequestLineImpl(requestLine);
        this.requestHeader = new RequestHeaderImpl();
        this.requestHeader.setHost(this.requestLine.getRequestUrl().getHost());
        this.source = source;
    }

    @Override
    public void setRequestLine(RequestLine requestLine) {
        this.requestLine = requestLine;
    }

    @Override
    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    @Override
    public RequestHeader getRequestHeader() {
        return this.requestHeader;
    }

    @Override
    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
    }

    @Override
    public RequestData getRequestData() {
        return this.requestData;
    }

    @Override
    public void setUrl(String url) throws MalformedURLException, UnsupportedEncodingException {
        this.requestLine.setHttpUrl(url);
    }

    @Override
    public void setUrl(RequestUrl url) {
        this.requestLine.setHttpUrl(url);
    }

    @Override
    public RequestLine getRequestLine() {
        return this.requestLine;
    }

    @Override
    public HttpResponse get() throws IOException {
        this.requestLine.setMethod(RequestMethod.GET);
        return this.request(this);
    }

    @Override
    public HttpResponse post(RequestData data) throws IOException {
        this.requestLine.setMethod(RequestMethod.POST);
        return this.request(this);
    }

    @Override
    public HttpResponse options() throws IOException {
        this.requestLine.setMethod(RequestMethod.OPTIONS);
        return this.request(this);
    }

    @Override
    public HttpResponse put() throws IOException {
        this.requestLine.setMethod(RequestMethod.PUT);
        return this.request(this);
    }

    @Override
    public HttpResponse delete() throws IOException {
        this.requestLine.setMethod(RequestMethod.DELETE);
        return this.request(this);
    }

    @Override
    public HttpResponse head() throws IOException {
        this.requestLine.setMethod(RequestMethod.HEAD);
        return this.request(this);
    }

    @Override
    public String buildRequestText(HttpRequest request) {
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append(request.getRequestLine().build())
                .append(request.getRequestHeader().build())
                .append("\r\n\r\n");
        return requestBuilder.toString();
    }

    @Override
    public HttpResponse parseResponse(InputStream stream) throws IOException {
        // Parse the Response
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        int statusCode = -1;
        ResponseStatusLine responseStatusLine = new ResponseStatusLineImpl();
        ResponseHeader responseHeader = new ResponseHeaderImpl();
        ResponseBody responseBody = new ResponseBodyImpl();
        // Parse the Headers
        int len = 0;
        while ((line = reader.readLine()) != null) {
            if (statusCode == -1) {
                // Parse the Http Code
                String[] statusLine = line.split(" ");
                statusCode = Integer.parseInt(statusLine[1]);
                responseStatusLine.setHttpCode(statusCode);
                responseStatusLine.setHttpCodeDescription(statusLine[2]);
            } else if (line.isEmpty()) {
                // End of Headers
                break;
            } else {
                // Parse the Headers
                String[] headerParts = line.split(": ");
                responseHeader.setHeader(headerParts[0], headerParts[1]);
            }
        }

        StringBuilder bodySb = new StringBuilder();
        // Parse the Response Body
        if (responseHeader.getContentLength() != null) {
            // Content-Length
            int length = Integer.parseInt(responseHeader.getContentLength());
            char[] buffer = new char[1024];
            int bytesRead;
            int totalBytesRead = 0;
            while ((bytesRead = reader.read(buffer)) != -1) {
                bodySb.append(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                if (totalBytesRead >= length) {
                    break;
                }
            }
        } else {
            // Chucked
            while ((line = reader.readLine()) != null) {
                int chunkSize = Integer.parseInt(line, 16); // headers len: 687
                if (chunkSize == 0) {
                    // End of the response
                    // Fuck you bilibili.
                    reader.readLine(); // Skip the final CRLF after the last chunk
                    break;
                }
                int point = 0;
                while (point <= chunkSize) {
                    char[] buffer = new char[1];
                    reader.read(buffer, 0,  1);
                    if (String.valueOf(buffer[0]).equals("\n")) {
                        break;
                    }
                    bodySb.append(buffer);
                    point++;
                }
            }
        }
        reader.close();
        responseBody.setBody(bodySb.toString());
        responseHeader.setStatusLine(responseStatusLine);
        HttpResponse httpResponse = new HttpResponseImpl();
        httpResponse.setHeader(responseHeader);
        httpResponse.setBody(responseBody);
        return httpResponse;
    }

    @Override
    public HttpResponse request(HttpRequest request) throws IOException {
        if (request.getRequestLine().getRequestUrl().getProtocol().contains("https")) {
            return httpsRequest(request);
        } else {

            return httpRequest(request);
        }
    }


    @Override
    public HttpResponse httpRequest(HttpRequest request) throws IOException {
        String host = request.getRequestLine().getRequestUrl().getHost();
        Integer port = request.getRequestLine().getRequestUrl().getPort();
        String requestText = buildRequestText(request);
        // Build socket and send request
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(requestText.getBytes());
        // Receive the Response
        InputStream inputStream = socket.getInputStream();
        // parse the response
        HttpResponse httpResponse = parseResponse(inputStream);
        socket.close();
        if (httpResponse.getResponseCode() / 100 == 3 && !source) {
            request.getRequestLine().setHttpUrl(httpResponse.getHeaderValue("Location"));
            return this.request(request);
        } else {
            return httpResponse;
        }
    }

    @Override
    public HttpResponse httpsRequest(HttpRequest request) throws IOException {
        String host = request.getRequestLine().getRequestUrl().getHost();
        Integer port = request.getRequestLine().getRequestUrl().getPort();
        String requestText = buildRequestText(request);
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
        socket.startHandshake();
        OutputStream os = socket.getOutputStream();
        os.write(requestText.getBytes());
        os.flush();
        HttpResponse httpResponse = parseResponse(socket.getInputStream());
        os.close();
        socket.close();
        if (httpResponse.getResponseCode() / 100 == 3) {
            request.getRequestLine().setHttpUrl(httpResponse.getHeaderValue("Location"));
            return request(request);
        } else {
            return httpResponse;
        }
    }

}
