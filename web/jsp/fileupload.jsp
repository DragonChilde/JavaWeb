<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/16
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/fileupload">
    姓名:<input type="text" name="name" value=""/>
    文件上传:<input type="file" name="file" value="" />
    <input type="submit" name="提交" />
</form>
</body>
</html>
