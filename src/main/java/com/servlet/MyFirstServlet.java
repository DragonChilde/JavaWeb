package com.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Lee
 * @create 2019/12/17 13:46
 */

public class MyFirstServlet implements Servlet {


    //初始化
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
    }

    //获取Servlet配置信息
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //服务
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //server是用来处理来自客户端的请求
        System.out.println("this is first servlet");

        //ServletResponse 给浏览器发送一个响应
        //获取一个写数据的对象
        PrintWriter writer = servletResponse.getWriter();
        writer.write("Hello world!");

    }

    //获取Servlet信息
    @Override
    public String getServletInfo() {
        return null;
    }

    //销毁
    @Override
    public void destroy() {
    }
}
