package com.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author Lee
 * @create 2020/3/11 11:20
 */
public class SessionAttrButeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        //新增的Key
        String name = event.getName();
        //新增的value
        Object value = event.getValue();
        System.out.println("session新增的属性名:"+name);
        System.out.println("session新增的属性值:"+value);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        //移除的Key
        String name = event.getName();
        //移除的value
        Object value = event.getValue();
        System.out.println("session删除的属性名:"+name);
        System.out.println("session删除的属性值:"+value);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        //修改的Key
        String name = event.getName();
        //修改前的旧value
        Object value = event.getValue();
        HttpSession session = event.getSession();
        Object attribute = session.getAttribute(name);
        System.out.println("session修改的属性名:"+name);
        System.out.println("session修改前的属性值:"+value);
        System.out.println("session修改后的属性值:"+attribute);
    }
}
