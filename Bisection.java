/**
 * Bisection Method for finding the roots of an equation
 * c = (a + b) / 2
 */
public class Bisection extends RootLocatingMethod {
    private Equation p;
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
        if(this.fC < 0) {
            this.a = this.c;
            this.fA = this.fC;
        } else if(this.fC > 0) {
            this.b = this.c;
            this.fB = this.fC;
        }
        double oldC = this.c;
        this.c = (this.a + this.b) / 2;
        this.fC = this.p.evaluate(this.c);
        this.aError = Math.abs((this.c - oldC) / this.c);
        ++this.n;
    }

    @Override
    public double getEstFuncVal() {
        return fC;
    }

    @Override
    public void print() {
        if(n > 0) {
            System.out.println(n + "\t" + formatter.format(c) + "\t\t" + formatter.format(fC) +
                    "\t\t\t" + formatter.format(aError));
        } else {
            System.out.println("0\t" + formatter.format(this.c) + "\t\t" + formatter.format(this.fC) + "\t\t\t----");
        }
        //System.out.println(n + "\t\t" + a + "\t\t" + b + "\t\t" + c
        //        + "\t\t" + fA + "\t\t" + fB + "\t\t" + fC + "\t\t" + fC + "\t\t" + aError);
    }
}
