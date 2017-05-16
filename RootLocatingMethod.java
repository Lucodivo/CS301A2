/**
 * Connor A. Haskins
 * Bronco ID# 010215400
 * CS 301 w/ Professor Raheja
 * Project 2: Root Locating Methods
 * Due Date: May 15th
 */

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * An abstract class made to simplify the root locating methods
 * Creates an interface to interact with the methods in an interchangeable way
 */
public abstract class RootLocatingMethod {
    protected int n;
    protected Equation p;
    protected double aError;
    protected static NumberFormat formatter = new DecimalFormat("#0.0000");

    public abstract void next();
    public abstract double getEstVal();
    public abstract double getEstFuncVal();

    public int getN() {
        return n;
    }

    public double getAError() {
        return aError;
    }

    public void print() {
        if(n > 0) {
            System.out.println(n + "\t" + formatter.format(getEstVal()) + "\t\t" + formatter.format(getEstFuncVal()) +
                    "\t\t\t" + formatter.format(getAError()));
        } else {
            System.out.println("0\t" + formatter.format(getEstVal()) + "\t\t" + formatter.format(getEstFuncVal()) +
                    "\t\t\t----");
        }
        //System.out.println(n + "\t\t" + a + "\t\t" + b + "\t\t" + c
        //        + "\t\t" + fA + "\t\t" + fB + "\t\t" + fC + "\t\t" + fC + "\t\t" + aError);
    }
}
