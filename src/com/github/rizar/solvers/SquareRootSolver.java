package com.github.rizar.solvers;

/**
 *
 * @author Rizar
 */
import com.github.rizar.bigdecimalmath.BigDecimalMath;
import java.math.RoundingMode;
import com.github.rizar.matrix.BigDecimalMatrix;
import java.math.BigDecimal;

/**
 *
 * @author IluhaAndr (modified by Rizar)
 */
public class SquareRootSolver extends AbstractSolver
{
    private BigDecimalMatrix a, b;

    private int scale;

    public SquareRootSolver(BigDecimalMatrix a, BigDecimalMatrix b, int scale)
    {
        this.a = a;
        this.b = b;
        this.scale = scale;
    }

    public BigDecimalMatrix solve()
    {
        log("Matrix a:", a);
        log("Matrix b:", b);

        BigDecimalMatrix d = new BigDecimalMatrix(a.getHeight(), a.getWidth(),
                scale);
        BigDecimalMatrix s = new BigDecimalMatrix(a.getHeight(), a.getWidth(),
                scale);
        for (int i = 1; i <= a.getHeight(); i++)
            for (int j = i; j <= a.getWidth(); j++)
            {
                BigDecimal temp = BigDecimal.ZERO;
                for (int k = 1; k <= i - 1; k++)
                    temp = temp.add(d.getElement(k, k).multiply(s.getElement(
                            k, i)).multiply(s.getElement(k, j)));
                temp = a.getElement(i, j).subtract(temp);
                if (i == j)
                {
                    d.setElement(i, i,
                            new BigDecimal(temp.compareTo(BigDecimal.ZERO)));
                    temp = BigDecimalMath.sqrt(temp.abs()).setScale(scale,
                            RoundingMode.HALF_UP);
                    s.setElement(i, i, temp);
                }
                else
                {
                    temp = temp.divide(
                            d.getElement(i, i).multiply(s.getElement(i, i)),
                            scale, RoundingMode.HALF_UP);
                    s.setElement(i, j, temp);
                }
            }
//
        BigDecimalMatrix st = new BigDecimalMatrix(s.transpose(), s.getScale(),
                false);

        log("Matrix st:", st);
        log("Matrix d:", d);
        log("Matrix s:", s);

        log("Matrix st * d * s:", st.multiply(d.multiply(s)));

        BigDecimalMatrix z = new LowerTriangularSolver(st, b, scale).solve();
        BigDecimalMatrix y = new UpperTriangularSolver(d, z, scale).solve();
        BigDecimalMatrix x = new UpperTriangularSolver(s, y, scale).solve();

        log("Matrix z:", z);
        log("Matrix y:", y);
        log("Matrix x:", x);

        return x;
    }
}
