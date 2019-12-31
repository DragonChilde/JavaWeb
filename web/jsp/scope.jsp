<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/30
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Scope</title>
</head>
<body>
<%
pageContext.setAttribute("pageName","page");
request.setAttribute("requestName","request");
session.setAttribute("sessionName","session");
application.setAttribute("applicationName","application");
%>
<jsp:forward page="scope2.jsp"></jsp:forward>
pageContest name is <%= pageContext.getAttribute("pageName")%><br>
request name is <%= request.getAttribute("requestName")%><br>
session name is <%= session.getAttribute("sessionName")%><br>
application name is <%= application.getAttribute("applicationName")%><br>

<a href="scope2.jsp">scope2</a>
</body>
</html>
