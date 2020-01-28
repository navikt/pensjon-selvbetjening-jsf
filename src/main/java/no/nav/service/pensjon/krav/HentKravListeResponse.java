package no.nav.service.pensjon.krav;

import java.util.List;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.kjerne.krav.KravHode;

/**
 * Response object for <b>TPEN104 - hentKravListe</b>.
 * <p>
 * A <i>HentKravListeResponse</i> consists of the following property:
 * <ul>
 * <li>kravListe (<code>List</code>)A list of Kravhode objects associated with a bruker or an enhet, identified by fnr or enhetId. If hentKravlinjer is true, then Kravlinje entries
 * associated to their respsective Kravhode objects in the list are included as well.</li>
 * </ul>
 * </p>
 *
 */
public class HentKravListeResponse extends ServiceResponse {
    // MEMBER VARIABLES //
    private static final long serialVersionUID = 3593406451473356761L;
    private List<KravHode> kravListe;

    // CONSTRUCTORS //

    /**
     * Default empty constructor
     */
    public HentKravListeResponse() {
    }

    /**
     * Intialise with property values.
     *
     * @param kravListe A list of Kravhode objects associated with a bruker or an
     * enhet, identified by fnr or enhetId. If hentKravlinjer is
     * true, then Kravlinje entries associated to their respsective
     * Kravhode objects in the list are included as well.
     */
    public HentKravListeResponse(List<KravHode> kravListe) {
        this.kravListe = kravListe;
    }

    // ACCESSOR METHODS //

    /**
     * Getter for property kravListe. A list of Kravhode objects associated with
     * a bruker or an enhet, identified by fnr or enhetId. If hentKravlinjer is
     * true, then Kravlinje entries associated to their respsective Kravhode
     * objects in the list are included as well.
     *
     * @return The value of this property.
     */
    public List<KravHode> getKravListe() {
        return kravListe;
    }

    /**
     * Setter for property kravListe. A list of Kravhode objects associated with
     * a bruker or an enhet, identified by fnr or enhetId. If hentKravlinjer is
     * true, then Kravlinje entries associated to their respsective Kravhode
     * objects in the list are included as well.
     *
     * @param kravListe The property value to set.
     */
    public void setKravListe(List<KravHode> kravListe) {
        this.kravListe = kravListe;
    }
}
