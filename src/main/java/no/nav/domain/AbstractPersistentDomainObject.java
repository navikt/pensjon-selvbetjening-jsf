package no.nav.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import no.stelvio.domain.time.ChangeStamp;

//import no.nav.domain.pensjon.annotations.IgnoreOnCopy;

/**
 * Abstract base class for all persistent domain objects.
 */
//@MappedSuperclass
public abstract class AbstractPersistentDomainObject implements Serializable {
    private static final long serialVersionUID = 1L;

    //    @Embedded
//    @AttributeOverrides({@AttributeOverride(name = "createdBy", column = @Column(name = "opprettet_av")),
//            @AttributeOverride(name = "createdDate", column = @Column(name = "dato_opprettet")),
//            @AttributeOverride(name = "updatedBy", column = @Column(name = "endret_av")),
//            @AttributeOverride(name = "updatedDate", column = @Column(name = "dato_endret"))})
//    @IgnoreOnCopy(reason = "ChangeStamp should never be copied")
    private ChangeStamp changeStamp;

    public boolean isCreatedAfter(AbstractPersistentDomainObject obj) {
        return obj != null && obj.getChangeStamp() != null &&
                getChangeStamp().getCreatedDate().getTime() > obj.getChangeStamp().getCreatedDate().getTime();
    }

    public ChangeStamp getChangeStamp() {
        return changeStamp;
    }

    public void setChangeStamp(ChangeStamp changeStamp) {
        this.changeStamp = changeStamp;
    }
}
