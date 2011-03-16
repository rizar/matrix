/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rizar.matrix;

/**
 *
 * @author Rizar
 */
class TransposedMatrix<E> extends AbstractMatrix<E>
{
    private Matrix<E> matrix;

    TransposedMatrix(Matrix<E> matrix)
    {
        this.matrix = matrix;
    }

    public E getElement(int i, int j)
    {
        return matrix.getElement(j, i);
    }

    public int getHeight()
    {
        return matrix.getWidth();
    }

    public int getWidth()
    {
        return matrix.getHeight();
    }

    public void setElement(int i, int j, E value)
    {
        matrix.setElement(j, i, value);
    }
}
