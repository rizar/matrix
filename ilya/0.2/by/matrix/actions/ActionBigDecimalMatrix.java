package by.matrix.actions;

import java.math.BigDecimal;
import java.math.RoundingMode;

import by.matrix.realizations.BigDecimalMatrix;

public class ActionBigDecimalMatrix {

	public static BigDecimalMatrix multiply(BigDecimalMatrix a,
			BigDecimalMatrix b) {
		// TODO check input
		BigDecimalMatrix res = new BigDecimalMatrix(a.getHeight(),
				b.getWidth(), BigDecimal.ZERO, a.getScale());
		for (int i = 1; i <= res.getHeight(); i++)
			for (int j = 1; j <= res.getWidth(); j++)
				for (int k = 1; k <= a.getWidth(); k++)
					res.setElement(
							i,
							j,
							res.getElement(i, j)
									.add(a.getElement(i, k).multiply(
											b.getElement(k, j)))
									.setScale(a.getScale(),
											RoundingMode.HALF_UP));
		return res;
	}

	public static BigDecimalMatrix subtract(BigDecimalMatrix a,
			BigDecimalMatrix b) {
		BigDecimalMatrix res = new BigDecimalMatrix(a.getHeight(),
				a.getWidth(), BigDecimal.ZERO, a.getScale());
		for (int i = 1; i <= a.getHeight(); i++)
			for (int j = 1; j <= a.getWidth(); j++)
				res.setElement(i, j,
						a.getElement(i, j).subtract(b.getElement(i, j))
								.setScale(a.getScale()));
		return res;
	}
	
	public static BigDecimalMatrix add(BigDecimalMatrix a,
			BigDecimalMatrix b) {
		BigDecimalMatrix res = new BigDecimalMatrix(a.getHeight(),
				a.getWidth(), BigDecimal.ZERO, a.getScale());
		for (int i = 1; i <= a.getHeight(); i++)
			for (int j = 1; j <= a.getWidth(); j++)
				res.setElement(i, j,
						a.getElement(i, j).add(b.getElement(i, j))
								.setScale(a.getScale()));
		return res;
	}

	public static BigDecimal getSquaredEuclidianNorm(BigDecimalMatrix a) {
		BigDecimal res = BigDecimal.ZERO;
		for (int i = 1; i <= a.getHeight(); i++)
			for (int j = 1; j <= a.getWidth(); j++)
				res = res.add(a.getElement(i, j).multiply(a.getElement(i, j)));
		return ActionBigDecimal.sqrt(res, a.getScale());
	}

	public static BigDecimal getCubicNorm(BigDecimalMatrix a) {
		BigDecimal res = BigDecimal.ZERO;
		BigDecimal temp = BigDecimal.ZERO;
		for (int i = 1; i <= a.getHeight(); i++) {
			for (int j = 1; j <= a.getWidth(); j++)
				temp = temp.add(a.getElement(i, j).abs());
			if (res.compareTo(temp) < 0)
				res = temp;
			temp = BigDecimal.ZERO;
		}
		return res;
	}
	
	public static BigDecimal getOctaedrNorm(BigDecimalMatrix a) {
		BigDecimal res = BigDecimal.ZERO;
		BigDecimal temp = BigDecimal.ZERO;
		for (int i = 1; i <= a.getWidth(); i++) {
			for (int j = 1; j <= a.getHeight(); j++)
				temp = temp.add(a.getElement(j, i).abs());
			if (res.compareTo(temp) < 0)
				res = temp;
			temp = BigDecimal.ZERO;
		}
		return res;
	}

}
