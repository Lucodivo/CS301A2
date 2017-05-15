/**
 * Modified Secant Method for finding the roots of an equation
 * X(n+1) = Xn - f(Xn)[(delta * Xn)) / (f(X(n) + (delta * X(n))) - f(X(n)))]
 */
public class ModSecant extends RootLocatingMethod {
    private int n;
    private double x;
    private double delta;
    private double fX;
    private double aError;

    public ModSecant(double x, double delta, Equation p) {
        this.n = 0;
        this.x = x;
        this.delta = delta;
        this.p = p;
        this.fX = this.p.evaluate(this.x);
        this.aError = Double.MAX_VALUE;
    }

    @Override
    public void next() {
        double oldX = x;
        double deltaX = delta * x;
        x = oldX - (fX * ((deltaX) / (p.evaluate(oldX + deltaX) - p.evaluate(oldX))));
        aError = Math.abs((x - oldX) / x);
        ++n;
    }

    @Override
    public double getEstVal() {
        return x;
    }

    @Override
    public double getEstFuncVal() {
        return fX;
    }
}
