package me.erickren.request;

import me.erickren.response.HttpResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public class HttpRequestImpl implements HttpRequest{
    RequestLine line;
    RequestHeader header;
    RequestData data;

    public HttpRequestImpl(RequestLine line, RequestHeader header, RequestData data) {
        this.line = line;
        this.header = header;
        this.data = data;
    }

    public HttpRequestImpl(RequestLine line) {
        this.line = line;
    }

    public HttpRequestImpl(String line) throws MalformedURLException, UnsupportedEncodingException {
        this.line = new RequestLineImpl(line);
    }

    @Override
    public void setLine(RequestLine line) {
        this.line = line;
    }

    @Override
    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    @Override
    public void setData(RequestData data) {
        this.data = data;
    }

    @Override
    public void setUrl(String url) throws MalformedURLException, UnsupportedEncodingException {
        this.line.setHttpUrl(url);
    }

    @Override
    public void setUrl(RequestUrl url) {
        this.line.setHttpUrl(url);
    }

    @Override
    public HttpResponse Get() {
        this.line.setMethod(RequestMethod.GET);
        return this.Request(this);
    }

    @Override
    public HttpResponse Post(RequestData data) {
        this.line.setMethod(RequestMethod.POST);
        return this.Request(this);
    }

    @Override
    public HttpResponse Options() {
        this.line.setMethod(RequestMethod.OPTIONS);
        return this.Request(this);
    }

    @Override
    public HttpResponse Put() {
        this.line.setMethod(RequestMethod.PUT);
        return this.Request(this);
    }

    @Override
    public HttpResponse Delete() {
        this.line.setMethod(RequestMethod.DELETE);
        return this.Request(this);
    }

    @Override
    public HttpResponse Head() {
        this.line.setMethod(RequestMethod.HEAD);
        return this.Request(this);
    }

    @Override
    public HttpResponse Request(HttpRequest request) {
        throw new NotImplementedException();
        //return null;
    }
}
