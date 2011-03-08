package com.github.rizar.matrix;

/**
 *
 * @author Rizar
 */
public class ArrayMatrix<E> extends AbstractMatrix<E> implements Cloneable
{
    private int height, width;
    private E[][] data;

    /**
     * Constructs matrix with all null references.
     * @param height
     * @param width
     */
    public ArrayMatrix(int height, int width)
    {
        this.height = height;
        this.width = width;
        data = (E[][]) new Object[height][width];
    }

    /**
     * Constructs matrix with data from elements.
     * @param height
     * @param width
     * @param elements
     */
    public ArrayMatrix(E[][] elements)
    {
        this.height = elements.length;
        this.width = elements[0].length;
        data = elements;
    }

    /**
     * Constructs shallow copy of given matrix
     * @param matrix
     */
    public ArrayMatrix(Matrix<E> matrix)
    {
        height = matrix.getHeight();
        width = matrix.getWidth();
        data = (E[][]) new Object[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                data[i][j] = matrix.getElement(i + 1, j + 1);
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public E getElement(int i, int j)
    {
        try
        {
            return data[i - 1][j - 1];
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new MatrixIndexOutOfBoundsException(this, i, j);
        }
    }

    public void setElement(int i, int j, E value)
    {
        try
        {
            data[i - 1][j - 1] = value;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new MatrixIndexOutOfBoundsException(this, i, j);
        }
    }
}
