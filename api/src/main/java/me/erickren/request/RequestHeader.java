package me.erickren.request;

import me.erickren.header.Header;

/**
 * Build the Request header.
 * @author ErickRen
 * @since 0.0.1
 */
public interface RequestHeader extends Header {

    /**
     * Set the Accept-Language.
     * @param value language
     */
    void setAcceptLanguage(String value);

    /**
     * Set the Header Host.
     * @param host host
     */
    void setHost(String host);

}
