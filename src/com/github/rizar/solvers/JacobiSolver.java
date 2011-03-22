package com.github.rizar.solvers;

import com.github.rizar.matrix.BigDecimalMatrix;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Rizar
 */
public class JacobiSolver extends AbstractSolver
{
    private BigDecimalMatrix a, b, c, d;

    private int scale;

    public JacobiSolver(BigDecimalMatrix a, BigDecimalMatrix b, int scale)
    {
        this.a = a;
        this.b = b;
        this.scale = scale;
    }

    public BigDecimalMatrix solve()
    {
        log("Matrix a:", a);
        log("Matrix b:", b);

        c = new BigDecimalMatrix(a, scale + 2, true);
        d = new BigDecimalMatrix(b, scale + 2, true);
        for (int i = 1; i <= c.getHeight(); i++)
        {
            BigDecimal coof = BigDecimal.ONE.divide(c.getElement(i, i), scale + 2,
                    RoundingMode.HALF_UP);
            c.multiplyRow(i, coof);
            c.setElement(i, i, BigDecimal.ZERO);
            d.multiplyRow(i, coof);
        }
        c = c.multiply(BigDecimal.ONE.negate());

        log("Matrix c:", c);
        log("Matrix d:", d);
        log("c norm:", c.cubicNorm());
        log("d norm:", d.cubicNorm());

        BigDecimal eps = BigDecimal.ONE.scaleByPowerOfTen(-scale).divide(BigDecimal.
                valueOf(2), scale + 2, RoundingMode.HALF_UP);
        BigDecimal cNorm = c.cubicNorm();
        BigDecimal coof = d.cubicNorm().divide(BigDecimal.ONE.subtract(cNorm),
                scale, RoundingMode.HALF_UP);
        BigDecimal powCNorm = cNorm;
        int nSteps = 0;
        while (coof.multiply(powCNorm).compareTo(eps) == 1)
        {
            powCNorm = powCNorm.multiply(cNorm).setScale(scale + 2, RoundingMode.HALF_UP);
            nSteps++;
        }

        log("Steps needed:", BigDecimal.valueOf(nSteps));

        BigDecimalMatrix x = d;
        for (int k = 0; k < nSteps; k++)
        {
            x = c.multiply(x).add(d);
            log("After step " + (k + 1) + " Matrix x:", x);
        }

        return x;
    }
}
