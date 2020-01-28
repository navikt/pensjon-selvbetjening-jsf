package no.nav.presentation.pensjon.pselv.common.delegate;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import no.nav.consumer.pensjon.pselv.samhandler.SamhandlerServiceBi;
import no.nav.consumer.pensjon.pselv.samhandler.to.FinnSamhandlerRequest;
import no.nav.consumer.pensjon.pselv.samhandler.to.FinnSamhandlerResponse;
import no.nav.domain.pensjon.common.samhandler.Avdeling;
import no.nav.domain.pensjon.common.samhandler.Samhandler;
import no.nav.domain.pensjon.kjerne.kodetabeller.SamhandlerTypeCode;

/**
 * Common class for handling service calls to samhandler.
 */
public class SamhandlerCommonActionDelegate {

    private static final String NORSKPENSJON_OFFENTLIG_ID_MSG_KEY = "pselv.sis003.statisk_tekst.orgnrnorskpensjon";
    private static final String NORSKPENSJON_SAMHANDLERTYPE = "ORG";
    private static final String NORSKPENSJON_AVDELINGSNR = "00";
    private SamhandlerServiceBi samhandlerService;

    private MessageSource messageSource;

    public String getNorskPensjonTssEksternID() {
        List<Samhandler> samhandlerliste = hentSamhandlerListe(SamhandlerTypeCode.TEPE);
        for (Samhandler samhandler : samhandlerliste) {
            if (samhandlerIsNorskPensjon(samhandler)) {
                for (Avdeling avdeling : samhandler.getAvdelinger()) {
                    if (avdelingIsNorskPensjon(avdeling)) {
                        return avdeling.getIdTSSEkstern();
                    }
                }
            }
        }
        return null; // no match found
    }

    private List<Samhandler> hentSamhandlerListe(SamhandlerTypeCode samhandlerType) {
        FinnSamhandlerRequest request = new FinnSamhandlerRequest();
        request.setSamhandlerType(samhandlerType.name());
        FinnSamhandlerResponse response = samhandlerService.finnSamhandler(request);
        return response.getResponseObject();
    }

    private boolean samhandlerIsNorskPensjon(Samhandler samhandler) {
        final String offentligIdNorskPensjon = getMsg(NORSKPENSJON_OFFENTLIG_ID_MSG_KEY);
        return samhandler.getIdType().equals(NORSKPENSJON_SAMHANDLERTYPE)
                && samhandler.getOffentligId().equals(offentligIdNorskPensjon);
    }

    private boolean avdelingIsNorskPensjon(Avdeling avdeling) {
        return avdeling.getAvdelingsnr().equalsIgnoreCase(NORSKPENSJON_AVDELINGSNR);
    }

    /**
     * Looks up message from properties file, i.e. resources.properties.
     */
    private String getMsg(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    public void setSamhandlerService(SamhandlerServiceBi samhandlerService) {
        this.samhandlerService = samhandlerService;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
