package no.nav.presentation.pensjon.pselv.common.session;

import no.nav.presentation.pensjon.common.session.AbstractSessionMapWrapper;

public class PselvSessionMapWrapper extends AbstractSessionMapWrapper {

    /**
     * Session attribute name used to set and get the behalf of name.
     */
    public static final String ON_BEHALF_OF_NAME = "ON_BEHALF_OF_NAME";

    /**
     * Session attribute name used to set and get the 'first name' part of behalf of name.
     */
    public static final String ON_BEHALF_OF_FIRSTNAME = "ON_BEHALF_OF_FIRSTNAME";

    /**
     * Session attribute name used to set and get the FROM_LOGIN_PAGE.
     */
    public static final String FROM_LOGING_PAGE = "FROM_LOGIN_PAGE"; //TODO Fix typo

    /**
     * Should only be used by PsakTransferObject.
     */
    public static final String PSELV_TRANSFER_OBJECT = "PSELV_TRANSFER_OBJECT";

    /**
     * Session attribute name used to set and get the number of unread messages in left menu
     */
    public static final String ANTALL_ULESTE = "ANTALL_ULESTE";

    /**
     * Session attribute name used set and get the user group of the logged in user
     */
    public static final String USER_GROUP = "USER_GROUP";

    /**
     * Session attribute name used to decide active
     */
    public static final String ACTIVE = "ACTIVE";

    /**
     * Session attribute name used for the fullmakt filter
     */
    public static final String FULLMAKT_FILTER_CHECKED = "FULLMAKT_FILTER_CHECKED";

    /**
     * Session attribute name used for the uførevedtak filter
     */
    public static final String UFOREVEDTAK_FILTER_CHECKED = "UFOREVEDTAK_FILTER_CHECKED";

    /**
     * Session attribute name used for 'hurtig simulering' in PUS002.
     */
    public static final String SIMULATION_RESULTS = "SIMULATION_RESULTS";

    /**
     * Session attribute name used for EESSI Pensjon Selvbetjening in FES001.
     */
    public static final String EESSI_PENSJON_SELVBETJENING = "EESSI_PENSJON_SELVBETJENING";

    /**
     * Session attribute name used for EESSI Pensjon Selvbetjening in FES001.
     */
    public static final String AKTOER_ID = "AKTOER_ID";

    public Integer getANTALL_ULESTE() {
        return (Integer) PselvSessionMapWrapper.get(PselvSessionMapWrapper.ANTALL_ULESTE);
    }

    public boolean isEESSI_PENSJON_SELVBETJENING() {
        return (boolean) PselvSessionMapWrapper.get(PselvSessionMapWrapper.EESSI_PENSJON_SELVBETJENING);
    }

    public String getAKTOER_ID() {
        return (String) PselvSessionMapWrapper.get(PselvSessionMapWrapper.AKTOER_ID);
    }
}
