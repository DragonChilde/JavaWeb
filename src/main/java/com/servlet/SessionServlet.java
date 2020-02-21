package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Lee
 * @create 2020/2/21 15:15
 */
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        /*获取Session*/
        HttpSession session = req.getSession();
        if (method.equals("add"))
        {
            //判断Session是否新创建,
            boolean aNew = session.isNew();
            resp.getWriter().print("session is new :"+aNew);
        }

        if (method.equals("set"))
        {
            /*获取Session id,相当于cookie里的JSESSIONID*/
            String id = session.getId();
            session.setAttribute("sessionid",id);
            resp.getWriter().print("sessionid is "+id);
        }

        if (method.equals("get"))
        {
            String sessionid = (String) session.getAttribute("sessionid");
            resp.getWriter().print("sessionid get "+sessionid);
        }

        if(method.equals("time"))
        {
            //获取session的最大存话时间 以秒为单位
            //session默认是30分钟,为什么新会话开启会返回新session
            //因为获取session根据cookie带来的jsessionid来获取.cookie默认关闭浏览器就没了
            //再来获取session,返回新的session,旧的session还在.只是找不到而已
            int maxInactiveInterval = session.getMaxInactiveInterval();
            resp.getWriter().print("max time is "+maxInactiveInterval);
        }

        if(method.equals("updatetime")) {
            /*1传入负数:代表永不过期*/
            /*2传入正数:代表多少秒后过期,距离最后一次使用session时间*/
            session.setMaxInactiveInterval(3);
            resp.getWriter().print("set session time success!");
        }

        if(method.equals("invalid")) {

            session.invalidate();
            resp.getWriter().print("session is invalid");
        }
    }

}
