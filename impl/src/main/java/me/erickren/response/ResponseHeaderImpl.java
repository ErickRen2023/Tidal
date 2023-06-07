package me.erickren.response;

import me.erickren.header.HeaderImpl;

public class ResponseHeaderImpl extends HeaderImpl implements ResponseHeader {

    private ResponseStatusLine statusLine;

    @Override
    public void setStatusLine(ResponseStatusLine line) {
        this.statusLine = line;
    }

    @Override
    public String build(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.statusLine.build());
        for (String key : this.getHeaders().keySet()){
            sb.append(key)
                    .append(": ")
                    .append(this.getHeaders().get(key))
                    .append("\n");
        }
        return sb.toString();
    }
}
