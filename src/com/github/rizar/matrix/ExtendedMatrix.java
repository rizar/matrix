package com.github.rizar.matrix;

/**
 *
 * @author Rizar
 */
class ExtendedMatrix<E> extends AbstractMatrix<E>
{
    private Matrix<E> a, b;

    public ExtendedMatrix(Matrix<E> a, Matrix<E> b)
    {
        if (a.getHeight() != b.getHeight())
            throw new InconsistentMatrixesException(a, b);
        this.a = a;
        this.b = b;
    }

    public int getHeight()
    {
        return a.getHeight();
    }

    public int getWidth()
    {
        return a.getWidth() + b.getWidth();
    }

    public E getElement(int i, int j)
    {
        if (1 <= i && i <= a.getHeight())
        {
            if (1 <= j && j <= a.getWidth())
                return a.getElement(i, j);
            else if (a.getWidth() < j && j <= a.getWidth() + b.getWidth())
                return b.getElement(i, j - a.getWidth());
            else throw new MatrixIndexOutOfBoundsException(this, i, j);
        }
        else throw new MatrixIndexOutOfBoundsException(this, i, j);
    }

    public void setElement(int i, int j, E value)
    {
        if (1 <= i && i <= a.getHeight())
        {
            if (1 <= j && j <= a.getWidth())
                a.setElement(i, j, value);
            else if (a.getWidth() < j && j <= a.getWidth() + b.getWidth())
                b.setElement(i, j - a.getHeight(), value);
            else throw new MatrixIndexOutOfBoundsException(this, i, j);
        }
        else throw new MatrixIndexOutOfBoundsException(this, i, j);
    }
}
