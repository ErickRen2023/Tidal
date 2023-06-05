package me.erickren.request;

import java.util.HashMap;

/**
 * Build the Request header.
 * @author ErickRen
 * @since 0.0.1
 */
public interface RequestHeader {

    /**
     * Add a header to Request Header.
     * @param header header
     */
    void addHeader(String header);

    /**
     * Set the header on Request Header
     * @param headers HashMap[String, String]
     */
    void setHeader(HashMap<String, String> headers);
}
