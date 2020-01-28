package no.nav.presentation.pensjon.pselv.skjema.alderspensjon.innledning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.kjerne.kodetabeller.ElektroniskSkjemaTypeCode;
import no.nav.domain.pensjon.kjerne.skjema.Skjema;
import no.nav.domain.pensjon.kjerne.skjema.SkjemaInnledning;

public class SkjemaInnledningDomainPopulator {

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final Integer FULL_GRAD = 100;

    public SkjemaInnledning populateSkjemaInnledning(SkjemaInnledningForm form, Skjema skjema) {
        SkjemaInnledning skjemaInnledning = skjema.getSkjemaInnledning();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        if (form.getValgtPensjoneringstidspunkt() != null) {
            try {
                Date dato = sdf.parse(form.getValgtPensjoneringstidspunkt());
                skjemaInnledning.setIverksettelsesdato(dato);
            } catch (ParseException e) {
                throw new ImplementationUnrecoverableException(e);
            }
        }

        // check if it is old or new regelverk when uttaksgrad is set
        if (!form.isGammeltRegelverk()) {
            if (form.getValgtPensjoneringsGrad() != null) {
                String valgtGrad = form.getValgtPensjoneringsGrad();
                int grad = Integer.parseInt(valgtGrad.substring(0, valgtGrad.length() - 2));
                skjemaInnledning.setUttaksgrad(grad);
            } else {
                skjemaInnledning.setUttaksgrad(FULL_GRAD);
            }
        } else {
            skjemaInnledning.setUttaksgrad(null);
        }

        if (form.getForsorgningstilleggBarn() != null) {
            skjemaInnledning.setForsorgingstilleggBarn(form.getForsorgningstilleggBarn());
            if (form.isDelvisPensjon()) {
                skjemaInnledning.setForsorgingstilleggBarn(Boolean.FALSE);
            }
        } else {
            skjemaInnledning.setForsorgingstilleggBarn(Boolean.FALSE);
        }

        if (form.getForsorgningstilleggEPS() != null) {
            skjemaInnledning.setForsorgingstilleggEPS(form.getForsorgningstilleggEPS());
            if (form.isDelvisPensjon()) {
                skjemaInnledning.setForsorgingstilleggEPS(Boolean.FALSE);
            }
        } else {
            skjemaInnledning.setForsorgingstilleggEPS(Boolean.FALSE);
        }

        // check if it is old or new regelverk when uttaksgrad is set
        if (!form.isGammeltRegelverk()) {
            if (form.getSvarAFP() != null) {
                skjemaInnledning.setSoktAfpPrivat(form.getSvarAFP());
            } else {
                skjemaInnledning.setSoktAfpPrivat(Boolean.FALSE);
            }
        } else {
            skjemaInnledning.setSoktAfpPrivat(null);
        }

        if (skjema.getSkjemaPselvType().isCodeEqualTo(ElektroniskSkjemaTypeCode.AP) && skjema.getSkjemaId() != null) {
            // must be set since these variables are not nullable
            skjemaInnledning.setForsorgingstilleggEPS(Boolean.FALSE);
            skjemaInnledning.setForsorgingstilleggBarn(Boolean.FALSE);
            // if user opens a saved applcation Alderspensjon med ET, make sure that information about barneopplysninger,
            // fremtidig inntekt and annen pensjon/andre ytelser is not
            // avaible anymore
            skjema.setSkjemaBarneopplysninger(null);
            skjema.setSkjemaFremtidigInntekt(null);
            skjema.setSkjemaPensjonAndreYtelser(null);
        }

        return skjemaInnledning;
    }
}
