package com.github.rizar.matrix;

import java.math.BigDecimal;

/**
 *
 * @author Rizar
 */
public class BigDecimalVectorRow extends BigDecimalMatrix
{
    public BigDecimalVectorRow(int width, int scale)
    {
        super(1, width, scale);
    }

    public BigDecimalVectorRow(BigDecimalMatrix matrix)
    {
        //TODO check input
        super(matrix, false);
    }

    public void setElement(int j, BigDecimal value)
    {
        setElement(1, j, value);
    }

    public BigDecimal getElement(int j)
    {
        return getElement(1, j);
    }
}
