/**
 * Connor A. Haskins
 * Bronco ID# 010215400
 * CS 301 w/ Professor Raheja
 * Project 2: Root Locating Methods
 * Due Date: May 15th
 *
 * Hardcoded class. Not reusable.
 * Made for problem (b)
 */
public class HyperbolicBPrime implements Equation{
    private double c = 1;

    public HyperbolicBPrime() {}

    @Override
    public double evaluate(double xVal) {
        double result = c + Math.cosh(50 / xVal) - (50 * (Math.sinh(50 / xVal) / xVal));
        return result;
    }
}