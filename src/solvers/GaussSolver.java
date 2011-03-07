package solvers;

import java.math.RoundingMode;
import java.io.PrintWriter;
import matrix.BigDecimalMatrix;
import static java.math.BigDecimal.*;

/**
 *
 * @author Dmitry Bogdanov
 */
public class GaussSolver
{
    private BigDecimalMatrix a, b;
    private int scale;

    public GaussSolver(BigDecimalMatrix a, BigDecimalMatrix b, int scale)
    {
        this.a = new BigDecimalMatrix(a, scale, true);
        this.b = new BigDecimalMatrix(b, scale, true);
        this.scale = scale;
    }
    private PrintWriter logWriter = null;

    public void setLogWriter(PrintWriter logWriter)
    {
        this.logWriter = logWriter;
    }

    private void log(String message, BigDecimalMatrix matrix)
    {
        if (logWriter == null)
            return;
        logWriter.println(message);
        logWriter.println(matrix);
        logWriter.println();
        logWriter.flush();
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
                if (!ext.getElement(1, j).equals(ZERO))
                {
                    ext.swapRows(1, j);
                    break;
                }

            ext.multiplyRow(1, ONE.divide(ext.getElement(1, 1), scale, RoundingMode.HALF_UP));
            ext.zeroFirstColumn();

            log("Step " + i + " extended matrix after transformations:", ext);
        }

        BigDecimalMatrix x = new BigDecimalMatrix(a.getWidth(), b.getWidth(), scale);
        for (int j = 1; j <= b.getWidth(); j++)
            for (int i = a.getWidth(); i >= 1; i--)
            {
                x.setElement(i, j, b.getElement(i, j));
                for (int k = i + 1; k <= a.getWidth(); k++)
                    x.setElement(i, j, x.getElement(i, j).subtract(x.getElement(k, j).multiply(a.getElement(i, k))).setScale(scale, RoundingMode.HALF_UP));
                x.setElement(i, j, x.getElement(i, j).divide(a.getElement(i, i), scale, RoundingMode.HALF_UP));
            }

        log("Matrix x:", x);

        return x;
    }
}
