package no.nav.presentation.pensjon.pselv.jsf.html5;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;
import javax.faces.context.ResponseWriterWrapper;

public class Html5ResponseWriter extends ResponseWriterWrapper {

    private final ResponseWriter wrapped;

    public Html5ResponseWriter(final ResponseWriter wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ResponseWriter getWrapped() {
        return wrapped;
    }

    @Override
    public ResponseWriter cloneWithWriter(final Writer writer) {
        return new Html5ResponseWriter(super.cloneWithWriter(writer));
    }

    /**
     * Override to allow HTML5 attributes to be written for standard JSF components.
     */
    @Override
    public void startElement(final String name, final UIComponent component) throws IOException {
        super.startElement(name, component);

        if (component == null) {
            return;
        }

        final Map<String, Object> componentAttributes = component.getAttributes();
        writeHtml5AttributesIfPresent(Html5Attributes.GLOBAL, componentAttributes);
        writeHtml5AttributesIfPresent(determinePossibleAttributes(name), componentAttributes);
    }

    /**
     * Determine which HTML5 attributes are valid for the component.
     */
    private static Set<String> determinePossibleAttributes(final String name) {
        return Html5Attributes.MAP.containsKey(name) ? Html5Attributes.MAP.get(name) : Collections.emptySet();
    }

    private void writeHtml5AttributesIfPresent(final Set<String> possibleAttributes, final Map<String, Object> componentAttributes) throws IOException {
        for (final String attributeName : possibleAttributes) {
            Object attribute = componentAttributes.get(attributeName);

            if (attribute == null) {
                continue;
            }

            super.writeAttribute(attributeName, attribute, null);
        }
    }
}
