package com.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lee
 * @create 2019/12/24 16:05
 */
public class PathServlet extends HttpServlet {

    public PathServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //转发到a页面
        //相对路径:相对与当前资源的路径
        //经常可能产生一些问题,转发会导致相对路径出现问题
        //req.getRequestDispatcher("pages/a.html").forward(req,resp);
       // resp.sendRedirect("pages/a.html");

        //绝对路径:以/开如 代表的是项目的根目录
        //http://localhost:8080/javaweb/pages/a.html
        //req.getRequestDispatcher("pages/a.html").forward(req,resp);


        //1. 转发以后使用绝对路径来写   /   表示项目 的根目录开始
        //转发，代表从项目的根目录      http://localhost:8080/javaweb/
        //req.getRequestDispatcher("/index.jsp").forward(req,resp);


        //2. 重定向 代表从tomcat的根目录-服务器的根   http://localhost:8080开始
        //同项目的有可能项目名不同,最好动态获取项目的根目录,项目路径

        ServletContext servletContext = getServletContext();
        String path = servletContext.getContextPath();

        String contextPath = req.getContextPath();
        System.out.println(path);
        System.out.println(contextPath);

        resp.sendRedirect(path + "/pages/a.html");

        //由浏览器解析：绝对路径都是从web，即服务器根开始
        //由服务器解析：绝对路径都是从项目根开始
    }
}
