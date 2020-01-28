package no.nav.domain.pensjon.kjerne.opptjening;

import java.io.Serializable;
import java.util.Date;

import no.stelvio.domain.person.Pid;
import no.stelvio.domain.time.ChangeStamp;

public class Beholdning implements Serializable {

    private static final long serialVersionUID = 198836574804806332L;
    private Long beholdningId;
    private Pid fnr;
    private String status;
    private String beholdningType;
    private Double belop;
    private Long vedtakId;
    private Date fomDato;
    private Date tomDato;
    private Double beholdningGrunnlag;
    private Double beholdningGrunnlagAvkortet;
    private Double beholdningInnskudd;
    private Double beholdningInnskuddUtenOmsorg;
    private String oppdateringArsak;
    private Lonnsvekstregulering lonnsvekstregulering;
    private InntektOpptjeningBelop inntektOpptjeningBelop;
    private OmsorgOpptjeningBelop omsorgOpptjeningBelop;
    private DagpengerOpptjeningBelop dagpengerOpptjeningBelop;
    private ForstegangstjenesteOpptjeningBelop forstegangstjenesteOpptjeningBelop;
    private UforeOpptjeningBelop uforeOpptjeningBelop;
    private ChangeStamp changeStamp;

    public Double getBeholdningGrunnlag() {
        return beholdningGrunnlag;
    }

    public void setBeholdningGrunnlag(Double beholdningGrunnlag) {
        this.beholdningGrunnlag = beholdningGrunnlag;
    }

    public Double getBeholdningGrunnlagAvkortet() {
        return beholdningGrunnlagAvkortet;
    }

    public void setBeholdningGrunnlagAvkortet(Double beholdningGrunnlagAvkortet) {
        this.beholdningGrunnlagAvkortet = beholdningGrunnlagAvkortet;
    }

    public Long getBeholdningId() {
        return beholdningId;
    }

    public void setBeholdningId(Long beholdningId) {
        this.beholdningId = beholdningId;
    }

    public Double getBeholdningInnskudd() {
        return beholdningInnskudd;
    }

    public void setBeholdningInnskudd(Double beholdningInnskudd) {
        this.beholdningInnskudd = beholdningInnskudd;
    }

    public Double getBeholdningInnskuddUtenOmsorg() {
        return beholdningInnskuddUtenOmsorg;
    }

    public void setBeholdningInnskuddUtenOmsorg(Double beholdningInnskuddUtenOmsorg) {
        this.beholdningInnskuddUtenOmsorg = beholdningInnskuddUtenOmsorg;
    }

    public Double getBelop() {
        return belop;
    }

    public void setBelop(Double belop) {
        this.belop = belop;
    }

    public ChangeStamp getChangeStamp() {
        return changeStamp;
    }

    public void setChangeStamp(ChangeStamp changeStamp) {
        this.changeStamp = changeStamp;
    }

    public DagpengerOpptjeningBelop getDagpengerOpptjeningBelop() {
        return dagpengerOpptjeningBelop;
    }

    public void setDagpengerOpptjeningBelop(DagpengerOpptjeningBelop dagpengerOpptjeningBelop) {
        this.dagpengerOpptjeningBelop = dagpengerOpptjeningBelop;
    }

    public Pid getFnr() {
        return fnr;
    }

    public void setFnr(Pid fnr) {
        this.fnr = fnr;
    }

    public Date getFomDato() {
        return fomDato;
    }

    public void setFomDato(Date fomDato) {
        this.fomDato = fomDato;
    }

    public ForstegangstjenesteOpptjeningBelop getForstegangstjenesteOpptjeningBelop() {
        return forstegangstjenesteOpptjeningBelop;
    }

    public void setForstegangstjenesteOpptjeningBelop(ForstegangstjenesteOpptjeningBelop forstegangstjenesteOpptjeningBelop) {
        this.forstegangstjenesteOpptjeningBelop = forstegangstjenesteOpptjeningBelop;
    }

    public InntektOpptjeningBelop getInntektOpptjeningBelop() {
        return inntektOpptjeningBelop;
    }

    public void setInntektOpptjeningBelop(InntektOpptjeningBelop inntektOpptjeningBelop) {
        this.inntektOpptjeningBelop = inntektOpptjeningBelop;
    }

    public OmsorgOpptjeningBelop getOmsorgOpptjeningBelop() {
        return omsorgOpptjeningBelop;
    }

    public void setOmsorgOpptjeningBelop(OmsorgOpptjeningBelop omsorgOpptjeningBelop) {
        this.omsorgOpptjeningBelop = omsorgOpptjeningBelop;
    }

    public String getOppdateringArsak() {
        return oppdateringArsak;
    }

    public void setOppdateringArsak(String oppdateringArsak) {
        this.oppdateringArsak = oppdateringArsak;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTomDato() {
        return tomDato;
    }

    public void setTomDato(Date tomDato) {
        this.tomDato = tomDato;
    }

    public UforeOpptjeningBelop getUforeOpptjeningBelop() {
        return uforeOpptjeningBelop;
    }

    public void setUforeOpptjeningBelop(UforeOpptjeningBelop uforeOpptjeningBelop) {
        this.uforeOpptjeningBelop = uforeOpptjeningBelop;
    }

    public Long getVedtakId() {
        return vedtakId;
    }

    public void setVedtakId(Long vedtakId) {
        this.vedtakId = vedtakId;
    }

    public String getBeholdningType() {
        return beholdningType;
    }

    public void setBeholdningType(String beholdningType) {
        this.beholdningType = beholdningType;
    }

    public Lonnsvekstregulering getLonnsvekstregulering() {
        return lonnsvekstregulering;
    }

    public void setLonnsvekstregulering(Lonnsvekstregulering lonnsvekstregulering) {
        this.lonnsvekstregulering = lonnsvekstregulering;
    }
}
