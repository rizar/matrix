package matrix;

/**
 *
 * @author Admin
 */
public class ExtendedMatrix<E> extends AbstractMatrix<E>
{
    private Matrix<E> a, b;

    public ExtendedMatrix(Matrix<E> a, Matrix<E> b)
    {
        //TODO check input
        this.a = a;
        this.b = b;
    }

    public int getN()
    {
        return a.getN();
    }

    public int getM()
    {
        return a.getM() + b.getM();
    }

    public E get(int i, int j)
    {
        if (1 <= i && i <= a.getN())
        {
            if (1 <= j && j <= a.getM())
                return a.get(i, j);
            else if (a.getM() < j && j <= a.getM() + b.getM())
                return b.get(i, j - a.getM());
            else throw new MatrixIndexOutOfBoundsException(this, i, j);
        }
        else throw new MatrixIndexOutOfBoundsException(this, i, j);
    }

    public void set(int i, int j, E value)
    {
        if (1 <= i && i <= a.getN())
        {
            if (1 <= j && j <= a.getM())
                a.set(i, j, value);
            else if (a.getM() < j && j <= a.getM() + b.getM())
                b.set(i, j - a.getN(), value);
            else throw new MatrixIndexOutOfBoundsException(this, i, j);
        }
        else throw new MatrixIndexOutOfBoundsException(this, i, j);
    }

    @Override
    public Matrix getSubMatrix(int i1, int j1, int i2, int j2)
    {
        return new SubMatrix(this, i1, j1, i2, j2);
    }
}
