package no.nav.presentation.pensjon.common.taglib.help;

import java.io.IOException;

import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.apache.myfaces.custom.div.Div;

/**
 * Component for the display area of the help text functionality. When a user clicks on a AligningHelpInPageComponent in a page,
 * this component is used to display the help text to the user, aligned with the help icon. <br>
 * The component is a subclass of HtmlPanelGrid, and all attributes from the HtmlPanelGrid component can be applied to this
 * component as well. <br>
 * This component has two optional attributes:
 * <ul>
 * <li>headingText: use this attribute to supply a heading to the display area</li>
 * <li>defaultText: use this attribute to supply a default text to be displayed in the area when the page is rendered and no help icon has been clicked.</li>
 * </ul>
 */
public class AligningHelpDisplayAreaComponent extends HtmlPanelGrid {

    public static final String COMPONENT_TYPE = "no.nav.AligningHelpDisplayAreaComponent";
    public static final String HELP_DISPLAY_AREA_ID = "no_nav_AligningHelpDisplayAreaComponent_ID";
    private static final String ATTRIBUTE_HEADING_TEXT = "headingText";
    private static final String ATTRIBUTE_DEFAULT_TEXT = "defaultText";
    private static final String ATTRIBUTE_CLOSE_BUTTON_ID = "closeButtonId";
    private static final String FACET_HEADER = "header";
    private String headingText = null;
    private String defaultText = null;
    private String closeButtonId = null;
    private String idOfEnclosingTag;
    HtmlOutputText headerTextComponent = new HtmlOutputText();
    HtmlOutputText helpTextComponent = new HtmlOutputText();

    /**
     * Encodes this component. Sets the header and default text
     */
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        if (getChildCount() == 0) {
            createChildren();
        }

        headerTextComponent.setValue(getHeadingText());
        helpTextComponent.setValue(getDefaultText());
        super.encodeBegin(context);
    }

    /**
     * Returns the client id of the div component which will hold the help text to be displayed. This information is needed by
     * the help icon components in order to include the right javascript in the page
     */
    public String getHelpTextDivId() {
        if (getChildCount() == 0) {
            createChildren();
        }

        return getChildren().get(0).getClientId(FacesContext.getCurrentInstance());
    }

    @Override
    public Object saveState(FacesContext context) {
        return new Object[]{
                super.saveState(context),
                getHeadingText(),
                getDefaultText(),
                getIdOfEnclosingTag(),
                getCloseButtonId()};
    }

    /**
     * Restores the state of this component as it was last time <code>saveState()</code> was executed.
     */
    @Override
    public void restoreState(FacesContext context, Object state) {
        Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        setHeadingText((String) values[1]);
        setDefaultText((String) values[2]);
        setIdOfEnclosingTag((String) values[3]);
        setCloseButtonId((String) values[4]);
    }

    public String getHeadingText() {
        if (getValueExpression(ATTRIBUTE_HEADING_TEXT) == null) {
            return headingText;
        }

        headingText = getValue(ATTRIBUTE_HEADING_TEXT);
        return headingText;
    }

    public void setHeadingText(String text) {
        headingText = text;
    }

    public String getDefaultText() {
        if (getValueExpression(ATTRIBUTE_DEFAULT_TEXT) == null) {
            return defaultText;
        }

        defaultText = getValue(ATTRIBUTE_DEFAULT_TEXT);
        return defaultText;
    }

    public void setDefaultText(String text) {
        defaultText = text;
    }

    public String getIdOfEnclosingTag() {
        return idOfEnclosingTag;
    }

    public void setIdOfEnclosingTag(String id) {
        idOfEnclosingTag = id;
    }

    public String getCloseButtonId() {
        if (getValueExpression(ATTRIBUTE_CLOSE_BUTTON_ID) == null) {
            return closeButtonId;
        }

        closeButtonId = getValue(ATTRIBUTE_CLOSE_BUTTON_ID);
        return closeButtonId;
    }

    public void setCloseButtonId(String id) {
        closeButtonId = id;
    }

    private void createChildren() {
        headerTextComponent = new HtmlOutputText();
        helpTextComponent = new HtmlOutputText();
        Div div = new Div();
        div.setId(HELP_DISPLAY_AREA_ID);
        getFacets().put(FACET_HEADER, headerTextComponent);
        div.getChildren().add(helpTextComponent);
        super.getChildren().add(div);
    }

    private String getValue(String name) {
        return (String) getValueExpression(name).getValue(FacesContext.getCurrentInstance().getELContext());
    }
}
