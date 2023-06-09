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

    /**
     * Get the status line.
     * @return status line
     */
    ResponseStatusLine getStatusLine();

    /**
     * Set the content length.
     * @param contentLength length
     */
    void setContentLength(String contentLength);

    /**
     * Get the content length.
     * @return String content length.
     */
    String getContentLength();

    /**
     * Get the transfer encoding type
     * @return bool
     */
    boolean isChucked();

}
