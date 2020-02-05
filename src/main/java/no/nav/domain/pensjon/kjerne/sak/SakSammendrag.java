package no.nav.domain.pensjon.kjerne.sak;

import java.io.Serializable;
import java.util.Date;

import no.nav.domain.pensjon.common.PeriodisertInformasjon;
import no.nav.domain.pensjon.common.util.DateCopyUtil;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakStatusCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCti;

/**
 * TPEN212 SakServiceBi - hentSakSammendragListe
 */
public class SakSammendrag implements Serializable, PeriodisertInformasjon {

    private static final long serialVersionUID = -1260251528930586380L;
    private Long sakId;
    private SakTypeCti sakType;
    private SakStatusCti sakStatus;
    private Date fomDato;
    private Date tomDato;
    private String enhetId;
    private String unitName;

    public String getEnhetId() {
        return enhetId;
    }

    public void setEnhetId(String enhetId) {
        this.enhetId = enhetId;
    }

    public Date getFomDato() {
        return DateCopyUtil.copyDate(fomDato);
    }

    public void setFomDato(Date fomDato) {
        this.fomDato = DateCopyUtil.copyDate(fomDato);
    }

    public Long getSakId() {
        return sakId;
    }

    public void setSakId(Long sakId) {
        this.sakId = sakId;
    }

    public SakStatusCti getSakStatus() {
        return sakStatus;
    }

    public void setSakStatus(SakStatusCti sakStatus) {
        this.sakStatus = sakStatus;
    }

    public SakTypeCti getSakType() {
        return sakType;
    }

    public void setSakType(SakTypeCti sakType) {
        this.sakType = sakType;
    }

    public Date getTomDato() {
        return DateCopyUtil.copyDate(tomDato);
    }

    public void setTomDato(Date tomDato) {
        this.tomDato = DateCopyUtil.copyDate(tomDato);
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(final String unitName) {
        this.unitName = unitName;
    }
}
