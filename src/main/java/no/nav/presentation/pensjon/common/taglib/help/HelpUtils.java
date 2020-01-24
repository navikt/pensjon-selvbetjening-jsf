package no.nav.presentation.pensjon.common.taglib.help;

import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import no.stelvio.presentation.binding.context.MessageContextUtil;

import no.nav.domain.pensjon.common.cm.Content;
import no.nav.presentation.pensjon.common.jsf.renderkit.JsfResourceHandler;

public final class HelpUtils {

    public static final String HELP = "helpurl";
    private static final String FILE_SEPARATOR = "/";
    private static final String DEFAULT_IMAGE_URL = "hjelp.gif";

    /**
     * Adds default help icon url to the page. If an url is specified in the tag, that url is used, if not the image url is set
     * to the default image residing in the resource directory of this class.
     */
    static HtmlGraphicImage addHelpIconUrl(FacesContext context, HtmlGraphicImage image, String imageUrl, String alt) {
        if (!StringUtils.isEmpty(imageUrl)) {
            image.setUrl(imageUrl);
            return image;
        }

        JsfResourceHandler resourceHandler = new JsfResourceHandler(HelpPopUpComponent.class, DEFAULT_IMAGE_URL);
        AddResource addResource = AddResourceFactory.getInstance(context);
        String uri = addResource.getResourceUri(context, resourceHandler, false);
        image.setUrl(uri);
        image.setStyle("border-style:none");
        image.setAlt(alt);
        return image;
    }

    /**
     * Retrieves the help content which is going to be displayed for this component.
     * Reads folder and file name from the resources file.
     */
    static Content getHelpContent(FacesContext context, String key) {
        String contextPath = MessageContextUtil.getMessage(HELP);
        Content content = new Content();
        content.setContentKey(key);
        String helpFile = MessageContextUtil.getMessage(key, null);
        content.setUrl(contextPath + FILE_SEPARATOR + helpFile);
        return content;
    }

    static Content getHelpText(String key) {
        Content content = new Content();
        content.setText(key);
        return content;
    }

    private HelpUtils() {
    }
}
