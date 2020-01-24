package no.nav.presentation.pensjon.common.taglib.help;

import java.io.IOException;
import java.text.MessageFormat;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import no.nav.domain.pensjon.common.cm.Content;
import no.nav.presentation.pensjon.common.jsf.renderkit.JsfResourceHandler;

/**
 * The helpPopUpComponent renders an image (default is a small icon with a question mark), when the user clicks on the icon, a
 * help text is displayed in a separate window. This component can also be used to render the link as a text and not as an icon.
 * There are three different ways to supply the help text to be displayed
 * <ul>
 * <li>If the key attribute is given, the key is used to look up the help text in the vertical site content management system.</li>
 * <li>If a value binding expression is given in the value attribute, this value binding expression is resolved and the help text is the result of the value binding</li>
 * <li>The value attribute may consist of a static string which will be displayed as the help text</li>
 * </ul>
 * This component uses the javascript located in no.stelvio.presentation.jsf.taglib.help.resources.helpPopup.js
 */
public class HelpPopUpComponent extends HtmlOutputLink {

    public static final String COMPONENT_TYPE = "no.nav.HelpPopUpComponent";
    protected final Log log = LogFactory.getLog(this.getClass());

    // name of target used for popup window
    private static final String HELP_TARGET = "helpTarget";

    // name of file where the javascript is located
    private static final String JS_POPUP_HELP = "helpPopup.js";

    private static final String JS_ONCLICK_POPUP_HELP = "javascript:openWindow(''{0}'', ''{1}'', {2}, {3});";
    private static final String JS_ONCLICK_POPUP_HELP_MOCK = "javascript:openWindowWithMock(''{0}'', ''{1}'', {2}, {3}, ''{4}'');";
    private static final String JAVASCRIPT_ENCODED = "no.nav.presentation.pensjon.common.taglib.help.HelpPopUpComponent.JAVASCRIPT_ENCODED";
    private static final String DEFAULT_WINDOW_WIDTH = "800";
    private static final String DEFAULT_WINDOW_HEIGHT = "540";

    // Attribute names
    private static final String ATTRIBUTE_HELP_URL = "helpUrl";
    private static final String ATTRIBUTE_IMAGE_URL = "imageUrl";
    private static final String ATTRIBUTE_TEXT_ONLY = "textOnly";
    private static final String ATTRIBUTE_KEY = "key";
    private static final String ATTRIBUTE_LINK_TEXT = "linkText";
    private static final String ATTRIBUTE_ALT = "alt";
    private static final String ATTRIBUTE_TABINDEX = "tabindex";

    // attributes for this component
    private String key;
    private String helpUrl;
    private String imageUrl;
    private boolean textOnly;
    private String linkText;
    private String alt;
    private String tabindex;

    /**
     * Render this component, before rendering, the help icon and url are fetched. A popup window is displayed when the
     * icon/text link is clicked.
     */
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        if (isRendered()) {
            createPopupLink(HelpUtils.getHelpContent(context, getKey()));
        }

