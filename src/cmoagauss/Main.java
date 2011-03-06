package cmoagauss;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import matrix.ArrayMatrix;
import matrix.BigDecimalMatrix;
import matrix.GaussSolver;

/**
 *
 * @author Admin
 */
public class Main
{
    private int SCALE = 4;

    ArrayMatrix<BigDecimal> getA(int alpha)
    {
        double ad [][] = new double [4][4];
        ad[0][0] = 1; ad[0][1] = 2; ad[0][2] = 3; ad[0][3] = 4;
        ad[1][0] = 2; ad[1][1] = 2 - alpha; ad[1][2] = 5; ad[1][3] = 1;
        ad[2][0] = 3; ad[2][1] = 5; ad[2][2] = 1 + alpha / 10.0; ad[2][3] = 8;
        ad[3][0] = 4; ad[3][1] = 1; ad[3][2] = 8; ad[3][3] = 3 - alpha / 10.0;
        BigDecimal a [][] = new BigDecimal [4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                a[i][j] = BigDecimal.valueOf(ad[i][j]).setScale(SCALE);
        return new ArrayMatrix<BigDecimal>(4, 4, a);
    }

    ArrayMatrix<BigDecimal> getB(int alpha)
    {
        double bd [][] = new double [4][1];
        bd[0][0] = alpha;
        bd[1][0] = 3;
        bd[2][0] = 1 + (alpha - 1) / (double)alpha;
        bd[3][0] = 2 - (alpha + 1) / (double)alpha;
        BigDecimal b [][] = new BigDecimal[4][1];
        for (int i = 0; i < 4; i++)
            b[i][0] = BigDecimal.valueOf(bd[i][0]).setScale(SCALE);
        return new ArrayMatrix<BigDecimal>(4, 1, b);
    }

    public void doGauss()
    {
        PrintWriter pw;
        try
        {
            pw = new PrintWriter("result.txt");

            pw.println("Solving equation with accuracy up to 0.5E-" + SCALE);
            pw.println();

            ArrayMatrix<BigDecimal> a, b, x;
            GaussSolver gs = new GaussSolver(a = getA(5), b = getB(5), SCALE);
            gs.setLogWriter(pw);
            x = gs.solve();

            pw.println("ax - b:");
            BigDecimalMatrix am = new BigDecimalMatrix(a, 2 * SCALE);
            BigDecimalMatrix bm = new BigDecimalMatrix(b, 2 * SCALE);
            BigDecimalMatrix xm = new BigDecimalMatrix(x, 2 * SCALE);
            BigDecimalMatrix dis = am.multiply(xm).subtract(bm);
            dis.printlnMatrix(pw);
            pw.println();
            pw.println("||ax - b||^2 = " + dis.squaredEuclidianNorm());

            pw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        new Main().doGauss();
    }
}
