<%@ page import="com.xml.bean.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--
el表达式中有一个empty运算符 就是判断一个对象是否为空 ==null 返回的是true或false
1 null
    变量的值是null true
    域对象中不存在这个变量 true
2空集合 true
3空数组    int[] i = null true
4空字符串
5空字符
--%>
<%
    Customer customer = null;

    ArrayList<Customer> list = new ArrayList<>();
    HashMap<Object, Object> map = new HashMap<>();
    Set<Object> set = new HashSet<>();
    set.add(null);

    pageContext.setAttribute("list",list);
    pageContext.setAttribute("list2","   ");
    pageContext.setAttribute("list3",map);
    pageContext.setAttribute("list4",set);
    pageContext.setAttribute("list5",new Customer());

    int[] i = new int[0];
    int[] i2 = null;

    String str = "";
    String str2 = "  ";

   pageContext.setAttribute("num",20);
    pageContext.setAttribute("num2","20");
%>
${empty customer}        <%--true--%>
${empty pageScope.list}  <%--true--%>
${empty list}            <%--true--%>
${empty str}             <%--true--%>
${empty str2}           <%--true--%>
${empty list2}          <%--false--%>
${empty i}              <%--true--%>    <%--数组就是一个对象,如果new一个对象的话,永远都是true--%>
${empty i2}             <%--true--%>
${empty list3}          <%--true--%>
${empty list4}          <%--false--%>
${empty list5}          <%--false--%>

${empty customer? "空对象":customer}       <%--空对象--%>
${num + 10}     <%--30--%>
${num2 + "11"}    <%--31--%>
${num2}11   <%--要拼接成字符串要放在外面,不然使用运算符都是进行运算--%>

<%--在实际项目中可以使用EL表达式减少和前端代码的交互--%>
${pageContext.request.contextPath}      <%--/javaweb--%>
${pageContext.request.scheme}           <%--http--%>

<%

pageContext.setAttribute("ctx",request);
%>
<base href="${ctx.scheme}//:${ctx.serverName}:${ctx.serverPort}${ctx.contextPath}">
</body>
</html>
