package me.erickren.response;

public class ResponseStatusLineImpl implements ResponseStatusLine{
    private final String httpVersion = "HTTP/1.1";
    private Integer httpCode;
    private String httpCodeDescription;

    @Override
    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    @Override
    public Integer getHttpCode() {
        return this.httpCode;
    }

    @Override
    public void setHttpCodeDescription(String description) {
        this.httpCodeDescription = description;
    }

    @Override
    public String getHttpCodeDescription() {
        return this.httpCodeDescription;
    }

    @Override
    public String getHttpVersion() {
        return this.httpVersion;
    }



    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getHttpVersion())
                .append(" ")
                .append(this.getHttpCode())
                .append(" ")
                .append(this.getHttpCodeDescription())
                .append("\n");
        return sb.toString();
    }

}
