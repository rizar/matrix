package com.github.rizar.solvers;

import com.github.rizar.matrix.BigDecimalVectorColumn;
import com.github.rizar.matrix.BigDecimalVectorRow;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Rizar
 */
public class ShuttleSolver extends AbstractSolver
{
    private BigDecimalVectorRow a, b, c;
    private BigDecimalVectorColumn d;
    private int scale;

    public ShuttleSolver(BigDecimalVectorRow a,
                         BigDecimalVectorRow b,
                         BigDecimalVectorRow c,
                         BigDecimalVectorColumn d,
                         int scale)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.scale = scale;
    }

    public BigDecimalVectorColumn solve()
    {
        log("Vector a: ", a);
        log("Vector b: ", b);
        log("Vector c: ", c);
        log("Vector d: ", d);

        int n = b.getWidth();
        BigDecimalVectorRow xi = new BigDecimalVectorRow(n, scale);
        BigDecimalVectorRow eta = new BigDecimalVectorRow(n, scale);
        xi.setElement(2, c.getElement(2).divide(b.getElement(2), scale, RoundingMode.HALF_UP));
        eta.setElement(2, d.getElement(2).divide(b.getElement(2), scale, RoundingMode.HALF_UP).
                negate());
        for (int i = 2; i < n; i++)
        {
            BigDecimal denominator = b.getElement(i).subtract(a.getElement(i).
                    multiply(xi.getElement(i))).setScale(scale, RoundingMode.HALF_UP);
            xi.setElement(i + 1, c.getElement(i).divide(denominator, scale, RoundingMode.HALF_UP));
            BigDecimal numerator = a.getElement(i).multiply(eta.getElement(i)).
                    subtract(d.getElement(i)).setScale(scale, RoundingMode.HALF_UP);
            eta.setElement(i + 1, numerator.divide(denominator, scale, RoundingMode.HALF_UP));
        }

        log("Vector xi: ", xi);
        log("Vector eta: ", eta);

        BigDecimalVectorColumn x = new BigDecimalVectorColumn(n, scale);
        BigDecimal numerator = d.getElement(n).subtract(a.getElement(n).multiply(eta.
                getElement(n))).setScale(scale);
        BigDecimal denominator = a.getElement(n).multiply(xi.getElement(n)).
                subtract(b.getElement(n)).setScale(scale, RoundingMode.HALF_UP);
        x.setElement(n, numerator.divide(denominator, scale, RoundingMode.HALF_UP));
        for (int i = n - 1; i >= 1; i--)
            x.setElement(i, x.getElement(i + 1).multiply(xi.getElement(i + 1)).
                    add(eta.getElement(i + 1)).setScale(scale, RoundingMode.HALF_UP));

        log("Vector x: ", x);

        return x;
    }
}
