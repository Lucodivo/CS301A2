/**
 * Newton-Raphson Method for finding the roots of an equation
 * X(n+1) = Xn - [f(Xn)/f'(Xn)]
 */
public class NewtonRaphson extends RootLocatingMethod {
    private double x;
    private double fX;
    private double fPrimeX;

    private Equation pPrime;

    public NewtonRaphson(double x, Equation p, Equation pPrime) {
        this.n = 0;
        this.x = x;
        this.p = p;
        this.pPrime = pPrime;
        this.fX = this.p.evaluate(x);
        this.fPrimeX = this.pPrime.evaluate(x);
        this.aError = Double.MAX_VALUE;
    }

    @Override
    public void next() {
        double oldX = x;
        x = oldX - (fX / fPrimeX);
        fX = p.evaluate(x);
        fPrimeX = pPrime.evaluate(x);
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
