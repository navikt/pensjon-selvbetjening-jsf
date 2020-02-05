package no.nav.domain.pensjon.kjerne.vedtak;

import no.nav.domain.pensjon.AbstractMerknadDomainObject;
import no.nav.domain.pensjon.common.PeriodisertInformasjon;
import no.nav.domain.pensjon.kjerne.beregning.Beregning;
import no.nav.domain.pensjon.kjerne.beregning2011.BeregningHoveddel;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.VedtakstatusTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.VedtakstatusTypeCti;
import no.nav.domain.pensjon.kjerne.krav.KravHode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vedtak extends AbstractMerknadDomainObject implements Comparable<Vedtak>, PeriodisertInformasjon, GjelderPeriode {

    private static final long serialVersionUID = 5108323261061547577L;

    private SakTypeCti saksType;

    public SakTypeCti getSakstype() {
        return saksType;
    }

    public Date getVirkFom() {
        return new Date();
    }

    public KravHode getKravhode() {
        return new KravHode();
    }

    public Beregningsperiode[] getBeregningsperiodeListe() {
        return new Beregningsperiode[0];
    }

    public List<Beregning> getBeregningListe() {
        return new ArrayList<>();
    }

    public boolean erAvTypenUforetrygd() {
        return false;
    }

    public BeregningHoveddel[] getBeregningsresultatListe() {
        return new BeregningHoveddel[0];
    }

    public Long getSakId() {
        return 6543L;
    }

    public VedtakstatusTypeCti getVedtakstatus() {
        return null;
    }

    @Override
    public int compareTo(Vedtak o) {
        return 0;
    }

    @Override
    public Date getFomDato() {
        return new Date();
    }

    @Override
    public Date getTomDato() {
        return new Date();
    }

    @Override
    public void setFomDato(Date fom) {
    }

    @Override
    public void setTomDato(Date tom) {
    }
}
