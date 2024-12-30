package com.physicssimulation;

import com.physicssimulation.chapters.chapter1.MoneyDistribution;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        int N = 1000;
        double m = 100.0;

        MoneyDistribution md_no_savings = new MoneyDistribution(N, m);
        md_no_savings.simulate(1000 * N, false);
        md_no_savings.sort();
        md_no_savings.plot("Money Without Savings", "Agent #", "Money in $", "Lambda = 0");

        MoneyDistribution md_with_savings1 = new MoneyDistribution(N, m);
        md_with_savings1.simulate(1000 * N, false, 0.25);
        md_with_savings1.plot("Money With Savings", "Agent #", "Money in $", "Lambda = 0.25");

        MoneyDistribution md_with_savings2 = new MoneyDistribution(N, m);
        md_with_savings2.simulate(1000 * N, false, 0.5);
        md_with_savings2.plot("Money With Savings", "Agent #", "Money in $", "Lambda = 0.5");

        MoneyDistribution md_with_savings3 = new MoneyDistribution(N, m);
        md_with_savings3.simulate(1000 * N, false, 0.75);
        md_with_savings3.plot("Money With Savings", "Agent #", "Money in $", "Lambda = 0.75");

        MoneyDistribution md_with_savings4 = new MoneyDistribution(N, m);
        md_with_savings4.simulate(1000 * N, false, 0.9);
        md_with_savings4.sort();
        md_with_savings4.plot("Money With Savings", "Agent #", "Money in $", "Lambda = 0.9");
    }
}
