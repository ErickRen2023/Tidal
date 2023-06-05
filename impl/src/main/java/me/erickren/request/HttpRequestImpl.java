package me.erickren.request;

import me.erickren.response.HttpResponse;

public class HttpRequestImpl implements HttpRequest{
    RequestLine line;
    RequestHeader header;
    RequestData data;

    @Override
    public void setLine(RequestLine line) {

    }

    @Override
    public void setHeader(RequestHeader header) {

    }

    @Override
    public void setData(RequestData data) {

    }

    @Override
    public void setUrl(String url) {

    }

    @Override
    public void setUrl(HttpUrl url) {

    }

    @Override
    public HttpResponse Get() {
        return null;
    }

    @Override
    public HttpResponse Post(RequestData data) {
        return null;
    }

    @Override
    public HttpResponse Options() {
        return null;
    }

    @Override
    public HttpResponse Put() {
        return null;
    }

    @Override
    public HttpResponse Delete() {
        return null;
    }

    @Override
    public HttpResponse Head() {
        return null;
    }
}
