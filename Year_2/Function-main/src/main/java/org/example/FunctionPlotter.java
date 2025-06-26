package org.example;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.awt.*;

public class FunctionPlotter {

    private static double a = 6.2;
    private static double B = 3.1;
    private static double step = 0.05;
    private static double start = 0;
    private static double end = 20 * Math.PI;
    private static Color plotColor = Color.MAGENTA;

    private static FunctionPlotChart functionPlotChart;
    private static JSplitPane splitPane;

    public static void main(String[] args) {
        functionPlotChart = new FunctionPlotChart(a, B, step, start, end, plotColor);
        XYChart chart = functionPlotChart.createChart();

        JFrame frame = new JFrame("Function Plotter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);

        JPanel topPanel = new JPanel();
        JLabel madeByLabel = new JLabel("Made by Stanislav Kulakevych", SwingConstants.RIGHT);
        madeByLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.add(madeByLabel);

        JPanel controlPanel = createControlPanel();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new XChartPanel<>(chart), controlPanel);
        splitPane.setDividerLocation(800);

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));

        JLabel labelA = new JLabel("a:");
        labelA.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField fieldA = new JTextField(String.valueOf(a));
        JLabel labelB = new JLabel("B:");
        labelB.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField fieldB = new JTextField(String.valueOf(B));
        JLabel labelStep = new JLabel("Step:");
        labelStep.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField fieldStep = new JTextField(String.valueOf(step));
        JLabel labelStart = new JLabel("Start:");
        labelStart.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField fieldStart = new JTextField(String.valueOf(start));
        JLabel labelEnd = new JLabel("End:");
        labelEnd.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField fieldEnd = new JTextField(String.valueOf(end));

        JButton updateButton = new JButton("Update");
        JButton saveButton = new JButton("Save as Image");
        JButton colorPickerButton = new JButton("Choose Color");

        panel.add(labelA);
        panel.add(fieldA);
        panel.add(labelB);
        panel.add(fieldB);
        panel.add(labelStep);
        panel.add(fieldStep);
        panel.add(labelStart);
        panel.add(fieldStart);
        panel.add(labelEnd);
        panel.add(fieldEnd);
        panel.add(new JLabel());
        panel.add(colorPickerButton);
        panel.add(new JLabel());
        panel.add(updateButton);
        panel.add(new JLabel());
        panel.add(saveButton);

        updateButton.addActionListener(e -> {
            try {
                a = Double.parseDouble(fieldA.getText());
                B = Double.parseDouble(fieldB.getText());
                step = Double.parseDouble(fieldStep.getText());
                start = Double.parseDouble(fieldStart.getText());
                end = Double.parseDouble(fieldEnd.getText());

                functionPlotChart.updateParameters(a, B, step, start, end, plotColor);
                XYChart chart = functionPlotChart.createChart();
                splitPane.setLeftComponent(new XChartPanel<>(chart));
                splitPane.revalidate();
                splitPane.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        saveButton.addActionListener(e -> ImageSaver.saveComponentAsImage(splitPane));

        colorPickerButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(panel, "Choose Plot Color", plotColor);
            if (selectedColor != null) {
                plotColor = selectedColor;
                functionPlotChart.updateParameters(a, B, step, start, end, plotColor);
                XYChart chart = functionPlotChart.createChart();
                splitPane.setLeftComponent(new XChartPanel<>(chart));
                splitPane.repaint();
            }
        });

        return panel;
    }
}
