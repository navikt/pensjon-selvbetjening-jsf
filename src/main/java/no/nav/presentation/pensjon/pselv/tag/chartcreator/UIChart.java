/*
 * Copyright 2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package no.nav.presentation.pensjon.pselv.tag.chartcreator;

import java.io.IOException;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class UIChart extends UIComponentBase {

    public static final String COMPONENT_TYPE = "net.sf.jsfcomp.chartcreator.UIChart";
    public static final String COMPONENT_FAMILY = "net.sf.jsfcomp.chartcreator";
    protected static final String CHART_REQUEST = "chart.chartcreatorrequest";
    private Object datasource;
    private Integer width;
    private Integer height;
    private Integer alpha;
    private Integer depth;
    private Integer startAngle;
    private Double barWidth;
    private String title;
    private String type;
    private String background;
    private String foreground;
    private String xlabel;
    private String ylabel;
    private String orientation;
    private String colors;
    private Boolean is3d;
    private Boolean legend;
    private Boolean antialias;
    private Boolean outline;
    private String styleClass;
    private String alt;
    private String imgTitle;
    private String onclick;
    private String ondblclick;
    private String onmousedown;
    private String onmouseup;
    private String onmouseover;
    private String onmousemove;
    private String onmouseout;
    private String onkeypress;
    private String onkeydown;
    private String onkeyup;
    private String output;
    private String usemap;

    public UIChart() {
        super();
        setRendererType(null);
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        setChartDataAtSession(context);
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("img", this);
        writer.writeAttribute("id", getClientId(context), null);
        writer.writeAttribute("width", String.valueOf(getWidth()), null);
        writer.writeAttribute("height", String.valueOf(getHeight()), null);
        writer.writeAttribute("src", CHART_REQUEST + "?ts=" + System.currentTimeMillis() + "&id=" + getClientId(context), null);
        ChartUtils.renderPassThruImgAttributes(writer, this);
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        context.getResponseWriter().endElement("img");
    }

    @SuppressWarnings("unchecked")
    private void setChartDataAtSession(FacesContext facesContext) {
        Map session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        String clientId = getClientId(facesContext);
        ChartData data = new ChartData(this);
        session.put(clientId, data);
    }

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    /**
     * Alpha attribute for pie charts
     */
    public int getAlpha() {
        return alpha == null ? getIntegerElContextValue("alpha", 100) : alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public boolean getAntialias() {
        return antialias == null ? getBooleanElContextValue("antialias", false) : antialias;
    }

    public void setAntialias(boolean antialias) {
        this.antialias = antialias;
    }

    public String getBackground() {
        return background == null ? getElContextValue("background", "white") : background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getForeground() {
        return foreground == null ? getElContextValue("foreground", "white") : foreground;
    }

    public void setForeground(String foreground) {
        this.foreground = foreground;
    }

    public boolean getIs3d() {
        return is3d == null ? getBooleanElContextValue("is3d", true) : is3d;
    }

    public void setIs3d(boolean is3d) {
        this.is3d = is3d;
    }

    public String getColors() {
        return colors == null ? getElContextValue("colors", null) : colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public Object getDatasource() {
        if (datasource != null) {
            return datasource;
        }

        ValueExpression expression = getValueExpression("datasource");
        return expression == null ? null : expression.getValue(getFacesContext().getELContext());
    }

    public void setDatasource(Object datasource) {
        this.datasource = datasource;
    }

    public int getDepth() {
        return depth == null ? getIntegerElContextValue("depth", 15) : depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getWidth() {
        return width == null ? getIntegerElContextValue("width", 400) : width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getBarWidth() {
        if (barWidth != null) {
            return barWidth;
        }

        ValueExpression expression = getValueExpression("barWidth");
        Double value = expression == null ? null : (Double) expression.getValue(getFacesContext().getELContext());
        return value == null ? 1d : value;
    }

    public void setBarWidth(double barWidth) {
        this.barWidth = barWidth;
    }

    public int getHeight() {
        return height == null ? getIntegerElContextValue("height", 300) : height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean getLegend() {
        return legend == null ? getBooleanElContextValue("legend", true) : legend;
    }

    public void setLegend(boolean legend) {
        this.legend = legend;
    }

    public String getOrientation() {
        return orientation == null ? getElContextValue("orientation", "vertical") : orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public boolean getOutline() {
        return outline == null ? getBooleanElContextValue("outline", true) : outline;
    }

    public void setOutline(boolean outline) {
        this.outline = outline;
    }

    public int getStartAngle() {
        return startAngle == null ? getIntegerElContextValue("startAngle", 0) : startAngle;
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
    }

    public String getTitle() {
        return title == null ? getElContextValue("title") : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type == null ? getElContextValue("type") : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getXlabel() {
        return xlabel == null ? getElContextValue("xlabel") : xlabel;
    }

    public void setXlabel(String xlabel) {
        this.xlabel = xlabel;
    }

    public String getYlabel() {
        return ylabel == null ? getElContextValue("ylabel") : ylabel;
    }

    public void setYlabel(String ylabel) {
        this.ylabel = ylabel;
    }

    public String getStyleClass() {
        return styleClass == null ? getElContextValue("styleClass") : styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getAlt() {
        return alt == null ? getElContextValue("alt") : alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getImgTitle() {
        return imgTitle == null ? getElContextValue("imgTitle") : imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getOnclick() {
        return onclick == null ? getElContextValue("onclick") : onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }

    public String getOndblclick() {
        return ondblclick == null ? getElContextValue("ondblclick") : ondblclick;
    }

    public void setOndblclick(String ondblclick) {
        this.ondblclick = ondblclick;
    }

    public String getOnkeydown() {
        return onkeydown == null ? getElContextValue("onkeydown") : onkeydown;
    }

    public void setOnkeydown(String onkeydown) {
        this.onkeydown = onkeydown;
    }

    public String getOnkeypress() {
        return onkeypress == null ? getElContextValue("onkeypress") : onkeypress;
    }

    public void setOnkeypress(String onkeypress) {
        this.onkeypress = onkeypress;
    }

    public String getOnkeyup() {
        return onkeyup == null ? getElContextValue("onkeyup") : onkeyup;
    }

    public void setOnkeyup(String onkeyup) {
        this.onkeyup = onkeyup;
    }

    public String getOnmousedown() {
        return onmousedown == null ? getElContextValue("onmousedown") : onmousedown;
    }

    public void setOnmousedown(String onmousedown) {
        this.onmousedown = onmousedown;
    }

    public String getOnmousemove() {
        return onmousemove == null ? getElContextValue("onmousemove") : onmousemove;
    }

    public void setOnmousemove(String onmousemove) {
        this.onmousemove = onmousemove;
    }

    public String getOnmouseout() {
        return onmouseout == null ? getElContextValue("onmouseout") : onmouseout;
    }

    public void setOnmouseout(String onmouseout) {
        this.onmouseout = onmouseout;
    }

    public String getOnmouseover() {
        return onmouseover == null ? getElContextValue("onmouseover") : onmouseover;
    }

    public void setOnmouseover(String onmouseover) {
        this.onmouseover = onmouseover;
    }

    public String getOnmouseup() {
        return onmouseup == null ? getElContextValue("onmouseup") : onmouseup;
    }

    public void setOnmouseup(String onmouseup) {
        this.onmouseup = onmouseup;
    }

    public String getOutput() {
        return output == null ? getElContextValue("output", "png") : output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getUsemap() {
        return usemap == null ? getElContextValue("usemap") : usemap;
    }

    public void setUsemap(String usemap) {
        this.usemap = usemap;
    }

    @Override
    public Object saveState(FacesContext context) {
        return new Object[]{
                super.saveState(context),
                datasource,
                width,
                height,
                alpha,
                depth,
                startAngle,
                title,
                type,
                background,
                foreground,
                xlabel,
                ylabel,
                orientation,
                colors,
                is3d,
                legend,
                antialias,
                outline,
                styleClass,
                alt,
                imgTitle,
                onclick,
                ondblclick,
                onmousedown,
                onmouseup,
                onmouseover,
                onmousemove,
                onmouseout,
                onkeypress,
                onkeydown,
                onkeyup,
                output,
                usemap,
                barWidth};
    }

    @Override
    public void restoreState(FacesContext context, Object state) {
        Object values[] = (Object[]) state;
        super.restoreState(context, values[0]);
        datasource = values[1];
        width = (Integer) values[2];
        height = (Integer) values[3];
        alpha = (Integer) values[4];
        depth = (Integer) values[5];
        startAngle = (Integer) values[6];
        title = (String) values[7];
        type = (String) values[8];
        background = (String) values[9];
        foreground = (String) values[10];
        xlabel = (String) values[11];
        ylabel = (String) values[12];
        orientation = (String) values[13];
        colors = (String) values[14];
        is3d = (Boolean) values[15];
        legend = (Boolean) values[16];
        antialias = (Boolean) values[17];
        outline = (Boolean) values[18];
        styleClass = (String) values[19];
        alt = (String) values[20];
        imgTitle = (String) values[21];
        onclick = (String) values[22];
        ondblclick = (String) values[23];
        onmousedown = (String) values[24];
        onmouseup = (String) values[25];
        onmouseover = (String) values[26];
        onmousemove = (String) values[27];
        onmouseout = (String) values[28];
        onkeypress = (String) values[29];
        onkeydown = (String) values[30];
        onkeyup = (String) values[31];
        output = (String) values[32];
        usemap = (String) values[33];
        barWidth = (Double) values[34];
    }

    private boolean getBooleanElContextValue(String name, boolean defaultValue) {
        ValueExpression expression = getValueExpression(name);
        Boolean value = expression == null ? null : (Boolean) expression.getValue(getFacesContext().getELContext());
        return value == null ? defaultValue : value;
    }

    private int getIntegerElContextValue(String width, int defaultValue) {
        ValueExpression expression = getValueExpression(width);
        Integer value = expression == null ? null : (Integer) expression.getValue(getFacesContext().getELContext());
        return value == null ? defaultValue : value;
    }

    private String getElContextValue(String name) {
        ValueExpression expression = getValueExpression(name);
        return expression == null ? null : (String) expression.getValue(getFacesContext().getELContext());
    }

    private String getElContextValue(String name, String defaultValue) {
        String value = getElContextValue(name);
        return value == null ? defaultValue : value;
    }
}
