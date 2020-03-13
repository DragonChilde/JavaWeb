<%@ page import="com.listener.bean.Student" %>
<%@ page import="com.listener.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/11
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  /*  Thread.sleep(10000);
    session.invalidate();*/


  /*  request.setAttribute("user","111111");
    Thread.sleep(100);
    request.setAttribute("user","222222");
    Thread.sleep(100);
    request.removeAttribute("user");*/


 /* application.setAttribute("age","11");
    Thread.sleep(100);
    application.setAttribute("age","12");
      Thread.sleep(100);
    application.removeAttribute("age");*/

   /* session.setAttribute("sex","男");
    Thread.sleep(100);
    session.setAttribute("sex","女");
    Thread.sleep(100);
    session.invalidate();*/

  /*Student student = new Student("张三");
  session.setAttribute("username",student);*/

    User user = new User("李四");
    /*给session中保存User对象(绑定)*/
    session.setAttribute("user",user);
    Thread.sleep(100);
    //移除
    session.removeAttribute("user");
%>
</body>
</html>
