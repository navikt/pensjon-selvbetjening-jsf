package no.nav.presentation.pensjon.pselv.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.myfaces.component.html.ext.HtmlSelectOneMenu;

/**
 * Custom component for populating dropdown lists with elements from a specific codestable class. The codetable items are
 * retrieved from the codes table manager, and mapped into select item elements which are populated into the drop down list. <br>
 * The component is a subcomponent of the JSF (MyFaces Tomahawk) built in drop down list component, and inherits all the
 * attributes provided by the super type component.
 */
public class SplitTextItemSelectOneMenu extends HtmlSelectOneMenu {

    private String splitText;
    private static final String ATTRIBUTE_SPLITTEXT = "splitText";

    /**
     * Encodes this component, the codestableitems which should be displayed in the dropdown list are populated as SelectItems,
     * and are added as children of this component.
     */
    @Override
    public void encodeBegin(FacesContext facesContext) throws IOException {
        buildSelectItems();
        super.encodeBegin(facesContext);
    }

    @Override
    public Object saveState(FacesContext context) {
        return new Object[]{
                super.saveState(context),
                getSplitText()};
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        setSplitText((String) values[1]);
    }

    /**
     * Retrieves the splitTextitems from the resource and mappes these into SelectItems which are displayed in the drop down
     * list. The select items are added as children of this component.
     */
    private void buildSelectItems() {
        if (getChildCount() > 0) {
            return;
        }

        if (getSplitText() == null || "".equals(getSplitText())) {
            return;
        }

        List<SelectItem> selectItems = new ArrayList<>();
        String[] itemList = splitText.split(";");

        for (int index = 0; index < itemList.length; index++) {
            SelectItem item = new SelectItem();
            item.setDescription(itemList[index]);
            item.setLabel(itemList[index]);
            item.setValue(itemList[index + 1]);
            selectItems.add(item);
            index++;
        }

        UISelectItems uiSelectItems = new UISelectItems();
        uiSelectItems.setValue(selectItems);
        getChildren().add(uiSelectItems);
    }

    public String getSplitText() {
        if (splitText == null) {
            splitText = (String) getValueBinding(ATTRIBUTE_SPLITTEXT).getValue(FacesContext.getCurrentInstance());
        }

        return splitText;
    }

    public void setSplitText(String splitText) {
        this.splitText = splitText;
    }
}
