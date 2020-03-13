package com.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Lee
 * @create 2020/3/10 16:11
 */
public class ApplicationLifeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("context listener init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("context listener destory");
    }
}
