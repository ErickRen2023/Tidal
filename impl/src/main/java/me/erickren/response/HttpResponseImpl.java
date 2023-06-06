package me.erickren.response;

public class HttpResponseImpl implements HttpResponse{

    private ResponseStatusLine statusLine;
    private ResponseHeader responseHeader;
    private ResponseBody responseBody;


    @Override
    public void setHttpCode(Integer code) {
        this.statusLine.setHttpCode(code);
    }

    @Override
    public void setHeader(ResponseHeader header) {
        this.responseHeader = header;
    }

    @Override
    public void setBody(ResponseBody body) {
        this.responseBody = body;
    }

    @Override
    public Integer getResponseCode() {
        return this.statusLine.getHttpCode();
    }
}
