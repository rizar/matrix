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
        for (int i = 1; i <= this.m.getHeight(); i++)
            for (int j = 1; j <= this.m.getWidth(); j++)
                this.m.set(i, j, i == j ? scalar : BigDecimal.ZERO);
        this.scale = scale;
    }

    public BigDecimalMatrix multiply(BigDecimalMatrix that)
    {
        //TODO check input
        BigDecimalMatrix res = new BigDecimalMatrix(this.getHeight(), that.getWidth(), BigDecimal.ZERO, scale);
        for (int i = 1; i <= res.getHeight(); i++)
            for (int j = 1; j <= res.getWidth(); j++)
                for (int k = 1; k <= this.getWidth(); k++)
                    res.set(i, j, res.getElement(i, j).add(this.getElement(i, k).multiply(that.getElement(k, j))).setScale(scale, RoundingMode.HALF_UP));
        return res;
    }

    public BigDecimalMatrix subtract(BigDecimalMatrix that)
    {
        BigDecimalMatrix res = new BigDecimalMatrix(getHeight(), getWidth(), BigDecimal.ZERO, scale);
        for (int i = 1; i <= getHeight(); i++)
            for (int j = 1; j <= getWidth(); j++)
                res.set(i, j, getElement(i, j).subtract(that.getElement(i, j)).setScale(scale, RoundingMode.HALF_UP));
        return res;
    }

    public void multiplyRow(int i, BigDecimal value)
    {
        for (int j = 1; j <= m.getWidth(); j++)
            m.set(i, j, m.getElement(i, j).multiply(value).setScale(scale, RoundingMode.HALF_UP));
    }

    public void multiplyRowAndAddToRow(int i1, int i2, BigDecimal value)
    {
        for (int j = 1; j <= m.getWidth(); j++)
            m.set(i2, j, m.getElement(i1, j).multiply(value).add(m.getElement(i2, j)).setScale(scale, RoundingMode.HALF_UP));
    }

    public void zeroFirstColumn()
    {
        for (int i = 2; i <= m.getHeight(); i++)
        {
            BigDecimal k = m.getElement(i, 1).divide(m.getElement(1, 1), scale, RoundingMode.HALF_UP).negate();
            multiplyRowAndAddToRow(1, i, k);
        }
    }

    public BigDecimal squaredEuclidianNorm()
    {
        BigDecimal res = BigDecimal.ZERO;
        for (int i = 1; i <= getHeight(); i++)
            for (int j = 1; j <= getWidth(); j++)
                res = res.add(getElement(i, j).multiply(getElement(i, j)));
        return res;
    }

    public int getHeight()
    {
        return m.getHeight();
    }

    public int getWidth()
    {
        return m.getWidth();
    }

    public BigDecimal getElement(int i, int j)
    {
        return m.getElement(i, j);
    }

    public void set(int i, int j, BigDecimal value)
    {
        m.set(i, j, value);
    }

    @Override
    public void printlnMatrix(PrintWriter pw)
    {
        for (int i = 1; i <= getHeight(); i++)
            for (int j = 1; j <= getWidth(); j++)
            {
                pw.print(getElement(i, j).toPlainString());
                if (j < getWidth())
                    pw.print(' ');
                else
                    pw.println();
            }
    }
}
