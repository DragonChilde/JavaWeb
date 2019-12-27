package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Lee
 * @create 2019/12/24 14:27
 */
public class EncodingServlet  extends HttpServlet {

    public EncodingServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //在第一次操作response之前,设置好内容类型和字符编码
        //第一种方式:setContentType(),setCharacterEncoding()
        //告诉浏览器传输的数据的内容类型
        //resp.setContentType("text/html");
        //告诉浏览器编码
        //resp.setCharacterEncoding("utf-8");


        //2. 直接设置响应头Content-Type=text/html; charset=utf-8
        //resp.setHeader("content-type","text/html; charset=utf-8");

        //3. 设置Content-TyPE字段的值
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("请求成功");
    }
}
