package matrix;

/**
 *
 * @author Dmitry Bogdanov
 */
public interface Matrix<E>
{
    int getHeight();

    int getWidth();

    E getElement(int i, int j);

    void setElement(int i, int j, E value);

    void swapRows(int i1, int i2);

    Matrix<E> subMatrix(int i, int j);

    Matrix<E> subMatrix(int i1, int j1, int i2, int j2);

    Matrix<E> extendedMatrix(Matrix<E> matrix);
}
