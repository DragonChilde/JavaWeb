package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Lee
 * @create 2020-02-19 15:30
 */
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if(req.getParameter("method").equals("add"))
        {
            Cookie cookie = new Cookie("username","张三");
            Cookie age = new Cookie("age","18");
           resp.addCookie(cookie);
           resp.addCookie(age);
            resp.getWriter().write("add cookie success!");
        }

        if(req.getParameter("method").equals("get"))
        {
            Cookie[] cookies = req.getCookies();
            for(Cookie cookie:cookies)
            {
                System.out.println(cookie.getName()+"------"+cookie.getValue());
            }
        }

        if(req.getParameter("method").equals("del"))
        {
            Cookie cookie = new Cookie("username","张三");
            //负数 不保存Cookie,即使发给浏览器也不会保存
            //正数 Cookie的最大存在时间 单位:秒
            //0 表示删除Cookie
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            System.out.println("del success!");

        }

        if(req.getParameter("method").equals("pre")) {
            Cookie cookie = new Cookie("username","张三");
            cookie.setMaxAge(60*60);
            resp.addCookie(cookie);
            System.out.println("cookie presist success!");
        }


        if(req.getParameter("method").equals("path")) {
            Cookie cookie = new Cookie("msg","你好");
            cookie.setPath("/hello");
            resp.addCookie(cookie);
            System.out.println("cookie path set success!");
        }

        if(req.getParameter("method").equals("edit")) {

            Cookie cookie = new Cookie("username", "李四");
            resp.addCookie(cookie);

            /*Cookie[] cookies = req.getCookies();
            for (Cookie cookie:cookies)
            {
                if (cookie.getName().equals("username"))
                {
                    cookie.setValue("李四");
                    resp.addCookie(cookie);
                }
            }*/
            System.out.println("edit cookie success!");
        }
    }
}
