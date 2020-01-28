package no.nav.domain.pensjon.kjerne.opptjening;

import static no.nav.domain.pensjon.common.util.DateCopyUtil.copyDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.stelvio.domain.person.Pid;
import no.stelvio.domain.time.ChangeStamp;

//import no.nav.domain.pensjon.annotations.IgnoreOnCopy;
//import no.nav.domain.pensjon.annotations.IgnoreOnCopyReason;
//import no.nav.domain.pensjon.common.Copyable;

/**
 * Non-persistent class used to represent Forstegangstjeneste. This class is used to map the ForstegangstjenesteDto object in
 * the request to TPOPP007 hentOpptjeningsgrunnlag service.
 */
public class Forstegangstjeneste implements Serializable /*, Copyable*/ {

    private static final long serialVersionUID = -3682514745110077173L;

    //    @IgnoreOnCopy(reason = IgnoreOnCopyReason.PRIMARY_KEY)
    private Long forstegangstjenesteId;

    private Pid fnr;
    private List<ForstegangstjenestePeriode> forstegangstjenestePeriodeListe;
    private ChangeStamp changeStamp;
    private String kilde;
    private String rapportType;
    private Date tjenestestartDato;
    private Date dimitteringDato;

    public Forstegangstjeneste() {
    }

    //    @Override
    public Forstegangstjeneste copy() {
        Forstegangstjeneste forstegangstjeneste = new Forstegangstjeneste();
        forstegangstjeneste.setFnr(fnr != null ? new Pid(fnr.getPid()) : null);
        forstegangstjeneste.setKilde(kilde);
        forstegangstjeneste.setRapportType(rapportType);
        forstegangstjeneste.setTjenestestartDato(copyDate(tjenestestartDato));
        forstegangstjeneste.setDimitteringDato(copyDate(dimitteringDato));

        if (forstegangstjenestePeriodeListe != null) {
            List<ForstegangstjenestePeriode> periodes = new ArrayList<>();

            for (ForstegangstjenestePeriode periode : forstegangstjenestePeriodeListe) {
                periodes.add(periode.copy());
            }

            forstegangstjeneste.setForstegangstjenestePeriodeListe(periodes);
        }

        if (changeStamp != null) {
            ChangeStamp cs = changeStamp;
            forstegangstjeneste.setChangeStamp(new ChangeStamp(cs.getCreatedBy(), cs.getCreatedDate(), cs.getUpdatedBy(), cs.getUpdatedDate()));
        }

        return forstegangstjeneste;
    }

    public ChangeStamp getChangeStamp() {
        return changeStamp;
    }

    public void setChangeStamp(ChangeStamp changeStamp) {
        this.changeStamp = changeStamp;
    }

    public Pid getFnr() {
        return fnr;
    }

    public void setFnr(Pid fnr) {
        this.fnr = fnr;
    }

    public Long getForstegangstjenesteId() {
        return forstegangstjenesteId;
    }

    public void setForstegangstjenesteId(Long forstegangstjenesteId) {
        this.forstegangstjenesteId = forstegangstjenesteId;
    }

    public List<ForstegangstjenestePeriode> getForstegangstjenestePeriodeListe() {
        return forstegangstjenestePeriodeListe;
    }

    public void setForstegangstjenestePeriodeListe(List<ForstegangstjenestePeriode> forstegangstjenestePeriodeListe) {
        this.forstegangstjenestePeriodeListe = forstegangstjenestePeriodeListe;
    }

    public String getKilde() {
        return kilde;
    }

    public void setKilde(String kilde) {
        this.kilde = kilde;
    }

    public String getRapportType() {
        return rapportType;
    }

    public void setRapportType(String rapportType) {
        this.rapportType = rapportType;
    }

    public Date getTjenestestartDato() {
        return tjenestestartDato;
    }

    public void setTjenestestartDato(Date tjenestestartDato) {
        this.tjenestestartDato = tjenestestartDato;
    }

    public Date getDimitteringDato() {
        return dimitteringDato;
    }

    public void setDimitteringDato(Date dimitteringDato) {
        this.dimitteringDato = dimitteringDato;
    }
}
