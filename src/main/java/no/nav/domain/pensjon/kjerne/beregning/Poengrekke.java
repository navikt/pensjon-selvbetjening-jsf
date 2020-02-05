package no.nav.domain.pensjon.kjerne.beregning;

import static org.hibernate.annotations.FetchMode.SELECT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;

import no.nav.domain.pensjon.AbstractMerknadDomainObject;
import no.nav.domain.pensjon.kjerne.Merknad;

//@Entity
//@Table(name = "T_POENGREKKE")
public class Poengrekke extends AbstractMerknadDomainObject {
    private static final long serialVersionUID = -514407014035307185L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POENGREKKE_ID")
    @SuppressWarnings("unused") // brukes av hibernate
    private Long poengrekkeId;

    /**
     * Antall poengår totalt
     */
    @Column(name = "PA", nullable = false)
    private Integer pa = 0;

    /**
     * Antall poengår før 1992
     */
    @Column(name = "PA_F92", nullable = true)
    private Integer paF92 = 0;

    /**
     * Antall poengår etter 1991
     */
    @Column(name = "PA_E91", nullable = false)
    private Integer paE91 = 0;

    /**
     * Antall poengår, faktisk
     */
    // --fjern (gjør om til paFaNorge
    @Column(name = "PA_FA_NORGE", nullable = true)
    private Integer pa_fa_norge = 0;

    /**
     * Tidligere pensjonsgivende inntekt
     */
    @Column(name = "TPI", nullable = true)
    private Integer tpi = 0;

    /**
     * TPI beregnet etter hovedregelen
     */
    @Column(name = "TPI_ETTER_HOVEDREGEL")
    private Integer tpiEtterHovedregel = 0;

    /**
     * Tidligere pensjonsgivende inntekt faktor
     */
    @Column(name = "TPI_FAKTOR", nullable = true)
    private Double tpiFaktor = 0d;

