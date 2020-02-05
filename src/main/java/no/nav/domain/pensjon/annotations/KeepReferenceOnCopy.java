package no.nav.domain.pensjon.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import no.nav.domain.pensjon.common.Copyable;

/**
 * Specifies that a field should be copied by reference instead of creating a new instance in the {@link Copyable#copy()}
 * method for classes implementing {@link Copyable}.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface KeepReferenceOnCopy {

    /**
     * A reason why the copy should use the reference instead of using the copy.
     *
     * @return the reason.
     */
    public String reason();
}
