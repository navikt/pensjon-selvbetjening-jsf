package no.nav.presentation.pensjon.pselv.skjema.alderspensjon;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SkjemaDataForPreComplementing implements Serializable {

    private static final long serialVersionUID = -4983236754803398419L;
    private Date pensjoneringstidspunkt;
    private Boolean forsorgingstilleggForEPS;
    private Boolean forsorgingstilleggForBarn;
    private Boolean flyktning;
    private Boolean isSAMB;
    private Boolean isGLAD;
    private Boolean hasEpsAnnenInntekt;
    private Boolean tidligereGiftEllerBarnMedSamboer;
    private Integer samletEPSinntekt;
    private Integer samletTjenestePensjon;
    private Boolean showBarnList;
    private Map<String, SkjemaAlderpensjonBarnData> childrenMap;
    private Boolean hasChildrenInntekt;
    private Boolean hasAarIUtlandet;
    private Boolean hasForventetArbeidsinntekt;
    private Integer forventetArbeidsinntekt;
    private Boolean mottarEllerSoktOmYtelserFraAndreNorskePenOrdninger;
    private List<SkjemaAlderspensjonPensjonsordningerData> pensjonsordningListTp;
    private List<SkjemaAlderspensjonPensjonsordningerData> pensjonsordningListAndrePensjonsordninger;
    private Integer pensjoneringsgrad;
    private Boolean afpRett;

    public Boolean getFlyktning() {
        return flyktning;
    }

    public void setFlyktning(Boolean flyktning) {
        this.flyktning = flyktning;
    }

    public Boolean getForsorgingstilleggForBarn() {
        return forsorgingstilleggForBarn;
    }

    public void setForsorgingstilleggForBarn(Boolean forsorgingstilleggForBarn) {
        this.forsorgingstilleggForBarn = forsorgingstilleggForBarn;
    }

    public Boolean getForsorgingstilleggForEPS() {
        return forsorgingstilleggForEPS;
    }

    public void setForsorgingstilleggForEPS(Boolean forsorgingstilleggForEPS) {
        this.forsorgingstilleggForEPS = forsorgingstilleggForEPS;
    }

    public Integer getForventetArbeidsinntekt() {
        return forventetArbeidsinntekt;
    }

    public void setForventetArbeidsinntekt(Integer forventetArbeidsinntekt) {
        this.forventetArbeidsinntekt = forventetArbeidsinntekt;
    }

    public Boolean getHasAarIUtlandet() {
        return hasAarIUtlandet;
    }

    public void setHasAarIUtlandet(Boolean hasAarIUtlandet) {
        this.hasAarIUtlandet = hasAarIUtlandet;
    }

    public Boolean getHasChildrenInntekt() {
        return hasChildrenInntekt;
    }

    public void setHasChildrenInntekt(Boolean hasChildrenInntekt) {
        this.hasChildrenInntekt = hasChildrenInntekt;
    }

    public Boolean getHasEpsAnnenInntekt() {
        return hasEpsAnnenInntekt;
    }

    public void setHasEpsAnnenInntekt(Boolean hasEpsAnnenInntekt) {
        this.hasEpsAnnenInntekt = hasEpsAnnenInntekt;
    }

    public Boolean getHasForventetArbeidsinntekt() {
        return hasForventetArbeidsinntekt;
    }

    public void setHasForventetArbeidsinntekt(Boolean hasForventetArbeidsinntekt) {
        this.hasForventetArbeidsinntekt = hasForventetArbeidsinntekt;
    }

    public boolean isGLAD() {
        return isGLAD;
    }

    public void setIsGLAD(Boolean isGLAD) {
        this.isGLAD = isGLAD;
    }

    public boolean isSAMB() {
        return isSAMB;
    }

    public void setIsSAMB(Boolean isSAMB) {
        this.isSAMB = isSAMB;
    }

    public Boolean getMottarEllerSoktOmYtelserFraAndreNorskePenOrdninger() {
        return mottarEllerSoktOmYtelserFraAndreNorskePenOrdninger;
    }

    public void setMottarEllerSoktOmYtelserFraAndreNorskePenOrdninger(Boolean mottarEllerSoktOmYtelserFraAndreNorskePenOrdninger) {
        this.mottarEllerSoktOmYtelserFraAndreNorskePenOrdninger = mottarEllerSoktOmYtelserFraAndreNorskePenOrdninger;
    }

    public List<SkjemaAlderspensjonPensjonsordningerData> getPensjonsordningListAndrePensjonsordninger() {
        return pensjonsordningListAndrePensjonsordninger;
    }

    public void setPensjonsordningListAndrePensjonsordninger(
            List<SkjemaAlderspensjonPensjonsordningerData> pensjonsordningListAndrePensjonsordninger) {
        this.pensjonsordningListAndrePensjonsordninger = pensjonsordningListAndrePensjonsordninger;
    }

    public List<SkjemaAlderspensjonPensjonsordningerData> getPensjonsordningListTp() {
        return pensjonsordningListTp;
    }

    public void setPensjonsordningListTp(List<SkjemaAlderspensjonPensjonsordningerData> pensjonsordningListTp) {
        this.pensjonsordningListTp = pensjonsordningListTp;
    }

    public Integer getSamletEPSinntekt() {
        return samletEPSinntekt;
    }

    public void setSamletEPSinntekt(Integer samletEPSinntekt) {
        this.samletEPSinntekt = samletEPSinntekt;
    }

    public Integer getSamletTjenestePensjon() {
        return samletTjenestePensjon;
    }

    public void setSamletTjenestePensjon(Integer samletTjenestePensjon) {
        this.samletTjenestePensjon = samletTjenestePensjon;
    }

    public Boolean getShowBarnList() {
        return showBarnList;
    }

    public void setShowBarnList(Boolean showBarnList) {
        this.showBarnList = showBarnList;
    }

    public Boolean getTidligereGiftEllerBarnMedSamboer() {
        return tidligereGiftEllerBarnMedSamboer;
    }

    public void setTidligereGiftEllerBarnMedSamboer(Boolean tidligereGiftEllerBarnMedSamboer) {
        this.tidligereGiftEllerBarnMedSamboer = tidligereGiftEllerBarnMedSamboer;
    }

    public Date getPensjoneringstidspunkt() {
        return pensjoneringstidspunkt;
    }

    public void setPensjoneringstidspunkt(Date pensjoneringstidspunkt) {
        this.pensjoneringstidspunkt = pensjoneringstidspunkt;
    }

    public Map<String, SkjemaAlderpensjonBarnData> getChildrenMap() {
        return childrenMap;
    }

    public void setChildrenMap(Map<String, SkjemaAlderpensjonBarnData> childrenMap) {
        this.childrenMap = childrenMap;
    }

    public Integer getPensjoneringsgrad() {
        return pensjoneringsgrad;
    }

    public void setPensjoneringsgrad(Integer pensjoneringsgrad) {
        this.pensjoneringsgrad = pensjoneringsgrad;
    }

    public Boolean getAfpRett() {
        return afpRett;
    }

    public void setAfpRett(Boolean afpRett) {
        this.afpRett = afpRett;
    }
}
