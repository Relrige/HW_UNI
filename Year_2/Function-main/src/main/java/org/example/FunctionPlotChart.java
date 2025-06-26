package org.example;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FunctionPlotChart {

    private double a;
    private double B;
    private double step;
    private double start;
    private double end;
    private Color plotColor;

    public FunctionPlotChart(double a, double B, double step, double start, double end, Color plotColor) {
        this.a = a;
        this.B = B;
        this.step = step;
        this.start = start;
        this.end = end;
        this.plotColor = plotColor;
    }

    public XYChart createChart() {
        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();

        for (double t = start; t <= end; t += step) {
            double x = a * (Math.cos(t) - Math.cos(B * t) / B);
            double y = a * (Math.sin(t) - Math.sin(B * t) / B);
            xData.add(x);
            yData.add(y);
        }

        String chartTitle = "Графік функції";
        String formula = "x = A (cos(t) - cos(Bt)/B), y = A (sin(t) - sin(Bt)/B)";
        XYChart chart = new XYChartBuilder().width(800).height(600).title(chartTitle).xAxisTitle("X").yAxisTitle("Y").build();
        chart.addSeries(formula, xData, yData).setMarker(SeriesMarkers.NONE).setLineColor(plotColor);
        chart.setTitle(chartTitle + " (" + formula + ")");
        chart.getStyler().setLegendVisible(false);

        saveChartAsImage(chart);

        return chart;
    }

    private void saveChartAsImage(XYChart chart) {
        try {
            BitmapEncoder.saveBitmap(chart, "./FunctionPlot", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateParameters(double a, double B, double step, double start, double end, Color plotColor) {
        this.a = a;
        this.B = B;
        this.step = step;
        this.start = start;
        this.end = end;
        this.plotColor = plotColor;
    }
}
