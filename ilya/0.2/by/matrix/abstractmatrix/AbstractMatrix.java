package by.matrix.abstractmatrix;


/**
 * 
 * @author Admin
 */

abstract public class AbstractMatrix<E> implements Matrix<E> {

	public void swapRows(int i1, int i2) {
		for (int j = 1; j <= getWidth(); j++) {
			E t = getElement(i2, j);
			setElement(i2, j, getElement(i1, j));
			setElement(i1, j, t);
		}
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 1; i <= getHeight(); i++)
			for (int j = 1; j <= getWidth(); j++) {
				str.append(getElement(i, j).toString());
				if (j < getWidth())
					str.append(" ");
				else
					str.append("\n");
			}
		return str.toString();
	}

}
