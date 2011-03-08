package com.github.rizar.matrix;

import java.math.BigDecimal;

/**
 *
 * @author Rizar
 */
public class BigDecimalVectorColumn extends BigDecimalMatrix
{
    public BigDecimalVectorColumn(int height, int scale)
    {
        super(height, 1, scale);
    }

    public BigDecimalVectorColumn(BigDecimalMatrix matrix)
    {
        //TODO check input
        super(matrix, false);
    }

    public void setElement(int i, BigDecimal value)
    {
        setElement(i, 1, value);
    }

    public BigDecimal getElement(int i)
    {
        return getElement(i, 1);
    }
}
