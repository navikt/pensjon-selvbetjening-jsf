package no.nav.domain.pensjon.kjerne;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import no.nav.domain.pensjon.Merknader;

/**
 * This data type contains three values. A "kode", a "argumentListeString" and a "ar". This can be used to give further descriptions of initialized domains in "pensjon" and can
 * be saved to the database as a string mapping the field {@link Merknader} to a column in the database and using the
 * custom hibernate user type no.nav.domain.pensjon.MerknaderUserType.
 */
public class Merknad implements Serializable {

    public static final String LINE_SEPARATOR = "¤";
    private static final long serialVersionUID = -1182458196271710306L;
    private Integer ar;
    private String argumentListeString;
    private String kode;

    public Merknad() {
    }

    public Merknad(Merknad src) {
        setKode(src.getKode());
        setArgumentListeString(src.getArgumentListeString());
        setAr(src.getAr());
    }

    public Merknad(String merknadCode, Object... arguments) {
        setKode(merknadCode);
        List<String> argumentList = new ArrayList<>();

        for (Object argument : arguments) {
            argumentList.add(argument.toString());
        }

        setArgumentListe(argumentList);
    }

    public String asString() {
        StringBuilder builder = new StringBuilder(kode);
        builder.append(Merknader.SPLITTER_OF_VALUES);
        builder.append(argumentListeString);
        builder.append(Merknader.SPLITTER_OF_VALUES);

        if (ar != null) {
            builder.append(ar);
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Merknad && new EqualsBuilder()
                .append(kode, ((Merknad) o).kode)
                .append(argumentListeString, (((Merknad) o).argumentListeString))
                .append(ar, ((Merknad) o).ar)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(kode).append(argumentListeString).append(ar).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append(kode)
                .append(argumentListeString)
                .append(ar)
                .toString();
    }

    private void createStringFromArgumentListe(List<String> argumentListe) {
        StringBuilder builder = new StringBuilder();
        Iterator<String> argIterator = argumentListe.iterator();

        if (argIterator.hasNext()) {
            builder.append(argIterator.next());
        }

        while (argIterator.hasNext()) {
            builder.append(LINE_SEPARATOR).append(argIterator.next());
        }

        setArgumentListeString(builder.toString());
    }

    private String trim(String string) {
        return string != null ? string.trim() : null;
    }

    public void setKode(String kode) {
        this.kode = trim(kode);
    }

    public String getKode() {
        return kode;
    }

    public List<String> getArgumentListe() {
        String listAsString = getArgumentListeString();

        if (listAsString != null) {
            ArrayList<String> argumentListe = new ArrayList<>();

            if (!"".equals(listAsString)) {
                Collections.addAll(argumentListe, listAsString.split(LINE_SEPARATOR));
            }

            return argumentListe;
        }

        return new ArrayList<>();
    }

    public void setArgumentListe(List<String> argumentListe) {
        ArrayList<String> argumentListeParameter = new ArrayList<>();

        if (argumentListe != null) {
            argumentListeParameter = new ArrayList<>(argumentListe.size());

            for (String str : argumentListe) {
                if (str != null) { // Empty Strings are ok, but not null values
                    argumentListeParameter.add(str);
                }
            }
        }

        createStringFromArgumentListe(argumentListeParameter);
    }

    public String getArgumentListeString() {
        return argumentListeString;
    }

    public void setArgumentListeString(String argumentListeString) {
        this.argumentListeString = trim(argumentListeString);
    }

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public static MerknadBuilder aMerknad() {
        return new MerknadBuilder();
    }

    public static class MerknadBuilder {

        private Integer ar;
        private String argumentListeString;
        private String kode;
        private final List<String> argumentListe = new ArrayList<>();

        public Merknad build() {
            Merknad merknad = new Merknad();
            merknad.setAr(ar);
            merknad.setKode(kode);

            if (argumentListeString != null && !argumentListeString.equals("")) {
                merknad.setArgumentListeString(argumentListeString);
            } else {
                merknad.setArgumentListe(argumentListe);
            }

            return merknad;
        }

        /**
         * Note that this string will be used as argumentListeString and not the individual arguments to generate the value for argumentListeString
         */
        public MerknadBuilder withArgumentListeString(String argumentListeString) {
            this.argumentListeString = argumentListeString;
            return this;
        }

        public MerknadBuilder withKode(String kode) {
            this.kode = kode;
            return this;
        }

        public MerknadBuilder withAr(Integer ar) {
            this.ar = ar;
            return this;
        }

        /**
         * Note that argumentListeString and not these individual arguments are used to set the value for argumentListeString if it is provided
         */
        public MerknadBuilder withArgument(String argument) {
            argumentListe.add(argument);
            return this;
        }
    }
}
