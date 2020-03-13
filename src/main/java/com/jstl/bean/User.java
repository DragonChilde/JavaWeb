package com.jstl.bean;

import java.io.Serializable;

/**
 * @author Lee
 * @create 2020/2/24 12:14
 */
public class User implements Serializable {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
