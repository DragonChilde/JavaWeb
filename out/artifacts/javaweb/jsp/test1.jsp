<%@ page import="java.util.Date" %>
<%@ page  pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" errorPage="/jsp/error.jsp" session="true" isELIgnored="false"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%=new Date()%>

<%
    int age = 16;
    if (age >= 16)
    {
%>
         <h1>11111<h1/>

<%  } else {
%>
        <h1>22222</h1>
<%  }

%>

<%
 int a = 1;

%>
 <%
System.out.println(a);
%>

 <%!
    private String name = "李四";
    private void test()
    {
        System.out.println(name);
    }
 %>
 <% test();%>


             <!--HTML注释-->

             <%--JSP注释--%>


 <% int b = 10/0;%>

             <% session.setAttribute("name","张三");%>
           ${sessionScope.get("name")}
</body>
</html>
