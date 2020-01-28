package no.nav.presentation.pensjon.pselv.skjema.alderspensjon;

import java.io.Serializable;

public class SkjemaAlderspensjonCommonInputData implements Serializable {

    private static final long serialVersionUID = 1248369505626619618L;
    private Long skjemaId;
    private boolean barnetilleggIsChosen;
    private boolean ektefelletilleggIsChosen;
    private boolean alderspensjonSoknad;
    private boolean afpPrivat;
    private SkjemaDataForPreComplementing skjemaData;

    public SkjemaAlderspensjonCommonInputData(Long skjemaId,
                                              boolean barnetilleggIsChosen,
                                              boolean alderspensjonSoknad,
                                              SkjemaDataForPreComplementing skjemaData) {
        this.skjemaId = skjemaId;
        this.barnetilleggIsChosen = barnetilleggIsChosen;
        this.alderspensjonSoknad = alderspensjonSoknad;
        this.skjemaData = skjemaData;
    }

    public Long getSkjemaId() {
        return skjemaId;
    }

    public boolean getBarnetilleggIsChosen() {
        return barnetilleggIsChosen;
    }

    public boolean getAlderspensjonSoknad() {
        return alderspensjonSoknad;
    }

    public SkjemaDataForPreComplementing getSkjemaData() {
        return skjemaData;
    }

    public void setSkjemaData(SkjemaDataForPreComplementing skjemaData) {
        this.skjemaData = skjemaData;
    }

    public boolean isEktefelletilleggIsChosen() {
        return ektefelletilleggIsChosen;
    }

    public void setEktefelletilleggIsChosen(boolean ektefelletilleggIsChosen) {
        this.ektefelletilleggIsChosen = ektefelletilleggIsChosen;
    }

    public boolean isAfpPrivat() {
        return afpPrivat;
    }

    public void setAfpPrivat(boolean afpPrivat) {
        this.afpPrivat = afpPrivat;
    }
}
