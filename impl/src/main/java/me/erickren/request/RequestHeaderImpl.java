package me.erickren.request;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;

public class RequestHeaderImpl implements RequestHeader{

    private HashMap<String, String> header = new HashMap<>();

    @Override
    public void setHeader(String headerKey, String headerValue) {
        this.header.put(headerKey, headerValue);
    }

    @Override
    public void setAcceptLanguage(String value) {
        throw new NotImplementedException();
    }

    @Override
    public void setHost(String host) {
        this.header.put("Host", host);
    }

    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        for (String key : header.keySet()) {
            sb.append(key)
                    .append(": ")
                    .append(header.get(key))
                    .append("\n");
        }
        return sb.toString();
    }
}
