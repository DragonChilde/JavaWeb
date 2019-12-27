package com.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Lee
 * @create 2019/12/17 16:00
 */
public class ConfigServlet implements Servlet {

    private ServletConfig servletConfig;

    /*servlet的初如化方法,在servlet第一次被服务器创建的时候调用,只调用一次,因此这里的参数传值是由服务器传进来的*/
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    /*获取servlet的配置信息*/
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**/
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //ServletConfig
        //封装了servlet配置信息的对象,一个Servlet对象对应一个ServletConfig,封装的是当前servlet配置信息
        //1. 获取servlet的别名:getServletName()
        System.out.println(servletConfig.getServletName());     //打印的是web.xml里配置的servlet-name别名:ConfigServlet
        //2. 获取servlet初如化参数
        System.out.println(servletConfig.getInitParameter("username"));     //李四
        //3. 获取ServletContext对象.对象当前servlet的上下文.代表当前项目的信息
        System.out.println(servletConfig.getServletContext());          //org.apache.catalina.core.ApplicationContextFacade@eee260a


    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
