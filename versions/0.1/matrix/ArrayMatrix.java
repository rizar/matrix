package matrix;

import java.util.Arrays;

/**
 *
 * @author Admin
 */
public class ArrayMatrix<E> extends AbstractMatrix<E> implements Cloneable
{
    private int n, m;
    private E [][] data;

    public ArrayMatrix(int n, int m)
    {
        this.n = n;
        this.m = m;
        data = (E [][]) new Object[n][m];
    }

    public ArrayMatrix(int n, int m, E [][] elements)
    {
       this.n = n;
       this.m = m;
       data = elements;
    }

    public ArrayMatrix(ArrayMatrix<E> matrix)
    {
        n = matrix.n;
        m = matrix.m;
        data = matrix.data;
    }

    @Override
    public ArrayMatrix<E> clone()
    {
        E [][] dataCopy = (E [][]) new Object [n][];
        for (int i = 0; i < n; i++)
            dataCopy[i] = Arrays.copyOf(data[i], m);
        return new ArrayMatrix(n, m, dataCopy);
    }

    public int getN()
    {
        return n;
    }

    public int getM()
    {
        return m;
    }

    public E get(int i, int j)
    {
        if (1 <= i && i <= n && 1 <= j && j <= m)
            return data[i - 1][j - 1];
        else
            throw new MatrixIndexOutOfBoundsException(this, i, j);
    }

    public void set(int i, int j, E value)
    {
        if (1 <= i && i <= n && 1 <= j && j <= m)
            data[i - 1][j - 1] = value;
        else
            throw new MatrixIndexOutOfBoundsException(this, i, j);
    }
}
