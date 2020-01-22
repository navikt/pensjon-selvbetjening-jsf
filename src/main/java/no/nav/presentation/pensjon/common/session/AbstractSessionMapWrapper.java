package no.nav.presentation.pensjon.common.session;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.stelvio.domain.person.Pid;

import static org.springframework.webflow.context.ExternalContextHolder.getExternalContext;

/**
 * Kind of a wrapper class for the session object. This class stores objects into a map which is stored in session. It also
 * keeps constants used to set and get session attributes. Class is set to be abstract to make sure specific constants is set in
 * the correct project. Naming convention is <COMPONENT NAME>_<CONSTANT NAME>, i.e. PSAK_LOGGED_ON_NAVANSATT.
 * SessionMapWrapper has getters for session variables used in the flows.
 */
public abstract class AbstractSessionMapWrapper {

    /**
     * Should only be used by filter or special classes that doesn't have FacesContext available.
     */
    public static final String PENSJON_COMMON_SESSIONMAP = "PENSJON_COMMON_SESSIONMAP";

    /**
     * Session attribute name used to set and get a user's PID.
     */
    public static final String COMMON_BRUKER_PID = "COMMON_BRUKER_PID";

    /**
     * Session attribute name used to set and get a user's fullmakt information.
     */
    public static final String COMMON_BRUKER_FULLMAKT_INFO = "COMMON_BRUKER_FULLMAKT_INFO";

    /**
     * Session attribute name used to set and get a user's PID.
     */
    public static final String COMMON_LOGGED_ON_NAME = "COMMON_LOGGED_ON_NAME";

    /**
     * This class' logger. Uses a Utility class to perform the logging, since it is required - and best practice - to always
     * check for the log level before actually performing the logging.
     */
    protected static final Log LOGGER = LogFactory.getLog(AbstractSessionMapWrapper.class);

    public static Pid getBrukerPid() {
//TODO
//        return (Pid) get(COMMON_BRUKER_PID);
        return new Pid("09094812167"); // normally obtained via getStartPid()?
    }

    public static void put(String key, Object value) {
        getHolder().put(key, value);
    }

    public static Object get(String key) {
        return getHolder().get(key);
    }

    public static Object remove(String key) {
        return getHolder().remove(key);
    }

    public static void removeAll() {
        Map<String, Object> map = getSessionMap();

        if (map.containsKey(PENSJON_COMMON_SESSIONMAP)) {
            removeFrom(map);
        }
    }

    public static boolean containsKey(String key) {
        return getHolder().containsKey(key);
    }

    /**
     * Returns a specific object contained in session. The returning object is the session map tied to common pensjon.
     * <p>
     * The session map tied to common pensjon, is retrieved from and filtered by:
     * <code>javax.faces.context.FacesContext.getExternalContext().getSessionMap().get(PENSJON_COMMON_SESSIONMAP)</code>
     * </p>
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object> getHolder() {
        Map<String, Object> map = getSessionMap();
        Map<String, Object> holder = (Map<String, Object>) map.get(PENSJON_COMMON_SESSIONMAP);
        return holder == null ? putNewHolder(map) : holder;
    }

    private static Map<String, Object> putNewHolder(Map<String, Object> map) {
        Map<String, Object> holder = new HashMap<>();
        map.put(PENSJON_COMMON_SESSIONMAP, holder);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The Pensjon session map is null, a map is created an put on the session.");
        }

        return holder;
    }

    private static void removeFrom(Map<String, Object> map) {
        map.remove(PENSJON_COMMON_SESSIONMAP);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The Pensjon session map is removed.");
        }
    }

    private static Map<String, Object> getSessionMap() {
        return getExternalContext().getSessionMap().asMap();
    }
}
