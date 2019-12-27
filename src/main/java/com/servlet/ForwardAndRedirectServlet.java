package com.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lee
 * @create 2019/12/24 9:35
 */
public class ForwardAndRedirectServlet extends HttpServlet{
    public ForwardAndRedirectServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //1 . 重定向到页面,就是告诉浏览器重新请求一个资源
        resp.sendRedirect("success.html");

        //2. 转发页面
        //转发:服务器处理完以后转交到另外一个资源.当我们转发的资源是一个页面资源(静态资源),服务器会给浏览器返回这个资源
        //当转交给下一个servlet的,servlet可以继续处理
       // req.getRequestDispatcher("success.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
