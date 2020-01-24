package no.nav.presentation.pensjon.common.taglib.help;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import no.nav.domain.pensjon.common.cm.Content;


/**
 * The AligningHelpInPageComponent renders an image (default is a small icon with a question mark). When the user clicks on the
 * icon, a help text is displayed in the page. The help text is aligned with the tag associated with this component. The id of
 * the tag surrounding the help text must be defined (the idOfTagToMove-property) in order for this component to move the help
 * text. This component requires a HelpDisplayAreaComponent on the same page as the help icon is used. The icon is rendered
 * omitted by a <code>a href</code> link in order to get focus set on the icon.
 * There are three different ways to supply the help text to be displayed
 * <ul>
 * <li>If the key attribute is given, the key is used to look up the help text in the vertical site content management system. This feature is yet not implemented</li>
 * <li>If a value binding expression is given in the value attribute, this value binding expression is resolved and the help text is the result of the value binding</li>
 * <li>The value attribute may consist of a static string which will be displayed as the help text</li> *
 * <li>Dynamic parameter values to the help text can be added by the use of &lt;f:param /&gt; tags the same way as for the &lt;h:outputFormat /&gt; tag.</li>
 * </ul>
 */
public class AligningHelpInPageComponent extends HtmlOutputLink {

    private static final String UNCHECKED = "unchecked";
    private static final String ATTRIBUTE_KEY = "key";
    private static final String ATTRIBUTE_TEXT = "text";
    private static final String ATTRIBUTE_URL = "url";
    private static final String ATTRIBUTE_ALT = "alt";
    private static final String ATTRIBUTE_ID_TO_FOCUS_ON_CLOSE = "idToFocusOnClose";
    protected final Log log = LogFactory.getLog(this.getClass());

    public static final String COMPONENT_TYPE = "no.nav.AligningHelpInPageComponent";

    // the javascript to add to the onclick of the image
    private static final String JS_ONCLICK_INPAGE_HELP = "showHelp(this, ''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}''); return false;";

    // name of file where the javascript is located
    private static final String JS_INPAGE_HELP = "helpInPage.js";

    private static final String JAVASCRIPT_ENCODED = "no.nav.presentation.pensjon.common.taglib.help.AligningHelpInPageComponent.JAVASCRIPT_ENCODED";

    private String key;
    private String text;
    private String url;
    private String alt;
    private String idToFocusOnClose;

