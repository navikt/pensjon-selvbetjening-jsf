package no.nav.presentation.pensjon.pselv.jsf.html5;

import java.util.Iterator;

import javax.faces.context.FacesContext;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;

public class Html5RenderKitFactory extends RenderKitFactory {

    private final RenderKitFactory wrapped;

    public Html5RenderKitFactory(RenderKitFactory wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public RenderKitFactory getWrapped() {
        return wrapped;
    }

    @Override
    public void addRenderKit(final String renderKitId, final RenderKit renderKit) {
        wrapped.addRenderKit(renderKitId, renderKit);
    }

    @Override
    public RenderKit getRenderKit(final FacesContext facesContext, final String renderKitId) {
        final RenderKit renderKit = wrapped.getRenderKit(facesContext, renderKitId);

        if (HTML_BASIC_RENDER_KIT.equals(renderKitId)) {
            return new Html5RenderKit(renderKit);
        } else {
            return renderKit;
        }
    }

    @Override
    public Iterator<String> getRenderKitIds() {
        return wrapped.getRenderKitIds();
    }
}
