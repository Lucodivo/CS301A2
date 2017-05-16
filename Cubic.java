/**
 * Connor A. Haskins
 * Bronco ID# 010215400
 * CS 301 w/ Professor Raheja
 * Project 2: Root Locating Methods
 * Due Date: May 15th
 *
 * Class used for storing polynomial functions up to (x^3)
 */
public class Cubic implements Equation {

    private double x3;
    private double x2;
    private double x;
    private double c;

    public Cubic(double x3, double x2, double x, double c) {
        this.x3 = x3;
        this.x2 = x2;
        this.x = x;
        this.c = c;
    }

    public double evaluate(double xVal) {
        double result = c + xVal * (x + xVal * (x2 + xVal * (x3)));
        return result;
    }
}
