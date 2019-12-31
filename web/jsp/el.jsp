<%@ page import="com.xml.bean.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--

<%pageContext.setAttribute("page","page1");%>
<%request.setAttribute("request","request2");%>
<%session.setAttribute("session","session3");%>
<%application.setAttribute("application","application4");%>


el表达式获取属性值<br>
page:${page}<br>
request:${request}<br>
session:${session}<br>
application:${application}<br>

--%>



<%--<%
    Customer customer = new Customer();
    customer.setName("张三");
%>
<%pageContext.setAttribute("cus",customer);%>
<%request.setAttribute("cus","request2");%>
<%session.setAttribute("cus","session3");%>
<%application.setAttribute("cus","application4");%>--%>

<%--

el表达式获取属性值<br>
page:${cus}<br>
request:${cus}<br>
session:${sessionScope.cus}<br>
application:${applicationScope.cus}<br>

--%>
<%--
el表达式获取属性值<br>
page:${pageScope.cus.name}<br>
request:${requestScope.cus}<br>
session:${sessionScope.cus}<br>
application:${applicationScope.cus}<br>
--%>
<%--
<%
String str = "你好";
%>
jspl:<%=str%>
el:${str}
--%>

<%--<%
    Customer customer = new Customer();
    customer.setName("张三");
%>
<%pageContext.setAttribute("cus-s",customer);%>
el表达式获取属性值<br>
pageContext: ${pageScope['cus-s']}<br>
pageContext: ${pageScope['cus-s'].name}<br>
pageContext: ${pageScope['cus-s']['name']}<br>--%>


<%--<%
pageContext.setAttribute("page","page1");
%>

jstl:<%=pageContext.getAttribute("page1")%><br>
el:${page1}--%>

<%--el中的其它对象 pageContext可以出jsp页面其它的隐含对象,然后可以取出所有隐含对象中的属性了--%>
jstl:<%=pageContext.getRequest().getScheme()%>
el:${pageContext.request.scheme}


<%--HTTP相关 5个--%>
param (封装了所有的请求参数的key-value)对应一个请求参数 request.getParam("username) <br>
paramValues 对应一组请求参数<br>
header 请求头 request.getHeader("User-Agent")<br>
headerValues 请求头返回字符数组<br>
cookie 获取某个cookie对象 取出cookie的值<br>

请求参数:${param.username}<br>
请求头:${header['User-Agent']}<br>
cookie:${cookie.JSESSIONID.name} || ${cookie.JSESSIONID.value}<br>

<%--initParam 获取web.xml的初始化参数--%>
<%--注意取的不是Servlet的初始他参数,因为JSP页面都是继承Tomcat下的org.apache.jasper.servlet.JspServlet,命名为JSP--%>
web.xml初始化参数:${initParam.user}<br>

</body>
</html>
