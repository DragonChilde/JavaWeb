package com.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Lee
 * @create 2019/12/17 15:46
 */
/*
 *   Servlet的生命周期
 *   Servlet是在Tomcat服务器上的
 *   Tomcat服务器---->创建Servlet容器
 *
 *   生命周期:从出生到死亡的过程
 *   Servlet的生命周期:Servlet从创建到销毁的过程
 *
 *   当第一次访问MyFirstServlet时
 *   1. 创建一个Servlet对象
 *   2. 调用init方法 init()初始化Servlet
 *   3. 调用Service方法  service()处理请求
 *
 *   以后请求:
 *   4. 只调用Service方法来处理请求     整个运行期间只创建一个servlet对象Servlet是(单例多线程)
 *
 *   当项目从服务器上卸载:
 *   5. 服务器会调用destory方法
 * */
public class HelloServlet implements Servlet {
    public HelloServlet() {
        System.out.println("this is Construct");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("this is init method");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("this is getServletConfig method");
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("this is service method");
    }

    @Override
    public String getServletInfo() {
        System.out.println("this is getServletInfo method");
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("this is destroy method");
    }
}
