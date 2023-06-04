package me.erickren.response;

/**
 * The status line in response.
 * @author ErickRen
 * @since 0.0.1
 */
public interface ResponseStatusLine {
    String httpVersion = "HTTP/1.0";
    Integer httpCode = 0;

    /**
     * Set the HttpCode.
     * @param httpCode httpCode
     */
    void setHttpCode(Integer httpCode);

    /**
     * Get the response code.
     * @return code
     */
    Integer getHttpCode();
}
