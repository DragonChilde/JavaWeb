<%@ page import="org.omg.CORBA.Request" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/24
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Code</title>
</head>
<body>

<img src="<%=request.getContextPath()+"/kaptcha"%>" />

<a href="<c:url value="code2.jsp"></c:url>">验证验证码</a>
</body>
</html>
