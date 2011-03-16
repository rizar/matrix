package com.github.rizar.solvers;

import com.github.rizar.matrix.BigDecimalMatrix;

/**
 *
 * @author Rizar
 */
public class LowerTriangularSolver extends AbstractSolver
{
    private BigDecimalMatrix a, b;
    private int scale;

    public LowerTriangularSolver(BigDecimalMatrix a, BigDecimalMatrix b, int scale)
    {
        this.a = new BigDecimalMatrix(a, true);
        this.b = new BigDecimalMatrix(b, true);
        this.scale = scale;
    }

    public BigDecimalMatrix solve()
    {
        log("Matrix a:", a);
        log("Matrix b:", b);

        a.reverseRows();
        b.reverseRows();
        a.reverseColumns();

        BigDecimalMatrix x = new UpperTriangularSolver(a, b, scale).solve();
        x.reverseRows();

        log("Matrix x:", x);

        return x;
    }
}
