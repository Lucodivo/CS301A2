import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * (a) f(x) = 2x3 - 11.7x2 + 17.7x - 5
 */
public class Assignment2 {

    private static final int MAX_ITERATIONS = 100;
    private static final double MINIMUM_ERROR = 0.01;
    private static NumberFormat formatter = new DecimalFormat("#0.0000");

    private static Equation problemA = new Cubic(2, -11.7, 17.7, -5);
    private static Equation problemAPrime = new Cubic(0, 6, -23.4, 17.7);
    private static Equation problemB = new HyperbolicB();
    private static Equation problemBPrime = new HyperbolicBPrime();

    private static Equation currProblem = problemA;
    private static Equation currProblemPrime = problemAPrime;

    // constants for Problem (a)
    private static final double BISECTION_A1_P1 = 0;
    private static final double BISECTION_B1_P1 = 1;
    private static final double BISECTION_A2_P1 = 3;
    private static final double BISECTION_B2_P1 = 1;
    private static final double BISECTION_A3_P1 = 3;
    private static final double BISECTION_B3_P1 = 4;

    private static final double NEWTON_RAPHSON_X1_P1 = 0.75;
    private static final double NEWTON_RAPHSON_X2_P1 = 1.5;
    private static final double NEWTON_RAPHSON_X3_P1 = 3.0;

    private static final double MOD_SECANT_DELTA = 0.01;

    //constants for Problem (b)
    private static final double BISECTION_A_P2 = 120;
    private static final double BISECTION_B_P2 = 130;

    private static Scanner in;

    public static void main(String[] args) {
        System.out.println("Which function would you like to use to locate zeroes?");
        System.out.println("1. f(x) = 2(x^3) - 11.7(x^2) + 17.7(x) - 5");
        System.out.println("2. f(x) = x + 10 - xcosh(50/x)");

        in = new Scanner(System.in);
        RootLocatingMethod [] rlm = null;

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
                    rlm = InitBisectionMethod();
                    break;
                case '2':
                    rlm = InitNewtonRaphsonMethod();
                    break;
                case '3':
                    rlm = InitSecantMethod();
                    break;
                case '4':
                    rlm = InitFalsePositionMethod();
                    break;
                case '5':
                    rlm = InitModSecantMethod();
                    break;
                default:
                    System.out.println("Invalid response.");
                    validResp = false;
            }
        }

        if(currProblem == problemA) {
            for(int i = 0; i < rlm.length; ++i) {
                System.out.println("n\tx\t\t\tf(x)\t\t\tapprox. error");
                // find first zero
                rlm[i].print();
                while(rlm[i].getAError() > MINIMUM_ERROR && rlm[i].getN() < MAX_ITERATIONS
                        && rlm[i].getEstFuncVal() != 0 && Math.abs(rlm[i].getEstFuncVal()) < 1000){
                    rlm[i].next();
                    rlm[i].print();
                }
                System.out.println("+++++++++++++");
            }
        }
    }

    private static RootLocatingMethod [] InitBisectionMethod() {
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
                    if(currProblem == problemA) {
                        bv[0] = new Bisection(BISECTION_A1_P1, BISECTION_B1_P1, currProblem);
                        bv[1] = new Bisection(BISECTION_A2_P1, BISECTION_B2_P1, currProblem);
                        bv[2] = new Bisection(BISECTION_A3_P1, BISECTION_B3_P1, currProblem);
                    } else {

                    }
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

        return bv;
    }

    private static RootLocatingMethod [] InitNewtonRaphsonMethod() {
        RootLocatingMethod [] nr = new NewtonRaphson[3];

        System.out.println("=====Newton-Raphson Method=====");

        boolean validResp = false;
        while(!validResp) {
            System.out.println("Would you like too...");
            System.out.println("1. Use hardcoded starting points?");
            System.out.println("2. Enter your own starting points?");

            char choice = in.nextLine().charAt(0);
            validResp = true;
            switch(choice){
                case '1':
                    if(currProblem == problemA) {
                        nr[0] = new NewtonRaphson(NEWTON_RAPHSON_X1_P1, currProblem, currProblemPrime);
                        nr[1] = new NewtonRaphson(NEWTON_RAPHSON_X2_P1, currProblem, currProblemPrime);
                        nr[2] = new NewtonRaphson(NEWTON_RAPHSON_X3_P1, currProblem, currProblemPrime);
                    } else {

                    }
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

        return nr;
    }

    private static RootLocatingMethod [] InitSecantMethod() {
        RootLocatingMethod [] s = new Secant[3];

        System.out.println("=====Secant Method=====");

        boolean validResp = false;
        while(!validResp) {
            System.out.println("Would you like too...");
            System.out.println("1. Use hardcoded starting points?");
            System.out.println("2. Enter your own starting points?");

            char choice = in.nextLine().charAt(0);
            validResp = true;
            switch(choice){
                case '1':
                    if(currProblem == problemA) {
                        s[0] = new Secant(BISECTION_A1_P1, BISECTION_B1_P1, currProblem);
                        s[1] = new Secant(BISECTION_A2_P1, BISECTION_B2_P1, currProblem);
                        s[2] = new Secant(BISECTION_A3_P1, BISECTION_B3_P1, currProblem);
                    } else {

                    }
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

        return s;
    }

    private static RootLocatingMethod [] InitFalsePositionMethod() {
        RootLocatingMethod [] fp = new FalsePosition[3];

        System.out.println("=====False Position Method=====");

        boolean validResp = false;
        while(!validResp) {
            System.out.println("Would you like too...");
            System.out.println("1. Use hardcoded starting points?");
            System.out.println("2. Enter your own starting points?");

            char choice = in.nextLine().charAt(0);
            validResp = true;
            switch(choice){
                case '1':
                    if(currProblem == problemA) {
                        fp[0] = new FalsePosition(BISECTION_A1_P1, BISECTION_B1_P1, currProblem);
                        fp[1] = new FalsePosition(BISECTION_A2_P1, BISECTION_B2_P1, currProblem);
                        fp[2] = new FalsePosition(BISECTION_A3_P1, BISECTION_B3_P1, currProblem);
                    } else {

                    }
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

        return fp;
    }

    private static RootLocatingMethod [] InitModSecantMethod() {
        RootLocatingMethod [] ms = new ModSecant[3];

        System.out.println("=====Modified Secant Method=====");

        boolean validResp = false;
        while(!validResp) {
            System.out.println("Would you like too...");
            System.out.println("1. Use hardcoded starting points?");
            System.out.println("2. Enter your own starting points?");

            char choice = in.nextLine().charAt(0);
            validResp = true;
            switch(choice){
                case '1':
                    if(currProblem == problemA) {
                        ms[0] = new ModSecant(NEWTON_RAPHSON_X1_P1, MOD_SECANT_DELTA, currProblem);
                        ms[1] = new ModSecant(NEWTON_RAPHSON_X2_P1, MOD_SECANT_DELTA, currProblem);
                        ms[2] = new ModSecant(NEWTON_RAPHSON_X3_P1, MOD_SECANT_DELTA, currProblem);
                    } else {

                    }
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

        return ms;
    }
}