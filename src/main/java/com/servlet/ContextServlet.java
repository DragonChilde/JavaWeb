package com.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Lee
 * @create 2019/12/18 10:40
 */
public class ContextServlet implements Servlet {
    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        /*获取到ServletConfig对象*/
        ServletConfig servletConfig = this.getServletConfig();
        /*使用获取到ServletConfig对象获取到ServletContext*/
        ServletContext servletContext = servletConfig.getServletContext();

        /*研究ServletContext*/
        /*一个WEB对应一个ServletContext,代表整个WEB项目*/
        /*可以获取web项目的配置信息,获取web项目的初始化参数*/
        String user = servletContext.getInitParameter("user");
        String username = servletContext.getInitParameter("username");
        System.out.println(user +"------"+username);        //张三------null
        /*获取web项目的路径*/
        String contextPath = servletContext.getContextPath();
        System.out.println(contextPath);        ///javaweb

        /*获取资源的真实路径*/
        //虚拟路径:是网络访问使用虚拟路径.每一个虚拟路径应该对应一个实际的资源
        //静态资源(文件的形式),动态资源(只是启动一段程序代码)
        //真实路径:文件在磁盘中的存储路径
        String realPath = servletContext.getRealPath("/index.jsp");
        System.out.println(realPath);       //F:\Code\JavaWeb\out\artifacts\javaweb\index.jsp

        /*可以作为最大的域对象共享数据域对象:共享数据4大域对象.application对象*/


    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
