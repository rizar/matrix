package com.github.rizar.runners;

import com.github.rizar.matrix.BigDecimalVectorColumn;
import com.github.rizar.matrix.BigDecimalVectorRow;
import com.github.rizar.solvers.ShuttleSolver;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 *
 * @author Rizar
 */
public class DifferencesRunner
{
    public final static int SCALE = 6;

    public final static int K = 5;

    public final static int N = 11;

    public final static double A = 0, B = 1;

    public final static double U_AT_A = 0, U_AT_B = Math.cos(1);

    public double calcPointByIndex(int index)
    {
        return ((index - 1) / (double) (N - 1)) * (B - A);
    }

    public double rightPart(double x)
    {
        return 2 - K * (x * x - 1) - Math.cos(x) * (K + 1);
    }

    public DifferencesRunner()
    {
    }

    public void solve() throws Exception
    {
        BigDecimalVectorRow a = new BigDecimalVectorRow(N, SCALE),
                b = new BigDecimalVectorRow(N, SCALE),
                c = new BigDecimalVectorRow(N, SCALE);
        double h = (B - A) / (N - 1);
        b.setElement(1, BigDecimal.valueOf(-1));
        for (int i = 2; i <= N - 1; i++)
        {
            a.setElement(i, BigDecimal.valueOf(1 / h / h));
            b.setElement(i, BigDecimal.valueOf(2 / h / h + K));
            c.setElement(i, BigDecimal.valueOf(1 / h / h));
        }
        b.setElement(N, BigDecimal.valueOf(-1));
        
        BigDecimalVectorColumn d = new BigDecimalVectorColumn(N, SCALE);
        d.setElement(1, BigDecimal.valueOf(U_AT_A));
        for (int i = 2; i <= N - 1; i++)
        {
            double x = calcPointByIndex(i);
            d.setElement(i, BigDecimal.valueOf(rightPart(x)));
        }
        d.setElement(N, BigDecimal.valueOf(U_AT_B));

        PrintWriter pw = new PrintWriter("result.txt");

        ShuttleSolver solver = new ShuttleSolver(a, b, c, d, SCALE);
        solver.setLogWriter(pw);
        BigDecimalVectorColumn u = solver.solve();

        pw.println("Values of u:");
        for (int i = 1; i <= N; i++)
        {
            double x = calcPointByIndex(i);
            pw.println(u.getElement(i).toPlainString() + " at " + x);
        }
    }

    public static void main(String[] args)
    {
        try
        {
            new DifferencesRunner().solve();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
