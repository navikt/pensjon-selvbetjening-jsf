package no.nav.domain.pensjon.kjerne.beregning;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import no.nav.domain.pensjon.common.ArligInformasjon;
import no.nav.domain.pensjon.common.util.EnumUtils;
import no.nav.domain.pensjon.kjerne.Merknad;
import no.nav.domain.pensjon.kjerne.kodetabeller.PoengtallTypeCode;

/**
 * Denne klassen blir representert som en String i databasen.
 */
public class Poengtall implements Serializable, ArligInformasjon {

    private static final long serialVersionUID = 3889087146527646252L;
    private static final String FIELD_SEPARATOR = ":";
    private static final String RECORD_SEPARATOR = ";";

    /**
     * Pensjonspoeng (Heter bare p i domenemodelldokumentet)
     */
    private Double pp = 0d;

    /**
     * Pensjonsgivende inntekt anvendt (heter bare pa i domenemodelldokumentet)
     */
    private Integer pia = 0;

    /**
     * Pensjonsgivende inntekt (Heter også bare p i domenemodelldokumentet, så det er en åpenbar feil et sted der)
     */
    private Integer pi = 0;

    private Integer ar = 0;
    private Boolean bruktIBeregning = false;

    /**
     * Veiet grunnbeløp (heter bare g i domenemodelldokumentet
     */
    private Integer gv = 0;

    private PoengtallTypeCode poengtallType;
    private Integer maksUforegrad = 0;

    /**
     * Merknadliste, denne blir populert av PoengtallListeParseren
     */
    private List<Merknad> merknadListe = new ArrayList<>();

    /**
     * Tilbakereferanse til Poengrekke
     */
    private Poengrekke poengrekke = null;

    /**
     * Tilbakereferanse til FramtidigPensjonspoengtall
     */
    private FramtidigPensjonspoengtall fpp = null;

    /**
     * Angir om årets uføreopptjening er beregnet som uføreår (true) eller mellomliggende år (false).
     */
    private Boolean uforear;

    /**
     * Copy Constructor. Deep copy. Omits id and back references.
     */
    public Poengtall(Poengtall poengtall) {
        setPp(poengtall.getPp());
        setPia(poengtall.getPia());
        setPi(poengtall.getPi());
        setAr(poengtall.getAr());
        setBruktIBeregning(poengtall.getBruktIBeregning());
        setGv(poengtall.getGv());
        setPoengtallType(poengtall.getPoengtallType());
        setMaksUforegrad(poengtall.getMaksUforegrad());
        setMerknadListe(poengtall.getMerknadListe());
        setUforear(poengtall.getUforear());
    }

    public Poengtall() {
    }

    /**
     * Construct based on a serialized stringrepresentation. This is the same as: <code>
     * Poengtall p = new Poengtall();
     * deserializeFromString(stringRepresentation)
     * </code>
     */
    public Poengtall(String stringRepresentation) {
        deserializeFromString(stringRepresentation);
    }

