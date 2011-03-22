/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.rizar.runners;

import com.github.rizar.matrix.BigDecimalMatrix;
import com.github.rizar.solvers.JacobiSolver;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Rizar
 */
public class JacobiRunner
{
    public void doSolve() throws Exception
    {
        Scanner sc = new Scanner(new File("jacobi_input.txt"));
        int n = sc.nextInt(), scale = sc.nextInt();
        BigDecimalMatrix a0 = BigDecimalMatrix.scan(sc, n, n, scale);
        BigDecimalMatrix b0 = BigDecimalMatrix.scan(sc, n, 1, scale);
        BigDecimalMatrix a1 = BigDecimalMatrix.scan(sc, n, n, scale);
        BigDecimalMatrix b1 = BigDecimalMatrix.scan(sc, n, 1, scale);
        sc.close();

        JacobiSolver js = new JacobiSolver(a1, b1, scale);
        PrintWriter pw = new PrintWriter(new File("jacobi_output.txt"));
        js.setLogWriter(pw);
        BigDecimalMatrix x = js.solve();
        
        BigDecimalMatrix dis0 = a0.multiply(x).subtract(b0);
        pw.println("a0:");
        pw.println(a0);
        pw.println("b0:");
        pw.println(b0);
        pw.println("a0 * x - b0:");
        pw.println(dis0);
        pw.println("||a0 * x - b0||^2");
        pw.println(dis0.squaredEuclidianNorm());

        BigDecimalMatrix dis1 = a1.multiply(x).subtract(b1);
        pw.println("a1:");
        pw.println(a1);
        pw.println("b1:");
        pw.println(b1);
        pw.println("a1 * x + b1:");
        pw.println(dis1);
        pw.println("||a1 * x + b1||^2");
        pw.println(dis1.squaredEuclidianNorm());

        pw.close();
    }

    public static void main(String [] args)
    {
        try
        {
            new JacobiRunner().doSolve();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
