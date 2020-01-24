package no.nav.presentation.pensjon.pselv.tag.chartcreator;

import java.io.Serializable;

public class ChartData implements Serializable {

    private static final long serialVersionUID = 17438458484848L;
    private Object datasource;
    private int width;
    private int height;
    private int alpha;
    private int depth;
    private int startAngle;
    private double barWidth;
    private String title;
    private String type;
    private String background;
    private String foreground;
    private String xlabel;
    private String ylabel;
    private String orientation;
    private String colors;
    private boolean chart3d;
    private boolean legend;
    private boolean antialias;
    private boolean outline;
    private String output;

    public ChartData() {
    }

    public ChartData(UIChart chart) {
        datasource = chart.getDatasource();
        width = chart.getWidth();
        height = chart.getHeight();
        alpha = chart.getAlpha();
        depth = chart.getDepth();
        startAngle = chart.getStartAngle();
        barWidth = chart.getBarWidth();
        title = chart.getTitle();
        type = chart.getType();
        background = chart.getBackground();
        foreground = chart.getForeground();
        xlabel = chart.getXlabel();
        ylabel = chart.getYlabel();
        orientation = chart.getOrientation();
        colors = chart.getColors();
        chart3d = chart.getIs3d();
        legend = chart.getLegend();
        antialias = chart.getAntialias();
        outline = chart.getOutline();
        output = chart.getOutput();
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public boolean isAntialias() {
        return antialias;
    }

    public void setAntialias(boolean antialias) {
        this.antialias = antialias;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getForeground() {
        return foreground;
    }

    public void setForeground(String foreground) {
        this.foreground = foreground;
    }

    public boolean isChart3d() {
        return chart3d;
    }

    public void setChart3d(boolean chart3d) {
        this.chart3d = chart3d;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public Object getDatasource() {
        return datasource;
    }

    public void setDatasource(Object datasource) {
        this.datasource = datasource;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(double barWidth) {
        this.barWidth = barWidth;
    }

    public boolean isLegend() {
        return legend;
    }

    public void setLegend(boolean legend) {
        this.legend = legend;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public boolean isOutline() {
        return outline;
    }

    public void setOutline(boolean outline) {
        this.outline = outline;
    }

    public int getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(int startAngle) {
        this.startAngle = startAngle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getXlabel() {
        return xlabel;
    }

    public void setXlabel(String xlabel) {
        this.xlabel = xlabel;
    }

    public String getYlabel() {
        return ylabel;
    }

    public void setYlabel(String ylabel) {
        this.ylabel = ylabel;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
