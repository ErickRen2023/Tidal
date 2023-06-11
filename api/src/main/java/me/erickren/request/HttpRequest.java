package me.erickren.request;

import me.erickren.response.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
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
     * @param requestLine line
     */
    void setRequestLine(RequestLine requestLine);

    /**
     * Set the request header.
     * @param requestHeader header
     */
    void setRequestHeader(RequestHeader requestHeader);

    /**
     * Get the request header.
     * @return Request Header
     */
    RequestHeader getRequestHeader();

    /**
     * Set the request data.If the Request method is not POST,the data will not work.
     * @param requestData data
     */
    void setRequestData(RequestData requestData);

    /**
     * Get the Request Data.
     * @return Request Data
     */
    RequestData getRequestData();

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
     * Get the request line of the Request
     * @return RequestLine
     */
    RequestLine getRequestLine();

    /**
     * Send the GET request.
     * @return HttpResponse
     */
    HttpResponse get() throws IOException;

    /**
     * Send the POST request.
     * @return HttpResponse
     */
    HttpResponse post(RequestData data) throws IOException;

    /**
     * Send the OPTIONS request.
     * @return HttpResponse
     */
    HttpResponse options() throws IOException;

    /**
     * Send the PUT request.
     * @return HttpResponse
     */
    HttpResponse put() throws IOException;

    /**
     * Send the DELETE request.
     * @return HttpResponse
     */
    HttpResponse delete() throws IOException;

    /**
     * Send the HEAD request.
     * @return HttpResponse
     */
    HttpResponse head() throws IOException;

    /**
     * Build the request text.
     * @return request text
     */
    String buildRequestText(HttpRequest request);

    /**
     * Parse the response
     * @param stream InputStream
     * @return response
     */
    HttpResponse parseResponse(InputStream stream) throws IOException;

    /**
     * Send the request by the RequestLine method.
     * @return HttpResponse
     */
    HttpResponse request(HttpRequest request) throws IOException;

    /**
     * Http Request
     * @param request request
     * @return HttpResponse
     */
    HttpResponse httpRequest(HttpRequest request) throws IOException;

    /**
     * Https Request
     * @param request request
     * @return HttpResponse
     */
    HttpResponse httpsRequest(HttpRequest request) throws IOException;

}
