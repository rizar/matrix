package com.github.rizar.runners;

import com.github.rizar.matrix.ArrayMatrix;
import com.github.rizar.matrix.BigDecimalMatrix;
import com.github.rizar.matrix.BigDecimalVectorColumn;
import com.github.rizar.matrix.BigDecimalVectorRow;
import com.github.rizar.solvers.ShuttleSolver;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 *
 * @author Rizar
 */
public class ShuttleRunner
{
    public final static int SCALE = 6;

    public ShuttleRunner()
    {
    }

    private int N = 6;

    private BigDecimalMatrix getBigA()
    {
        BigDecimal[][] a = new BigDecimal[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = BigDecimal.ZERO;
        for (int i = 0; i < N; i++)
        {
            if (i - 1 >= 0)
                a[i][i - 1] = BigDecimal.valueOf(2);
            a[i][i] = BigDecimal.valueOf(7);
            if (i + 1 < N)
                a[i][i + 1] = BigDecimal.valueOf(3);
        }
        return new BigDecimalMatrix(new ArrayMatrix<BigDecimal>(a), SCALE, false);
    }

    private BigDecimalVectorColumn getD()
    {
        BigDecimal[][] d = new BigDecimal[N][1];
        for (int i = 0; i < N; i++)
            d[i][0] = BigDecimal.valueOf(10);
        return new BigDecimalVectorColumn(new BigDecimalMatrix(new ArrayMatrix<BigDecimal>(
                d), SCALE, false));
    }

    public void doShuttle()
    {
        BigDecimalMatrix bigA = getBigA();
        BigDecimalVectorColumn d = getD();

        BigDecimalVectorRow a = new BigDecimalVectorRow(N, SCALE),
                b = new BigDecimalVectorRow(N, SCALE),
                c = new BigDecimalVectorRow(N, SCALE);
        for (int i = 1; i <= N; i++)
        {
            a.setElement(i, i > 1 ? bigA.getElement(i, i - 1) : BigDecimal.ZERO);
            b.setElement(i, bigA.getElement(i, i).negate());
            c.setElement(i, i < N ? bigA.getElement(i, i + 1) : BigDecimal.ZERO);
        }

        ShuttleSolver solver = new ShuttleSolver(a, b, c, d, SCALE);
        solver.setLogWriter(new PrintWriter(System.out));
        BigDecimalVectorColumn x = solver.solve();

        BigDecimalMatrix dis = bigA.multiply(x).subtract(d);
        System.out.println("ax - b:");
        System.out.println(dis);
        System.out.println();
        System.out.println("||ax - b||^2:");
        System.out.println(dis.squaredEuclidianNorm());
    }

    public static void main(String args[])
    {
        new ShuttleRunner().doShuttle();
    }
}
