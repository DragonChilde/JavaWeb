<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
this is test3 jsp
<%--<jsp:include page="error.jsp"></jsp:include>--%>
<%--转发到指定页面--%>
<jsp:forward page="error.jsp">
    <jsp:param name="name" value="wangwu"/>
    <jsp:param name="age" value="16"/>
</jsp:forward>
</body>
</html>
