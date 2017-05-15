import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Connor on 5/14/2017.
 */
public abstract class RootLocatingMethod {
    protected int n;
    protected double aError;
    protected static NumberFormat formatter = new DecimalFormat("#0.0000");

    public abstract void next();
    public abstract double getEstFuncVal();
    public abstract void print();

    public int getN() {
        return n;
    }

    public double getAError() {
        return aError;
    }
}
