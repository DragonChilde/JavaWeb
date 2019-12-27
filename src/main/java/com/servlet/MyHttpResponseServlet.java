package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Lee
 * @create 2019/12/19 14:26
 */
public class MyHttpResponseServlet extends HttpServlet {

    private static final long serialVersionUID = -4986016053554532451L;

    public MyHttpResponseServlet() {
        super();
    }

    /*用来处理GET请求,*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //每次GET请求调用doGet方法
        System.out.println("this is doGet method");
        //正常不区分请求方式
        this.doPost(req,resp);
    }

    /*用来处理POST请求*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("this is doPost method");

        //HttpServletRequest req, HttpServletResponse resp
        //HttpServletRequest request代表就是封装浏览器请求的信息对象,收到浏览器请求
        //HttpServletResponse response代表就是要发送浏览器的响应对象,封装响应信息

        //1.可以给浏览器响应字符串
       /* PrintWriter writer = resp.getWriter();
        writer.write("hello , this is HttpServlet");*/

        //2.可以重定向一个页面或者其它资源.重定向就是服务器告诉浏览器重新请求别的资源
        //已经对之前的请求处理完了,让浏览器重新请求新的资源
        resp.sendRedirect("success.html");
    }
}
