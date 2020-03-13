package com.filter;

import javax.servlet.*;
import java.io.IOException;


/*创建Filter的实现类
* 是来过滤所有要访问页面的请求
* 服务端的三大组件,运行在服务器上,服务器调用
* */
public class HelloFilter implements Filter {

    public HelloFilter() {
        System.out.println("filter construct...");
    }

    /*初始化方法*/
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init....");
    }

    /*执行过滤方法*/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String money = (String)servletRequest.getParameter("money");
        System.out.println(money);
        if (money != null){
            //请求放行(程序不清楚是否还有其它过滤器,因此这里使用doFilter方法放行)
            System.out.println("放行前一");
            filterChain.doFilter(servletRequest,servletResponse);
            System.out.println("放行后三");
        } else {
            servletResponse.getWriter().print("no money no talk!");
        }
    }


    /*销毁方法*/
    @Override
    public void destroy() {
        System.out.println("filter destory....");
    }
}
