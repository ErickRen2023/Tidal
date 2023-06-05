package me.erickren.request;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RequestLineImpl implements RequestLine {

    private RequestMethod method;
    private HttpUrl HttpUrl;
    private final String HttpVersion = "HTTP/1.1";

    public RequestLineImpl(RequestMethod method, me.erickren.request.HttpUrl httpUrl) {
        this.method = method;
        this.HttpUrl = httpUrl;
    }

    @Override
    public void setMethod(RequestMethod method) {
        switch(method) {
            case GET:
                this.method = RequestMethod.GET;
                break;
            case POST:
                this.method = RequestMethod.POST;
                break;
            case PUT:
                this.method = RequestMethod.PUT;
                break;
            case DELETE:
                this.method = RequestMethod.DELETE;
                break;
            case OPTIONS:
                this.method = RequestMethod.OPTIONS;
                break;
            case HEAD:
                this.method = RequestMethod.HEAD;
                break;
            default:
                throw new NotImplementedException();
        }
    }

    @Override
    public void setHttpUrl(HttpUrl httpUrl) {
        throw new NotImplementedException();
    }

    @Override
    public void setHttpUrl(String httpUrl) {
        throw new NotImplementedException();
    }
}
