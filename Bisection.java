/**
 * Bisection Method for finding the roots of an equation
 * c = (a + b) / 2
 */
public class Bisection {

    private static final int MAX_ITERATIONS = 100;

    private static int n;
    private static double a;
    private static double b;
    private static double c;
    private static double fA;
    private static double fB;
    private static double fC;
    private static double aError;
    private static double tError;

    public static void main(String[] args) {
        Polynomial a = new Polynomial(2, -11.7, 17.7, -5);
        System.out.println("f(x) = 2(x^3) - 11.7(x^2) + 17.7(x) - 5, evaluated at x = 1 is:");
        System.out.println(a.evaluate(1));
    }
}
