package com.github.rizar.runners;

import com.github.rizar.matrix.BigDecimalMatrix;
import com.github.rizar.solvers.SquareRootSolver;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 *
 * @author Rizar
 */
public class SquareRootRunner
{
    public final static int SCALE = 6;

    public void doSolve()
    {
        BigDecimalMatrix a = new BigDecimalMatrix(3, 3, SCALE);
        for (int i = 1; i <= a.getHeight(); i++)
            for (int j = i; j <= a.getWidth(); j++)
            {
                a.setElement(i, j, BigDecimal.valueOf(i * i - j * j * j));
                a.setElement(j, i, a.getElement(i, j));
            }
        a.setElement(1, 1, BigDecimal.valueOf(7));
        BigDecimalMatrix b = new BigDecimalMatrix(3, 1, SCALE);
        for (int i = 1; i <= a.getHeight(); i++)
            b.setElement(i, 1, BigDecimal.valueOf(i));

        PrintWriter pw = new PrintWriter(System.out);
        SquareRootSolver rs = new SquareRootSolver(a, b, SCALE);
        rs.setLogWriter(pw);
        BigDecimalMatrix x = rs.solve();
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
            new SquareRootRunner().doSolve();
        }
        catch (Exception e)
        {    
            e.printStackTrace();
        }
    }
}
