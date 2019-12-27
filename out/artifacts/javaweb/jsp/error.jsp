<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/27
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
this is error page

<%--捕获异常信息,在页面进行打印输出--%>
<%--<%= exception.getMessage()%>--%>

<%--打印输出获取页面转发过来的参数--%>
<%=request.getParameter("name")%>>
<%=request.getParameter("age")%>>
</body>
</html>
