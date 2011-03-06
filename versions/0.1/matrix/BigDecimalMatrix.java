package matrix;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Admin
 */
public class BigDecimalMatrix extends AbstractMatrix<BigDecimal>
{
    private Matrix<BigDecimal> m;
    private int scale;

    public BigDecimalMatrix(Matrix<BigDecimal> matrix, int scale)
    {
        this.m = matrix;
        this.scale = scale;
    }

    public BigDecimalMatrix(int n, int m, BigDecimal scalar, int scale)
    {
        this.m = new ArrayMatrix<BigDecimal>(n, m);
        for (int i = 1; i <= this.m.getN(); i++)
            for (int j = 1; j <= this.m.getM(); j++)
                this.m.set(i, j, i == j ? scalar : BigDecimal.ZERO);
        this.scale = scale;
    }

    public BigDecimalMatrix multiply(BigDecimalMatrix that)
    {
        //TODO check input
        BigDecimalMatrix res = new BigDecimalMatrix(this.getN(), that.getM(), BigDecimal.ZERO, scale);
        for (int i = 1; i <= res.getN(); i++)
            for (int j = 1; j <= res.getM(); j++)
                for (int k = 1; k <= this.getM(); k++)
                    res.set(i, j, res.get(i, j).add(this.get(i, k).multiply(that.get(k, j))).setScale(scale, RoundingMode.HALF_UP));
        return res;
    }

    public BigDecimalMatrix subtract(BigDecimalMatrix that)
    {
        BigDecimalMatrix res = new BigDecimalMatrix(getN(), getM(), BigDecimal.ZERO, scale);
        for (int i = 1; i <= getN(); i++)
            for (int j = 1; j <= getM(); j++)
                res.set(i, j, get(i, j).subtract(that.get(i, j)).setScale(scale, RoundingMode.HALF_UP));
        return res;
    }

    public void multiplyRow(int i, BigDecimal value)
    {
        for (int j = 1; j <= m.getM(); j++)
            m.set(i, j, m.get(i, j).multiply(value).setScale(scale, RoundingMode.HALF_UP));
    }

    public void multiplyRowAndAddToRow(int i1, int i2, BigDecimal value)
    {
        for (int j = 1; j <= m.getM(); j++)
            m.set(i2, j, m.get(i1, j).multiply(value).add(m.get(i2, j)).setScale(scale, RoundingMode.HALF_UP));
    }

    public void zeroFirstColumn()
    {
        for (int i = 2; i <= m.getN(); i++)
        {
            BigDecimal k = m.get(i, 1).divide(m.get(1, 1), scale, RoundingMode.HALF_UP).negate();
            multiplyRowAndAddToRow(1, i, k);
        }
    }

    public BigDecimal squaredEuclidianNorm()
    {
        BigDecimal res = BigDecimal.ZERO;
        for (int i = 1; i <= getN(); i++)
            for (int j = 1; j <= getM(); j++)
                res = res.add(get(i, j).multiply(get(i, j)));
        return res;
    }

    public int getN()
    {
        return m.getN();
    }

    public int getM()
    {
        return m.getM();
    }

    public BigDecimal get(int i, int j)
    {
        return m.get(i, j);
    }

    public void set(int i, int j, BigDecimal value)
    {
        m.set(i, j, value);
    }

    @Override
    public void printlnMatrix(PrintWriter pw)
    {
        for (int i = 1; i <= getN(); i++)
            for (int j = 1; j <= getM(); j++)
            {
                pw.print(get(i, j).toPlainString());
                if (j < getM())
                    pw.print(' ');
                else
                    pw.println();
            }
    }
}
