package me.erickren.request;

import me.erickren.enums.RequestMethod;
import me.erickren.response.HttpResponseImpl;

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
    public HttpResponseImpl get() throws IOException {
        this.requestLine.setMethod(RequestMethod.GET);
        return this.request(this);
    }

    @Override
    public HttpResponseImpl post(RequestData data) throws IOException {
        this.requestLine.setMethod(RequestMethod.POST);
        return this.request(this);
    }

    @Override
    public HttpResponseImpl options() throws IOException {
        this.requestLine.setMethod(RequestMethod.OPTIONS);
        return this.request(this);
    }

    @Override
    public HttpResponseImpl put() throws IOException {
        this.requestLine.setMethod(RequestMethod.PUT);
        return this.request(this);
    }

    @Override
    public HttpResponseImpl delete() throws IOException {
        this.requestLine.setMethod(RequestMethod.DELETE);
        return this.request(this);
    }

    @Override
    public HttpResponseImpl head() throws IOException {
        this.requestLine.setMethod(RequestMethod.HEAD);
        return this.request(this);
    }

    @Override
    public HttpResponseImpl request(HttpRequest request) throws IOException {
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
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        socket.close();
        return null;
    }
}
