package no.nav.presentation.pensjon.pselv.jsf.html5;

import static no.nav.domain.pensjon.common.collections.Collections.mapOf;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Html5Attributes {

    /**
     * Valid attributes for every HTML5 tag.
     */
    public static final Set<String> GLOBAL = immutableSetOf(
            "role",
            "data-dismiss");

    /**
     * Valid attributes for the respective HTML5 tags.
     */
    public static final Map<String, Set<String>> MAP = Collections.unmodifiableMap(
            mapOf("input", immutableSetOf("placeholder"),
                    "textarea", immutableSetOf("placeholder")));

    private static Set<String> immutableSetOf(String... values) {
        return Collections.unmodifiableSet(Stream.of(values).collect(Collectors.toSet()));
    }

    private Html5Attributes() {
    }
}
