package com.github.rizar.matrix;

/**
 *
 * @author Rizar
 */
abstract public class AbstractMatrix<E> implements Matrix<E>
{
    public void swapRows(int i1, int i2)
    {
        for (int j = 1; j <= getWidth(); j++)
        {
            E tmp = getElement(i2, j);
            setElement(i2, j, getElement(i1, j));
            setElement(i1, j, tmp);
        }
    }

    public void swapColumns(int j1, int j2)
    {
        for (int i = 1; i <= getHeight(); i++)
        {
            E tmp = getElement(i, j2);
            setElement(i, j2, getElement(i, j1));
            setElement(i, j1, tmp);
        }
    }

    public void reverseRows()
    {
        for (int i1 = 1, i2 = getHeight(); i1 < i2; i1++, i2--)
            swapRows(i1, i2);
    }

    public void reverseColumns()
    {
        for (int j1 = 1, j2 = getWidth(); j1 < j2; j1++, j2--)
            swapColumns(j1, j2);
    }

    public Matrix<E> transpose()
    {
        return new TransposedMatrix<E>(this);
    }

    public Matrix<E> subMatrix(int i, int j)
    {
        return subMatrix(i, j, getHeight(), getWidth());
    }

    public Matrix<E> subMatrix(int i1, int j1, int i2, int j2)
    {
        return new SubMatrix<E>(this, i1, j1, i2, j2);
    }

    public Matrix<E> extendedMatrix(Matrix matrix)
    {
        return new ExtendedMatrix(this, matrix);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= getHeight(); i++)
        {
            for (int j = 1; j <= getWidth(); j++)
            {
                sb.append(getElement(i, j));
                if (j < getWidth())
                    sb.append(" ");
            }
            if (i < getHeight())
                sb.append("\n");
        }
        return sb.toString();
    }
}
