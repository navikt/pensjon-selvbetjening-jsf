package no.nav.presentation.pensjon.common.session;

/**
 * TransferObject is an interface used for transferring objects on between flows using the session briefly.
 */
public interface TransferObject {

    void put(String key, Object value);

    /**
     * Gets the object from session with key key, and should remove the object from the session. This is the default
     * get-implementation and should be used for mandatory transfer objects.
     */
    Object get(String key) throws RuntimeException;

    /**
     * Gets an optional object from session with key key, and should remove the object from the session. This will not throw a
     * RunTimeException if the key is not found. Should be used for optional transfer objects.
     */
    Object getOptional(String key);

    boolean containsKey(String key);
}
