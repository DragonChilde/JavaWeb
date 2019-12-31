<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/30
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Scope2</title>
</head>
<body>
pageContest name is <%= pageContext.getAttribute("pageName")%><br>
request name is <%= request.getAttribute("requestName")%><br>
session name is <%= session.getAttribute("sessionName")%><br>
application name is <%= application.getAttribute("applicationName")%><br>

</body>
</html>
