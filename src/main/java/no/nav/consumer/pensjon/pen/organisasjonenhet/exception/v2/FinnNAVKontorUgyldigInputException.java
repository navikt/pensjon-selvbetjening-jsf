package no.nav.consumer.pensjon.pen.organisasjonenhet.exception.v2;

/**
 * Thrown when call to NORG2 finnNavkontor recieves invalid input
 */
public class FinnNAVKontorUgyldigInputException extends Exception {
    public FinnNAVKontorUgyldigInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
