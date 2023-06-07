package me.erickren.request;

import me.erickren.enums.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 * Build the request line.
 * @author ErickRen
 * @since 0.0.1
 */
public interface RequestLine {

    /**
     * Get Http Version. "HTTP/1.1" only.
     * @return version
     */
    String getHttpVersion();

    /**
     * Set the request method.
     * @param method method
     */
    void setMethod(RequestMethod method);

    /**
     * Set the request url by HttpUrl Object.
     * @param requestUrl url
     */
    void setHttpUrl(RequestUrl requestUrl);

    /**
     * Set the request url by String Object.
     * @param httpUrl url
     */
    void setHttpUrl(String httpUrl) throws MalformedURLException, UnsupportedEncodingException;

    /**
     * Get the RequestUrl.
     * @return RequestUrl
     */
    RequestUrl getRequestUrl();

    /**
     * Get the Request Method.
     * @return method
     */
    RequestMethod getMethod();

    /**
     * Build the request line to String Object.
     * @return line
     */
    String build();
}
