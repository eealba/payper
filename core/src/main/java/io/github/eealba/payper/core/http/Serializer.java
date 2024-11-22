package io.github.eealba.payper.core.http;

/**
 * The interface Serializer.
 */
public interface Serializer {

    /**
     * Serialize to byte array byte [ ].
     *
     * @param obj the obj
     * @return the byte [ ]
     */
    byte[] serializeToByteArray(Object obj);

    /**
     * Serialize to string string.
     *
     * @param obj the obj
     * @return the string
     */
    String serializeToString(Object obj);

}
