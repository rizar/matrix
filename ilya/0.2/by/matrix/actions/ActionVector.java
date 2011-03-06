package by.matrix.actions;

import java.math.BigDecimal;
import java.util.List;

public class ActionVector {

	public static BigDecimal getCubicNorm(List<BigDecimal> list) {
		BigDecimal res = BigDecimal.ZERO;
		for (BigDecimal i : list)
			if (res.compareTo(i.abs()) < 0)
				res = i.abs();
		return res;
	}

	public static BigDecimal getOctaedrNorm(List<BigDecimal> list) {
		BigDecimal res = BigDecimal.ZERO;
		for (BigDecimal i : list)
			res = res.add(i.abs());
		return res;
	}
	
	public static BigDecimal getSquaredEuclidianNorm(List<BigDecimal> list) {
		BigDecimal res = BigDecimal.ZERO;
		for (BigDecimal i : list)
			res = res.add(i.multiply(i));
		return ActionBigDecimal.sqrt(res,res.precision());
	}

}
