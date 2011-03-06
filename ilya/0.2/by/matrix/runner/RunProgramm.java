package by.matrix.runner;

import java.math.BigDecimal;
import java.math.RoundingMode;

import by.matrix.abstractmatrix.Matrix;
import by.matrix.actions.ActionBigDecimalMatrix;
import by.matrix.equations.MethodRunner;
import by.matrix.realizations.ArrayMatrix;
import by.matrix.realizations.BigDecimalMatrix;

public class RunProgramm {

	public static void main(String[] args) {

		BigDecimal a[][] = new BigDecimal[4][4];

		a[0][0] = new BigDecimal(1);
		a[1][0] = new BigDecimal(7);
		a[2][0] = new BigDecimal(3);
		a[3][0] = new BigDecimal(4);

		a[0][1] = new BigDecimal(2);
		a[1][1] = new BigDecimal(-1);
		a[2][1] = new BigDecimal(5);
		a[3][1] = new BigDecimal(1);

		a[0][2] = new BigDecimal(3);
		a[1][2] = new BigDecimal(5);
		a[2][2] = new BigDecimal(1.3);
		a[3][2] = new BigDecimal(7);

		a[0][3] = new BigDecimal(4);
		a[1][3] = new BigDecimal(1);
		a[2][3] = new BigDecimal(8);
		a[3][3] = new BigDecimal(2.7);

		BigDecimal inverse[][] = new BigDecimal[4][4];

		inverse[0][0] = new BigDecimal(-0.3885);
		inverse[1][0] = new BigDecimal(-1.5367);
		inverse[2][0] = new BigDecimal(0.0159);
		inverse[3][0] = new BigDecimal(1.1036);

		inverse[0][1] = new BigDecimal(0.0881);
		inverse[1][1] = new BigDecimal(-0.5456);
		inverse[2][1] = new BigDecimal(-0.0973);
		inverse[3][1] = new BigDecimal(0.3237);

		inverse[0][2] = new BigDecimal(0.1582);
		inverse[1][2] = new BigDecimal(0.5151);
		inverse[2][2] = new BigDecimal(-0.0695);
		inverse[3][2] = new BigDecimal(-0.2449);

		inverse[0][3] = new BigDecimal(0.0741);
		inverse[1][3] = new BigDecimal(0.9527);
		inverse[2][3] = new BigDecimal(0.2189);
		inverse[3][3] = new BigDecimal(-0.6587);

		Matrix<BigDecimal> am = new ArrayMatrix<BigDecimal>(a);
		BigDecimalMatrix abg = new BigDecimalMatrix(am, 5);
		System.out.println(abg);

		Matrix<BigDecimal> inversem = new ArrayMatrix<BigDecimal>(inverse);
		BigDecimalMatrix inversebg = new BigDecimalMatrix(inversem, 5);
		System.out.println(inversebg);

		System.out.println(ActionBigDecimalMatrix.getCubicNorm(abg).setScale(5,
				RoundingMode.HALF_UP));
		System.out.println(ActionBigDecimalMatrix.getCubicNorm(inversebg)
				.setScale(5, RoundingMode.HALF_UP));
		System.out.println(ActionBigDecimalMatrix.getCubicNorm(abg)
				.multiply(ActionBigDecimalMatrix.getCubicNorm(inversebg))
				.setScale(5, RoundingMode.HALF_UP));
		System.out.println();
		MethodRunner r = new MethodRunner(new BigDecimal(1), new BigDecimal(2),
				new BigDecimal(1), new BigDecimal(4), 5, 4);
		System.out.println(r);

	}

}
