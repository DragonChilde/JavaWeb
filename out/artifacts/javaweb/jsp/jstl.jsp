<%@ page import="com.jstl.bean.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL</title>
</head>
<body>

<%
    pageContext.setAttribute("msg","<h1>你好</h1>");
%>
<c:out value="${msg}" default="hello"></c:out>

<hr/>
<c:set var="tip" scope="request" value="我是提示信息"></c:set>
${requestScope.tip}

<hr/>
<%
    Student student = new Student("张三",16);
    pageContext.setAttribute("student",student);
%>
<c:set property="username" value="李四" target="${student}"></c:set>
<%=student.getUsername()%>

<hr/>
<%
    pageContext.setAttribute("student",student);
    request.setAttribute("student",student);
    session.setAttribute("student",student);
    application.setAttribute("student",student);
%>
<c:remove var="student" scope="page"/>
page:${pageScope.student.username}<br/>
request:${requestScope.student.username}<br/>
session:${sessionScope.student.username}<br/>
application:${applicationScope.student.username}<br/>


<hr/>
<%--判断为真,页面直接进入--%>
<c:if test="${10>1}" scope="page" var="flag">
    这是在if的作用域里
</c:if>
${flag}
<%--if判断没有else语句只能再写多个if判断--%>
<c:if test="${10<1}" scope="page" var="flag2">
    .....
</c:if>

<!--if标签还可以相互嵌套-->
<c:if test="${1>0}">
    这里是判断1>0
    <c:if test="${2>1}">
        这里是判断2>1
    </c:if>
</c:if>

<hr>
<%--当所有when的条件都不满足时,才会进入otherwise--%>
<c:choose>
    <c:when test="${student.age > 19}">
        你已是成年人
    </c:when>
    <c:when test="${student.age <=18 && student.age >=16}">
        你正在读高中
    </c:when>
    <c:when test="${student.age <=15 && student.age >=13}">
        你正在读初中
    </c:when>
    <c:otherwise>
        你还是个小BB
    </c:otherwise>
</c:choose>
<hr>

<c:forEach var="num" begin="1" end="10" step="1">
    ${num}
</c:forEach>

<%
    ArrayList<Student> list = new ArrayList<>();
    list.add(new Student("张三",12));
    list.add(new Student("李四",13));
    list.add(new Student("王五",14));
    list.add(new Student("陈二",15));

    request.setAttribute("list",list);
%>
<hr>
<c:forEach items="${requestScope.list}" var="student">
    ${student.username}----${student.age}<br/>
</c:forEach>

<hr>
name----age----begin----end---step----count----index<br/>
<c:forEach begin="0" end="5" step="2" items="${requestScope.list}" var="student" varStatus="status">

    ${student.username}----${student.age}----${status.begin}----${status.end}----${status.step}----${status.count}----${status.index}<br/>
</c:forEach>
<%--
    张三----12----javax.servlet.jsp.jstl.core.LoopTagSupport$1Status@50a4d770
    李四----13----javax.servlet.jsp.jstl.core.LoopTagSupport$1Status@50a4d770
    王五----14----javax.servlet.jsp.jstl.core.LoopTagSupport$1Status@50a4d770
    陈二----15----javax.servlet.jsp.jstl.core.LoopTagSupport$1Status@50a4d770
--%>

<hr>

<c:url value="/index.jsp" var="uri" scope="request"></c:url>
${uri}


<hr>



<c:redirect url="/index.jsp"></c:redirect>

<%--<c:if test="${msg}" scope="request">
    <c:redirect url="/index.jsp"></c:redirect>
</c:if>--%>
</body>
</html>
