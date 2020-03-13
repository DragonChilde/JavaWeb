package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Lee
 * @create 2020/3/3 10:55
 */
public class AFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //filterConfig是封装filter配置信息的对象
        String filterName = filterConfig.getFilterName();       //获取别名
        System.out.println(filterName);
        //filter初始化参数
        String username = filterConfig.getInitParameter("username");
        System.out.println(username);
        //获取web初始化参数
        ServletContext servletContext = filterConfig.getServletContext();
        String user = servletContext.getInitParameter("user");
        System.out.println(user);

        System.out.println(servletContext);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //System.out.println("this is AFilter");

        /*转化成HttpServletRequest会多了许多方法*/
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        /*获取访问路径*/
        String uri = httpServletRequest.getRequestURI();
        /*获取完整的访问路径*/
        StringBuffer url = httpServletRequest.getRequestURL();
        //System.out.println(uri);     /*/javaweb/jsp/filter.jsp*/
       //this is AFilter System.out.println(url);     /*http://localhost:8080/javaweb/jsp/filter.jsp*/
        if (uri.endsWith("jpg"))      //如果是以jpg为后缀进行拦截
        {
            System.out.println("ok");
        } else {
            servletResponse.setContentType("text/html;charset=utf-8");
            servletResponse.getWriter().print("你好");
            System.out.println("this is A Filter 1");
            filterChain.doFilter(servletRequest,servletResponse);
            System.out.println("this is A Filter 2");
        }
    }

    @Override
    public void destroy() {

    }
}
