package me.erickren.request;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 * Build the request line.
 * @author ErickRen
 * @since 0.0.1
 */
public interface RequestLine {

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
     * Get the Request Method.
     * @return method
     */
    RequestMethod getMethod();
}
