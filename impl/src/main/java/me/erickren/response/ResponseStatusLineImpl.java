package me.erickren.response;

public class ResponseStatusLineImpl implements ResponseStatusLine{
    private Integer httpCode;

    @Override
    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    @Override
    public Integer getHttpCode() {
        return this.httpCode;
    }
}
