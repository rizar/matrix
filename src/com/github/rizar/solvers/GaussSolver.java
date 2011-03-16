package com.github.rizar.solvers;

import java.math.RoundingMode;
import com.github.rizar.matrix.BigDecimalMatrix;
import java.math.BigDecimal;

/**
 *
 * @author Rizar
 */
public class GaussSolver extends AbstractSolver
{
    private BigDecimalMatrix a, b;
    private int scale;

    public GaussSolver(BigDecimalMatrix a, BigDecimalMatrix b, int scale)
    {
        this.a = new BigDecimalMatrix(a, scale, true);
        this.b = new BigDecimalMatrix(b, scale, true);
        this.scale = scale;
    }
    
    public BigDecimalMatrix solve()
    {
        log("Matrix a: ", a);
        log("Matrix b: ", b);

        for (int i = 1; i < a.getHeight(); i++)
        {
            BigDecimalMatrix ext = new BigDecimalMatrix(
                    a.subMatrix(i, i).extendedMatrix(b.subMatrix(i, 1)),
                    scale, false);

            log("Step " + i + " extended matrix:", ext);

            for (int j = 1; j < ext.getHeight(); j++)
                if (!ext.getElement(1, j).equals(BigDecimal.ZERO))
                {
                    ext.swapRows(1, j);
                    break;
                }

            ext.multiplyRow(1, BigDecimal.ONE.divide(ext.getElement(1, 1), scale, RoundingMode.HALF_UP));
            ext.zeroFirstColumn();

            log("Step " + i + " extended matrix after transformations:", ext);
        }

        BigDecimalMatrix x = new UpperTriangularSolver(a, b, scale).solve();

        log("Matrix x:", x);

        return x;
    }
}