    public String serializeToString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getAr() == null ? "" : getAr().toString()).append(FIELD_SEPARATOR);
        builder.append(getPoengtallType() == null ? "" : getPoengtallType().toString()).append(FIELD_SEPARATOR);
        builder.append(getMaksUforegrad() == null ? "" : getMaksUforegrad().toString()).append(FIELD_SEPARATOR);
        builder.append(getPp() == null ? "" : getPp().toString()).append(FIELD_SEPARATOR);
        builder.append(getPia() == null ? "" : getPia().toString()).append(FIELD_SEPARATOR);
        builder.append(getPi() == null ? "" : getPi().toString()).append(FIELD_SEPARATOR);

        // Use 'T' and 'F' for true and false
        if (getBruktIBeregning() != null) {
            builder.append(getBruktIBeregning() ? 'T' : 'F');
        }

        builder.append(FIELD_SEPARATOR);
        builder.append(getGv() == null ? "" : getGv().toString()).append(FIELD_SEPARATOR);

        if (getUforear() != null) {
            builder.append(getUforear() ? 'T' : 'F');
        }

        builder.append(RECORD_SEPARATOR);
        return builder.toString();
    }

    public void deserializeFromString(String stringRepresentation) {
        if (stringRepresentation == null || stringRepresentation.isEmpty()) {
            return;
        }

        String[] fields = stringRepresentation.split(FIELD_SEPARATOR);

        if (fields.length != 9) {
            // All fields must be included, or String is invalid
            throw new IllegalArgumentException("Invalid stringRepresentation for Poengtall: " + stringRepresentation);
        }

        // Chomp recordseparator
        String lastElem = fields[fields.length - 1];
        lastElem = lastElem.substring(0, lastElem.lastIndexOf(RECORD_SEPARATOR));
        fields[fields.length - 1] = lastElem;

        setAr(!fields[0].isEmpty() ? Integer.parseInt(fields[0]) : null);
        setPoengtallType(!fields[1].isEmpty() ? EnumUtils.valueOf(PoengtallTypeCode.class, fields[1]) : null);
        setMaksUforegrad(!fields[2].isEmpty() ? Integer.parseInt(fields[2]) : null);
        setPp(!fields[3].isEmpty() ? Double.valueOf(fields[3]) : null);
        setPia(!fields[4].isEmpty() ? Integer.parseInt(fields[4]) : null);
        setPi(!fields[5].isEmpty() ? Integer.parseInt(fields[5]) : null);

        if ("T".equalsIgnoreCase(fields[6])) {
            setBruktIBeregning(true);
        } else if ("F".equalsIgnoreCase(fields[6])) {
            setBruktIBeregning(false);
        } else {
            setBruktIBeregning(null);
        }

        setGv(!fields[7].isEmpty() ? Integer.parseInt(fields[7]) : null);

        if ("T".equalsIgnoreCase(fields[8])) {
            setUforear(true);
        } else if ("F".equalsIgnoreCase(fields[8])) {
            setUforear(false);
        } else {
            setUforear(null);
        }
    }

    public Integer getMaksUforegrad() {
        return maksUforegrad;
    }

    public void setMaksUforegrad(Integer maksUforegrad) {
        this.maksUforegrad = maksUforegrad;
    }

    public void setPp(Double pp) {
        this.pp = pp;
    }

    @Override
    public Integer getAr() {
        return ar;
    }

    public Boolean getBruktIBeregning() {
        return bruktIBeregning;
    }

    public Integer getGv() {
        return gv;
    }

    public List<Merknad> getMerknadListe() {
        return merknadListe;
    }

    public void addMerknad(Merknad merknad) {
        if (merknad != null) {
            getMerknadListe().add(merknad);
            merknad.setAr(getAr());
        }
    }

    public Integer getPi() {
        return pi;
    }

    public Integer getPia() {
        return pia;
    }

    public Double getPp() {
        return pp;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public void setBruktIBeregning(Boolean bruktIBeregning) {
        this.bruktIBeregning = bruktIBeregning;
    }

    public void setGv(Integer gv) {
        this.gv = gv;
    }

    public void setMerknadListe(List<Merknad> merknadListe) {
        this.merknadListe = merknadListe;
    }

    public void setPi(Integer pi) {
        this.pi = pi;
    }

    public void setPia(Integer pia) {
        this.pia = pia;
    }

    public PoengtallTypeCode getPoengtallType() {
        return poengtallType;
    }

    public void setPoengtallType(PoengtallTypeCode poengtallType) {
        this.poengtallType = poengtallType;
    }

    protected FramtidigPensjonspoengtall getFpp() {
        return fpp;
    }

    protected void setFpp(FramtidigPensjonspoengtall fpp) {
        this.fpp = fpp;
    }

    protected Poengrekke getPoengrekke() {
        return poengrekke;
    }

    protected void setPoengrekke(Poengrekke poengrekke) {
        this.poengrekke = poengrekke;
    }

    public Boolean getUforear() {
        return uforear;
    }

    public void setUforear(Boolean uforear) {
        this.uforear = uforear;
    }
}
