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

import java.awt.*;
import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.WindDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;

import static java.lang.Integer.parseInt;
import static org.jfree.chart.ChartFactory.*;

public class ChartUtils {

    private static String[] passthruImgAttributes = {"alt", "styleClass", "onclick", "ondblclick", "onmousedown", "onmouseup",
            "onmouseover", "onmousemove", "onmouseout", "onkeypress", "onkeydown", "onkeyup", "usemap",};

    public static void renderPassThruImgAttributes(ResponseWriter writer, UIComponent component) throws IOException {
        for (String attribute : passthruImgAttributes) {
            Object value = component.getAttributes().get(attribute);

            if (value != null) {
                writer.writeAttribute(attribute, value, null);
            }
        }
        // title attribute overlaps with the chart title so renamed to imgTitle to define img tag's title
        Object imageTitle = component.getAttributes().get("imgTitle");

        if (imageTitle == null) {
            return;
        }

        writer.writeAttribute("title", imageTitle, null);
    }

    public static PlotOrientation getPlotOrientation(String orientation) {
        if (orientation.equalsIgnoreCase("horizontal")) {
            return PlotOrientation.HORIZONTAL;
        }
        if (orientation.equalsIgnoreCase("vertical")) {
            return PlotOrientation.VERTICAL;
        }

        throw new RuntimeException("Unsupported plot orientation:" + orientation);
    }

