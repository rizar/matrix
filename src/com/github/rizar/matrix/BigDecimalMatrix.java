package com.github.rizar.matrix;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *\
 * @author Rizar
 */
public class BigDecimalMatrix extends AbstractMatrix<BigDecimal>
{
    private Matrix<BigDecimal> matrix;
    private int scale;

    /**
     * Constructs zero matrix.
     * @param height
     * @param width
     * @param scale
     */
    public BigDecimalMatrix(int height, int width, int scale)
    {
        matrix = new ArrayMatrix<BigDecimal>(height, width);
        for (int i = 1; i <= height; i++)
            for (int j = 1; j <= width; j++)
                matrix.setElement(i, j, BigDecimal.ZERO);
        this.scale = scale;
    }

    /**
     * Constructs scalar matrix.
     * @param size
     * @param scalar
     * @param scale
     */
    public BigDecimalMatrix(int size, BigDecimal scalar, int scale)
    {
        matrix = new ArrayMatrix<BigDecimal>(size, size);
        for (int i = 1; i <= matrix.getHeight(); i++)
            for (int j = 1; j <= matrix.getWidth(); j++)
                matrix.setElement(i, j, i == j ? scalar : BigDecimal.ZERO);
        this.scale = scale;
    }

    /**
     * If makeCopy, construct matrix shallow copy, else uses matrix data.
     * @param matrix
     * @param scale
     */
    public BigDecimalMatrix(Matrix<BigDecimal> matrix, int scale, boolean makeCopy)
    {
        this.matrix = makeCopy ? new ArrayMatrix<BigDecimal>(matrix) : matrix;
        this.scale = scale;
    }

    /**
     * If makeCopy, construct matrix shallow copy, else uses matrix data.
     * @param matrix
     */
    public BigDecimalMatrix(BigDecimalMatrix matrix, boolean makeCopy)
    {
        this.matrix = makeCopy ? new ArrayMatrix<BigDecimal>(matrix) : matrix;
        this.scale = matrix.scale;
    }

    public void setScale(int scale)
    {
        this.scale = scale;
        for (int i = 1; i <= getHeight(); i++)
            for (int j = 1; j <= getWidth(); j++)
                setElement(i, j, getElement(i, j).setScale(scale));
    }

    public int getScale()
    {
        return scale;
    }

    public BigDecimalMatrix multiply(BigDecimalMatrix matrix)
    {
        if (getWidth() != matrix.getHeight())
            throw new InconsistentMatrixesException(this, matrix);
        BigDecimalMatrix res = new BigDecimalMatrix(getHeight(), matrix.getWidth(), scale);
        for (int i = 1; i <= res.getHeight(); i++)
            for (int j = 1; j <= res.getWidth(); j++)
                for (int k = 1; k <= getWidth(); k++)
                    res.setElement(i, j, res.getElement(i, j).add(getElement(i, k).multiply(matrix.getElement(k, j))).setScale(scale, RoundingMode.HALF_UP));
        return res;
    }

    public BigDecimalMatrix subtract(BigDecimalMatrix matrix)
    {
        BigDecimalMatrix res = new BigDecimalMatrix(getHeight(), getWidth(), scale);
        for (int i = 1; i <= getHeight(); i++)
            for (int j = 1; j <= getWidth(); j++)
                res.setElement(i, j, getElement(i, j).subtract(matrix.getElement(i, j)).setScale(scale, RoundingMode.HALF_UP));
        return res;
    }

    public void multiplyRow(int i, BigDecimal value)
    {
        for (int j = 1; j <= matrix.getWidth(); j++)
            matrix.setElement(i, j, matrix.getElement(i, j).multiply(value).setScale(scale, RoundingMode.HALF_UP));
    }

    public void multiplyRowAndAddToRow(int i1, int i2, BigDecimal value)
    {
        for (int j = 1; j <= matrix.getWidth(); j++)
            matrix.setElement(i2, j, matrix.getElement(i1, j).multiply(value).add(matrix.getElement(i2, j)).setScale(scale, RoundingMode.HALF_UP));
    }

    public void zeroFirstColumn()
    {
        for (int i = 2; i <= matrix.getHeight(); i++)
        {
            BigDecimal k = matrix.getElement(i, 1).divide(matrix.getElement(1, 1), scale, RoundingMode.HALF_UP).negate();
            multiplyRowAndAddToRow(1, i, k);
        }
    }

    public BigDecimal squaredEuclidianNorm()
    {
        BigDecimal res = BigDecimal.ZERO;
        for (int i = 1; i <= getHeight(); i++)
            for (int j = 1; j <= getWidth(); j++)
                res = res.add(getElement(i, j).multiply(getElement(i, j)));
        return res;
    }

    public int getHeight()
    {
        return matrix.getHeight();
    }

    public int getWidth()
    {
        return matrix.getWidth();
    }

    public BigDecimal getElement(int i, int j)
    {
        return matrix.getElement(i, j);
    }

    public void setElement(int i, int j, BigDecimal value)
    {
        matrix.setElement(i, j, value.setScale(scale, RoundingMode.HALF_UP));
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= getHeight(); i++)
        {
            for (int j = 1; j <= getWidth(); j++)
            {
                sb.append(getElement(i, j).toPlainString());
                if (j < getWidth())
                    sb.append(" ");
            }
            if (i < getHeight())
                sb.append("\n");
        }
        return sb.toString();
    }
}
