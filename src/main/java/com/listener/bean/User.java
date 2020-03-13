package com.listener.bean;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.Serializable;

/*
* 	监听User的绑定和解绑
对象要和session一起绑定和解绑必须实现序列化接口
* */
public class User implements HttpSessionBindingListener, Serializable {

    private static final long serialVersionUID = 8794577266998791489L;
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    /*Use类的对象绑定到session中*/
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("session 绑定");
        //对象绑定在session中使用的Key
        String name = event.getName();
        //绑定在session中的具体对象
        Object value = event.getValue();
        System.out.println("session name is "+name+" value is "+value);

    }

    /*User类的对象从session中移队*/
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("session解绑");
        //对象绑定在session中使用的Key
        String name = event.getName();
        //绑定在session中的具体对象(监控的对象,User类的对象)
        Object value = event.getValue();
        System.out.println("session name is "+name+" value is "+value);

    }
}
