package com.github.rizar.runners;

import com.github.rizar.bigdecimalmath.BigDecimalMath;
import java.math.BigDecimal;

/**
 *
 * @author Rizar
 */
public class BigDecimalMathRunner
{
    public static void main(String[] args)
    {
        for (int x = 1; x <= 10; x++)
            System.out.println("sqrt(" + x + ") = " + BigDecimalMath.sqrt(new BigDecimal(
                    x + ".000000"), 20));
    }
}
