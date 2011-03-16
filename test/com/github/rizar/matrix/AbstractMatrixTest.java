/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rizar.matrix;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rizar
 */
public class AbstractMatrixTest
{
    private Matrix<Integer> matrix;

    @Before
    public void setUp()
    {
        Integer a [][] = new Integer [3][2];
        a[0][0] = 0;
        a[0][1] = 1;
        a[1][0] = 2;
        a[1][1] = 3;
        a[2][0] = 4;
        a[2][1] = 5;
        matrix = new ArrayMatrix<Integer>(a);
    }

    @Test
    public void testTranspose()
    {
        Matrix<Integer> transposed = matrix.transpose();
        assertEquals(matrix.getHeight(), transposed.getWidth());
        assertEquals(matrix.getWidth(), transposed.getHeight());
        for (int i = 1; i <= matrix.getHeight(); i++)
            for (int j = 1; j <= matrix.getWidth(); j++)
                assertEquals(matrix.getElement(i, j), transposed.getElement(j, i));
    }
}
