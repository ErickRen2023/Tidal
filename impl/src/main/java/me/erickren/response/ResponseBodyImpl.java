package me.erickren.response;

public class ResponseBodyImpl implements ResponseBody{

    private String body = "";

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getBody() {
        return this.body;
    }
}