    /**
     * Render this component, before rendering, the help icon and help text is fetched. The help text is inserted in the
     * javascript which is executed when the help icon is clicked. This component has a image icon as a child, and the child is
     * rendered by the encodeChildren method
     */
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        String value = getValueExpression(ATTRIBUTE_KEY) == null ? getText() : getKey();
        createInPageLink(HelpUtils.getHelpText(value));
        setValue("#");
        super.encodeBegin(context);
    }

    /**
     * Encodes the children of this component. A HtmlGrapicImage object is added as a child to display the help icon.
     */
    @Override
    public void encodeChildren(FacesContext context) throws IOException {
        if (isRendered()) {
            HtmlGraphicImage image = new HtmlGraphicImage();
            HelpUtils.addHelpIconUrl(context, image, getUrl(), getAlt());
            UIComponent child = image;
            // to avoid duplicate entries in datatables
            if (!isIconAdded()) {
                getChildren().add(child);
            }
        }

        super.encodeChildren(context);
    }

    /**
     * Iterates through the children of this component, if this component has a child that is of type HtmlGraphicImage, true is
     * returned, false otherwise.
     */
    @SuppressWarnings("unchecked")
    private boolean isIconAdded() {
        boolean iconAdded = false;

        for (Object element : getChildren()) {
            UIComponent child = (UIComponent) element;

            if (child instanceof HtmlGraphicImage) {
                iconAdded = true;
            }
        }

        return iconAdded;
    }

    /**
     * Create the onclick event to happen when the user clicks on the icon
     *
     * @param helpContent the Content to display, if null, the text is fetched from local attribute values
     */
    private void createInPageLink(Content helpContent) {
        String helpText = helpContent == null ? "" : helpContent.getText();
        helpText = formatHelpText(helpText);
        AligningHelpDisplayAreaComponent component = findHelpDisplayAreaComponent();
        configureJavaScriptToAlignHelpDisplayArea(helpText, component);
    }

    /**
     * Configures the JavaScript required to align the help text display area with the help icon.
     */
    private void configureJavaScriptToAlignHelpDisplayArea(String helpText, AligningHelpDisplayAreaComponent helpDisplayArea) {
        String displayAreaId = null;
        String idOfTagToMove = null;
        String closeButtonId = null;

        if (helpDisplayArea != null) {
            // The id of the display area is required for the getElementById() Javascript function
            displayAreaId = helpDisplayArea.getHelpTextDivId();
            idOfTagToMove = helpDisplayArea.getIdOfEnclosingTag();
            closeButtonId = helpDisplayArea.getCloseButtonId();
        }

        boolean inputIsValid = validateInput(displayAreaId, idOfTagToMove);

        if (inputIsValid) {
            setOnclick(MessageFormat.format(JS_ONCLICK_INPAGE_HELP, displayAreaId, idOfTagToMove, helpText, closeButtonId, getIdToFocusOnClose()));
        }
    }

    protected boolean validateInput(String displayAreaId, String idOfTagToMove) {
        boolean isValid = true;

        if (displayAreaId == null) {
            isValid = false;

            if (log.isWarnEnabled()) {
                log.warn("The id of the display area was not found, make sure there is a display area in the page.");
            }
        }

        if (idOfTagToMove == null) {
            isValid = false;

            if (log.isWarnEnabled()) {
                log.warn("The idOfTagToMove property was not set on the helpDisplayArea tag, make sure it is set.");
            }
        }

        return isValid;
    }

    /**
     * If this component has one or several UIParameter in its list of children, the parameters in the UIParameter objects are
     * extracted, and formatted into the pattern
     *
     * @param pattern The pattern where the parameters should be applied
     * @return the formatted string with parameters inserted.
     */
    @SuppressWarnings(UNCHECKED)
    private String formatHelpText(String pattern) {
        List argsList = new ArrayList();
        List<UIComponent> children = getChildren();
        List<UIParameter> removeParametersList = new ArrayList<>();

        for (UIComponent child : children) {
            if (child instanceof UIParameter) {
                UIParameter param = (UIParameter) child;
                removeParametersList.add(param);

                if (param.getName() == null) {
                    param.setName(param.getId());
                }

                Object paramValue = param.getValue();
                String value = paramValue == null ? "null" : paramValue.toString();
                argsList.add(value);
            }
        }

        children.removeAll(removeParametersList);

        if (argsList.isEmpty()) {
            return pattern;
        }

        Object[] args = argsList.toArray(new Object[argsList.size()]);
        MessageFormat format = new MessageFormat(pattern, LocaleContextHolder.getLocale());

        try {
            return format.format(args);
        } catch (IllegalArgumentException iae) {
            if (log.isInfoEnabled()) {
                log.info("Error formatting message of component " + getClientId(FacesContext.getCurrentInstance()));
            }
            return "";
        }
    }

    /**
     * Finds the client id for where to place the help information.
     * The id is used in the javascript triggered when the help icon is clicked.
     */
    private AligningHelpDisplayAreaComponent findHelpDisplayAreaComponent() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        UIViewRoot viewRoot = ctx.getViewRoot();
        return (AligningHelpDisplayAreaComponent) findHelpDisplayAreaComponent(viewRoot.getChildren());
    }

    UIComponent findHelpDisplayAreaComponent(List<UIComponent> uiComponentsList) {
        UIComponent component = null;

        for (UIComponent nextComponent : uiComponentsList) {
            if (nextComponent instanceof AligningHelpDisplayAreaComponent) {
                component = nextComponent;
                break;
            }

            component = findHelpDisplayAreaComponent(nextComponent.getChildren());

            if (component != null) {
                break;
            }
        }

        return component;
    }

    @Override
    public Object saveState(FacesContext context) {
        return new Object[]{
                super.saveState(context),
                getKey(),
                getText(),
                getUrl(),
                getAlt(),
                getIdToFocusOnClose()};
    }

    /**
     * Restores the state of this component as it was last time <code>saveState()</code> was executed.
     */
    @Override
    public void restoreState(FacesContext context, Object state) {
        Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        setKey((String) values[1]);
        setText((String) values[2]);
        setUrl((String) values[3]);
        setAlt((String) values[4]);
        setIdToFocusOnClose((String) values[5]);
    }

    /**
     * Returns the value of the tag attribute named "key". If there exists a value binding for the attribute, the value binding
     * is resolved to get the correct value
     */
    public String getKey() {
        if (getValueExpression(ATTRIBUTE_KEY) == null) {
            return key;
        }

        key = (String) getValueExpression(ATTRIBUTE_KEY).getValue(FacesContext.getCurrentInstance().getELContext());
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the value of the tag attribute named "text". If there exists a value binding for the attribute, the value binding
     * is resolved to get the correct value
     */
    public String getText() {
        if (getValueExpression(ATTRIBUTE_TEXT) == null) {
            return text;
        }

        text = (String) getValueExpression(ATTRIBUTE_TEXT).getValue(FacesContext.getCurrentInstance().getELContext());
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns the value of the tag attribute named "url". If there exists a value binding for the attribute, the value binding
     * is resolved to get the correct value
     */
    public String getUrl() {
        if (getValueExpression(ATTRIBUTE_URL) == null) {
            return url;
        }

        url = (String) getValueExpression(ATTRIBUTE_URL).getValue(FacesContext.getCurrentInstance().getELContext());
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        if (getValueExpression(ATTRIBUTE_ALT) == null) {
            return alt;
        }

        alt = (String) getValueExpression(ATTRIBUTE_ALT).getValue(FacesContext.getCurrentInstance().getELContext());
        return alt;
    }

    /**
     * The alt attribute holds the alternative text to show when the image cannot be displayed
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * Returns the value of the tag attribute named "idToFocusOnClose". If there exists a value binding for the attribute, the
     * value binding is resolved to get the correct value
     */
    public String getIdToFocusOnClose() {
        if (getValueExpression(ATTRIBUTE_ID_TO_FOCUS_ON_CLOSE) == null) {
            return idToFocusOnClose;
        }

        idToFocusOnClose = (String) getValueExpression(ATTRIBUTE_ID_TO_FOCUS_ON_CLOSE).getValue(FacesContext.getCurrentInstance().getELContext());
        return idToFocusOnClose;
    }

    public void setIdToFocusOnClose(String idToFocusOnClose) {
        this.idToFocusOnClose = idToFocusOnClose;
    }
}
