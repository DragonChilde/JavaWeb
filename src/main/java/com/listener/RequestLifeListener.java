package com.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author Lee
 * @create 2020/3/10 16:00
 */
public class RequestLifeListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request  listener destoryed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request listener init");
    }
}
