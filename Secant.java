/**
 * Secant Method for finding the roots of an equation
 * X(n+1) = Xn - f(Xn)[(X(n) - X(n-1)) / (f(X(n)) - f(X(n-1)))]
 */
public class Secant extends RootLocatingMethod{
    private double a;
    private double b;
    private double fA;
    private double fB;

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
        this.aError = Double.MAX_VALUE;
    }

    @Override
    public void next() {
        if(Math.abs(fA) > Math.abs(fB)) {
            double tmpA = a;
            double tmpFA = fA;
            a = b;
            fA = fB;
            b = tmpA;
            fB = tmpFA;
        }
        double oldA = a;
        double d = (b - a) / (fB - fA);
        b = a;
        fB = fA;
        d = d * fA;
        a = a - d;
        fA = p.evaluate(a);
        aError = Math.abs((a - oldA) / a);
        ++n;
    }

    @Override
    public double getEstVal() {
        return a;
    }

    @Override
    public double getEstFuncVal() {
        return fA;
    }
}
