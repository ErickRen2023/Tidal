package me.erickren.response;

/**
 * Http Response Object.
 * @author ErickRen
 * @since 0.0.1
 */
public interface HttpResponse {
    ResponseStatusLine statusLine = null;
    ResponseHeader header = null;
    ResponseBody body = null;

    /**
     * Set the Http status code of the response.
     * @param code codee
     */
    void setCode(Integer code);

    /**
     * Set the header of the response.
     * @param header header
     */
    void setHeader(ResponseHeader header);

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
}
