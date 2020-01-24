package no.nav.presentation.pensjon.pselv.jsf.html5;

import java.io.Writer;

import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitWrapper;

public class Html5RenderKit extends RenderKitWrapper {

    private final RenderKit wrapped;

    public Html5RenderKit(final RenderKit wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public RenderKit getWrapped() {
        return wrapped;
    }

    @Override
    public ResponseWriter createResponseWriter(final Writer writer, final String contentTypeList, final String characterEncoding) {
        return new Html5ResponseWriter(super.createResponseWriter(writer, contentTypeList, characterEncoding));
    }
}
