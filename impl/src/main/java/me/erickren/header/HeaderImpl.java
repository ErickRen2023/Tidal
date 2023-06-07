package me.erickren.header;

import java.util.HashMap;

public class HeaderImpl implements Header {

    private HashMap<String, String> headers = new HashMap<>();

    @Override
    public void setHeader(String headerKey, String headerValue) {
        this.headers.put(headerKey, headerValue);
    }

    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        for (String key : this.headers.keySet()){
            sb.append(key)
                    .append(": ")
                    .append(this.headers.get(key))
                    .append("\n");
        }
        return sb.toString();
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }
}
