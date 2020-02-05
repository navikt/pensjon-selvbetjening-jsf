package no.nav.domain.pensjon.kjerne.sak;

import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.kjerne.PenPerson;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakStatusCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCti;
import no.nav.domain.pensjon.kjerne.krav.KravHode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO stub
public class Sak extends AbstractVersionedPersistentDomainObject {

    private SakStatusCti sakStatus;
    private SakTypeCti sakType;
    private List<KravHode> kravHodeListe;

    public Sak() {}

    public Sak(SakStatusCti sakStatus, SakTypeCti sakType, List<KravHode> kravHodeListe) {
        this.sakStatus = sakStatus;
        this.sakType = sakType;
        this.kravHodeListe = kravHodeListe;
    }

    public SakStatusCti getSakStatus() {
        return sakStatus;
    }

    public SakTypeCti getSakType() {
        return sakType;
    }

    public List<KravHode> getKravHodeListe() {
        return kravHodeListe;
    }

    public Long getSakId() {
        return 123L;
    }

    public List<Uttaksgrad> getUttaksgradhistorikk() {
        return new ArrayList<>();
    }

    public PenPerson getPenPerson() {
        return new PenPerson();
    }

    public Date bestemForsteVirkningsdato() {
        return new Date();
    }
}
