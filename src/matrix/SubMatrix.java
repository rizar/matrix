package matrix;

/**
 *
 * @author Admin
 */
class SubMatrix<E> extends AbstractMatrix<E>
{
    private Matrix<E> base;

    private int i1, j1, i2, j2;

    SubMatrix(Matrix base, int i1, int j1, int i2, int j2)
    {
        this.base = base;
        this.i1 = i1;
        this.j1 = j1;
        this.i2 = i2;
        this.j2 = j2;
    }

    public int getHeight()
    {
        return i2 - i1 + 1;
    }
    
    public int getWidth()
    {
        return j2 - j1 + 1;
    }

    public E getElement(int i, int j)
    {
        return base.getElement(i1 + i - 1, j1 + j - 1);
    }

    public void set(int i, int j, E value)
    {
        base.set(i1 + i - 1, j1 + j - 1, value);
    }
}
