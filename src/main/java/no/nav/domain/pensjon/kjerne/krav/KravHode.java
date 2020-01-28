package no.nav.domain.pensjon.kjerne.krav;

import no.nav.domain.pensjon.kjerne.grunnlag.Persongrunnlag;
import no.nav.domain.pensjon.kjerne.kodetabeller.GrunnlagsrolleCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.RegelverkTypeCode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.sak.Uttaksgrad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KravHode implements Serializable {

    private List<Uttaksgrad> uttaksgradListe = new ArrayList<>();
    private Sak sak;

    public List<Uttaksgrad> getUttaksgradListe() {
        return uttaksgradListe;
    }

    public boolean kravKanGiLopendeYtelse() {
        return false;
    }

    public boolean isAapentKrav() {
        return false;
    }

    public RegelverkTypeCode getRegelverkType() {
        return RegelverkTypeCode.G_REG;
    }

    public boolean isUforepensjon() {
        return false;
    }

    public boolean isUforetrygd() {
        return false;
    }

    public Persongrunnlag findPersonGrunnlagIGrunnlagsRolle(GrunnlagsrolleCode soker) {
        return new Persongrunnlag();
    }

    public boolean hasKravlinjeOfTypeBT() {
        return false;
    }

    public Sak getSak() {
        return sak;
    }

    public void setSak(Sak sak) {
        this.sak = sak;
    }

}
