package me.erickren.request;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

/**
 * Build HttpUrl.
 * @author ErickRen
 * @since 0.0.1
 */
public interface HttpUrl {

    /**
     * Set the protocol of request in HttpUrl.
     * @param protocol protocol
     * @return HttpUrl
     */
    HttpUrl setProtocol(String protocol);

    /**
     * Get the http protocol.
     * @return protocol
     */
    String getProtocol();

    /**
     * Set the host of request in HttpUrl.PS:the method will never check the legality of value.
     * @param host host
     * @return HttpUrl
     */
    HttpUrl setHost(String host);

    /**
     * Get the host.
     * @return host
     */
    String getHost();

    /**
     * Set the port of request,default port is 80.
     * @param port port
     * @return HttpUrl
     */
    HttpUrl setPort(Integer port);

    /**
     * Get the port.
     * @return Integer
     */
    Integer getPort();

    /**
     * Add a path to the HttpUrl.
     * @param path String -> path
     * @return HttpUrl
     */
    HttpUrl addPath(String path);

    /**
     * Set the paths on the HttpUrl.
     * @param path String -> paths like: http://host:port/[path1/path2/...]
     * @return HttpUrl
     */
    HttpUrl setPaths(String path) throws UnsupportedEncodingException;

    /**
     * Set the paths on the HttpUrl.
     * @param path List:String -> paths
     * @return HttpUrl
     */
    HttpUrl setPaths(List<String> path);

    /**
     * Add a param to the parameters of the HttpUrl.
     * @param name param key.
     * @param value param value.
     * @return HttpUrl
     */
    HttpUrl addParameter(String name, String value);

    /**
     * Set the parameters on the HttpUrl.
     * @param parameter List:HashMap -> key:String value:String
     * @return HttpUrl
     */
    HttpUrl setParameter(HashMap<String, String> parameter);

    /**
     * Build the HttpUrl.
     * @return String HttpUrl.
     */
    String toString();

    /**
     * Parse the url by String Object.
     * @param path String path.
     * @return HttpUrl Object
     */
    HttpUrl parseUrlByString(String path) throws MalformedURLException, UnsupportedEncodingException;

    /**
     * Build the url.
     * @return String
     */
    String build();
}
