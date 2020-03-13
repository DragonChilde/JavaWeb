package com.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequest;

/**
 * @author Lee
 * @create 2020/3/11 10:44
 */
public class ContextAttributeListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        //新增的Key
        String name = event.getName();
        //新增的value
        Object value = event.getValue();
        System.out.println("context新增的属性名:"+name);
        System.out.println("context新增的属性值:"+value);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        //移除的Key
        String name = event.getName();
        //移除的value
        Object value = event.getValue();
        System.out.println("context删除的属性名:"+name);
        System.out.println("context删除的属性值:"+value);
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        //修改的Key
        String name = event.getName();
        //修改前的旧value
        Object value = event.getValue();
        ServletContext servletContext = event.getServletContext();
        ServletContext context = servletContext.getContext(name);
        System.out.println("context修改的属性名:"+name);
        System.out.println("context修改前的属性值:"+value);
        System.out.println("context修改后的属性值:"+context);

    }
}
