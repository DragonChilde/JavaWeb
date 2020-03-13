package com.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Lee
 * @create 2020/3/10 16:15
 */
public class SessionLifeListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session listener create");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session listener destory");
    }
}
