package io.github.eealba.payper.core.http;

/**
 * A deserializer receives raw data and converts it to a Java entity, usually a json from a rest request.
 *
 * @param <T> the type parameter
 */
public interface Deserializer<T> {
    /**
     * Returns an entity T from the byte array, usually a json from a Rest request
     *
     * @param data A raw data
     * @return Returns an entity T
     */
    T deserialize(byte[] data);
    
}
