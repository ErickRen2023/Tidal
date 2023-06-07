package me.erickren.header;

public interface Header {

    /**
     * Set the header line.
     * @param headerKey headerKey
     * @param headerValue headerValue
     */
    void setHeader(String headerKey, String headerValue);

    /**
     * Build the header to String
     * @return String header
     */
    String build();
}
