package by.matrix.exceptions;

import by.matrix.abstractmatrix.Matrix;

/**
 * 
 * @author Admin
 */
public class MatrixIndexOutOfBoundsException extends RuntimeException {

	private static final long serialVersionUID = 2L;

	private Exception hidden;
	private String error;

	public MatrixIndexOutOfBoundsException(Matrix<?> matrix, int i, int j) {
		super("width = " + matrix.getHeight() + " height = " + matrix.getWidth() + " i = " + i
				+ " j = " + j);
	}

	public MatrixIndexOutOfBoundsException() {
		super();
	}

	public MatrixIndexOutOfBoundsException(String err) {
		super(err);
		this.error = err;
	}

	public MatrixIndexOutOfBoundsException(String err, Exception e) {
		super(err, e);
		this.hidden = e;
	}

	public MatrixIndexOutOfBoundsException(Exception e) {
		super(e);
		this.hidden = e;
	}

	public Exception getHidden() {
		return hidden;
	}

	public String getError() {
		return error;
	}

}
