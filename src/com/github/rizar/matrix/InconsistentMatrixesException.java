package com.github.rizar.matrix;

/**
 *
 * @author Rizar
 */
class InconsistentMatrixesException extends RuntimeException
{
    public InconsistentMatrixesException()
    {
    }

    public InconsistentMatrixesException(String message)
    {
        super(message);
    }

    public InconsistentMatrixesException(Matrix first, Matrix second)
    {
        super(makeMessage(first, second));
    }

    private static String makeMessage(Matrix first, Matrix second)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("First matrix is ");
        sb.append("(").append(first.getHeight()).append(" ").append(first.getWidth()).append(") ");
        sb.append("while second is ");
        sb.append("(").append(second.getHeight()).append(" ").append(second.getWidth()).append(") ");
        return sb.toString();
    }
}
