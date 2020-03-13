package com.i18n;

import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Lee
 * @create 2020/3/13 10:36
 */
public class i18nTest {

    @Test
    public void test1()
    {
        //获取默认区域信息ZH_CN     EN_US
        //一个Locale由语言_国家
        //Locale本身存储一些区域
        Locale us = Locale.US;
        Locale cn = Locale.CHINA;
        Locale korea = Locale.KOREA;

        DateFormat dateInstance1 = DateFormat.getDateInstance(DateFormat.FULL, cn);
        DateFormat dateInstance2 = DateFormat.getDateInstance(DateFormat.LONG, cn);
        DateFormat dateInstance3 = DateFormat.getDateInstance(DateFormat.MEDIUM, cn);
        DateFormat dateInstance4 = DateFormat.getDateInstance(DateFormat.SHORT, cn);
        DateFormat dateInstance5 = DateFormat.getDateInstance(DateFormat.DEFAULT, cn);

        String format1 = dateInstance1.format(new Date());
        String format2 = dateInstance2.format(new Date());
        String format3 = dateInstance3.format(new Date());
        String format4 = dateInstance4.format(new Date());
        String format5 = dateInstance5.format(new Date());

        System.out.println(format1);
        System.out.println(format2);
        System.out.println(format3);
        System.out.println(format4);
        System.out.println(format5);

    }

    @Test
    public void test2()
    {
        //写资源文件ResourceBundle来管理的.可以根据不同国家获取不同的值
        //.properties 文件名:基础名_语言_国家.properties
        //如果是中国 i18n_zh_CN.properties
        //如果是美国 i18n_en_US.properties
        //将要显示的信息放在这些文件中,然后通过文件动态获取.这些文件放在类路径下(src)
        Locale us = Locale.US;
        Locale china = Locale.CHINA;
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", us);
        String login = bundle.getString("login");
        System.out.println(login);
    }
}
