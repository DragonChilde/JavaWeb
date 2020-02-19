package com.jstl.bean;

/**
 * @author Lee
 * @create 2020-02-17 00:00
 */
public class Student {
    private String username;
    private int age;


    public Student(String username) {
        this.username = username;
    }

    public Student(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
