package by.matrix.equations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.matrix.abstractmatrix.Matrix;
import by.matrix.realizations.ArrayMatrix;
import by.matrix.realizations.BigDecimalMatrix;

public class MethodRunner {

	private List<BigDecimal> dList;
	private BigDecimalMatrix matrix;

	// TODO check in
	public MethodRunner(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal d,
			int dimension, int scale) {
		matrix = matrixCreator(a, b, c, dimension, scale);
		dList = listCreator(d, dimension);
	}

	public MethodRunner(BigDecimal a, BigDecimal b, BigDecimal c,
			List<BigDecimal> dList, int dimension, int scale) {
		matrix = matrixCreator(a, b, c, dimension, scale);
		this.dList = dList;
	}

	public MethodRunner(BigDecimalMatrix matrix, List<BigDecimal> list) {
		this.dList = list;
		this.matrix = matrix;
	}

	private BigDecimalMatrix matrixCreator(BigDecimal a, BigDecimal b,
			BigDecimal c, int dimension, int scale) {
		Matrix<BigDecimal> matr = new ArrayMatrix<BigDecimal>(dimension,
				dimension);
		BigDecimalMatrix bgMatr = new BigDecimalMatrix(matr, scale);
		for (int i = 1; i <= matr.getHeight(); i++)
			for (int j = 1; j <= matr.getHeight(); j++)
				bgMatr.setElement(i, j, BigDecimal.ZERO);
		bgMatr.setElement(1, 1, b);
		bgMatr.setElement(1, 2, c);
		for (int i = 2; i < matr.getHeight(); i++) {
			bgMatr.setElement(i, i - 1, a);
			bgMatr.setElement(i, i, b);
			bgMatr.setElement(i, i + 1, c);
		}
		bgMatr.setElement(dimension, dimension - 1, a);
		bgMatr.setElement(dimension, dimension, b);
		return bgMatr;
	}

	private List<BigDecimal> listCreator(BigDecimal d, int dimension) {
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		for (int i = 0; i < dimension; i++)
			list.add(d);
		return list;
	}

	public List<BigDecimal> getSolution() {
		int height = matrix.getHeight();
		List<BigDecimal> solv = new ArrayList<BigDecimal>();
		List<BigDecimal> eps = getEpsilon();
		List<BigDecimal> etto = getEtto();
		BigDecimal next = dList
				.get(height - 1)
				.subtract(
						matrix.getElement(height, height - 1).multiply(
								etto.get(height - 1)))
				.divide(matrix.getElement(height, height - 1).multiply(
						eps.get(height - 1).add(
								matrix.getElement(height, height))),
						matrix.getScale(), RoundingMode.HALF_UP);
		solv.add(next.setScale(matrix.getScale(), RoundingMode.HALF_UP));
		for (int i = height - 1; i >= 1; i--) {
			next = eps.get(i).multiply(next).add(etto.get(i));
			solv.add(next.setScale(matrix.getScale(), RoundingMode.HALF_UP));
		}
		Collections.reverse(solv);
		return solv;
	}

	public List<BigDecimal> getEpsilon() {
		List<BigDecimal> eps = new ArrayList<BigDecimal>();
		eps.add(BigDecimal.ZERO);
		BigDecimal prev = matrix.getElement(1, 2).divide(
				matrix.getElement(1, 1).negate(), matrix.getScale(),
				RoundingMode.HALF_UP);
		eps.add(prev);
		for (int i = 2; i < matrix.getHeight(); i++) {
			prev = matrix.getElement(i, i + 1).divide(
					(matrix.getElement(i, i).negate()).subtract(matrix
							.getElement(i, i - 1).multiply(prev)),
					matrix.getScale(), RoundingMode.HALF_UP);
			eps.add(prev);
		}
		return eps;
	}

	public List<BigDecimal> getEtto() {
		List<BigDecimal> etto = new ArrayList<BigDecimal>();
		List<BigDecimal> eps = getEpsilon();
		etto.add(BigDecimal.ZERO);
		BigDecimal prev = dList.get(0).divide(matrix.getElement(1, 1).negate(),
				matrix.getScale(), RoundingMode.HALF_UP);
		etto.add(prev);
		for (int i = 2; i < matrix.getHeight(); i++) {
			prev = ((matrix.getElement(i, i - 1).multiply(prev)).subtract(dList
					.get(i - 1))).divide(
					matrix.getElement(i, i)
							.negate()
							.subtract(
									matrix.getElement(i, i - 1).multiply(
											eps.get(i - 1))),
					matrix.getScale(), RoundingMode.HALF_UP);
			etto.add(prev);
		}
		return etto;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Matrix: \n");
		str.append(matrix);
		str.append("Vector: \n");
		str.append(dList + "\n");
		str.append("Epsilon: \n");
		str.append(getEpsilon() + "\n");
		str.append("Etto: \n");
		str.append(getEtto() + "\n");
		str.append("Solution: \n");
		str.append(getSolution() + "\n");
		return str.toString();
	}

}
