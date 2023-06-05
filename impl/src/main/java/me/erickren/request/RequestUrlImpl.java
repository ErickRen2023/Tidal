package me.erickren.request;

import me.erickren.exception.PortOutOfRangeException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestUrlImpl implements RequestUrl {

    private String protocol = "http://";
    private String host;
    private Integer port = 80;
    @Nullable private List<String> path = new ArrayList<>();
    @Nullable private HashMap<String, String> parameter = new HashMap<>();

    public RequestUrlImpl(@NotNull String url) throws MalformedURLException, UnsupportedEncodingException {
        this.parseUrlByString(url);
    }

    public RequestUrlImpl(@NotNull String protocol, @NotNull String host, @NotNull Integer port, @Nullable List<String> path, @Nullable HashMap<String, String> parameter) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
        this.parameter = parameter;
    }

    @Override
    public RequestUrl setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    @Override
    public @NotNull String getProtocol() {
        return this.protocol;
    }

    @Override
    public RequestUrl setHost(String host) {
        this.host = host;
        return this;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public RequestUrl setPort(Integer port) {
        if (port > 65535 || port < 1){
            throw new PortOutOfRangeException("Port is out of range!");
        } else {
            this.port = port;
        }
        return this;
    }

    @Override
    public Integer getPort() {
        return this.port;
    }

    @Override
    public RequestUrl addPath(String path) {
        this.path.add(path);
        return this;
    }

    @Override
    public RequestUrl setPaths(String path) throws UnsupportedEncodingException {
        if (path.startsWith("/")){
            path = path.substring(1);
        }
        String[] split = path.split("/");
        for (String s : split) {
            this.path.add(URLDecoder.decode(s, "UTF-8"));
        }
        return this;
    }

    @Override
    public RequestUrl setPaths(List<String> path) {
        this.path = path;
        return this;
    }

    @Override
    public RequestUrl addParameter(String name, String value) {
        this.parameter.put(name, value);
        return this;
    }

    @Override
    public RequestUrl setParameter(HashMap<String, String> parameter) {
        this.parameter = parameter;
        return this;
    }

    @Override
    public RequestUrl parseUrlByString(String path) throws MalformedURLException, UnsupportedEncodingException {
        URL url = new URL(path);
        this.setProtocol(url.getProtocol() + "://");
        this.setHost(url.getHost());
        if (url.getPort() == -1){
            this.setPort(url.getDefaultPort());
        } else {
            this.setPort(url.getPort());
        }
        this.setPaths(url.getPath());
        String[] params = url.getQuery().split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            String key = URLDecoder.decode(keyValue[0], "UTF-8");
            String value="";
            if (keyValue.length > 1) {
                value = URLDecoder.decode(keyValue[1], "UTF-8");
            }
            this.addParameter(key, value);
        }
        return this;
    }

    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(protocol)
                .append(host)
                .append(":")
                .append(port);
        for (String s : path) {
            sb.append("/");
            sb.append(s);
        }
       sb.append("?");
        for (String k : parameter.keySet()) {
            sb.append(k)
                    .append("=")
                    .append(parameter.get(k))
                    .append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
