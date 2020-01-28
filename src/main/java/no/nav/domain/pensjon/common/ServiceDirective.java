package no.nav.domain.pensjon.common;

import java.io.Serializable;

/**
 * Base class for service directives. Contains only a fetchSet property modeled as a String because the IBM bus does not
 * understand inheritance... Legal values for fetch sets are described in the service error messages.
 */
public class ServiceDirective implements Serializable {

    private static final long serialVersionUID = -2205107286197176263L;
    private String fetchSet = null;

    public ServiceDirective() {
    }

    public ServiceDirective(String fetchSet) {
        this.fetchSet = fetchSet;
    }

    public String getFetchSet() {
        return fetchSet;
    }

    public void setFetchSet(String fetchSet) {
        this.fetchSet = fetchSet;
    }
}
