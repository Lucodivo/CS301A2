/**
 * Connor A. Haskins
 * Bronco ID# 010215400
 * CS 301 w/ Professor Raheja
 * Project 2: Root Locating Methods
 * Due Date: May 15th
 *
 * Bisection Method for finding the roots of an equation
 * c = (a + b) / 2
 */
public class Bisection extends RootLocatingMethod {
    private double a;
    private double b;
    private double c;
    private double fA;
    private double fB;
    private double fC;

    public Bisection(double a, double b, Equation p) {
        this.n = 0;
        this.p = p;
        this.a = a;
        this.b = b;
        this.c = (a + b) / 2;
        this.fA = p.evaluate(this.a);
        this.fB = p.evaluate(this.b);
        this.fC = p.evaluate(this.c);
        this.aError = Double.MAX_VALUE;
    }

    @Override
    public void next() {
        if((fA * fC) > 0) {
            a = c;
            fA = fC;
        } else {
            b = c;
            fB = fC;
        }
        double oldC = c;
        c = (a + b) / 2;
        fC = p.evaluate(c);
        aError = Math.abs((c - oldC) / c);
        ++n;
    }

    @Override
    public double getEstVal() {
        return c;
    }

    @Override
    public double getEstFuncVal() {
        return fC;
    }
}
