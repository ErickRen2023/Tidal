package me.erickren.request;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.List;

/**
 * Build HttpUrl.
 * @author ErickRen
 * @since 0.0.1
 */
public interface HttpUrl {

    @NotNull String protocol = "";
    @NotNull String host = "";
    @NotNull Integer port = 80;
    @Nullable List<String> path = null;
    @Nullable List<HashMap<String, String>> parameter = null;

    /**
     * Set the protocol of request in HttpUrl.
     * @param protocol protocol
     * @return HttpUrl
     */
    HttpUrl setProtocol(String protocol);

    /**
     * Set the host of request in HttpUrl.
     * @param host host
     * @return HttpUrl
     */
    HttpUrl setHost(String host);

    /**
     * Set the port of request,default port is 80.
     * @param port port
     * @return HttpUrl
     */
    HttpUrl setPort(Integer port);

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
    HttpUrl setPaths(String path);

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
    HttpUrl setParameter(List<HashMap<String, String>> parameter);

    /**
     * Build the HttpUrl.
     * @return String HttpUrl.
     */
    String toString();
}
