package me.erickren.request;

import me.erickren.exception.UnKnowRequestMethodException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public class RequestLineImpl implements RequestLine {

    private RequestMethod method;
    private RequestUrl requestUrl;
    private final String httpVersion = "HTTP/1.1";

    public RequestLineImpl(RequestMethod method, RequestUrl requestUrl) {
        this.method = method;
        this.requestUrl = requestUrl;
    }

    public RequestLineImpl(String url) throws MalformedURLException, UnsupportedEncodingException {
        this.requestUrl = new RequestUrlImpl(url);
    }

    @Override
    public void setMethod(RequestMethod method) throws UnKnowRequestMethodException {
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
                throw new UnKnowRequestMethodException("UnKnow Request Method!");
        }
    }

    @Override
    public void setHttpUrl(RequestUrl requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void setHttpUrl(String httpUrl) throws MalformedURLException, UnsupportedEncodingException {
        this.requestUrl = new RequestUrlImpl(httpUrl);
    }

    @Override
    public RequestMethod getMethod() {
        return this.method;
    }
}