    public static Color getColor(String color) {
        // HTML colors (#FFFFFF format)
        if (color.startsWith("#")) {
            return new Color(parseInt(color.substring(1), 16));
        }

        // Colors by name
        if (color.equalsIgnoreCase("black")) {
            return Color.black;
        }
        if (color.equalsIgnoreCase("grey")) {
            return Color.gray;
        }
        if (color.equalsIgnoreCase("yellow")) {
            return Color.yellow;
        }
        if (color.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (color.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (color.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (color.equalsIgnoreCase("orange")) {
            return Color.orange;
        }
        if (color.equalsIgnoreCase("cyan")) {
            return Color.cyan;
        }
        if (color.equalsIgnoreCase("magenta")) {
            return Color.magenta;
        }
        if (color.equalsIgnoreCase("darkgray")) {
            return Color.darkGray;
        }
        if (color.equalsIgnoreCase("lightgray")) {
            return Color.lightGray;
        }
        if (color.equalsIgnoreCase("pink")) {
            return Color.pink;
        }
        if (color.equalsIgnoreCase("white")) {
            return Color.white;
        }

        throw new RuntimeException("Unsupported chart color:" + color);
    }

    public static String resolveContentType(String output) {
        if (output.equalsIgnoreCase("png")) {
            return "img/png";
        }
        if (output.equalsIgnoreCase("jpeg")) {
            return "img/jpeg";
        }

        throw new RuntimeException("Unsupported output format:" + output);
    }

    public static JFreeChart createChartWithType(ChartData data) {
        Object datasource = data.getDatasource();

        if (datasource instanceof PieDataset) {
            return createChartWithPieDataSet(data);
        }
        if (datasource instanceof CategoryDataset) {
            return createChartWithCategoryDataSet(data);
        }
        if (datasource instanceof XYDataset) {
            return createChartWithXYDataSet(data);
        }

        throw new RuntimeException("Unsupported chart type");
    }

    public static void setGeneralChartProperties(JFreeChart chart, ChartData data) {
        chart.setBackgroundPaint(ChartUtils.getColor(data.getBackground()));
        chart.getPlot().setBackgroundPaint(ChartUtils.getColor(data.getForeground()));
        chart.setTitle(data.getTitle());
        chart.setAntiAlias(data.isAntialias());

        // Alpha transparency (100% means opaque)
        if (data.getAlpha() < 100) {
            chart.getPlot().setForegroundAlpha((float) data.getAlpha() / 100);
        }
    }

    public static JFreeChart createChartWithCategoryDataSet(ChartData data) {
        JFreeChart chart = createCategoryChart(data);
        setCategorySeriesColors(chart, data);
        return chart;
    }

    private static JFreeChart createCategoryChart(ChartData data) {
        PlotOrientation plotOrientation = getPlotOrientation(data.getOrientation());
        CategoryDataset dataset = (CategoryDataset) data.getDatasource();
        String type = data.getType();
        String xAxis = data.getXlabel();
        String yAxis = data.getYlabel();
        boolean is3d = data.isChart3d();
        boolean legend = data.isLegend();
        JFreeChart chart;

        if (type.equalsIgnoreCase("bar")) {
            chart = is3d
                    ? createBarChart3D("", xAxis, yAxis, dataset, plotOrientation, legend, true, false)
                    : createBarChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);

            setBarOutline(chart, data);
            return chart;
        }

        if (type.equalsIgnoreCase("stackedbar")) {
            chart = is3d
                    ? createStackedBarChart3D("", xAxis, yAxis, dataset, plotOrientation, legend, true, false)
                    : createStackedBarChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);

            setBarOutline(chart, data);
            return chart;
        }

        if (type.equalsIgnoreCase("line")) {
            return is3d
                    ? createLineChart3D("", xAxis, yAxis, dataset, plotOrientation, legend, true, false)
                    : createLineChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
        }

        if (type.equalsIgnoreCase("area")) {
            return createAreaChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
        }
        if (type.equalsIgnoreCase("stackedarea")) {
            return createStackedAreaChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
        }
        if (type.equalsIgnoreCase("waterfall")) {
            return createWaterfallChart("", xAxis, yAxis, dataset, plotOrientation, legend, true, false);
        }
        if (type.equalsIgnoreCase("gantt")) {
            return createGanttChart("", xAxis, yAxis, (IntervalCategoryDataset) dataset, legend, true, false);
        }

        return null;
    }

    public static JFreeChart createChartWithPieDataSet(ChartData data) {
        JFreeChart chart = createPieOrRingChart(data);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setStartAngle(data.getStartAngle());
        setPieSectionColors(chart, data);
        return chart;
    }

    public static JFreeChart createChartWithXYDataSet(ChartData data) {
        JFreeChart chart = createChart(data);
        setXYSeriesColors(chart, data);
        return chart;
    }

    /**
     * Series coloring Plot has no getRenderer so two methods for each plot type (category plot and XY-plot).
     */
    public static void setCategorySeriesColors(JFreeChart chart, ChartData data) {
        if (!(chart.getPlot() instanceof CategoryPlot)) {
            return;
        }

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        if (data.getColors() == null) {
            return;
        }

        String[] colors = data.getColors().split(",");

        for (int index = 0; index < colors.length; index++) {
            plot.getRenderer().setSeriesPaint(index, getColor(colors[index].trim()));
        }
    }

    public static void setXYSeriesColors(JFreeChart chart, ChartData data) {
        if (!(chart.getPlot() instanceof XYPlot) || data.getColors() == null) {
            return;
        }

        XYPlot plot = (XYPlot) chart.getPlot();
        String[] colors = data.getColors().split(",");

        for (int index = 0; index < colors.length; index++) {
            plot.getRenderer().setSeriesPaint(index, getColor(colors[index].trim()));
        }
    }

    public static void setPieSectionColors(JFreeChart chart, ChartData data) {
        if (!(chart.getPlot() instanceof PiePlot) || data.getColors() == null) {
            return;
        }

        String[] colors = data.getColors().split(",");

        for (int index = 0; index < colors.length; index++) {
            ((PiePlot) chart.getPlot()).setSectionPaint(index, getColor(colors[index].trim()));
        }
    }

    public static void setBarOutline(JFreeChart chart, ChartData data) {
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(data.isOutline());
        renderer.setMaximumBarWidth(data.getBarWidth());
    }

    private static JFreeChart createChart(ChartData data) {
        XYDataset dataset = (XYDataset) data.getDatasource();
        String type = data.getType();
        String xAxis = data.getXlabel();
        String yAxis = data.getYlabel();
        boolean legend = data.isLegend();
        PlotOrientation orientation = getPlotOrientation(data.getOrientation());

        if (type.equalsIgnoreCase("timeseries")) {
            return createTimeSeriesChart("", xAxis, yAxis, dataset, legend, true, false);
        }
        if (type.equalsIgnoreCase("xyline")) {
            return createXYLineChart("", xAxis, yAxis, dataset, orientation, legend, true, false);
        }
        if (type.equalsIgnoreCase("polar")) {
            return createPolarChart("", dataset, legend, true, false);
        }
        if (type.equalsIgnoreCase("scatter")) {
            return createScatterPlot("", xAxis, yAxis, dataset, orientation, legend, true, false);
        }
        if (type.equalsIgnoreCase("xyarea")) {
            return createXYAreaChart("", xAxis, yAxis, dataset, orientation, legend, true, false);
        }
        if (type.equalsIgnoreCase("xysteparea")) {
            return createXYStepAreaChart("", xAxis, yAxis, dataset, orientation, legend, true, false);
        }
        if (type.equalsIgnoreCase("xystep")) {
            return createXYStepChart("", xAxis, yAxis, dataset, orientation, legend, true, false);
        }
        if (type.equalsIgnoreCase("bubble")) {
            return createBubbleChart("", xAxis, yAxis, (XYZDataset) dataset, orientation, legend, true, false);
        }
        if (type.equalsIgnoreCase("candlestick")) {
            return createCandlestickChart("", xAxis, yAxis, (OHLCDataset) dataset, legend);
        }
        if (type.equalsIgnoreCase("boxandwhisker")) {
            return createBoxAndWhiskerChart("", xAxis, yAxis, (BoxAndWhiskerXYDataset) dataset, legend);
        }
        if (type.equalsIgnoreCase("highlow")) {
            return createHighLowChart("", xAxis, yAxis, (OHLCDataset) dataset, legend);
        }
        if (type.equalsIgnoreCase("histogram")) {
            return createHistogram("", xAxis, yAxis, (IntervalXYDataset) dataset, orientation, legend, true, false);
        }
        if (type.equalsIgnoreCase("wind")) {
            return createWindPlot("", xAxis, yAxis, (WindDataset) dataset, legend, true, false);
        }

        return null;
    }

    private static JFreeChart createPieOrRingChart(ChartData data) {
        PieDataset dataset = (PieDataset) data.getDatasource();
        String type = data.getType();
        boolean legend = data.isLegend();
        JFreeChart chart;

        if (type.equalsIgnoreCase("pie")) {
            if (data.isChart3d()) {
                chart = createPieChart3D("", dataset, legend, true, false);
                PiePlot3D plot = (PiePlot3D) chart.getPlot();
                plot.setDepthFactor((float) data.getDepth() / 100);
                return chart;
            }

            return createPieChart("", dataset, legend, true, false);
        }

        if (type.equalsIgnoreCase("ring")) {
            return createRingChart("", dataset, legend, true, false);
        }

        return null;
    }

    private ChartUtils() {
    }
}
