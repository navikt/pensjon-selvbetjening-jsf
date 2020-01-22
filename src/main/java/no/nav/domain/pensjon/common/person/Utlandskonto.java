package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.Calendar;

public class Utlandskonto implements Serializable {

    private static final long serialVersionUID = 1212161094846023921L;
    private String banknavn;
    private String bankadresse1;
    private String bankadresse2;
    private String bankadresse3;
    private String swiftkode;
    private String kontonummer;
    private String valuta;
    private String land;
    private String bankkode;
    private String regSaksbehandler;
    private String regSystem;
    private Calendar regTidspunkt;

    public String getBankadresse1() {
        return bankadresse1;
    }

    public void setBankadresse1(String bankadresse1) {
        this.bankadresse1 = bankadresse1;
    }

    public String getBankadresse2() {
        return bankadresse2;
    }

    public void setBankadresse2(String bankadresse2) {
        this.bankadresse2 = bankadresse2;
    }

    public String getBankadresse3() {
        return bankadresse3;
    }

    public void setBankadresse3(String bankadresse3) {
        this.bankadresse3 = bankadresse3;
    }

    public String getBankkode() {
        return bankkode;
    }

    public void setBankkode(String bankkode) {
        this.bankkode = bankkode;
    }

    public String getBanknavn() {
        return banknavn;
    }

    public void setBanknavn(String banknavn) {
        this.banknavn = banknavn;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getRegSaksbehandler() {
        return regSaksbehandler;
    }

    public void setRegSaksbehandler(String regSaksbehandler) {
        this.regSaksbehandler = regSaksbehandler;
    }

    public String getRegSystem() {
        return regSystem;
    }

    public void setRegSystem(String regSystem) {
        this.regSystem = regSystem;
    }

    public Calendar getRegTidspunkt() {
        return regTidspunkt;
    }

    public void setRegTidspunkt(Calendar regTidspunkt) {
        this.regTidspunkt = regTidspunkt;
    }

    public String getSwiftkode() {
        return swiftkode;
    }

    public void setSwiftkode(String swiftkode) {
        this.swiftkode = swiftkode;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }
}
