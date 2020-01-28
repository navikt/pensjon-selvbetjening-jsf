package no.nav.domain.pensjon.common.exception.tjenestepensjon;

import no.nav.domain.pensjon.common.exception.ConsumerRecoverableException;

public class ElementetFinnesIkkeException extends ConsumerRecoverableException {

    private static final long serialVersionUID = 3850026859622640509L;
    private String origin;
    private String id;

    public ElementetFinnesIkkeException(String origin, String id) {
        super("Element " + id + " not found, source: " + origin);
        this.id = id;
        this.origin = origin;
    }

    public ElementetFinnesIkkeException(Throwable cause, String origin, String id) {
        super("Element " + id + " not found, source: " + origin, cause);
        this.id = id;
        this.origin = origin;
    }

    public ElementetFinnesIkkeException(String message, String origin, String id) {
        super(message);
        this.id = id;
        this.origin = origin;
    }

    public ElementetFinnesIkkeException(String message, Throwable cause, String origin, String id) {
        super(message, cause);
        this.id = id;
        this.origin = origin;
    }

    public String getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }
}
