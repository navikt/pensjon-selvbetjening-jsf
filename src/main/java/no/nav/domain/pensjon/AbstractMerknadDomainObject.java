package no.nav.domain.pensjon;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.kjerne.Merknad;

/**
 * Abstract class used by domain objects which needs to have a {@link List} with {@link Merknad}s associated with it.
 */
//@MappedSuperclass
public abstract class AbstractMerknadDomainObject extends AbstractVersionedPersistentDomainObject {
    private static final long serialVersionUID = 1674742927435326639L;

    @Column(name = "MERKNAD_LISTE", nullable = true)
    @Type(type = "no.nav.domain.pensjon.MerknaderUserType")
    private Merknader merknader = new Merknader();

    public void addMerknad(Merknad merknad) {
        merknader.add(merknad);
    }

    public void removeMerknader() {
        merknader.clear();
    }

    public List<Merknad> getMerknadListe() {
        return merknader.getMerknadListe();
    }

    public void setMerknadListe(List<Merknad> merknadListe) {
        merknader.clear();
        merknader.addAll(merknadListe);
    }
}
