package no.nav.domain.pensjon.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import no.nav.domain.pensjon.common.Copyable;

/**
 * Specifies that a field should be ignored in the copy method if the domain class implement {@link Copyable}.
 *
 * @see Copyable
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IgnoreOnCopy {

    /**
     * The reason why it should be ignored.
     *
     * @return the reason.
     */
    public String reason();
}
