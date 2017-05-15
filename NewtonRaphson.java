import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Newton-Raphson Method for finding the roots of an equation
 * X(n+1) = Xn + [f(Xn)/f'(Xn)]
 */
public class NewtonRaphson extends RootLocatingMethod {
    private double x;
    private double fX;
    private double fPrimeX;

    private Equation p;
    private Equation pPrime;

    public NewtonRaphson(double x, Equation p, Equation pPrime) {
        this.n = 0;
        this.x = x;
        this.p = p;
        this.pPrime = pPrime;
        this.fX = this.p.evaluate(x);
        this.fPrimeX = this.pPrime.evaluate(x);
    }

    @Override
    public void next() {
        double oldX = x;
        x += (fX / fPrimeX);
        fX = p.evaluate(x);
        fPrimeX = pPrime.evaluate(x);
        this.aError = Math.abs((x - oldX) / x);
        ++this.n;
    }

    @Override
    public double getEstFuncVal() {
        return fX;
    }

    @Override
    public void print() {
        if(n > 0) {
            System.out.println(n + "\t" + formatter.format(x) + "\t\t" + formatter.format(fX) +
                    "\t\t\t" + formatter.format(aError));
        } else {
            System.out.println("0\t" + formatter.format(this.x) + "\t\t" + formatter.format(this.fX) + "\t\t\t----");
        }
    }
}
