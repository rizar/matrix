package com.github.rizar.solvers;

import com.github.rizar.matrix.BigDecimalMatrix;
import java.math.RoundingMode;

/**
 *
 * @author Rizar
 */
public class UpperTriangularSolver extends AbstractSolver
{
    private BigDecimalMatrix a, b;

    private int scale;

    public UpperTriangularSolver(BigDecimalMatrix a, BigDecimalMatrix b, int scale)
    {
        this.a = a;
        this.b = b;
        this.scale = scale;
    }

    BigDecimalMatrix solve()
    {
        log("Matrix a:", a);
        log("Matrix b:", b);

        BigDecimalMatrix x = new BigDecimalMatrix(a.getWidth(), b.getWidth(),
                scale);
        for (int j = 1; j <= b.getWidth(); j++)
            for (int i = a.getWidth(); i >= 1; i--)
            {
                x.setElement(i, j, b.getElement(i, j));
                for (int k = i + 1; k <= a.getWidth(); k++)
                    x.setElement(i, j, x.getElement(i, j).subtract(x.getElement(
                            k, j).multiply(a.getElement(i, k))).setScale(scale,
                            RoundingMode.HALF_UP));
                x.setElement(i, j, x.getElement(i, j).divide(a.getElement(i, i),
                        scale, RoundingMode.HALF_UP));
            }

        log("Matrix x:", x);

        return x;
    }
}
