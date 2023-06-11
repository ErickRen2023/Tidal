package me.erickren.response;

/**
 * Http Response Object.
 * @author ErickRen
 * @since 0.0.1
 */
public interface HttpResponse {

    /**
     * Set the Http status code of the response.
     * @param code codee
     */
    void setHttpCode(Integer code);

    /**
     * Set the header of the response.
     * @param header header
     */
    void setHeader(ResponseHeader header);

    /**
     * Get header value by key
     * @param headerKey key
     * @return value
     */
    String getHeaderValue(String headerKey);

    /**
     * Get the header object.
     * @return ResponseHeader
     */
    ResponseHeader getHeaderObject();

    /**
     * Get the header text.
     * @return header text
     */
    String getHeader();

    /**
     * Set the body of the response.
     * @param body body
     */
    void setBody(ResponseBody body);

    /**
     * Get the response status code.
     * @return http code
     */
    Integer getResponseCode();

    /**
     * Get the body text.
     * @return body
     */
    String getBody();
}
