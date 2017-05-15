import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * (a) f(x) = 2x3 - 11.7x2 + 17.7x - 5
 */
public class Assignment2 {

    private static final int MAX_ITERATIONS = 100;
    private static final double MINIMUM_ERROR = 0.0001;
    private static NumberFormat formatter = new DecimalFormat("#0.0000");

    private static Equation problemA = new Cubic(2, -11.7, 17.7, -5);
    private static Equation problemAPrime = new Cubic(0, 6, -23.4, 17.7);
    private static Equation problemB = new HyperbolicB();
    private static Equation problemBPrime = new HyperbolicBPrime();

    private static Equation currProblem;
    private static Equation currProblemPrime;

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
    private static final double MOD_SECANT_DELTA_P1 = 0.01;

    //constants for Problem (b)
    private static final double BISECTION_A_P2 = 120;
    private static final double BISECTION_B_P2 = 130;
    private static final double NEWTON_RAPHSON_X_P2 = 130;
    private static final double MOD_SECANT_DELTA_P2 = 0.01;

    private static Scanner in;

    public static void main(String[] args) {

        in = new Scanner(System.in);

        while(true) {
            boolean validResp = false;
            while(!validResp) {
                System.out.println("Which function would you like to use to locate zeroes?");
                System.out.println("1. f(x) = 2(x^3) - 11.7(x^2) + 17.7(x) - 5");
                System.out.println("2. f(x) = x + 10 - xcosh(50/x)");
                System.out.println("3. Exit program");

                char choice = in.nextLine().charAt(0);
                System.out.println();
                validResp = true;
                switch(choice){
                    case '1':
                        currProblem = problemA;
                        currProblemPrime = problemAPrime;
                        break;
                    case '2':
                        currProblem = problemB;
                        currProblemPrime = problemBPrime;
                        break;
                    case '3':
                        System.out.println("Program terminating...");
                        return;
                    default:
                        System.out.println("Invalid response.");
                        validResp = false;
                }
            }

            RootLocatingMethod [] rlm = null;

            validResp = false;
            while(!validResp) {
                System.out.println("Which method would you like to use?");
                System.out.println("1. Bisection Method");
                System.out.println("2. Newton-Raphson Method");
                System.out.println("3. Secant Method");
                System.out.println("4. False-Position Method");
                System.out.println("5. Modified Secant Method");

                char choice = in.nextLine().charAt(0);
                System.out.println();
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

            for(int i = 0; i < rlm.length; ++i) {
                System.out.println("n\tx\t\t\tf(x)\t\t\tapprox. error");
                // find first zero
                rlm[i].print();
                while(rlm[i].getAError() > MINIMUM_ERROR && rlm[i].getN() < MAX_ITERATIONS
                        && rlm[i].getEstFuncVal() != 0 && Math.abs(rlm[i].getEstFuncVal()) < 1000 &&
                        Math.abs(rlm[i].getEstVal()) < 1000){
                    rlm[i].next();
                    rlm[i].print();
                }
                System.out.println("+++++++++++++");
            }
            System.out.println();
            System.out.println();
        }
    }

    private static RootLocatingMethod [] InitBisectionMethod() {
        RootLocatingMethod [] bm = null;

        System.out.println("=====Bisection Method=====");

        boolean validResp = false;
        while(!validResp) {
            System.out.println("Would you like too...");
            System.out.println("1. Use hardcoded starting points?");
            System.out.println("2. Enter your own starting points?");

            char choice = in.nextLine().charAt(0);
            System.out.println();
            validResp = true;
            switch(choice){
                case '1':
                    if(currProblem == problemA) {
                        bm = new Bisection[3];
                        bm[0] = new Bisection(BISECTION_A1_P1, BISECTION_B1_P1, currProblem);
                        bm[1] = new Bisection(BISECTION_A2_P1, BISECTION_B2_P1, currProblem);
                        bm[2] = new Bisection(BISECTION_A3_P1, BISECTION_B3_P1, currProblem);
                    } else {
                        bm = new Bisection[1];
                        bm[0] = new Bisection(BISECTION_A_P2, BISECTION_B_P2, currProblem);
                    }
                    break;
                case '2':
                    if(currProblem == problemA) {
                        bm = new Bisection[3];
                        bm[0] = bisectionInput();
                        bm[1] = bisectionInput();
                        bm[2] = bisectionInput();
                    } else {
                        bm = new Bisection[1];
                        bm[0] = bisectionInput();
                    }
                    break;
                default:
                    System.out.println("Invalid response.");
                    validResp = false;
            }
        }

        return bm;
    }

    private static Bisection bisectionInput() {
        boolean validInput = false;
        Bisection bm = null;
        while(!validInput) {
            System.out.println("Enter x-values that bracket a root.");
            double a = in.nextDouble();
            double b = in.nextDouble();
            if(currProblem.evaluate(a) * currProblem.evaluate(b) < 0) {
                validInput = true;
                if(currProblem.evaluate(a) < currProblem.evaluate(b)) {
                    bm = new Bisection(a, b, currProblem);
                } else {
                    bm = new Bisection(b, a, currProblem);
                }
            } else {
                System.out.println("X-values do not bracket root.");
            }
        }
        in.nextLine();

        return bm;
    }

    private static RootLocatingMethod [] InitNewtonRaphsonMethod() {
        RootLocatingMethod [] nr = null;;

        System.out.println("=====Newton-Raphson Method=====");

        boolean validResp = false;
        while(!validResp) {
            System.out.println("Would you like too...");
            System.out.println("1. Use hardcoded starting points?");
            System.out.println("2. Enter your own starting points?");

            char choice = in.nextLine().charAt(0);
            System.out.println();
            validResp = true;
            switch(choice){
                case '1':
                    if(currProblem == problemA) {
                        nr = new NewtonRaphson[3];
                        nr[0] = new NewtonRaphson(NEWTON_RAPHSON_X1_P1, currProblem, currProblemPrime);
                        nr[1] = new NewtonRaphson(NEWTON_RAPHSON_X2_P1, currProblem, currProblemPrime);
                        nr[2] = new NewtonRaphson(NEWTON_RAPHSON_X3_P1, currProblem, currProblemPrime);
                    } else {
                        nr = new NewtonRaphson[1];
                        nr[0] = new NewtonRaphson(NEWTON_RAPHSON_X_P2, currProblem, currProblemPrime);
                    }
                    break;
                case '2':
                    if(currProblem == problemA) {
                        nr = new NewtonRaphson[3];
                        nr[0] = NewtonRaphsonInput();
                        nr[1] = NewtonRaphsonInput();
                        nr[2] = NewtonRaphsonInput();
                    } else {
                        nr = new NewtonRaphson[1];
                        nr[0] = NewtonRaphsonInput();
                    }
                    break;
                default:
                    System.out.println("Invalid response.");
                    validResp = false;
            }
        }

        return nr;
    }

    private static RootLocatingMethod NewtonRaphsonInput() {
        System.out.println("Enter an x-value.");
        double x = in.nextDouble();
        in.nextLine();

        return new NewtonRaphson(x, currProblem, currProblemPrime);
    }

    private static RootLocatingMethod [] InitSecantMethod() {
        RootLocatingMethod [] s = null;;

        System.out.println("=====Secant Method=====");

        boolean validResp = false;
        while(!validResp) {
            System.out.println("Would you like too...");
            System.out.println("1. Use hardcoded starting points?");
            System.out.println("2. Enter your own starting points?");

            char choice = in.nextLine().charAt(0);
            System.out.println();
            validResp = true;
            switch(choice){
                case '1':
                    if(currProblem == problemA) {
                        s = new Secant[3];
                        s[0] = new Secant(BISECTION_A1_P1, BISECTION_B1_P1, currProblem);
                        s[1] = new Secant(BISECTION_A2_P1, BISECTION_B2_P1, currProblem);
                        s[2] = new Secant(BISECTION_A3_P1, BISECTION_B3_P1, currProblem);
                    } else {
                        s = new Secant[1];
                        s[0] = new Secant(BISECTION_A_P2, BISECTION_B_P2, currProblem);
                    }
                    break;
                case '2':
                    if(currProblem == problemA) {
                        s = new Secant[3];
                        s[0] = SecantInput();
                        s[1] = SecantInput();
                        s[2] = SecantInput();
                    } else {
                        s = new Secant[1];
                        s[0] = SecantInput();
                    }
                    break;
                default:
                    System.out.println("Invalid response.");
                    validResp = false;
            }
        }

        return s;
    }

    private static RootLocatingMethod SecantInput() {
        System.out.println("Enter two x-values.");
        double a = in.nextDouble();
        double b = in.nextDouble();
        in.nextLine();

        return new Secant(a, b, currProblem);
    }

    private static RootLocatingMethod [] InitFalsePositionMethod() {
        RootLocatingMethod [] fp = null;;

        System.out.println("=====False Position Method=====");

        boolean validResp = false;
        while(!validResp) {
            System.out.println("Would you like too...");
            System.out.println("1. Use hardcoded starting points?");
            System.out.println("2. Enter your own starting points?");

            char choice = in.nextLine().charAt(0);
            System.out.println();
            validResp = true;
            switch(choice){
                case '1':
                    if(currProblem == problemA) {
                        fp = new FalsePosition[3];
                        fp[0] = new FalsePosition(BISECTION_A1_P1, BISECTION_B1_P1, currProblem);
                        fp[1] = new FalsePosition(BISECTION_A2_P1, BISECTION_B2_P1, currProblem);
                        fp[2] = new FalsePosition(BISECTION_A3_P1, BISECTION_B3_P1, currProblem);
                    } else {
                        fp = new FalsePosition[1];
                        fp[0] = new FalsePosition(BISECTION_A_P2, BISECTION_B_P2, currProblem);
                    }
                    break;
                case '2':
                    if(currProblem == problemA) {
                        fp = new FalsePosition[3];
                        fp[0] = FalsePositionInput();
                        fp[1] = FalsePositionInput();
                        fp[2] = FalsePositionInput();
                    } else {
                        fp = new FalsePosition[1];
                        fp[0] = FalsePositionInput();
                    }
                    break;
                default:
                    System.out.println("Invalid response.");
                    validResp = false;
            }
        }

        return fp;
    }

    private static RootLocatingMethod FalsePositionInput() {
        boolean validInput = false;
        FalsePosition fp = null;
        while(!validInput) {
            System.out.println("Enter x-values that bracket a root.");
            double a = in.nextDouble();
            double b = in.nextDouble();
            if(currProblem.evaluate(a) * currProblem.evaluate(b) < 0) {
                validInput = true;
                if(currProblem.evaluate(a) < currProblem.evaluate(b)) {
                    fp = new FalsePosition(a, b, currProblem);
                } else {
                    fp = new FalsePosition(b, a, currProblem);
                }
            } else {
                System.out.println("X-values do not bracket root.");
            }
        }
        in.nextLine();

        return fp;
    }

    private static RootLocatingMethod [] InitModSecantMethod() {
        RootLocatingMethod [] ms = null;;

        System.out.println("=====Modified Secant Method=====");

        boolean validResp = false;
        while(!validResp) {
            System.out.println("Would you like too...");
            System.out.println("1. Use hardcoded starting points?");
            System.out.println("2. Enter your own starting points?");

            char choice = in.nextLine().charAt(0);
            System.out.println();
            validResp = true;
            switch(choice){
                case '1':
                    if(currProblem == problemA) {
                        ms = new ModSecant[3];
                        ms[0] = new ModSecant(NEWTON_RAPHSON_X1_P1, MOD_SECANT_DELTA_P1, currProblem);
                        ms[1] = new ModSecant(NEWTON_RAPHSON_X2_P1, MOD_SECANT_DELTA_P1, currProblem);
                        ms[2] = new ModSecant(NEWTON_RAPHSON_X3_P1, MOD_SECANT_DELTA_P1, currProblem);
                    } else /*Problem2*/ {
                        ms = new ModSecant[1];
                        ms[0] = new ModSecant(NEWTON_RAPHSON_X_P2, MOD_SECANT_DELTA_P2, currProblem);
                    }
                    break;
                case '2':
                    if(currProblem == problemA) {
                        ms = new ModSecant[3];
                        ms[0] = ModSecantInput();
                        ms[1] = ModSecantInput();
                        ms[2] = ModSecantInput();
                    } else /*Problem2*/ {
                        ms = new ModSecant[1];
                        ms[0] = ModSecantInput();
                    }
                    break;
                default:
                    System.out.println("Invalid response.");
                    validResp = false;
            }
        }

        return ms;
    }

    private static RootLocatingMethod ModSecantInput() {
        System.out.println("Enter an x-value.");
        double x = in.nextDouble();
        System.out.println("Enter a delta value.");
        double d = in.nextDouble();
        in.nextLine();

        return new ModSecant(x, d, currProblem);
    }
}
