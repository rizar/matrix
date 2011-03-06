package by.matrix.realizations;

import java.util.Arrays;

import by.matrix.abstractmatrix.AbstractMatrix;
import by.matrix.exceptions.MatrixIndexOutOfBoundsException;

/**
 * 
 * @author Admin
 */
public class ArrayMatrix<E> extends AbstractMatrix<E> implements Cloneable {

	private E[][] data;

	public ArrayMatrix(int height, int width) {
		data = (E[][]) new Object[height][width];
	}

	public ArrayMatrix(E[][] elements) {
		data = elements;
	}

	public ArrayMatrix(ArrayMatrix<E> matrix) {
		data = matrix.data;
	}

	public int getHeight() {
		return data.length;
	}

	public int getWidth() {
		return data[0].length;
	}

	public E getElement(int i, int j) {
		if (1 <= i && i <= getHeight() && 1 <= j && j <= getWidth())
			return data[i - 1][j - 1];
		else
			throw new MatrixIndexOutOfBoundsException(this, i, j);
	}

	public void setElement(int i, int j, E value) {
		if (1 <= i && i <= getHeight() && 1 <= j && j <= getWidth())
			data[i - 1][j - 1] = value;
		else
			throw new MatrixIndexOutOfBoundsException(this, i, j);
	}

	@Override
	public Object clone() {
		E[][] dataCopy = (E[][]) new Object[getHeight()][];
		for (int i = 0; i < getHeight(); i++)
			dataCopy[i] = Arrays.copyOf(data[i], getWidth());
		return new ArrayMatrix(dataCopy);
	}
}
