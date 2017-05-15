/**
 * Created by Connor on 5/8/2017.
 */
public class Polynomial implements Equation {

    private double x3;
    private double x2;
    private double x;
    private double c;

    public Polynomial(double x3, double x2, double x, double c) {
        this.x3 = x3;
        this.x2 = x2;
        this.x = x;
        this.c = c;
    }

    public double evaluate(double xVal) {
        double result = c + xVal * (x + xVal * (x2 + xVal * (x3)));
        return result;
    }
}
