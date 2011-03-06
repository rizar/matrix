package matrix;

import java.io.PrintWriter;

/**
 *
 * @author Admin
 */
public interface Matrix<E>
{
    int getHeight();
    int getWidth();

    E getElement(int i, int j);
    void set(int i, int j, E value);

    void swapRows(int i1, int i2);

    Matrix getSubMatrix(int i, int j);
    Matrix getSubMatrix(int i1, int j1, int i2, int j2);

    void printlnMatrix(PrintWriter pw);
}
