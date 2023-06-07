package me.erickren.response;

import me.erickren.header.Header;

/**
 * Build the response header.
 * @author ErickRen
 * @since 0.0.1
 */
public interface ResponseHeader extends Header {

    /**
     * Set the Status Line.
     * @param line line
     */
    void setStatusLine(ResponseStatusLine line);
}
