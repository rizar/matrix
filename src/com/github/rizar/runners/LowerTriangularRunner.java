package com.github.rizar.runners;

import com.github.rizar.matrix.BigDecimalMatrix;
import com.github.rizar.solvers.LowerTriangularSolver;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 *
 * @author Rizar
 */
public class LowerTriangularRunner
{
    public final static int SCALE = 5;

    public void doSolve()
    {
        BigDecimalMatrix a = new BigDecimalMatrix(3, BigDecimal.ZERO, SCALE);
        BigDecimalMatrix b = new BigDecimalMatrix(3, 1, SCALE);
        for (int i = 1; i <= 3; i++)
            for (int j = 1; j <= i; j++)
                a.setElement(i, j, BigDecimal.valueOf(i + j));
        for (int i = 1; i <= 3; i++)
            b.setElement(i, 1, BigDecimal.valueOf(i));

        PrintWriter pw = new PrintWriter(System.out);
        LowerTriangularSolver ls = new LowerTriangularSolver(a, b, SCALE);
        ls.setLogWriter(pw);
        BigDecimalMatrix x = ls.solve();
        BigDecimalMatrix dis = a.multiply(x).subtract(b);

        pw.println("ax - b:");
        pw.println(dis);
        pw.println();
        pw.println("||ax - b||^2 = " + dis.squaredEuclidianNorm());
        pw.close();
    }

    public static void main(String [] args)
    {
        try
        {
            new LowerTriangularRunner().doSolve();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
