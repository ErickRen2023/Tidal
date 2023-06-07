package me.erickren.response;

/**
 * Build the response body.
 * @author ErickRen
 * @since 0.0.1
 */
public interface ResponseBody {

    /**
     * Set body for response.
     * @param body body
     */
    void setBody(String body);

    /**
     * Get the response body.
     * @return body
     */
    String getBody();
}
