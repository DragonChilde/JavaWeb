<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020-02-18
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="mytag" uri="/mytag" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setAttribute("msg","HelloWorld");
%>
判断前面的字符串是否包含后面的(区分大小写):${fn:contains(msg,'hello')}<br>  <%--false--%>
判断前面的字符串是否包含后面的(不区分大小写):${fn:containsIgnoreCase(msg,'HELLO')}<br>  <%--false--%>
判断字符串是否以某个字符串开始:${fn:startsWith(msg,'Hello')}<br>   <%--true--%>
判断字符串是否以某个字符串结束:${fn:endsWith(msg,"world" )}<br>    <%--false--%>
判断某个字符串的起始位置:${fn:indexOf(msg,"o" )}<br>    <%--4--%>
字符串替换:${fn:replace(msg, "Hello", "Hi")}<br>     <%--HiWorld--%>
截取字符串(不包含后面的索引):${fn:substring(msg, 0, 5)}<br>    <%--Hello--%>

<hr>
<mytag:hello msg="hello"/>
</body>
</html>
