package no.nav.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

//import no.nav.domain.pensjon.annotations.IgnoreOnCopy;

/**
 * This class should be extended by all classes that use a version column
 */
//@MappedSuperclass
public abstract class AbstractVersionedPersistentDomainObject extends AbstractPersistentDomainObject {

    private static final long serialVersionUID = 1L;

    /**
     * Version column used for optimistic locking - Hibernate
     */
//    @Version
//    @IgnoreOnCopy(reason = "Don't copy version!")
    private Integer versjon;

    public Integer getVersjon() {
        return versjon;
    }

    public void setVersjon(Integer versjon) {
        this.versjon = versjon;
    }
}
