package no.nav.domain.pensjon.kjerne.beregning;

import static org.hibernate.annotations.FetchMode.SELECT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Fetch;

import no.nav.domain.pensjon.AbstractMerknadDomainObject;
import no.nav.domain.pensjon.kjerne.Merknad;

/**
 * Ved ufore, etterlatt og AFP blir det beregnet poengtall for fremtidige ar for a komme frem til et sluttpoengtall som pensjonen skal beregnes etter.
 */
//@Entity
//@Table(name = "T_FRAMT_POENGTALL")
public class FramtidigPensjonspoengtall extends AbstractMerknadDomainObject {
    private static final long serialVersionUID = 6149463114237651833L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FRAMT_POENGTALL_ID")
    @SuppressWarnings("unused") // brukes av hibernate
    private Long framtidigPensjonspoengtallId;

    @Column(name = "POENGTALL", nullable = false)
    private Double poengtall = 0d;

    /**
     * Ikke en del av domenemodellen, men trengs som fremmednøkkel fra Poengrekke
     */
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "POENGREKKE_ID")
    @Fetch(SELECT)
    private Poengrekke poengrekke;

    /**
     * Ikke en del av domenemodellen, men trengs som fremmednøkkel fra Poengrekke
     */
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "FPP_OMREGNET")
    @Fetch(SELECT)
    private Poengrekke poengrekkeOmregnet;

    /**
     * Liste med poengtall som ligger tilgrunn for sluttpoengtallet. Bruker accessor og mutator for persistens, ikke attributtet
     * direkte
     */
    @Column(name = "POENGTALL_LISTE", nullable = true)
    private String poengtallListeString = "";

    @Transient
    private List<Poengtall> poengtallListe;

    // Denne er utledet av listen i superklassen, og inneholder kun merknader som gjelder Poengrekke
    @Transient
    private List<Merknad> merknadListe;

    public FramtidigPensjonspoengtall(FramtidigPensjonspoengtall framtidigPensjonspoengtall) {
        this(framtidigPensjonspoengtall, new HashMap<Object, Object>());
    }

    public FramtidigPensjonspoengtall(FramtidigPensjonspoengtall framtidigPensjonspoengtall, Map<Object, Object> copies) {
        copies.put(framtidigPensjonspoengtall, this);
        setPoengtall(framtidigPensjonspoengtall.getPoengtall());

        // Prevent endless recursion
        if (framtidigPensjonspoengtall.getPoengrekke() != null) {
            if (copies.get(framtidigPensjonspoengtall.getPoengrekke()) != null) {
                setPoengrekke((Poengrekke) copies.get(framtidigPensjonspoengtall.getPoengrekke()));
            } else {
                setPoengrekke(new Poengrekke(framtidigPensjonspoengtall.getPoengrekke(), copies));
            }
        } else {
            setPoengrekke(null);
        }

        // Prevent endless recursion
        if (framtidigPensjonspoengtall.getPoengrekkeOmregnet() != null) {
            if (copies.get(framtidigPensjonspoengtall.getPoengrekkeOmregnet()) != null) {
                setPoengrekkeOmregnet((Poengrekke) copies.get(framtidigPensjonspoengtall.getPoengrekkeOmregnet()));
            } else {
                setPoengrekkeOmregnet(new Poengrekke(framtidigPensjonspoengtall.getPoengrekkeOmregnet(), copies));
            }
        } else {
            setPoengrekkeOmregnet(null);
        }

        if (framtidigPensjonspoengtall.getPoengtallListe() != null) {
            for (Poengtall pt : framtidigPensjonspoengtall.getPoengtallListe()) {
                addPoengtall(new Poengtall(pt));
            }
        }

        if (framtidigPensjonspoengtall.getMerknadListe() != null) {
            for (Merknad m : framtidigPensjonspoengtall.getMerknadListe()) {
                addMerknad(new Merknad(m));
            }
        }
    }

    public FramtidigPensjonspoengtall() {
    }

    private void recalculatePersistentFields() {
        for (Poengtall poengtall : getPoengtallListe()) {
            for (Merknad merknad : poengtall.getMerknadListe()) {
                Merknad existingMerknad = findMerknad(getMerknadListe(), merknad);

                if (existingMerknad == null) {
                    addMerknad(merknad);
                } else {
                    existingMerknad.setArgumentListeString(merknad.getArgumentListeString());
                }
            }
        }

        String plString = PoengtallListeParser.parsePoengtallListe(getPoengtallListe());

        if (!plString.equals(poengtallListeString)) {
            poengtallListeString = plString;
        }
    }

    /**
     * Find merknad in list. Equality is defined as:
     * <ol>
     * <li>ar and argumentlisteString is equal
     * </ol>
     */
    private Merknad findMerknad(List<Merknad> merknadListe, Merknad item) {
        if (merknadListe == null) {
            return null;
        }

        for (Merknad merknad : merknadListe) {
            if (merknad == item || isArEqualOn(item, merknad) && isArgumentListeStringEqualOn(item, merknad)) {
                return merknad;
            }
        }

        return null;
    }

    private boolean isArEqualOn(Merknad item, Merknad merknad) {
        return merknad.getAr() == null && item.getAr() == null
                || merknad.getAr() != null && item.getAr() != null && merknad.getAr().equals(item.getAr());
    }

    private boolean isArgumentListeStringEqualOn(Merknad item, Merknad m) {
        return StringUtils.equals(m.getArgumentListeString(), item.getArgumentListeString());
    }

    public List<Merknad> getMerknadListe() {
        if (merknadListe != null) {
            return merknadListe;
        }

        merknadListe = new ArrayList<>();

        for (Merknad merknad : super.getMerknadListe()) {
            if (merknad.getAr() == null) {
                merknadListe.add(merknad);
            }
        }

        return merknadListe;
    }

    public Double getPoengtall() {
        return poengtall;
    }

    public void setPoengtall(Double poengtall) {
        this.poengtall = poengtall;
    }

    public List<Poengtall> getPoengtallListe() {
        if (poengtallListe == null) {
            poengtallListe = PoengtallListeParser.parseString(poengtallListeString, getMerknadListe());
        }

        return poengtallListe;
    }

    public void addPoengtall(Poengtall poengtall) {
        if (poengtall == null) {
            return;
        }

        getPoengtallListe().add(poengtall);
        poengtall.setFpp(this);
        recalculatePersistentFields();
    }

    public Poengrekke getPoengrekke() {
        return poengrekke;
    }

    public void setPoengrekke(Poengrekke poengrekke) {
        this.poengrekke = poengrekke;
    }

    public Poengrekke getPoengrekkeOmregnet() {
        return poengrekkeOmregnet;
    }

    public void setPoengrekkeOmregnet(Poengrekke poengrekkeOmregnet) {
        this.poengrekkeOmregnet = poengrekkeOmregnet;
    }
}
