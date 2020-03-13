package com.xml;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Lee
 * @create 2020/2/28 11:36
 */
public class PrecisionTest {

    @Test
    public void test1()
    {
        int i =1;
        BigDecimal bigDecimal = new BigDecimal(i);
        for (int j = 1; j < 100; j++) {
            bigDecimal = bigDecimal.multiply(new BigDecimal(j));
        }

        System.out.println(bigDecimal);

    }

    @Test
    public void test2()
    {
        /*浮点数运算*/
        System.out.println(0.07 * 0.01);
        /*结果为:7.000000000000001E-4*/
    }

    @Test
    public void test3()
    {
        /*大整数运算*/
        int i =1;
        for (int j = 1; j < 100; j++) {
           i*=j;
        }
        //结果为0,无法运算
        System.out.println(i);
    }

    @Test
    public void test4()
    {
        double a = 0.01;
        double b = 0.07;
        BigDecimal bigDecimal1 = new BigDecimal(a);
        BigDecimal bigDecimal2 = new BigDecimal(b);
        System.out.println(bigDecimal1.multiply(bigDecimal2));
    }
}
