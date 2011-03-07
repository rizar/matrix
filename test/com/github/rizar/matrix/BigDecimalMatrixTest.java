/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rizar.matrix;

import com.github.rizar.matrix.BigDecimalMatrix;
import com.github.rizar.matrix.InconsistentMatrixesException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Rizar
 */
public class BigDecimalMatrixTest
{
    BigDecimalMatrix a, b, c;

    @Before
    public void setUp()
    {
        a = new BigDecimalMatrix(2, 3, 5);
        b = new BigDecimalMatrix(3, 2, 5);
        c = new BigDecimalMatrix(4, 4, 5);
    }

    @After
    public void tearDown()
    {
    }

    @Test (expected = InconsistentMatrixesException.class)
    public void testMatrixMultiplicationThrowsException()
    {
        a.multiply(c);
    }

    @Test
    public void testMatrixMultiplication()
    {
        a.multiply(b);
    }
}
