package me.erickren.response;

import me.erickren.header.HeaderImpl;

public class HttpResponseImpl implements HttpResponse {
    private ResponseHeader responseHeader;
    private ResponseBody responseBody;


    @Override
    public void setHttpCode(Integer code) {
        this.responseHeader.getStatusLine().setHttpCode(code);
    }

    @Override
    public void setHeader(ResponseHeader header) {
        this.responseHeader = header;
    }

    @Override
    public String getHeaderValue(String headerKey) {
        return responseHeader.getHeaderValue(headerKey);
    }

    @Override
    public String getHeader() {
        return this.responseHeader.build();
    }

    @Override
    public ResponseHeader getHeaderObject() {
        return this.responseHeader;
    }

    @Override
    public void setBody(ResponseBody body) {
        this.responseBody = body;
    }

    @Override
    public Integer getResponseCode() {
        return this.responseHeader.getStatusLine().getHttpCode();
    }

    @Override
    public String getBody() {
        return this.responseBody.getBody();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.responseHeader.build())
                .append("\n")
                .append(this.responseBody.getBody());
        return sb.toString();
    }
}
