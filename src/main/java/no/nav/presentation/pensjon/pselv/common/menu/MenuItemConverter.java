package no.nav.presentation.pensjon.pselv.common.menu;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Converts XML to menu items.
 */
public class MenuItemConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return MenuItem.class.equals(type);
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        MenuItem item = new MenuItem();

        while (reader.hasMoreChildren()) {
            unmarshal(item, reader, context);
        }

        return item;
    }

    private static void unmarshal(MenuItem item, HierarchicalStreamReader reader, UnmarshallingContext context) {
        reader.moveDown();
        populateItem(item, reader, context);
        reader.moveUp();
    }

    private static void populateItem(MenuItem item, HierarchicalStreamReader reader, UnmarshallingContext context) {
        String nodeName = reader.getNodeName();

        if ("label".equalsIgnoreCase(nodeName)) {
            item.setLabel(reader.getValue());
            return;
        }

        if ("action".equalsIgnoreCase(nodeName)) {
            item.setAction(reader.getValue());
            return;
        }

        if ("roles".equalsIgnoreCase(nodeName)) {
            item.setRoles(getRoles(reader));
            return;
        }

        if ("children".equalsIgnoreCase(nodeName)) {
            item.setChildren(getChildren(item, reader, context));
        }
    }

    private static List<String> getRoles(HierarchicalStreamReader reader) {
        List<String> roles = new ArrayList<>();

        while (reader.hasMoreChildren()) {
            reader.moveDown();
            roles.add(reader.getValue());
            reader.moveUp();
        }

        return roles;
    }

    private static List<MenuItem> getChildren(MenuItem item, HierarchicalStreamReader reader, UnmarshallingContext context) {
        List<MenuItem> children = new ArrayList<>();

        while (reader.hasMoreChildren()) {
            reader.moveDown();
            children.add(getChild(item, reader, context));
            reader.moveUp();
        }

        return children;
    }

    private static MenuItem getChild(MenuItem item, HierarchicalStreamReader reader, UnmarshallingContext context) {
        MenuItemConverter converter = new MenuItemConverter();
        MenuItem child = (MenuItem) converter.unmarshal(reader, context);
        child.setParent(item);
        return child;
    }
}
