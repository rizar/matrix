/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rizar.matrix;

import com.github.rizar.matrix.ExtendedMatrix;
import com.github.rizar.matrix.Matrix;
import com.github.rizar.matrix.ArrayMatrix;
import com.github.rizar.matrix.InconsistentMatrixesException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Rizar
 */
public class ExtendedMatrixTest
{
    Matrix<Object> a, b, c;

    @Before
    public void setUp()
    {
        a = new ArrayMatrix<Object>(1, 1);
        b = new ArrayMatrix<Object>(2, 3);
        c = new ArrayMatrix<Object>(2, 2);
    }

    @After
    public void tearDown()
    {
    }

    @Test (expected = InconsistentMatrixesException.class)
    public void testConstructorThrowsException()
    {
        new ExtendedMatrix(a, b);
    }

    @Test
    public void testConstructor()
    {
        new ExtendedMatrix(b, c);
    }
}