    /**
     * Poengtall ut fra antatt årlig inntekt på skadetidspunktet
     */
    @Column(name = "PAA", nullable = true)
    private Double paa = 0d;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "SLUTTPOENGTALL_ID")
    @Fetch(SELECT)
    private Sluttpoengtall sluttpoengtall;

    /**
     * Fremtidig pensjonspoengtall
     * Not really a list, but using one to many relation for performance
     */
    @OneToMany(mappedBy = "poengrekke", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(SELECT)
    private List<FramtidigPensjonspoengtall> fpp = new ArrayList<FramtidigPensjonspoengtall>();

    /**
     * Fremtidig pensjonspoengtall omregnet
     * Not really a list, but using one to many relation for performance
     */
    @OneToMany(mappedBy = "poengrekkeOmregnet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(SELECT)
    private List<FramtidigPensjonspoengtall> fppOmregnet = new ArrayList<FramtidigPensjonspoengtall>();

    /**
     * Liste med poengtall, bruker accessor og mutator for persistens, ikke attributtet direkte
     */
    @Column(name = "POENGTALL_LISTE", nullable = true)
    private String poengtallListeString = "";

    @Transient
    private List<Poengtall> poengtallListe;

    /**
     * Samlet antall poengår i Norge
     */
    @Column(name = "PA_NO", nullable = true)
    private Integer pa_no = 0;

    /**
     * Antall fremtidige poengtall
     */
    @Column(name = "FPA", nullable = true)
    private Integer fpa = 0;

    /**
     * Siste poengår med framtidig pensjonspoeng.
     */
    @Column(name = "SISTE_FPP_AR", nullable = true)
    private Integer siste_fpp_aar = 0;

    /**
     * Siste poengår med framtidig pensjonspoeng etter nordisk konvensjon
     */
    @Column(name = "SISTE_FPP_AR_NORD", nullable = true)
    private Integer siste_fpp_aar_norden = 0;

    /**
     * Siste poengår med framtidig pensjonspoeng, EØS tilfelle
     */
    @Column(name = "SISTE_FPP_AR_EOS", nullable = true)
    private Integer siste_fpp_aar_eos = 0;

    /**
     * Antall poengår før 1992 etter EØS-alternativet.
     */
    @Column(name = "PA_EOS_F92", nullable = true)
    private Integer pa_eos_f92 = 0;

    /**
     * Antall poengår etter 1991 etter EØS-alternativet.
     */
    @Column(name = "PA_EOS_E91", nullable = true)
    private Integer pa_eos_e91 = 0;

    /**
     * Brutto antall framtidige poengår i norden.
     */
    @Column(name = "PA_NORD_FRAMT_BRU", nullable = true)
    private Integer pa_nordisk_framt_brutto = 0;

    /**
     * Netto antall framtidige poengår i norden.
     */
    @Column(name = "PA_NORD_FRAMT_NET", nullable = true)
    private Integer pa_nordisk_framt_netto = 0;

    // Denne er utleded av listen over, og inneholder kun merknader som gjelder Poengrekke
    @Transient
    private List<Merknad> merknadListe;

    /**
     * Gjennomsnittlig uføregrad
     */
    @Column(name = "FPP_EOS_SNITT", nullable = true)
    private Double fpp_eos_snitt = 0d;

    /**
     * Antall poengår i avtaleland (faktisk) for Nordisk beregning
     */
    @Column(name = "PA_FA_NORDEN", nullable = true)
    private Integer pa_fa_norden = 0;

    /**
     * Antall poengår teoretisk (framtidig). Bare relevant for EØS beregning
     */
    @Column(name = "PA_EOS_TEORETISK", nullable = true)
    private Integer pa_eos_teoretisk = 0;

    /**
     * Antall poengår i avtaleland (faktisk) for EØS beregning
     */
    @Column(name = "PA_EOS_PRO_RATA", nullable = true)
    private Integer pa_eos_pro_rata = 0;

    /**
     * Proratabrøk tilleggspensjon EØS beregning. Teller
     */
    @Column(name = "PA_PRO_RATA_TELLER", nullable = true)
    private Integer pa_pro_rata_teller = 0;

    /**
     * Proratabrøk tilleggspensjon for EØS beregning. Nevner
     */
    @Column(name = "PA_PRO_RATA_NEVNER", nullable = true)
    private Integer pa_pro_rata_nevner = 0;

    /**
     * Flagg som viser om oppustert grunnlag fra pensjonsberegning fra TP-ordning er benyttet
     */
    @Column(name = "AFP_TPO_UP_GRUNNLAG_ANVENDT", nullable = true)
    private Boolean afpTpoUpGrunnlagAnvendt;

    /**
     * Det oppjusterte uførepensjonsgrunnlaget fra TP-ordningen som ble brukt i beregning av TPI
     */
    @Column(name = "AFP_TPO_UP_GRUNNLAG_OPPJUSTERT", nullable = true)
    private Integer afpTpoUpGrunnlagOppjustert = 0;

    public Poengrekke(Poengrekke poengrekke) {
        this(poengrekke, new HashMap<Object, Object>());
    }

    public Poengrekke(Poengrekke poengrekke, Map<Object, Object> copies) {
        copies.put(poengrekke, this);
        setPa(poengrekke.getPa());
        setPaF92(poengrekke.getPaF92());
        setPaE91(poengrekke.getPaE91());
        setPa_fa_norge(poengrekke.getPa_fa_norge());
        setTpi(poengrekke.getTpi());
        setTpiEtterHovedregel(poengrekke.getTpiEtterHovedregel());
        setTpiFaktor(poengrekke.getTpiFaktor());
        setPaa(poengrekke.getPaa());

        if (poengrekke.getFpp() != null) {
            if (copies.get(poengrekke.getFpp()) != null) {
                setFpp((FramtidigPensjonspoengtall) copies.get(poengrekke.getFpp()));
            } else {
                setFpp(new FramtidigPensjonspoengtall(poengrekke.getFpp(), copies));
            }
        } else {
            setFpp(null);
        }

        // Prevent endless recursion
        if (poengrekke.getFppOmregnet() != null) {
            if (copies.get(poengrekke.getFppOmregnet()) != null) {
                setFppOmregnet((FramtidigPensjonspoengtall) copies.get(poengrekke.getFppOmregnet()));
            } else {
                setFppOmregnet(new FramtidigPensjonspoengtall(poengrekke.getFppOmregnet(), copies));
            }
        } else {
            setFppOmregnet(null);
        }

        if (poengrekke.getPoengtallListe() != null) {
            for (Poengtall pt : poengrekke.getPoengtallListe()) {
                addPoengtall(new Poengtall(pt));
            }
        }

        setPa_no(poengrekke.getPa_no());
        setFpa(poengrekke.getFpa());
        setSiste_fpp_aar(poengrekke.getSiste_fpp_aar());
        setSiste_fpp_aar_norden(poengrekke.getSiste_fpp_aar_norden());
        setSiste_fpp_aar_eos(poengrekke.getSiste_fpp_aar_eos());
        setPa_eos_f92(poengrekke.getPa_eos_f92());
        setPa_eos_e91(poengrekke.getPa_eos_e91());
        setPa_nordisk_framt_brutto(poengrekke.getPa_nordisk_framt_brutto());
        setPa_nordisk_framt_netto(poengrekke.getPa_nordisk_framt_netto());

        if (poengrekke.getMerknadListe() != null) {
            for (Merknad m : poengrekke.getMerknadListe()) {
                addMerknad(new Merknad(m));
            }
        }

        setFpp_eos_snitt(poengrekke.getFpp_eos_snitt());
        setPa_fa_norden(poengrekke.getPa_fa_norden());
        setPa_eos_teoretisk(poengrekke.getPa_eos_teoretisk());
        setPa_eos_pro_rata(poengrekke.getPa_eos_pro_rata());
        setPa_pro_rata_teller(poengrekke.getPa_pro_rata_teller());
        setPa_pro_rata_nevner(poengrekke.getPa_pro_rata_nevner());
        setAfpTpoUpGrunnlagAnvendt(poengrekke.getAfpTpoUpGrunnlagAnvendt());
        setAfpTpoUpGrunnlagOppjustert(poengrekke.getAfpTpoUpGrunnlagOppjustert());
    }

    public Poengrekke() {
    }

    private void recalculatePersistentFields() {
        for (Poengtall poengtall : getPoengtallListe()) {
            for (Merknad merknad : poengtall.getMerknadListe()) {
                Merknad existingMerknad = findMerknad(getMerknadListe(), merknad);

                if (existingMerknad == null) {
                    super.addMerknad(merknad);
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

    public FramtidigPensjonspoengtall getFpp() {
        return fpp == null || fpp.isEmpty() ? null : fpp.get(0);
    }

    public void setFpp(FramtidigPensjonspoengtall fpp) {
        if (fpp == null) {
            return;
        }

        fpp.setPoengrekke(this);
        this.fpp.clear();
        this.fpp.add(fpp);
    }

    /**
     * Find merknad in list. Equality is defined as:
     * <ol>
     * <li>Object identity or
     * <li>merknadId is equal or
     * <li>ar and argumentlisteString is equal
     * </ol>
     */
    private Merknad findMerknad(List<Merknad> merknadListe, Merknad item) {
        Merknad foundMerknad = null;

        if (merknadListe == null) {
            return foundMerknad;
        }

        for (Merknad merknad : merknadListe) {
            if (merknad == item || (merknad.getAr() == null && item.getAr() == null
                    || merknad.getAr() != null && item.getAr() != null && merknad.getAr().equals(item.getAr()))
                    && merknad.getArgumentListeString().equals(item.getArgumentListeString())) {
                foundMerknad = merknad;
                break;
            }
        }

        return foundMerknad;
    }

    @Override
    public void addMerknad(Merknad merknad) {
        super.addMerknad(merknad);
        recalculatePersistentFields();
    }

    public Integer getPa() {
        return pa;
    }

    public void setPa(Integer pa) {
        this.pa = pa;
    }

    public Integer getPaE91() {
        return paE91;
    }

    public void setPaE91(Integer paE91) {
        this.paE91 = paE91;
    }

    public Integer getPaF92() {
        return paF92;
    }

    public void setPaF92(Integer paF92) {
        this.paF92 = paF92;
    }

    public Integer getPa_fa_norge() {
        return pa_fa_norge;
    }

    public void setPa_fa_norge(Integer pa_fa_norge) {
        this.pa_fa_norge = pa_fa_norge;
    }

    public Double getPaa() {
        return paa;
    }

    public void setPaa(Double paa) {
        this.paa = paa;
    }

    public List<Poengtall> getPoengtallListe() {
        if (poengtallListe == null) {
            poengtallListe = PoengtallListeParser.parseString(poengtallListeString, getMerknadListe());
        }

        return poengtallListe;
    }

    public void addPoengtall(Poengtall poengtall) {
        if (poengtall != null) {
            getPoengtallListe().add(poengtall);
            poengtall.setPoengrekke(this);
            recalculatePersistentFields();
        }
    }

    public Integer getTpi() {
        return tpi;
    }

    public void setTpi(Integer tpi) {
        this.tpi = tpi;
    }

    public Integer getTpiEtterHovedregel() {
        return tpiEtterHovedregel;
    }

    public void setTpiEtterHovedregel(Integer tpiEtterHovedregel) {
        this.tpiEtterHovedregel = tpiEtterHovedregel;
    }

    public Double getTpiFaktor() {
        return tpiFaktor;
    }

    public void setTpiFaktor(Double tpiFaktor) {
        this.tpiFaktor = tpiFaktor;
    }

    public Integer getPa_eos_e91() {
        return pa_eos_e91;
    }

    public void setPa_eos_e91(Integer pa_eos_e91) {
        this.pa_eos_e91 = pa_eos_e91;
    }

    public Integer getPa_eos_f92() {
        return pa_eos_f92;
    }

    public void setPa_eos_f92(Integer pa_eos_f92) {
        this.pa_eos_f92 = pa_eos_f92;
    }

    public Integer getPa_no() {
        return pa_no;
    }

    public void setPa_no(Integer pa_no) {
        this.pa_no = pa_no;
    }

    public Integer getPa_nordisk_framt_brutto() {
        return pa_nordisk_framt_brutto;
    }

    public void setPa_nordisk_framt_brutto(Integer pa_nordisk_framt_brutto) {
        this.pa_nordisk_framt_brutto = pa_nordisk_framt_brutto;
    }

    public Integer getPa_nordisk_framt_netto() {
        return pa_nordisk_framt_netto;
    }

    public void setPa_nordisk_framt_netto(Integer pa_nordisk_framt_netto) {
        this.pa_nordisk_framt_netto = pa_nordisk_framt_netto;
    }

    public Integer getSiste_fpp_aar() {
        return siste_fpp_aar;
    }

    public void setSiste_fpp_aar(Integer siste_fpp_aar) {
        this.siste_fpp_aar = siste_fpp_aar;
    }

    public Integer getSiste_fpp_aar_eos() {
        return siste_fpp_aar_eos;
    }

    public void setSiste_fpp_aar_eos(Integer siste_fpp_aar_eos) {
        this.siste_fpp_aar_eos = siste_fpp_aar_eos;
    }

    public Integer getSiste_fpp_aar_norden() {
        return siste_fpp_aar_norden;
    }

    public void setSiste_fpp_aar_norden(Integer siste_fpp_aar_norden) {
        this.siste_fpp_aar_norden = siste_fpp_aar_norden;
    }

    Sluttpoengtall getSluttpoengtall() {
        return sluttpoengtall;
    }

    void setSluttpoengtall(Sluttpoengtall sluttpoengtall) {
        this.sluttpoengtall = sluttpoengtall;
    }

    public FramtidigPensjonspoengtall getFppOmregnet() {
        return fppOmregnet == null || fppOmregnet.isEmpty() ? null : fppOmregnet.get(0);
    }

    public void setFppOmregnet(FramtidigPensjonspoengtall fppOmregnet) {
        if (fppOmregnet == null) {
            return;
        }

        fppOmregnet.setPoengrekke(this);
        this.fppOmregnet.clear();
        this.fppOmregnet.add(fppOmregnet);
    }

    public Double getFpp_eos_snitt() {
        return fpp_eos_snitt;
    }

    public void setFpp_eos_snitt(Double fpp_eos_snitt) {
        this.fpp_eos_snitt = fpp_eos_snitt;
    }

    public Integer getPa_eos_pro_rata() {
        return pa_eos_pro_rata;
    }

    public void setPa_eos_pro_rata(Integer pa_eos_pro_rata) {
        this.pa_eos_pro_rata = pa_eos_pro_rata;
    }

    public Integer getPa_eos_teoretisk() {
        return pa_eos_teoretisk;
    }

    public void setPa_eos_teoretisk(Integer pa_eos_teoretisk) {
        this.pa_eos_teoretisk = pa_eos_teoretisk;
    }

    public Integer getPa_fa_norden() {
        return pa_fa_norden;
    }

    public void setPa_fa_norden(Integer pa_fa_norden) {
        this.pa_fa_norden = pa_fa_norden;
    }

    public Integer getPa_pro_rata_nevner() {
        return pa_pro_rata_nevner;
    }

    public void setPa_pro_rata_nevner(Integer pa_pro_rata_nevner) {
        this.pa_pro_rata_nevner = pa_pro_rata_nevner;
    }

    public Integer getPa_pro_rata_teller() {
        return pa_pro_rata_teller;
    }

    public void setPa_pro_rata_teller(Integer pa_pro_rata_teller) {
        this.pa_pro_rata_teller = pa_pro_rata_teller;
    }

    public Integer getFpa() {
        return fpa;
    }

    public void setFpa(Integer fpa) {
        this.fpa = fpa;
    }

    public Boolean getAfpTpoUpGrunnlagAnvendt() {
        return afpTpoUpGrunnlagAnvendt;
    }

    public void setAfpTpoUpGrunnlagAnvendt(Boolean afpTpoUpGrunnlagAnvendt) {
        this.afpTpoUpGrunnlagAnvendt = afpTpoUpGrunnlagAnvendt;
    }

    public Integer getAfpTpoUpGrunnlagOppjustert() {
        return afpTpoUpGrunnlagOppjustert;
    }

    public void setAfpTpoUpGrunnlagOppjustert(Integer afpTpoUpGrunnlagOppjustert) {
        this.afpTpoUpGrunnlagOppjustert = afpTpoUpGrunnlagOppjustert;
    }

    protected String getPoengtallListeString() {
        String plString = PoengtallListeParser.parsePoengtallListe(getPoengtallListe());

        if (!plString.equals(poengtallListeString)) {
            poengtallListeString = plString;
        }

        return poengtallListeString;
    }

    protected void setPoengtallListeString(String poengtallListeString) {
        this.poengtallListeString = poengtallListeString;
    }

    public List<Merknad> getMerknadListe() {
        if (merknadListe != null) {
            return merknadListe;
        }

        merknadListe = new ArrayList<>();

        for (Merknad merknad : super.getMerknadListe()) {
            if (merknad.getAr() == null || 0 == merknad.getAr()) {
                merknadListe.add(merknad);
            }
        }

        return merknadListe;
    }
}
