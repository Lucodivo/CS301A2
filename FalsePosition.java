/**
 * Connor A. Haskins
 * Bronco ID# 010215400
 * CS 301 w/ Professor Raheja
 * Project 2: Root Locating Methods
 * Due Date: May 15th
 *
 * False Position Method for finding the roots of an equation
 * c = [((a * f(b)) - (b * f(a))) / (f(b) - f(a))]
 */
public class FalsePosition extends RootLocatingMethod {
    private double a;
    private double b;
    private double c;
    private double fA;
    private double fB;
    private double fC;

    public FalsePosition(double a, double b, Equation p) {
        this.n = 0;
        this.a = a;
        this.b = b;
        this.p = p;
        this.fA = this.p.evaluate(this.a);
        this.fB = this.p.evaluate(this.b);
        this.c = ((this.a * this.fB) - (this.b * this.fA)) /
                    (this.fB - this.fA);
        this.fC = this.p.evaluate(this.c);
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
        c = ((a * fB) - (b * fA)) /
                (fB - fA);
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
