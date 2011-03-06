package matrix;

/**
 *
 * @author Admin
 */
class MatrixIndexOutOfBoundsException extends RuntimeException
{
    public Matrix matrix;
    public int i, j;

    public MatrixIndexOutOfBoundsException(Matrix matrix, int i, int j)
    {
        super("n = " + matrix.getN() + " m = " + matrix.getM() + " i = " + i + " j = " + j);
        this.matrix = matrix;
        this.i = i;
        this.j = j;
    }
}
