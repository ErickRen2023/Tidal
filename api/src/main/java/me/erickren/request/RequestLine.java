package me.erickren.request;

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
     * @param httpUrl url
     */
    void setHttpUrl(HttpUrl httpUrl);

    /**
     * Set the request url by String Object.
     * @param httpUrl url
     */
    void setHttpUrl(String httpUrl);
}
