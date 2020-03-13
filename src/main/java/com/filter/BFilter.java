package com.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Lee
 * @create 2020/3/5 14:46
 */
public class BFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("this is B Filter 3");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("this is B Filter 4");
    }
}
