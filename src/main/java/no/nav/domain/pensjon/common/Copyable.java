package no.nav.domain.pensjon.common;

/**
 * A class implements the <code>Copyable</code> interface to
 * indicate that the class has a copy method that does deep copying.
 * <br/>
 * Implementing this interface also means you are obligated to fulfill
 * a contract to copy all the fields the object implementing this interface.
 * <br/>
 * If a field shouldn't be copied, it has to be annotated with @{@link no.nav.domain.pensjon.annotations.IgnoreOnCopy}.
 * A typical use of this is field for primary keys and inverse references.
 * <br/>
 * If a field should be copied by reference and not by creating a new copy, @{@link no.nav.domain.pensjon.annotations.KeepReferenceOnCopy} should be used.
 */
public interface Copyable<T> {

    /**
     * Create a new object and do a deep copy including copy of objects in lists.
     */
    T copy();
}
