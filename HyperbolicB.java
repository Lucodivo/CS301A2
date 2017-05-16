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
public class HyperbolicB implements Equation{
    private double c = 10;

    public HyperbolicB() {}

    @Override
    public double evaluate(double xVal) {
        double result = xVal + c - (xVal * Math.cosh(50 / xVal));
        return result;
    }
}
