package no.nav.presentation.pensjon.pselv.common.session;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.nav.presentation.pensjon.common.session.TransferObject;

/**
 * PselvTransferObject should be used to transfer objects between flows.
 */
public class PselvTransferObject implements TransferObject {

    /**
     * Represents the variant of calculator to run
     */
    public static final String PSELV_CALC_VARIANT = "PSELV_CALC_VARIANT";

    /**
     * Represents the type of barnepensjon to run
     */
    public static final String PSELV_CALC_BARNEPENSJON_TYPE = "PSELV_CALC_BARNEPENSJON_TYPE";

    /**
     * ID to retrieve the ForenkletSimuleringFrom from session
     */
    public static final String FORENKLETSIMULERINGFORM = "form";

    /**
     * The sak id
     */
    public static final String SAKSID = "saksId";

    /**
     * The pdf id used by PSU012 Skjemaoversikt
     */
    public static final String SKJEMA_PDF_FORMDATA = "pdfFormData";

    /**
     * Represents the mapping object from pensjonskalkulator to skjema
     */
    public static final String CALC_TO_SKJEMA_MAPPING = "CALC_TO_SKJEMA_MAPPING";

    /**
     * Represents the mapping object from pensjonskalkulator to skjema
     */
    public static final String CALC_TO_SKJEMA_ENDRING_MAPPING = "CALC_TO_SKJEMA_ENDRING_MAPPING";

    /**
     * value to decide if the simulation in pensjonskalkulator should be direct
     */
    public static final String DIRECT_SIMULATION = "directSimulation";

    /**
     * value to decide if it comes from pensjonskalkulator
     */
    public static final String VIEW_KALKULATOR = "viewkalkulator";

    /**
     * The RightColumnPensjonFormData
     */
    public static final String RIGHTCOLUMNFORMDATA = "rightColumnFormData";

    /**
     * the selected simulering used in pensjonskalkulator flow
     */
    public static final String SELECTED_SIMULERING = "selectedSimulering";

    /**
     * The skjema id for skjema flow
     */
    public static final String SKJEMA_ID = "skjemaId";

    /**
     * The skjema type to be used in skjema flow
     */
    public static final String SKJEMA_TYPE = "skjemaType";

    /**
     * The feilmelding to show in feilmelding-view. Used from skjemaalderspensjon if there are problems with the data returned
     * from TPS.
     */
    public static final String FEILMELDING = "feilmelding";

    /**
     * The key to the pagetitle to show in feilmelding-view. Used from skjemaalderspensjon if there are problems with the data
     * returned from TPS.
     */
    public static final String TITLE_KEY = "titleKey";

    /**
     * This class' logger. Uses a Utility class to perform the logging, since it is required - and best practice - to always
     * check for the log level before actually performing the logging.
     */
    private static final Log LOGGER = LogFactory.getLog(PselvTransferObject.class);

    @Override
    public Object get(String key) throws RuntimeException {
        Map<String, Object> holder = getHolder();
        LOGGER.debug("Trying to get a mandatory object from TransferObject with key '" + key + "'.");
        if (!holder.containsKey(key)) {
            throw new RuntimeException("The mandatory object, referenced by the key '" + key + "', doesn't exist!");
        }
        return holder.remove(key);
    }

    @Override
    public Object getOptional(String key) {
        Map<String, Object> holder = getHolder();
        LOGGER.debug("Trying to get a optional object from TransferObject with key '" + key + "'.");
        return holder.remove(key);
    }

    @Override
    public void put(String key, Object value) {
        Map<String, Object> holder = getHolder();
        holder.put(key, value);
        PselvSessionMapWrapper.put(PselvSessionMapWrapper.PSELV_TRANSFER_OBJECT, holder);
        LOGGER.debug("Adding a object (with key '" + key
                + "') to PselvTranferObject which is put on the PsakSessionsMapWrapper object on the session.");
    }

    @Override
    public boolean containsKey(String key) {
        Map<String, Object> holder = getHolder();
        return holder.containsKey(key);
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getHolder() {
        Map<String, Object> holder = (Map<String, Object>) PselvSessionMapWrapper
                .get(PselvSessionMapWrapper.PSELV_TRANSFER_OBJECT);
        if (holder == null) {
            holder = new HashMap<String, Object>();
            PselvSessionMapWrapper.put(PselvSessionMapWrapper.PSELV_TRANSFER_OBJECT, holder);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("The PSELV transfer object on the session map is null, a Map is created an put on the session.");
            }
        }
        return holder;
    }
}
