/**
 * Secant Method for finding the roots of an equation
 * X(n+1) = Xn + f(Xn)[(X(n) - X(n-1)) / (f(X(n)) - f(X(n-1)))]
 */
public class Secant extends RootLocatingMethod{
    private int n;
    private double a;
    private double b;
    private double c;
    private double fA;
    private double fB;
    private double fC;

    public Secant (double a, double b, Equation p) {
        this.n = 0;
        this.a = a;
        this.b = b;
        this.p = p;
        this.fA = this.p.evaluate(this.a);
        this.fB = this.p.evaluate(this.b);
        if(Math.abs(fA) > Math.abs(fB)) {
            double tmp = this.fA;
            this.a = this.b;
            this.fA = this.fB;
            this.b = a;
            this.fB = tmp;
        }
        this.c = this.b + (this.fB * ((this.b - this.a) / (this.fB - this.fA)));
        this.fC = this.p.evaluate(this.c);
        this.aError = Double.MAX_VALUE;
    }

    @Override
    public void next() {
        b = c;
        fB = fC;
        if(Math.abs(fA) > Math.abs(fB)) {
            double tmp = fA;
            a = b;
            fA = fB;
            b = a;
            fB = tmp;
        }
        double oldC = c;
        c = b + (fB * ((b - a) / (fB - fA)));
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
