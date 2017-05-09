/**
 * Newton-Raphson Method for finding the roots of an equation
 * X(n+1) = Xn + [f(Xn)/f'(Xn)]
 */
public class NewtonRaphson {

    private static final int MAX_ITERATIONS = 100;

    private static int n;
    private static double xMinus;
    private static double x;
    private static double xPlus;
    private static double fXMinus;
    private static double fPrimeXMinus;
    private static double fX;
    private static double fPrimeX;
    private static double aError;
    private static double tError;

    public static void main(String[] args) {
        Polynomial a = new Polynomial(2, -11.7, 17.7, -5);
        Polynomial aPrime = new Polynomial(0, 6, -23.4, 17.7);
        System.out.println("f(x) = 2(x^3) - 11.7(x^2) + 17.7(x) - 5, evaluated at x = 1 is:");
        System.out.println(a.evaluate(1));
        System.out.println("f'(x) = 6(x^2) - 23.4(x) + 17.7, evaluated at x = 1 is:");
        System.out.println(aPrime.evaluate(1));
    }
}
