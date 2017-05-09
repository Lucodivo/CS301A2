/**
 * Secant Method for finding the roots of an equation
 * X(n+1) = Xn + f(Xn)[(X(n) - X(n-1)) / (f(X(n)) - f(X(n-1)))]
 */
public class Secant {

    private static final int MAX_ITERATIONS = 100;

    private static int n;
    private static double xMinus;
    private static double x;
    private static double xPlus;
    private static double fXMinus;
    private static double fX;
    private static double fXPlus;
    private static double aError;
    private static double tError;

    public static void main(String[] args) {
        Polynomial a = new Polynomial(2, -11.7, 17.7, -5);
        System.out.println("f(x) = 2(x^3) - 11.7(x^2) + 17.7(x) - 5, evaluated at x = 1 is:");
        System.out.println(a.evaluate(1));
    }
}
