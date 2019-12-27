package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lee
 * @create 2019/12/24 15:06
 */
public class EncodingPostAndGetServlet extends HttpServlet {
    public EncodingPostAndGetServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //当在表单中填写中文的时候获取到的也是乱码
        //1. Post乱码原因
        //浏览器将数据编码并提交上来,但是服务器并不知道编码规则
        //解决方法,让服务器知道编码规则即可,重新设置请求的编码格式
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        System.out.println(username);

        //2 .GET乱码
        //原因:浏览器将地址栏也编码,服务器不知道,而且8080端口接受到url以后,已经按照默认的编码接收
        //所以在 req.setCharacterEncoding("utf-8");没用了
        //解决访求 ,修改Tomcat的server.xml配置文件 ,在8080端口配置处添加URIEncoding="UTF-8"
    }
}