        super.encodeBegin(context);
    }

    /**
     * Encodes the children of this component. This component has an image or an output text as a child.
     */
    @Override
    public void encodeChildren(FacesContext context) throws IOException {
        if (isRendered()) {
            UIComponent child = newChild(context);
            // to avoid duplicate entries in datatables
            if (getChildCount() == 0) {
                getChildren().add(child);
            }
        }

        super.encodeChildren(context);
    }

    /**
     * Gets the state of the instance of this component as a <code>Serializable</code> Object.
     */
    @Override
    public Object saveState(FacesContext context) {
        return new Object[]{
                super.saveState(context),
                getKey(),
                getHelpUrl(),
                getImageUrl(),
                isTextOnly(),
                getLinkText(),
                getAlt(),
                getTabindex()};
    }

    /**
     * Restores the state of the instance of tis component, the component is restored from the entries in the state Object
     */
    @Override
    public void restoreState(FacesContext context, Object state) {
        Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        setKey((String) values[1]);
        setHelpUrl((String) values[2]);
        setImageUrl((String) values[3]);

        if (values[4] != null) {
            setTextOnly((Boolean) values[4]);
        }

        setLinkText((String) values[5]);
        setAlt((String) values[6]);
        setTabindex((String) values[7]);
    }

    /**
     * Creates the popup link. The url is either fetched from a Content object or from the values given in the tag.
     */
    private void createPopupLink(Content helpContent) {
        String url = helpContent == null ? getHelpUrl() : helpContent.getUrl();

        if (url == null) {
            createMock(FacesContext.getCurrentInstance());
            // the help window is mocked, return
            return;
        }

        addPopupScriptToPage();
        setValue(url);
        setTarget(HELP_TARGET);

        setOnclick(MessageFormat.format(
                JS_ONCLICK_POPUP_HELP,
                url,
                HELP_TARGET, DEFAULT_WINDOW_WIDTH,
                DEFAULT_WINDOW_HEIGHT));
    }

    /**
     * Mocks the display of help information.
     * In the future, the helpinformation will be retrieved from the content management system.
     */
    private void createMock(FacesContext context) {
        JsfResourceHandler resourceHandler = new JsfResourceHandler(HelpPopUpComponent.class, "helpPopUpMock.xhtml");
        AddResource addResource = AddResourceFactory.getInstance(context);
        String mockUrl = addResource.getResourceUri(context, resourceHandler, true);
        addPopupScriptToPage();
        setValue(mockUrl);
        setTarget(HELP_TARGET);

        setOnclick(MessageFormat.format(
                JS_ONCLICK_POPUP_HELP_MOCK,
                mockUrl,
                HELP_TARGET,
                DEFAULT_WINDOW_WIDTH,
                DEFAULT_WINDOW_HEIGHT,
                "[" + getKey() + "]"));
    }

    /**
     * A resource handler is used to queue the javascript for inclusion in the page when it is rendered. When the response is
     * written, the extension filter will add the javascript to the header of the page.
     */
    private void addPopupScriptToPage() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getExternalContext().getRequestMap().containsKey(JAVASCRIPT_ENCODED)) {
            return;
        }

        JsfResourceHandler resourceHandler = new JsfResourceHandler(this.getClass(), JS_POPUP_HELP);
        AddResource addResource = AddResourceFactory.getInstance(context);
        addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, resourceHandler, true);
        context.getExternalContext().getRequestMap().put(JAVASCRIPT_ENCODED, Boolean.TRUE);
    }

    public String getKey() {
        if (getValueExpression(ATTRIBUTE_KEY) == null) {
            return key;
        }

        key = getElContextValue(ATTRIBUTE_KEY);
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHelpUrl() {
        if (getValueExpression(ATTRIBUTE_HELP_URL) == null) {
            return helpUrl;
        }

        helpUrl = getElContextValue(ATTRIBUTE_HELP_URL);
        return helpUrl;
    }

    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    public String getImageUrl() {
        if (getValueExpression(ATTRIBUTE_IMAGE_URL) == null) {
            return imageUrl;
        }

        imageUrl = getElContextValue(ATTRIBUTE_IMAGE_URL);
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLinkText() {
        if (getValueExpression(ATTRIBUTE_LINK_TEXT) == null) {
            return linkText;
        }

        linkText = getElContextValue(ATTRIBUTE_LINK_TEXT);
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public boolean isTextOnly() {
        if (getValueExpression(ATTRIBUTE_TEXT_ONLY) == null) {
            return textOnly;
        }

        textOnly = (Boolean) getValueExpression(ATTRIBUTE_TEXT_ONLY).getValue(FacesContext.getCurrentInstance().getELContext());
        return textOnly;
    }

    public void setTextOnly(boolean textOnly) {
        this.textOnly = textOnly;
    }

    public String getAlt() {
        if (getValueExpression(ATTRIBUTE_ALT) == null) {
            return alt;
        }

        alt = getElContextValue(ATTRIBUTE_ALT);
        return alt;
    }

    /**
     * The alt attribute holds the alternative text to show when the image cannot be displayed
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Override
    public String getTabindex() {
        if (getValueExpression(ATTRIBUTE_TABINDEX) == null) {
            return tabindex;
        }

        tabindex = getElContextValue(ATTRIBUTE_TABINDEX);
        return tabindex;
    }

    /**
     * The tabindex attribute holds the tab index value for focus sequencing
     */
    @Override
    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }

    private UIComponent newChild(FacesContext context) {
        if (isTextOnly()) {
            HtmlOutputText text = new HtmlOutputText();
            text.setValue(getLinkText());
            return text;
        }

        HtmlGraphicImage image = new HtmlGraphicImage();
        HelpUtils.addHelpIconUrl(context, image, getImageUrl(), getAlt());
        return image;
    }

    private String getElContextValue(String attributeKey) {
        return (String) getValueExpression(attributeKey).getValue(FacesContext.getCurrentInstance().getELContext());
    }
}
