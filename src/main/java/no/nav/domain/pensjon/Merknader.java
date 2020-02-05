package no.nav.domain.pensjon;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import static no.nav.domain.pensjon.kjerne.Merknad.aMerknad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import no.nav.domain.pensjon.kjerne.Merknad;

/**
 * All {@link Merknad}s listed for a row in a table
 */
public class Merknader implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String SPLITTER_OF_VALUES = "{$}";
    private static final String SPLITTER_OF_VALUES_REGEX = Pattern.quote(SPLITTER_OF_VALUES);
    private static final String SPLITTER_OF_MERKNADER = "{#}";
    private static final String SPLITTER_OF_MERKNADER_REGEX = Pattern.quote(SPLITTER_OF_MERKNADER);
    private static final int ARGUMENTS = 1;
    private static final int AR = 2;

    private final Set<Merknad> merknadSet = new HashSet<>(1);

    public void add(Merknad merknad) {
        if (merknad != null) {
            merknadSet.add(merknad);
        }
    }

    public void addAll(List<Merknad> merknadListe) {
        if (merknadListe != null) {
            merknadSet.addAll(merknadListe);
        }
    }

    public boolean isEmpty() {
        return merknadSet.isEmpty();
    }

    String createStringOfMerknader() {
        StringBuilder merknadString = new StringBuilder();
        Iterator<Merknad> merknadIterator = merknadSet.iterator();

        while (merknadIterator.hasNext()) {
            merknadString.append(merknadIterator.next().asString());

            if (merknadIterator.hasNext()) {
                merknadString.append(Merknader.SPLITTER_OF_MERKNADER);
            }
        }

        return merknadString.toString();
    }

    private void createMerknadListFrom(String string) {
        String[] merknader = isNotEmpty(string) ? string.split(SPLITTER_OF_MERKNADER_REGEX) : new String[]{};

        for (String stringForMerknad : merknader) {
            add(initFrom(stringForMerknad));
        }
    }

    public Merknader deepCopy() {
        Merknader merknader = new Merknader();
        merknader.merknadSet.addAll(merknadSet);
        return merknader;
    }

    public void clear() {
        merknadSet.clear();
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Merknader && new EqualsBuilder().append(merknadSet, ((Merknader) o).merknadSet).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(merknadSet).toHashCode();
    }

    public List<Merknad> getMerknadListe() {
        return new ArrayList<>(merknadSet);
    }

    protected static Object createFrom(String string) {
        Merknader merknader = new Merknader();
        merknader.createMerknadListFrom(string);

        return merknader;
    }

    static Merknad initFrom(String stringForMerknad) {
        String[] merknadValues = stringForMerknad.split(SPLITTER_OF_VALUES_REGEX);
        String kode = merknadValues[0];
        String argumentListeString = getArguments(merknadValues);
        Integer ar = getAr(merknadValues);
        return aMerknad().withKode(kode).withArgumentListeString(argumentListeString).withAr(ar).build();
    }

    private static String getArguments(String[] merknadValues) {
        return contains(merknadValues, ARGUMENTS) ? merknadValues[ARGUMENTS] : "";
    }

    private static Integer getAr(String[] merknadValues) {
        return contains(merknadValues, AR) ? Integer.valueOf(merknadValues[AR]) : null;
    }

    private static boolean contains(String[] merknadValues, Integer dataIndex) {
        return merknadValues.length > dataIndex && merknadValues[dataIndex] != null;
    }
}
