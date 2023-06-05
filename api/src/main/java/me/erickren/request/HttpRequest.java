package me.erickren.request;

import me.erickren.response.HttpResponse;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 * Http connection Object.
 * @author ErickRen
 * @since 0.0.1
 */
public interface HttpRequest {

    /**
     * Set the request line.
     * @param line line
     */
    void setLine(RequestLine line);

    /**
     * Set the request header.
     * @param header header
     */
    void setHeader(RequestHeader header);

    /**
     * Set the request data.If the Request method is not POST,the data will not work.
     * @param data data
     */
    void setData(RequestData data);

    /**
     * Set the request url by String Object.
     * @param url url
     */
    void setUrl(String url) throws MalformedURLException, UnsupportedEncodingException;

    /**
     * Set the request url by HttpUrl Object.
     * @param url url
     */
    void setUrl(RequestUrl url);

    /**
     * Send the GET request.
     * @return HttpResponse
     */
    HttpResponse Get();

    /**
     * Send the POST request.
     * @return HttpResponse
     */
    HttpResponse Post(RequestData data);

    /**
     * Send the OPTIONS request.
     * @return HttpResponse
     */
    HttpResponse Options();

    /**
     * Send the PUT request.
     * @return HttpResponse
     */
    HttpResponse Put();

    /**
     * Send the DELETE request.
     * @return HttpResponse
     */
    HttpResponse Delete();

    /**
     * Send the HEAD request.
     * @return HttpResponse
     */
    HttpResponse Head();

    /**
     * Send the request by the RequestLine method.
     * @return
     */
    HttpResponse Request(HttpRequest request);

}
