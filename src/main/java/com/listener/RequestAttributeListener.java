package com.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * @author Lee
 * @create 2020/3/11 10:28
 */
public class RequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        //新增的Key
        String name = srae.getName();
        //新增的value
        Object value = srae.getValue();
        System.out.println("request新增的属性名:"+name);
        System.out.println("request新增的属性值:"+value);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        //移除的Key
        String name = srae.getName();
        //移除的value
        Object value = srae.getValue();
        System.out.println("request删除的属性名:"+name);
        System.out.println("request删除的属性值:"+value);
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        //修改的Key
        String name = srae.getName();
        //修改前的旧value
        Object value = srae.getValue();
        ServletRequest servletRequest = srae.getServletRequest();
        Object attribute = servletRequest.getAttribute(name);
        System.out.println("request修改的属性名:"+name);
        System.out.println("request修改前的属性值:"+value);
        System.out.println("request修改后的属性值:"+attribute);
    }
}
