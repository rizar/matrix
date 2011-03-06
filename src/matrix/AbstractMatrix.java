package matrix;

import java.io.PrintWriter;

/**
 *
 * @author Admin
 */
abstract public class AbstractMatrix<E> implements Matrix<E>
{
    public void swapRows(int i1, int i2)
    {
        for (int j = 1; j <= getWidth(); j++)
        {
            E t = getElement(i2, j);
            set(i2, j, getElement(i1, j));
            set(i1, j, t);
        }
    }

    public Matrix<E> getSubMatrix(int i, int j)
    {
        return getSubMatrix(i, j, getHeight(), getWidth());
    }

    public Matrix<E> getSubMatrix(int i1, int j1, int i2, int j2)
    {
        return new SubMatrix<E>(this, i1, j1, i2, j2);
    }

    public void printlnMatrix(PrintWriter pw)
    {
        for (int i = 1; i <= getHeight(); i++)
            for (int j = 1; j <= getWidth(); j++)
            {
                pw.print(getElement(i, j).toString());
                if (j < getWidth())
                    pw.print(' ');
                else
                    pw.println();
            }
    }
}
