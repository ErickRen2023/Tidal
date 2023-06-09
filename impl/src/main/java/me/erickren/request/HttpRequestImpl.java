package me.erickren.request;

import me.erickren.enums.RequestMethod;
import me.erickren.response.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;


public class HttpRequestImpl implements HttpRequest{
    private RequestLine requestLine;
    private RequestHeader requestHeader;
    private RequestData requestData;

    public HttpRequestImpl(RequestLine requestLine, RequestHeader requestHeader, RequestData requestData) {
        this.requestLine = requestLine;
        this.requestHeader = requestHeader;
        this.requestData = requestData;
    }

    public HttpRequestImpl(RequestLine requestLine) {
        this.requestLine = requestLine;
    }

    public HttpRequestImpl(String requestLine) throws MalformedURLException, UnsupportedEncodingException {
        this.requestLine = new RequestLineImpl(requestLine);
        this.requestHeader = new RequestHeaderImpl();
        this.requestHeader.setHost(this.requestLine.getRequestUrl().getHost());
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
    public HttpResponse request(HttpRequest request) throws IOException {
        String host = request.getRequestLine().getRequestUrl().getHost();
        Integer port = request.getRequestLine().getRequestUrl().getPort();
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append(request.getRequestLine().build())
                .append(request.getRequestHeader().build())
                .append("\r\n\r\n");
        //Build socket and send request
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(requestBuilder.toString().getBytes());
        // Receive the Response
        InputStream inputStream = socket.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        // Parse the Response
        String line;
        int statusCode = -1;
        ResponseStatusLine responseStatusLine = new ResponseStatusLineImpl();
        ResponseHeader responseHeader = new ResponseHeaderImpl();
        ResponseBody responseBody = new ResponseBodyImpl();
        // Parse the Headers
        while ((line = reader.readLine()) != null) {
            if (statusCode == -1) {
                // Parse the Http Code
                String[] statusLine = line.split(" ");
                statusCode = Integer.parseInt(statusLine[1]);
            } else if (line.isEmpty()) {
                // End of Headers
                break;
            } else {
                // Parse the Headers
                String[] headerParts = line.split(": ");
                responseHeader.setHeader(headerParts[0], headerParts[1]);
            }
        }
        // Parse the Response Body
        int length = Integer.parseInt(responseHeader.getContentLength());
        StringBuilder bodySb = new StringBuilder();
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
        reader.close();
        socket.close();
        responseBody.setBody(bodySb.toString());
        responseHeader.setStatusLine(responseStatusLine);
        HttpResponse httpResponse = new HttpResponseImpl();
        httpResponse.setHeader(responseHeader);
        httpResponse.setBody(responseBody);
        return httpResponse;
    }
}
