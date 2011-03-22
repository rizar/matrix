/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.rizar.solvers;

import com.github.rizar.matrix.BigDecimalMatrix;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 *
 * @author Rizar
 */
abstract public class AbstractSolver
{
    private PrintWriter logWriter = null;

    public void setLogWriter(PrintWriter logWriter)
    {
        this.logWriter = logWriter;
    }

    protected void log(String message, BigDecimalMatrix matrix)
    {
        if (logWriter == null)
            return;
        logWriter.println(message);
        logWriter.println(matrix);
        logWriter.println();
        logWriter.flush();
    }

    protected void log(String message, BigDecimal number)
    {
        if (logWriter == null)
            return;
        logWriter.println(message);
        logWriter.println(number);
        logWriter.flush();
    }
}
