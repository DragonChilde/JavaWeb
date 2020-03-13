<%@ page import="com.jstl.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/24
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>+
    <title>BAND COOKIE</title>
</head>
<body>

<%
    session.setAttribute("id","100");
    session.setAttribute("user",new User("张三"));
%>

<%--Url重写--%>
<%--http://localhost:8080/javaweb/session;jsessionid=79F5E6DBB07C62742BF7033FBDB17352?method=add--%>
<a href="<%=response.encodeRedirectURL(request.getContextPath()+"/session?method=add")%>">去其它页面获取数据</a>
<%--JSTL可以替代value指定要重写哪个url     /代表当前--%>
<a href="<c:url value="/session?method=add"></c:url>">去其它页面获取数据</a>


<a href="<c:url value="session2.jsp"></c:url>">活化和钝化</a>
</body>
</html>
