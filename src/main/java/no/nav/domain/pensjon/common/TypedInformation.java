package no.nav.domain.pensjon.common;

/**
 * Interface that should be supported by domain objects that holds type information, i.e. returns a type code enum or a cti
 * representing the domain object's type. The {@link #getTypeCode()} method is a generic way of getting the object's type.
 * <p>
 * Many domain objects holds several types, but this interface is supposed to support the domain object's main type or discrimator - for
 * no.nav.domain.pensjon.kjerne.krav.Kravlinje it should return no.nav.domain.pensjon.kjerne.kodetabeller.KravlinjeTypeCode and so on.
 * <p>
 * Note that no domain objects currently has a {@link #getTypeCode()} method; you must implement this method in each domain object and make sure it returns the correct type code.
 * In the case no.nav.domain.pensjon.kjerne.krav.Kravlinje the method should return <code>getKravlinjeType().getCode()</code>.
 * <p>
 * The convenience class TypedInformationListeUtils can be used to perform common operations on type-aware domain objects.
 */
public interface TypedInformation<E extends Enum<E>> {

    /**
     * Returns the domain object's type as a type code enum.
     */
    E getTypeCode();
}
