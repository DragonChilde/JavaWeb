package com.listener.bean;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

/**
监听student对象的钝化和活化
 对象要和session一起活化钝化必须实现序列化接口
 */
public class Student implements HttpSessionActivationListener, Serializable {


    private static final long serialVersionUID = 2296288538308859889L;
    private String username;

    public Student() {
    }

    public Student(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                '}';
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {

        System.out.println("session 钝化");

    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("session 活化");
        HttpSession session = se.getSession();
        Object username = session.getAttribute("usename");
        System.out.println("student username is:"+username);
    }
}
