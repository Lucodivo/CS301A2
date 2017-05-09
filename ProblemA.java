/**
 * (a) f(x) = 2x3 - 11.7x2 + 17.7x - 5
 */
public class ProblemA {

    private static final int MAX_ITERATIONS = 100;

    /**
     * ProblemA Method for finding the roots of an equation
     * c = (a + b) / 2
     */
    class BisectionValues {
        public int n = 0;
        public double a = 0;
        public double b = 0;
        public double c = 0;
        public double fA = 0;
        public double fB = 0;
        public double fC = 0;
        public double aError = 0;
    }

    /**
     * Newton-Raphson Method for finding the roots of an equation
     * X(n+1) = Xn + [f(Xn)/f'(Xn)]
     */
    class NewtonRaphsonValues {
        private int n = 0;
        private double xMinus = 0;
        private double x = 0;
        private double xPlus = 0;
        private double fXMinus = 0;
        private double fPrimeXMinus = 0;
        private double fX = 0;
        private double fPrimeX = 0;
        private double aError = 0;
    }

    /**
     * False Position Method for finding the roots of an equation
     * c = [((a * f(b)) - (b * f(a))) / (f(b) - f(a))]
     */
    class FalsePositionValues {
        private int n = 0;
        private double a = 0;
        private double b = 0;
        private double c = 0;
        private double fA = 0;
        private double fB = 0;
        private double fC = 0;
        private double aError = 0;
    }

    /**
     * Secant Method for finding the roots of an equation
     * X(n+1) = Xn + f(Xn)[(X(n) - X(n-1)) / (f(X(n)) - f(X(n-1)))]
     */
    class SecantValues {
        private int n = 0;
        private double xMinus = 0;
        private double x = 0;
        private double xPlus = 0;
        private double fXMinus = 0;
        private double fX = 0;
        private double fXPlus = 0;
        private double aError = 0;
    }

    /**
     * Modified Secant Method for finding the roots of an equation
     * X(n+1) = Xn - f(Xn)[(delta * Xn)) / (f(X(n) + (delta * X(n))) - f(X(n)))]
     */
    class ModifiedSecantValues{
        private int n = 0;
        private double x = 0;
        private double xPlus = 0;
        private double delta = 0;
        private double fX = 0;
        private double fXDelta = 0;
        private double aError = 0;
    }


    public static void main(String[] args) {
        Polynomial a = new Polynomial(2, -11.7, 17.7, -5);
        System.out.println("f(x) = 2(x^3) - 11.7(x^2) + 17.7(x) - 5, evaluated at x = 1 is:");
        System.out.println(a.evaluate(1));
        Polynomial aPrime = new Polynomial(0, 6, -23.4, 17.7);
        System.out.println("f'(x) = 6(x^2) - 23.4(x) + 17.7, evaluated at x = 1 is:");
        System.out.println(aPrime.evaluate(1));
    }
}
