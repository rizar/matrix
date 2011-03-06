package by.matrix.abstractmatrix;


/**
 * 
 * @author Admin
 */
public interface Matrix<E> {

	void swapRows(int i1, int i2);

	int getHeight();

	int getWidth();

	E getElement(int i, int j);

	void setElement(int i, int j, E value);

	String toString();
}
