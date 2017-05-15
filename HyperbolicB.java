/**
 * Hardcoded class. Not reusable.
 * Made for problem (b)
 */
public class HyperbolicB implements Equation{
    private double c = 10;

    public HyperbolicB() {}

    @Override
    public double evaluate(double xVal) {
        double result = xVal + c - (xVal * Math.cosh(50 / xVal));
        return result;
    }
}
