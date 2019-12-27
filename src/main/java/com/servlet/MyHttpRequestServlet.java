package com.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lee
 * @create 2019/12/23 16:22
 */
public class MyHttpRequestServlet extends HttpServlet {

    public MyHttpRequestServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //HttpServletRequest request 代表浏览器发送给服务器的请求信息
        //http请求.请求首行 请求头 空行 请求体 (封装的请求数据-post)
        //get请求将所有携带的参数放在url
        //1. 获取请求数据get放在url后面post 放在请求体里
        String username = req.getParameter("username");
        System.out.println(username);       //test

        //使用getParameterValues多选框的内容
        String[] checkboxes = req.getParameterValues("checkbox");
        for (String v:
             checkboxes) {
            System.out.println(v);      //1 2 3
        }

        //2. 获取请求头信息
        String header = req.getHeader("User-Agent");
        System.out.println("user-agent:"+ header);  //user-agent:Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36

        //3. 转发一个页面/资源
        //先获取一个请求转发器
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("success.html");
        requestDispatcher.forward(req,resp);

        //4. 作为域对象共享数据 4个 application request
    }
}
