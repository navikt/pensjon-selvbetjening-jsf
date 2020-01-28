package no.nav.consumer.pensjon.pselv.samhandler.to;

import no.stelvio.common.transferobject.ServiceRequest;

/**
 * There are 4 different searches in accoranse with pp234622_DFTB_ESB_0811_8.1 -
 * version 010<br />
 * The request object have to conform to the spec. <i>This text may not be
 * accurate</i>.<br />
 * Given the current spec. it conformes to:
 * <p>
 * <b>Search for : Org name</b>
 * <ol>
 * <li>brukerid = <i>declared elsewhere.</i></li>
 * <li>name = name of a samhandler.</li>
 * <li>samhandlerType = from codetable.</li>
 * <li>offentligId = none.</li>
 * <li>idType = Assumed to be the country choosen for the organisation.
 * <ul>
 * <li>UTOR = Utenlandsk orgnummer</li>
 * <li>ORG = Norsk organisajonsnummer</li>
 * </ul>
 * </li>
 * </ol>
 * </p>
 * <p>
 * <b>Search for : Person</b> <i>The search itselv must be declared as a name service, how this is actually done is unclear. The assumption is that this is done by declaring idType
 * of a "personnr" as hidden from the user.</i>
 * <ol>
 * <li>brukerid = <i>declared elsewhere.</i></li>
 * <li>name = name of person. (Probably declared as: "firstname lastname").</li>
 * <li>samhandlerType = from codetable.</li>
 * <li>offentligId = none.</li>
 * <li>idType = none.</li> </li>
 * </ol>
 * </p>
 * <p>
 * <b>Search for : Direct</b> <i>The search itselv must be declared as a name service, how this is actually done is unclear. The assumption is that this is done by declaring idType
 * of a "personnr" as hidden from the user.</i>
 * <ol>
 * <li>brukerid = <i>declared elsewhere.</i></li>
 * <li>name = none</li>
 * <li>samhandlerType = from codetable.</li>
 * <li>offentligId = Id of the samhandler</li>
 * <li>idType = from codetable.</li>
 * </ol>
 * </p>
 * <p>
 * <b>Lookup to fnr</b> <i>unknown at the time beeing</i>
 * </p>
 * <p>
 * <b>Lookup to orgnr</b> <i>unknown at the time beeing</i>
 * </p>
 * <p>
 * <b>(Lookupto TPnr)</b> <i>unknown at the time beeing</i>
 * </p>
 */
public class FinnSamhandlerRequest extends ServiceRequest {

    private static final long serialVersionUID = 6547073758432763172L;

    /**
     * Defines the name that should be used for a search. Used by name searches
     */
    private String navn;

    /**
     * Defines the metadata of a samhandler, the values of a SamhandlerType is
     * ex lawyer and dentist. Used for all searches
     */
    private String samhandlerType;

    /**
     * Defines the id for a search ex the value of a personnr, orgnr. Used by direct search
     */
    private String offentligId;

    /**
     * Defines an the metatype of id given by offentligID ex personnr or
     * orgnummer. It is assumed that this can be used for spesifying a country
     * in the name attribute. Used by direct search
     */
    private String idType;

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getSamhandlerType() {
        return samhandlerType;
    }

    public void setSamhandlerType(String samhandlerType) {
        this.samhandlerType = samhandlerType;
    }

    public String getOffentligId() {
        return offentligId;
    }

    public void setOffentligId(String offentligId) {
        this.offentligId = offentligId;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }
}
