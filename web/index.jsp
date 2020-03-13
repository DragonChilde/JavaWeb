<%@ page import="java.math.BigDecimal" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/12
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
hello world !!!!!

<a href="/javaweb/first">First Servlet</a>

POST提交
<form method="post" action="./encodingpostandget">
    姓名: <input type="text" name="username" value=""/><br/>
    电话: <input type="text" name="phone" value=""/><br/>
    姓别:男<input type="radio" name="sex[]" value="" /> ,女<input type="radio" name="sex[]" value=""/><br/>

    <input type="checkbox" name="checkbox" value="1"/>
    <input type="checkbox" name="checkbox" value="2"/>
    <input type="checkbox" name="checkbox" value="3"/>
    <input type="checkbox" name="checkbox" value="4"/>
    提交:<input type="submit" name="submit"/><br/>
</form>

GET提交
<form method="GET" action="./encodingpostandget">
    姓名: <input type="text" name="username" value=""/><br/>
    电话: <input type="text" name="phone" value=""/><br/>
    姓别:男<input type="radio" name="sex[]" value="" /> ,女<input type="radio" name="sex[]" value=""/><br/>

    <input type="checkbox" name="checkbox" value="1"/>
    <input type="checkbox" name="checkbox" value="2"/>
    <input type="checkbox" name="checkbox" value="3"/>
    <input type="checkbox" name="checkbox" value="4"/>
    提交:<input type="submit" name="submit"/><br/>
</form>

<a href="./encoding">乱码问题</a>
<a href="./encoding">乱码问题</a>

<br/>
<hr/>
<a href="./jsp/filter.jsp?money=1">有钱可以进,过滤器</a><br/>
<a href="./jsp/filter.jsp">没钱不可进,过滤器</a>


<img src="aaa.jpg" />

<hr/>

<a href="./jsp/listener.jsp">监听器测试</a>
<hr/>

<a href="./jsp/lan.jsp">国际化</a>


</body>
</html>
