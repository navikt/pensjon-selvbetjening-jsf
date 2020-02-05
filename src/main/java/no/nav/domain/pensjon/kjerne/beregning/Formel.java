package no.nav.domain.pensjon.kjerne.beregning;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

public class Formel implements Serializable {

    private static final long serialVersionUID = 4189934296931681482L;
    private String navn;
    private Number resultat;
    private String notasjon;
    private String innhold;
    private List<Formel> subFormulaList;

    public Formel() {
        this.subFormulaList = new ArrayList<>();
    }

    public void setSubFormulaList(List<Formel> subFormulaList) {
        this.subFormulaList = new ArrayList<>(subFormulaList);
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Number getResultat() {
        return resultat;
    }

    public void setResultat(Number resultat) {
        this.resultat = resultat;
    }

    public String getNotasjon() {
        return notasjon;
    }

    public void setNotasjon(String notasjon) {
        this.notasjon = notasjon;
    }

    public List<Formel> getSubFormulaList() {
        return new ArrayList<>(subFormulaList);
    }

    public void addSubFormula(Formel formel) {
        this.subFormulaList.add(formel);
    }

    public String getInnhold() {
        return innhold;
    }

    public void setInnhold(String innhold) {
        this.innhold = innhold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Formel formel = (Formel) o;
        return Objects.equals(navn, formel.navn) &&
                Objects.equals(resultat, formel.resultat) &&
                Objects.equals(notasjon, formel.notasjon) &&
                Objects.equals(innhold, formel.innhold) &&
                Objects.equals(subFormulaList, formel.subFormulaList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(navn, resultat, notasjon, innhold, subFormulaList);
    }

    @Override
    public String toString() {
        return toTreeString(0, Integer.MAX_VALUE);
    }

    public String toString(int maxLevel) {
        return toTreeString(0, maxLevel);
    }

    private String toTreeString(int level, int maxLevel) {
        StringBuilder builder = new StringBuilder();

        builder.append(StringUtils.repeat(' ', level * 2))
                .append("Formelnavn: '").append(navn)
                .append(" level: ").append(level)
                .append(" resultat: ").append(resultat == null ? "---" : resultat)
                .append(" ant.subFormler: ").append(subFormulaList.size())
                .append(" hash: ").append(this.hashCode()).append("\n");
        builder.append(StringUtils.repeat(' ', level * 2)).append("    notasjon:\t'").append(notasjon).append("'\n");
        builder.append(StringUtils.repeat(' ', level * 2)).append("    innhold: \t'").append(innhold).append("'").append((resultat == null) ? "" : (" = " + resultat)).append("\n");

        if (level < maxLevel) {
            for (Formel formel : subFormulaList) {
                builder.append(formel.toTreeString(level + 1, maxLevel));
            }
        }
        return builder.toString();
    }
}
