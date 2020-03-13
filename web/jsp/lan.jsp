<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Date" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    /*定义特定的语言区域*/
/*    Locale locale = new Locale("en","US");
    ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale);
    String login = bundle.getString("login");
    pageContext.setAttribute("login",login);*/
%>
${login}


<%--传区域全名 语言_国家--%>
<fmt:setLocale value="${param.lan}"/>
<%--设置资源的基础名--%>
<fmt:setBundle basename="i18n"/>
<%--获取资源文件中的信息--%>
<fmt:message key="success" />
<fmt:formatDate value="<%=new Date()%>" dateStyle="FULL" timeStyle="FULL" type="both"/>
<%--成功 2020年3月13日 星期五 下午05时29分17秒 CST--%>
<%--success Friday, March 13, 2020 5:29:28 PM CST--%>
<br/>
<fmt:message key="message">
    <%--设置参数--%>
    <fmt:param>张三</fmt:param>
    <fmt:param><fmt:formatDate value="<%=new Date()%>" dateStyle="FULL" timeStyle="FULL" type="both"/></fmt:param>
</fmt:message>
</body>
</html>
