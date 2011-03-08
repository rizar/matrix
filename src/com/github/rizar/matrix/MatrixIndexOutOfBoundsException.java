package com.github.rizar.matrix;

/**
 *
 * @author Rizar
 */
class MatrixIndexOutOfBoundsException extends RuntimeException
{
    public MatrixIndexOutOfBoundsException(Matrix matrix, int i, int j)
    {
        super("width = " + matrix.getHeight() + " height = " + matrix.getWidth() + " i = " + i + " j = " + j);
    }

    public MatrixIndexOutOfBoundsException()
    {
    }

    public MatrixIndexOutOfBoundsException(String message)
    {
        super(message);
    }
}
