package me.erickren.response;

/**
 * The status line in response.
 * @author ErickRen
 * @since 0.0.1
 */
public interface ResponseStatusLine {

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

    /**
     * Set the HttpCode description.
     * @param description description
     */
    void setHttpCodeDescription(String description);

    /**
     * Get the description.
     * @return description
     */
    String getHttpCodeDescription();

    /**
     * Get the HttpVersion.
     * @return version
     */
    String getHttpVersion();

    /**
     * Build the statusLine
     * @return line
     */
    String build();
}
