import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * (a) f(x) = 2x3 - 11.7x2 + 17.7x - 5
 */
public class ProblemA {

    private static final int MAX_ITERATIONS = 100;
    private static final double MINIMUM_ERROR = 0.01;
    private static NumberFormat formatter = new DecimalFormat("#0.0000");

    private static Equation polynomialA = new Polynomial(2, -11.7, 17.7, -5);

    private static Scanner in;

    public static void main(String[] args) {
        System.out.println("f(x) = 2(x^3) - 11.7(x^2) + 17.7(x) - 5"); /* , evaluated at x = 1 is:");
        System.out.println(a.evaluate(1));
        Polynomial aPrime = new Polynomial(0, 6, -23.4, 17.7);
        System.out.println("f'(x) = 6(x^2) - 23.4(x) + 17.7, evaluated at x = 1 is:");
        System.out.println(aPrime.evaluate(1)); */

        in = new Scanner(System.in);

        boolean validResp = false;
        while(!validResp) {
            System.out.println("Which method would you like to use?");
            System.out.println("1. Bisection Method");
            System.out.println("2. Newton-Raphson Method");
            System.out.println("3. Secant Method");
            System.out.println("4. False-Position Method");
            System.out.println("5. Modified Secant Method");

            char choice = in.nextLine().charAt(0);
            validResp = true;
            switch(choice){
                case '1':
                    BisectionMethod();
                    break;
                case '2':
                    NewtonRaphsonMethod();
                    break;
                case '3':
                    SecantMethod();
                    break;
                case '4':
                    FalsePositionMethod();
                    break;
                case '5':
                    ModSecantMethod();
                    break;
                default:
                    System.out.println("Invalid response.");
                    validResp = false;
            }
        }
    }

    private static final double BISECTION_A1 = 0;
    private static final double BISECTION_B1 = 1;
    private static final double BISECTION_A2 = 3;
    private static final double BISECTION_B2 = 1;
    private static final double BISECTION_A3 = 3;
    private static final double BISECTION_B3 = 4;

    private static void BisectionMethod() {
        RootLocatingMethod [] bv = new Bisection[3];

        System.out.println("=====Bisection Method=====");

        boolean validResp = false;
        while(!validResp) {
            System.out.println("Would you like too...");
            System.out.println("1. Use hardcoded starting points?");
            System.out.println("2. Enter your own starting points?");

            char choice = in.nextLine().charAt(0);
            validResp = true;
            switch(choice){
                case '1':
                    bv[0] = new Bisection(BISECTION_A1, BISECTION_B1, polynomialA);
                    bv[1] = new Bisection(BISECTION_A2, BISECTION_B2, polynomialA);
                    bv[2] = new Bisection(BISECTION_A3, BISECTION_B3, polynomialA);
                    break;
                case '2':
                    System.out.println("Not written yet");
                    validResp = false;
                    break;
                default:
                    System.out.println("Invalid response.");
                    validResp = false;
            }
        }

        for(int i = 0; i < 3; ++i) {
            System.out.println("n\tc\t\t\tf(c)\t\t\tapprox. error");
            // find first zero
            bv[i].print();
            while(bv[i].getAError() > MINIMUM_ERROR && bv[i].getN() < MAX_ITERATIONS
                    && bv[i].getEstFuncVal() != 0){
                bv[i].next();
                bv[i].print();
            }
            System.out.println("+++++++++++++");
        }
    }

    private static void NewtonRaphsonMethod() {
        System.out.println("=====Newton-Raphson Method=====");
    }

    private static void SecantMethod() {
        System.out.println("=====Secant Method=====");
    }

    private static void FalsePositionMethod() {
        System.out.println("=====False Position Method=====");
    }

    private static void ModSecantMethod() {
        System.out.println("=====Modified Secant Method=====");
    }
}
