package no.nav.domain.pensjon.kjerne.opptjening;

import static no.nav.domain.pensjon.common.util.DateCopyUtil.copyDate;

import java.io.Serializable;
import java.util.Date;

import no.stelvio.domain.time.ChangeStamp;

//import no.nav.domain.pensjon.annotations.IgnoreOnCopy;
//import no.nav.domain.pensjon.annotations.IgnoreOnCopyReason;
//import no.nav.domain.pensjon.common.Copyable;

/**
 * Non-persistent class used to represent ForstegangstjenestePeriode. This class is used to map the
 * ForstegangstjenestePeriodeDto object in the request to TPOPP007 hentOpptjeningsgrunnlag service.
 */
public class ForstegangstjenestePeriode implements Serializable /*, Copyable*/ {
    private static final long serialVersionUID = 547620561116301736L;

//    @IgnoreOnCopy(reason = IgnoreOnCopyReason.PRIMARY_KEY)
    private Long forstegangstjenestePeriodeId;
    private String periodeType;
    private String tjenesteType;
    private Date fomDato;
    private Date tomDato;
    private ChangeStamp changeStamp;

    public ForstegangstjenestePeriode() {
    }

//    @Override
    public ForstegangstjenestePeriode copy() {
        ForstegangstjenestePeriode periode = new ForstegangstjenestePeriode();
        periode.setPeriodeType(periodeType);
        periode.setTjenesteType(tjenesteType);
        periode.setFomDato(copyDate(fomDato));
        periode.setTomDato(copyDate(tomDato));

        if (changeStamp != null) {
            ChangeStamp cs = changeStamp;
            periode.setChangeStamp(new ChangeStamp(cs.getCreatedBy(), cs.getCreatedDate(), cs.getUpdatedBy(), cs.getUpdatedDate()));
        }

        return periode;
    }

    public Long getForstegangstjenestePeriodeId() {
        return forstegangstjenestePeriodeId;
    }

    public void setForstegangstjenestePeriodeId(Long forstegangstjenestePeriodeId) {
        this.forstegangstjenestePeriodeId = forstegangstjenestePeriodeId;
    }

    public String getPeriodeType() {
        return periodeType;
    }

    public void setPeriodeType(String periodeType) {
        this.periodeType = periodeType;
    }

    public String getTjenesteType() {
        return tjenesteType;
    }

    public void setTjenesteType(String tjenesteType) {
        this.tjenesteType = tjenesteType;
    }

    public Date getFomDato() {
        return fomDato;
    }

    public void setFomDato(Date fomDato) {
        this.fomDato = fomDato;
    }

    public Date getTomDato() {
        return tomDato;
    }

    public void setTomDato(Date tomDato) {
        this.tomDato = tomDato;
    }

    public ChangeStamp getChangeStamp() {
        return changeStamp;
    }

    public void setChangeStamp(ChangeStamp changeStamp) {
        this.changeStamp = changeStamp;
    }
}
