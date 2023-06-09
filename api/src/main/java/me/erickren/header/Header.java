package me.erickren.header;

import java.util.HashMap;

/**
 * Header for request or response
 * @author ErickRen
 * @since 0.0.1
 */
public interface Header {

    /**
     * Set the header line.
     * @param headerKey headerKey
     * @param headerValue headerValue
     */
    void setHeader(String headerKey, String headerValue);

    /**
     * Build the header to String.
     * @return String header
     */
    String build();

    /**
     * Get the headers.
     * @return HashMap headers
     */
    HashMap<String, String> getHeaders();

    /**
     * Get the value by the key
     * @param headerKey key
     * @return header value
     */
    String getHeader(String headerKey);
}
