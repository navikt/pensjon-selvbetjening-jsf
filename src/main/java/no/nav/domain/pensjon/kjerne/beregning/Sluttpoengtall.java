package no.nav.domain.pensjon.kjerne.beregning;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import no.nav.domain.pensjon.AbstractMerknadDomainObject;

//@Entity
//@Table(name = "T_SLUTTPOENGTALL")
public class Sluttpoengtall extends AbstractMerknadDomainObject implements Cloneable {
    private static final long serialVersionUID = -254867726950402186L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SLUTTPOENGTALL_ID")
    private Long sluttpoengtallId;

    @Column(name = "SPT", nullable = false)
    private Double pt = 0.0;

    @OneToMany(mappedBy = "sluttpoengtall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<Poengrekke> poengrekkeList = new ArrayList<Poengrekke>();

    @Column(name = "POENG_TILLEGG", nullable = true)
    private Double poengtillegg = 0.0;

    @Column(name = "PT_EOS", nullable = true)
    private Double pt_eos = 0.0;

    @Column(name = "PT_A10", nullable = true)
    private Double pt_a10 = 0.0;

    @Column(name = "FPP_GRAD_EOS", nullable = true)
    private Double fpp_grad_eos = 0.0;

    public Sluttpoengtall(Sluttpoengtall sluttpoengtall) {
        super();
        setPt(sluttpoengtall.getPt());
        setPoengrekke(sluttpoengtall.getPoengrekke() == null ? null : new Poengrekke(sluttpoengtall.getPoengrekke()));

        if (getPoengrekke() != null) {
            getPoengrekke().setSluttpoengtall(this);
        }

        setPoengtillegg(sluttpoengtall.getPoengtillegg());
        setPt_eos(sluttpoengtall.getPt_eos());
        setPt_a10(sluttpoengtall.getPt_a10());
        setFpp_grad_eos(sluttpoengtall.getFpp_grad_eos());
        setMerknadListe(sluttpoengtall.getMerknadListe());
    }

    public Sluttpoengtall() {
        super();
    }

    public Poengrekke getPoengrekke() {
        return poengrekkeList == null || poengrekkeList.isEmpty() ? null : poengrekkeList.get(0);
    }

    public void setPoengrekke(Poengrekke poengrekke) {
        if (poengrekke == null) {
            return;
        }

        poengrekkeList.clear();
        poengrekkeList.add(poengrekke);
        poengrekke.setSluttpoengtall(this);
    }

    public Double getPoengtillegg() {
        return poengtillegg;
    }

    public void setPoengtillegg(Double poengtillegg) {
        this.poengtillegg = poengtillegg;
    }

    public Double getPt() {
        return pt;
    }

    public void setPt(Double pt) {
        this.pt = pt;
    }

    public Long getSluttpoengtallId() {
        return sluttpoengtallId;
    }

    public void setSluttpoengtallId(Long id) {
        sluttpoengtallId = id;
    }

    public Double getFpp_grad_eos() {
        return fpp_grad_eos;
    }

    public void setFpp_grad_eos(Double fpp_grad_eos) {
        this.fpp_grad_eos = fpp_grad_eos;
    }

    public Double getPt_a10() {
        return pt_a10;
    }

    public void setPt_a10(Double pt_a10) {
        this.pt_a10 = pt_a10;
    }

    public Double getPt_eos() {
        return pt_eos;
    }

    public void setPt_eos(Double pt_eos) {
        this.pt_eos = pt_eos;
    }
}
