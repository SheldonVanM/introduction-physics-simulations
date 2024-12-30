package com.physicssimulation.chapters.chapter1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MoneyDistribution {

    private Double[] agents;

    public MoneyDistribution(Integer num_of_agents, Double money_for_each_agent) {
        this.agents = new Double[num_of_agents];
        Arrays.fill(this.agents, money_for_each_agent);
    }

    private void performRandomTransactionWithoutSaving(int i, int j) {
        Random r = new Random();
        Double e = r.nextDouble();
        Double m_i = this.agents[i];
        Double m_j = this.agents[j];
        this.agents[j] = e * (m_i + m_j);
        this.agents[i] = (1 - e) * (m_i + m_j);
    }

    private void performRandomTransactionWithSaving(int i, int j, Double lambda) {
        Random r = new Random();
        Double e = r.nextDouble();
        Double m_i = this.agents[i];
        Double m_j = this.agents[j];
        Double d_m = (1 - lambda) * (e * m_j - (1 - e) * m_i);
        this.agents[i] = m_i - d_m;
        this.agents[j] = m_i + d_m;
        m_i = this.agents[i];
        m_j = this.agents[j];
        this.agents[j] = e * (m_i + m_j);
        this.agents[i] = (1 - e) * (m_i + m_j);
    }

    public void simulate(int iterations, boolean saving, Double lambda) {
        Random rand = new Random();
        if (saving) {
            for (int index = 0; index < iterations; index++) {
                int i = rand.nextInt(this.agents.length);
                int j = rand.nextInt(this.agents.length);
                this.performRandomTransactionWithSaving(i, j, lambda);
            }
        } else {
            for (int index = 0; index < iterations; index++) {
                int i = rand.nextInt(this.agents.length);
                int j = rand.nextInt(this.agents.length);
                this.performRandomTransactionWithoutSaving(i, j);
            }
        }
    }

    public void simulate(int iterations, boolean saving) {
        Double lambda = 0.0;
        this.simulate(iterations, saving, lambda);
    }

    public void sort() {
        Arrays.sort(this.agents);
    }

    public void plot(String title, String xAxis, String yAxis, String lineTitle) {
        XYSeries series = new XYSeries(title);
        for (int i = 0; i < this.agents.length; i++) {
            series.add(i + 1, this.agents[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(lineTitle,
                xAxis,
                yAxis,
                dataset);

        ChartPanel panel = new ChartPanel(chart);
        JFrame frame = new JFrame("Chart Example");
        frame.setContentPane(panel);
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // public static void main(String[] args) {
    // MoneyDistribution md = new MoneyDistribution(1000, 100);
    // md.simulate(10 * 1000, false);
    // }

}
