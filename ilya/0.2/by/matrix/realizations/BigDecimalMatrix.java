package by.matrix.realizations;

import java.math.BigDecimal;
import java.math.RoundingMode;

import by.matrix.abstractmatrix.AbstractMatrix;
import by.matrix.abstractmatrix.Matrix;

/**
 * 
 * @author Admin
 */
public class BigDecimalMatrix extends AbstractMatrix<BigDecimal> {

	private Matrix<BigDecimal> matrix;
	private int scale;

	public BigDecimalMatrix(Matrix<BigDecimal> matrix, int scale) {
		this.matrix = matrix;
		this.scale = scale;
	}

	public BigDecimalMatrix(int height, int width, BigDecimal scalar, int scale) {
		this.matrix = new ArrayMatrix<BigDecimal>(height, width);
		for (int i = 1; i <= this.matrix.getHeight(); i++)
			for (int j = 1; j <= this.matrix.getWidth(); j++)
				this.matrix.setElement(i, j, i == j ? scalar : BigDecimal.ZERO);
		this.scale = scale;
	}

	public void multiplyRow(int i, BigDecimal value) {
		for (int j = 1; j <= matrix.getWidth(); j++)
			matrix.setElement(i, j, matrix.getElement(i, j).multiply(value)
					.setScale(scale, RoundingMode.HALF_UP));
	}

	public void multiplyRowAndAddToRow(int i1, int i2, BigDecimal value) {
		for (int j = 1; j <= matrix.getWidth(); j++)
			matrix.setElement(
					i2,
					j,
					matrix.getElement(i1, j).multiply(value)
							.add(matrix.getElement(i2, j))
							.setScale(scale, RoundingMode.HALF_UP));
	}

	public void makeZeroFirstColumn() {
		for (int i = 2; i <= matrix.getHeight(); i++) {
			BigDecimal k = matrix
					.getElement(i, 1)
					.divide(matrix.getElement(1, 1), scale,
							RoundingMode.HALF_UP).negate();
			multiplyRowAndAddToRow(1, i, k);
		}
	}

	public int getHeight() {
		return matrix.getHeight();
	}

	public int getWidth() {
		return matrix.getWidth();
	}

	public BigDecimal getElement(int i, int j) {
		return matrix.getElement(i, j);
	}

	public void setElement(int i, int j, BigDecimal value) {
		matrix.setElement(i, j, value);
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 1; i <= getHeight(); i++)
			for (int j = 1; j <= getWidth(); j++) {
				str.append(getElement(i, j).setScale(scale, RoundingMode.HALF_UP).toString());
				if (j < getWidth())
					str.append(" ");
				else
					str.append("\n");
			}
		return str.toString();
	}
}
