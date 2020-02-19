<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我是JSP</title>
</head>
<body>
<%--JSP脚本片段,java代码片段--%>

<% System.out.println("HELLO WORLD!");%>
<%="hello world"%>
<br>
<%--JSP表达式 在页面输出内容--%>
<%--可以看到当引用所需的类时,会自动导入相应的包--%>
<%=new Date()%>
</body>
</html>
