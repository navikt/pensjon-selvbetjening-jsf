package no.nav.domain.pensjon.common.tjenestepensjon;

import java.io.Serializable;
import java.util.Calendar;

import no.nav.domain.pensjon.common.Endringsinfo;

/**
 * The mirror domain object of the ASBOPenTjenestepensjonsYtelse ESB object.
 */
public class TjenestepensjonsYtelse implements Serializable {

    private static final long serialVersionUID = -8653339841174934606L;
    private String ytelseId;
    private Calendar innmeldtFom;
    private String ytelseKode;
    private String ytelseBeskrivelse;
    private Calendar iverksattFom;
    private Calendar iverksattTom;
    private Endringsinfo endringsInfo;

    public Endringsinfo getEndringsInfo() {
        return endringsInfo;
    }

    public void setEndringsInfo(Endringsinfo endringsInfo) {
        this.endringsInfo = endringsInfo;
    }

    public Calendar getInnmeldtFom() {
        return innmeldtFom;
    }

    public void setInnmeldtFom(Calendar innmeldtFom) {
        this.innmeldtFom = innmeldtFom;
    }

    public Calendar getIverksattFom() {
        return iverksattFom;
    }

    public void setIverksattFom(Calendar iverksattFom) {
        this.iverksattFom = iverksattFom;
    }

    public Calendar getIverksattTom() {
        return iverksattTom;
    }

    public void setIverksattTom(Calendar iverksattTom) {
        this.iverksattTom = iverksattTom;
    }

    public String getYtelseBeskrivelse() {
        return ytelseBeskrivelse;
    }

    public void setYtelseBeskrivelse(String ytelseBeskrivelse) {
        this.ytelseBeskrivelse = ytelseBeskrivelse;
    }

    public String getYtelseId() {
        return ytelseId;
    }

    public void setYtelseId(String ytelseId) {
        this.ytelseId = ytelseId;
    }

    public String getYtelseKode() {
        return ytelseKode;
    }

    public void setYtelseKode(String ytelseKode) {
        this.ytelseKode = ytelseKode;
    }
}
