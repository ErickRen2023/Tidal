package me.erickren.request;

import me.erickren.response.HttpResponse;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;

public class HttpRequestImpl implements HttpRequest{
    RequestLine requestLine;
    RequestHeader requestHeader;
    RequestData requestData;

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
    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
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
        requestBuilder.append(request.getRequestLine().getMethod().toString())
                .append(" ")
                .append(request.getRequestLine().getRequestUrl().getPath())
                .append(" ")
                .append(request.getRequestLine().getHttpVersion())
                .append("\r\n")
                .append("Host: ")
                .append(host)
                .append("\r\n")
                .append("Connection: close\r\n\r\n");
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(requestBuilder.toString().getBytes());

        // 接收HTTP响应
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // 关闭连接
        socket.close();
        return null;
    }
}
