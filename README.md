 <a href="http://120.77.237.175:9080/photos/javaweb/">JavaWeb</a>

# XML #

**转义字符**		

		< 	&lt;
		>   &gt;
		&	&amp;
		"	&quot;
		'	&apos;


**CDATA区**

CDATA区的内容不会被解析器当成标签解析.在浏览器会原封不动的输出


## xml解析 ##

**常用的解析方式: DOM 和SAX**

![](http://120.77.237.175:9080/photos/javaweb/01.png)

- DOM 第三方使用Dom4j
- SAX 使用pull(Android内置)

## 主要方法 ##

- **getNodeType()**: 获取当节点类型
- **getRootElement()**: 获取根节点
- **getName()**: 获取当前节点名
- **elements()**: 获取当前节点下的所有子节点
- **elements(name)**: 获取name节点下的所有子元素
- **element(name)**: 获取节点下的首个指定节点
- **element()**: 找到第一个子元素
- **elementText(name)** : 获取当前节点下名为name子元素的文本值
- **attributeValue(name)** : 获取标签里指定的属性值
- **getText()** : 获取文本标签里面的文本值


## 代码示例 ##

**pom**
	
		/**加载的DOM包级即支持DOM也支持SAX解析**/
	  <dependency>
	        <groupId>org.dom4j</groupId>
	        <artifactId>dom4j</artifactId>
	        <version>2.1.1</version>
	    </dependency>

**XML**

	<?xml version="1.0" encoding="utf-8" ?>
	<customers>
	    <customer id="1">
	        <name>张三</name>
	        <age>11</age>
	    </customer>
	    <customer id="2">
	        <name>李四</name>
	        <age>12</age>
	    </customer>
	</customers>

- **测试读取**

	    @Test
	    public void test1() throws Exception
	    {
	        //创建一个阅读器
	        SAXReader saxReader = new SAXReader();
	        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("customer.xml");
	        //使用阅读器读取文件
	        Document document = saxReader.read(stream);
	        //getNodeType()获取当节点类型
	        short nodeType = document.getNodeType();
	    
	        System.out.println(nodeType); // short DOCUMENT_NODE = 9; 文档节点
	    
	        //先要获取根节点,使用根节点往下找
	        Element rootElement = document.getRootElement();
	        //getName()获取当前节点名
	        String name = rootElement.getName();
	    
	        System.out.println(name);//customers
	    
	        //使用根节点往下找
	        //获取当前节点下的所有子节点
	        //elements代表所有customer的集合
	        List<Element> elements = rootElement.elements();
	        for (Element element:
	             elements) {
	            //每个element代表一个customer标签
	            System.out.println(element.getName());  //customer
	    
	            //elementText(name)获取当前节点下名为name子元素的文本值
	            String text = element.elementText("name");
	            System.out.println(text);   //张三  |  李四
	    
	            //attributeValue(name)获取标签里指定的属性史
	            Integer id =  Integer.parseInt(element.attributeValue("id"));
	            System.out.println(id);     //  1 |  2
	    
	            List<Element> elementList = element.elements();
	            for (Element element2:
	                    elementList) {
	                System.out.println(element2.getName());     //name	| age
	                //getText()获取文本标签里面的文本值
	                String test2 = element2.getText();  //张三   11|  李四  12
	                System.out.println(test2);
	            }
	        }
	    
	    }


	把读取到的值封装到Bean里
	
	 	 @Test
	    public void test2() throws Exception
	    {
	        //创建一个阅读器
	        SAXReader saxReader = new SAXReader();
	        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("customer.xml");
	        //使用阅读器读取文件
	        Document document = saxReader.read(stream);
	
	        //先要获取根节点,使用根节点往下找
	        Element rootElement = document.getRootElement();
	
	        //使用根节点往下找
	        //获取当前节点下的所有子节点
	        //elements代表所有customer的集合
	        List<Customer> list = new ArrayList<>();
	        List<Element> elements = rootElement.elements();
	        for (Element element:
	                elements) {
	            //attributeValue(name)获取标签里指定的属性史
	            Integer id =  Integer.parseInt(element.attributeValue("id"));
	            //elementText(name)获取当前节点下名为name子元素的文本值
	            String customerName = element.elementText("name");
	            Integer age =   Integer.parseInt(element.elementText("age"));
	            Customer customer = new Customer(id, customerName, age);
	
	            list.add(customer);
	        }
	        System.out.println(list);
			/**[Customer{id=1, name='张三', age=11}, Customer{id=2, name='李四', age=12}]**/
	    }

- **测试写入**

	    @Test
	    public void test3() throws Exception
	    {
	        //创建一个阅读器
	        SAXReader saxReader = new SAXReader();
	        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("customer.xml");
	        //使用阅读器读取文件
	        Document document = saxReader.read(stream);
	        //获取根节点
	        Element rootElement = document.getRootElement();
	        //获取根节点下的名为customer的首个节点
	        Element element = rootElement.element("customer");
	        Element name = element.element("name");
	        name.setText("张一");
	        element.addAttribute("firstName","张");
	    
	        //把修改的东西保存起来
	        //OutputFormat将辆出的数据格式化
	        //OutputFormat format = OutputFormat.createCompactFormat();
	        OutputFormat format = OutputFormat.createPrettyPrint();
	    
	        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("out.xml"),format);
	        xmlWriter.write(document);
	        xmlWriter.close();
	    
	    }


## XPath  ##

XPath是在XML文档中查找信息的语言

XPath是通过元素和属性进行查找,简化了Dom4j查找节点的过程

<a href="https://www.w3school.com.cn/xpath/xpath_syntax.asp">XPath语法</a>

	//:	找到所有
	/	:从根开始找
	selectSingleNode(xPath):		根据xPath规则获取单个节点
	selectNodes(xPath):				根据xPath规则返回节点集合

- **POM**
		
		<!--必须要导入jaxen包-->
		<dependency>
	        <groupId>jaxen</groupId>
	        <artifactId>jaxen</artifactId>
	        <version>1.2.0</version>
	    </dependency>

- 测试

	    @Test
	    public void testXpath() throws Exception{
	        SAXReader saxReader = new SAXReader();
	        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("customer.xml");
	    
	        Document document = saxReader.read(stream);
	        //获取根节点
	        Element rootElement = document.getRootElement();
	        //获取根节点下的标签为customer,属性id为1的标签
	        Node node = rootElement.selectSingleNode("//customer[@id=1]");
	        //Element是Node的子类
	        Element element = (Element)node;
	        //获取属性id的值
	        String id = element.attributeValue("id");
	        System.out.println(id);		//1
	    
	        //选取所有标签为name的节点
	        List<Node> nodes = rootElement.selectNodes("//name");
	    
	        for (Node node1:
	        nodes) {
	            Element element1 = (Element) node1;
	            System.out.println(element1.getText());	//张三	|李四
	        }
	    }
	
# HTTP协议 #

## HTTP协议简介 ##

- HTTP 超文本传输协议 (HTTP-Hypertext transfer protocol)，是一个属于应用层的面向对象的协议，由于其简捷、快速的方式，适用于分布式超媒体信息系统。它是一种详细规定了浏览器和万维网服务器之间互相通信的规则，通过因特网传送万维网文档的数据传送协议
- 客户端与服务端通信时传输的内容我们称之为报文
- HTTP就是一个通信规则，这个规则规定了客户端发送给服务器的报文格式，也规定了服务器发送给客户端的报文格式。实际我们要学习的就是这两种报文。客户端发送给服务器的称为”请求报文“，服务器发送给客户端的称为”响应报文“。

## 报文 ##

### 报文格式 ###

![](http://120.77.237.175:9080/photos/javaweb/02.png)

### 请求报文 ###

- 请求首行（请求行）；
- 请求头信息（请求头）；
- 空行；
- 请求体

### 示例代码(模拟服务器) ###

	  public static void main(String[] args) throws Exception {
	    ServerSocket serverSocket = new ServerSocket(8080);
	
	    while (true)
	    {
	        try {
	            System.out.println("正在监听8080端口............");
	            Socket accept = serverSocket.accept();
	            InputStream inputStream = accept.getInputStream();
	
	            byte[] bytes = new byte[1024];
	
	            inputStream.read(bytes);
	
	            String s = new String(bytes);
	            System.out.println(s);
	
	        } catch (Exception e){
	            e.printStackTrace();
	
	        }
	    }
	}

### GET请求 ###

	请求首行 GET /?username=test HTTP/1.1
	请求头信息 Host: localhost:8080
	Connection: keep-alive
	Upgrade-Insecure-Requests: 1
	User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36
	Sec-Fetch-User: ?1
	Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
	Sec-Fetch-Site: cross-site
	Sec-Fetch-Mode: navigate
	Accept-Encoding: gzip, deflate, br
	Accept-Language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7,ja;q=0.6,zh-TW;q=0.5
	这里是空行

- GET /?username=test HTTP/1.1：GET请求，请求服务器路径为http://localohost:8080/?username=test，协议为1.1；**没有请求体，所以请求信息会放在url上，即第一行上**
- Host:localhost：请求的主机名为localhost；
- User-Agent: Mozilla/5.0 (compatible; MSIE 8.0…：与浏览器和OS相关的信息。有些网站会显示用户的系统版本和浏览器版本信息，这都是通过获取User-Agent头信息而来的；
- Accept: /：告诉服务器，当前客户端可以接收的文档类型， /，就表示什么都可以接收；
- Accept-Language: zh-CN：当前客户端支持的语言，可以在浏览器的工具选项中找到语言相关信息；
- Accept-Encoding: gzip, deflate：支持的压缩格式。数据在网络上传递时，可能服务器会把数据压缩后再发送；
- Connection: keep-alive：客户端支持的链接方式，保持一段时间链接，默认为3000ms；
- Cookie:JSESSIONID=369766FDF6220F7803433C0B2DE36D98：因为不是第一次访问这个地址，所以会在请求中把上一次服务器响应中发送过来的Cookie在请求中一并发送过去。


### POST请求 ###

	请求首行 POST / HTTP/1.1
	请求头信息 Host: localhost:8080
	Connection: keep-alive
	Content-Length: 13
	Cache-Control: max-age=0
	Upgrade-Insecure-Requests: 1
	Origin: null
	Content-Type: application/x-www-form-urlencoded
	User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36
	Sec-Fetch-User: ?1
	Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
	Sec-Fetch-Site: cross-site
	Sec-Fetch-Mode: navigate
	Accept-Encoding: gzip, deflate, br
	Accept-Language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7,ja;q=0.6,zh-TW;q=0.5
	这里是空行
	username=test

- **POST请求有请求体，而GET请求没有请求体。所以POST可以提交大量数据，GET不能提交大量数据**
- Referer: http://localhost:8080/hello/index.jsp：请求来自哪个页面，例如你在百度上点击链接到了这里，那么Referer:http://www.baidu.com；如果你是在浏览器的地址栏中直接输入的地址，那么就没有Referer这个请求头了；
- Content-Type:application/x-www-form-urlencoded：表单的数据类型，说明会使用url格式编码数据；url编码的数据都是以“%”为前缀，后面跟随两位的16进制，例如“传智”这两个字使用UTF-8的url编码用为“%E4%BC%A0%E6%99%BA”；
- Content-Length:13：请求体的长度，这里表示13个字节。
- username=test：请求体内容！test是在表单中输入的数据，username是表单字段的名字。

## 响应报文 ##

### 格式 ###

- 响应首行（响应行）；
- 响应头信息（响应头）；
- 空行；
- 响应体；服务器传递给客户端的网页信息

		HTTP/1.1 200 OK
		Server: Apache-Coyote/1.1
		Content-Type: text/html;charset=UTF-8
		Content-Length: 274
		Date: Tue, 07 Apr 2015 10:08:26 GMT
		
		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
		<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		</head>
		<body>
		<h1>Hello</h1>
		</body>
		</html>

- HTTP/1.1 200 OK：响应协议为HTTP1.1**，状态码为200，表示请求成功**；
- Server: Apache-Coyote/1.1：服务器的版本信息；
- Content-Type: text/html;charset=UTF-8：响应体使用的编码为UTF-8；
- Content-Length: 274：响应体为274字节；
- Date: Tue, 07 Apr 2015 10:08:26 GMT：响应的时间，这可能会有8小时的时区差；

### 响应码 ###

响应码对浏览器来说很重要，它告诉浏览器响应的结果；

- 200：请求成功，浏览器会把响应体内容（通常是html）显示在浏览器中；
- 404：请求的资源没有找到，说明客户端错误的请求了不存在的资源；
- 500：请求资源找到了，但服务器内部出现了错误；
- 302：重定向，当响应码为302时，表示服务器要求浏览器重新再发一个请求，服务器会发送一个响应头Location，它指定了新请求的URL地址；

- **以2xx的响应码：都表示成功**
- **以3xx的响应码：都表示需要重新请求另一个资源**
- **以4xx的响应码：都表示资源未找到（请求地址错误），服务器启动失败**
- **以5xx的响应码：都表示服务器内部出错，（即代码错误）**


## MIME类型 ##

<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Basics_of_HTTP/MIME_Types">超链接</a>

# Tomcat #

## 服务器的目录结构 ##

- **bin**:	包含Tomcat启动命令,停止Tomcat命令等批处理,可执行文件...
- **conf**:	包含的是Tomcat的配置文件
- **lib**:	Tomcat运行依赖的jar包
- **logs**:	Tomcat的运行日志文件
- **temp**:	Tomcat存取临时文件的文件夹
- **webapps**:	里面集合了所有的web项目,每一个文件夹就代表一个项目.Tomcat启动默认访问的是ROOT目录
- **work**:	保存Tomcat运行时编译好的文件

## 动态WEB工程 ##

- **META-INF**: 项目的基础信息(不用管)
- **WEB-INF**:	WEB应用的配置信息(WEB-INF下的所有东西都是受保护,不能直接访问)
	- **lib**: 引入的所有的第三房jar包都放在lib里面,会自动添加到buildpath
	- **class**	:	java文件的全类名存储:把classes目录也叫作类路径
	- **web.xml**: 整个web应用的配置信息,<welcome-file-list>定义的是项目默认访问的页面

**发布:webapps里面的所有内容. WEB-INF/classes/全类名**

# Servlet #

## 什么是Servlet ##

1. Servlet是Sun公司制定的一套技术标准，包含与Web应用相关的一系列接口，是Web应用实现方式的宏观解决方案。而具体的Servlet容器负责提供标准的实现。
2. Servlet作为服务器端的一个组件，它的本意是“服务器端的小程序”。Servlet的实例对象由Servlet容器负责创建；Servlet的方法由容器在特定情况下调用；Servlet容器会在Web应用卸载时销毁Servlet对象的实例。
3. 简单可以理解为  **Servlet就是用来处理客户端的请求的**.

API上的介绍：A servlet is a small Java program that runs within a Web server. Servlets receive and respond to requests from Web clients, usually across HTTP, the HyperText Transfer Protocol.

**一个servlet就是一个小的java程序，servlets运行在web服务器，servlet接收和响应来自客服端的请求，通过http**

## Servlet作用 ##

1. 接收请求
2. 处理请求
3. 完成响应

## HelloWorld ##

编写Servlet三步:

1. 创建自己的类MyFirstServlet,实现Servlet接口
2. 实现service方法
3. 在web.xml中配置servlet信息

**首先定义自定义Servlet**

		public class MyFirstServlet implements Servlet {
		
		    //初始化
		    @Override
		    public void init(ServletConfig servletConfig) throws ServletException {
		
		    }
		
		    //获取Servlet配置信息
		    @Override
		    public ServletConfig getServletConfig() {
		        return null;
		    }
		
		    //服务
		    @Override
		    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
		        //server是用来处理来自客户端的请求
		        System.out.println("this is first servlet");
		
		        //ServletResponse 给浏览器发送一个响应
		        //获取一个写数据的对象
		        PrintWriter writer = servletResponse.getWriter();
		        writer.write("Hello world!");
		    }
		
		    //获取Servlet信息
		    @Override
		    public String getServletInfo() {
		        return null;
		    }
		
		    //销毁
		    @Override
		    public void destroy() {
		
		    }
		}

在web.xml配置

    <!--在servlet标签配置servlet的类信息:告诉服务器有这个servlet-->
    <servlet>
        <!--配置servlet的名字:相当于给servlet类起别名	给程序员看的-->
        <servlet-name>MyFirstServlet</servlet-name>
        <!--配置servlet类的全名	给服务器看:服务器通过全类名找到MyFirstServlet-->
        <servlet-class>com.servlet.MyFirstServlet</servlet-class>
    </servlet>
    <!--servlet映射信息,也这是servlet用来处理哪一个请求-->
    <servlet-mapping>
        <!--刚才配置的servlet的别名-->
        <servlet-name>MyFirstServlet</servlet-name>
        <!--告诉服务器处理这个servlet(MyFirstServlet用来处理哪个请求)   http://localhost:8080/javaweb/first-->
    	!--表示就是访问当前项目下的/first-->
        <url-pattern>/first</url-pattern>
    </servlet-mapping>

测试访问

	http://localhost:8080/javaweb/first

**运行分析**

1. 当用户在浏览器输入http://localhost:8080/javaweb/first时,会在相应位置找是否有对应的静态资源，当没有对应的静态资源时,会到web.xml下找响应的动态资源
2. 此时匹配到地址/first,根据配置的servlet别名找到对应的servlet配置
3. 根据配置的servlet进入到全类名下定义的Servlet

![](http://120.77.237.175:9080/photos/javaweb/03.png)

## Servlet的声明周期 ##

	/*
	 *   Servlet的生命周期
	 *   Servlet是在Tomcat服务器上的
	 *   Tomcat服务器---->创建Servlet容器
	 *
	 *   生命周期:从出生到死亡的过程
	 *   Servlet的生命周期:Servlet从创建到销毁的过程
	 *
	 *   当第一次访问MyFirstServlet时
	 *   1. 创建一个Servlet对象
	 *   2. 调用init方法 init()初始化Servlet
	 *   3. 调用Service方法  service()处理请求
	 *
	 *   以后请求:
	 *   4. 只调用Service方法来处理请求     整个运行期间只创建一个servlet对象Servlet是(单例多线程)
	 *
	 *   当项目从服务器上卸载:
	 *   5. 服务器会调用destory方法
	 * */
	public class HelloServlet implements Servlet {
	    public HelloServlet() {
	        System.out.println("this is Construct");
	    }
	
	    @Override
	    public void init(ServletConfig servletConfig) throws ServletException {
	        System.out.println("this is init method");
	    }
	
	    @Override
	    public ServletConfig getServletConfig() {
	        System.out.println("this is getServletConfig method");
	        return null;
	    }
	
	    @Override
	    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
	        System.out.println("this is service method");
	    }
	
	    @Override
	    public String getServletInfo() {
	        System.out.println("this is getServletInfo method");
	        return null;
	    }
	
	    @Override
	    public void destroy() {
	        System.out.println("this is destroy method");
	    }
	}

首次执行:

	this is Construct
	this is init method
	this is service method
再次执行:

	this is service method

**Servlet的生命周期**

Servlet是在Tomcat服务器上的,Tomcat服务器---->创建Servlet容器

- 生命周期:从出生到死亡的过程
- Servlet的生命周期:Servlet从创建到销毁的过程

**当第一次访问HelloServlet时**

1. 创建一个Servlet对象
2. 调用init方法 init()初始化Servlet
3. 调用Service方法  service()处理请求

	**以后请求:**

4. 只调用Service方法来处理请求     整个运行期间只创建一个servlet对象Servlet是(单例多线程)

	**当项目从服务器上卸载:**

5. 服务器会调用destory方法

## ServletConfig ##

- **ServletConfig接口封装了Servlet配置信息**，这一点从接口的名称上就能够看出来。但同时，**代表当前Web应用的ServletContext对象也封装到了ServletConfig对象中**，使ServletConfig对象成为了获取ServletContext对象的一座桥梁。

- 一个Servlet对应一个ServletConfig，所以ServletConfig类也只能获取对应Servlet的配置信息
- ServletConfig对象的主要功能
	1. 获取Servlet别称 ：getServletName()
	2. 获取Servlet初始化参数：getInitParameter()，getInitParameterNames()
	3. 获取ServletContext对象：getServletContext()

			public class ConfigServlet implements Servlet {
			
			    private ServletConfig servletConfig;
			
			    /*servlet的初如化方法,在servlet第一次被服务器创建的时候调用,只调用一次,因此这里的参数传值是由服务器传进来的*/
			    @Override
			    public void init(ServletConfig servletConfig) throws ServletException {
			        this.servletConfig = servletConfig;
			    }
			
			    /*获取servlet的配置信息*/
			    @Override
			    public ServletConfig getServletConfig() {
			        return null;
			    }
			
			    @Override
			    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
			        //ServletConfig
			        //封装了servlet配置信息的对象,一个Servlet对象对应一个ServletConfig,封装的是当前servlet配置信息
			        //1. 获取servlet的别名:getServletName()
			        System.out.println(servletConfig.getServletName());     //打印的是web.xml里配置的servlet-name别名:ConfigServlet
			        //2. 获取servlet初如化参数
			        System.out.println(servletConfig.getInitParameter("username"));     //李四
			        //3. 获取ServletContext对象.对象当前servlet的上下文.代表当前项目的信息
			        System.out.println(servletConfig.getServletContext());          //org.apache.catalina.core.ApplicationContextFacade@eee260a
			
			    }
			
			    @Override
			    public String getServletInfo() {
			        return null;
			    }
			
			    @Override
			    public void destroy() {
			
			    }
			}

## ServletContext ##

ServletContext代表Servlet上下文，也就是当前Web应用，一个Wed应用对应一个ServletContext

常用方法:

- **getInitParameter()**:获取web项目的配置信息
- **getContextPath**:获取web项目的路径
- **getRealPath**:获取资源的真实路径

**ContextServlet**

	public class ContextServlet implements Servlet {
	    private ServletConfig servletConfig;
	
	    @Override
	    public void init(ServletConfig servletConfig) throws ServletException {
	        this.servletConfig = servletConfig;
	    }
	
	    @Override
	    public ServletConfig getServletConfig() {
	        return servletConfig;
	    }
	
	    @Override
	    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
	        /*获取到ServletConfig对象*/
	        ServletConfig servletConfig = this.getServletConfig();
	        /*使用获取到ServletConfig对象获取到ServletContext*/
	        ServletContext servletContext = servletConfig.getServletContext();
	
	        /*研究ServletContext*/
	        /*一个WEB对应一个ServletContext,代表整个WEB项目*/
	        /*可以获取web项目的配置信息,获取web项目的初始化参数*/
	        String user = servletContext.getInitParameter("user");
	        String username = servletContext.getInitParameter("username");
	        System.out.println(user +"------"+username);        //张三------null
	        /*获取web项目的路径*/
	        String contextPath = servletContext.getContextPath();
	        System.out.println(contextPath);        ///javaweb
	
	        /*获取资源的真实路径*/
	        //虚拟路径:是网络访问使用虚拟路径.每一个虚拟路径应该对应一个实际的资源
	        //静态资源(文件的形式),动态资源(只是启动一段程序代码)
	        //真实路径:文件在磁盘中的存储路径
	        String realPath = servletContext.getRealPath("/index.jsp");
	        System.out.println(realPath);       //F:\Code\JavaWeb\out\artifacts\javaweb\index.jsp
	
	        /*可以作为最大的域对象共享数据域对象:共享数据4大域对象.application对象*/
	
	    }
	
	    @Override
	    public String getServletInfo() {
	        return null;
	    }
	
	    @Override
	    public void destroy() {
	
	    }
	}

**web.xml**

	 <!--ServletContext-->
	<servlet>
	    <servlet-name>ContextServlet</servlet-name>
	    <servlet-class>com.servlet.ContextServlet</servlet-class>
	    <!--配置Servlet的初始化参数-->
	    <init-param>
	        <param-name>username</param-name>
	        <param-value>王五</param-value>
	    </init-param>
	</servlet>
	<servlet-mapping>
	    <servlet-name>ContextServlet</servlet-name>
	    <url-pattern>/context</url-pattern>
	</servlet-mapping>
	<!--配置WEB项目的初始化参数-->
	<context-param>
	    <param-name>user</param-name>
	    <param-value>张三</param-value>
	</context-param>


## HttpServlet ##

	public class MyHttpServlet extends HttpServlet {
	
	    private static final long serialVersionUID = -4986016053554532451L;
	
	    public MyHttpServlet() {
	        super();
	    }
	
	    /*用来处理GET请求,*/
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        //每次GET请求调用doGet方法
	        System.out.println("this is doGet method");
	        //正常不区分请求方式
	        this.doPost(req,resp);
	    }
	
	    /*用来处理POST请求*/
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        System.out.println("this is doPost method");
	    }
	}

![](http://120.77.237.175:9080/photos/javaweb/04.png)

从上图可以看到各类间的继承关系

1. MyHttpServlet继承HttpServlet,HttpServlet继承GenericServlet,GenericServlet继承Servlet
2. 当访问连接http://localhost:8080/javaweb/myhttp时,Tomcat会把请求服务给Servlet.service()
3. 因为GenericServlet的继承关系,会访问到自身的抽像方法GenericServlet.service()
4. 注意这里的HttpServlet的实现类,有两个service()方法,首先抽用的是service(ServletRequest req, ServletResponse res),在方法里可看到会把传进来的两个参数,强制转成带有HttpServlet,再把参数再次调用自身的service(HttpServletRequest req, HttpServletResponse resp)
5. 然后会根据调用的方法不同,调用不同的doXXX()方法,
6. 最终因为重写了MyHttpServlet类的doGET(),doPost()方法,所以会进入所重写的类的方法里

## HttpServletRequest和HttpServletResponse ##

### HttpServletResponse接口 ###
1. **该接口是ServletResponse接口的子接口**，封装了HTTP响应的相关信息，由Servlet容器创建其实现类对象并传入service(ServletRequest req, ServletResponse res)方法中。以下所说HttpServletResponse对象指的是容器提供的HttpServletResponse实现类对象。
2. 主要功能
- 使用PrintWriter对象向浏览器输出数据
- 实现请求重定向。

		  /*用来处理POST请求*/
		    @Override
		    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		        System.out.println("this is doPost method");
		
		        //HttpServletRequest req, HttpServletResponse resp
		        //HttpServletRequest request代表就是封装浏览器请求的信息对象,收到浏览器请求
		        //HttpServletResponse response代表就是要发送浏览器的响应对象,封装响应信息
		
		        //1.可以给浏览器响应字符串
		       /* PrintWriter writer = resp.getWriter();
		        writer.write("hello , this is HttpServlet");*/
		
		        //2.可以重定向一个页面或者其它资源.重定向就是服务器告诉浏览器重新请求别的资源
				//已经对之前的请求处理完了,让浏览器重新请求新的资源
		        resp.sendRedirect("success.html");
		    }


### HttpServletRequest接口 ###

1. **该接口是ServletRequest接口的子接口**，封装了HTTP请求的相关信息，由Servlet容器创建其实现类对象并传入service(ServletRequest req, ServletResponse res)方法中。以下我们所说的HttpServletRequest对象指的是容器提供的HttpServletRequest实现类对象。
2. HttpServletRequest对象的主要功能有
	- **获取请求参数**
	- **获取请求头信息**
	- **转发一个页面/资源**
	- **作为域对象共享数据**

		    @Override
		    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    
		        //HttpServletRequest request 代表浏览器发送给服务器的请求信息
		        //http请求.请求首行 请求头 空行 请求体 (封装的请求数据-post)
		        //get请求将所有携带的参数放在url
		        //1. 获取请求数据get放在url后面 post放在请求体里
		        String username = req.getParameter("username");
		        System.out.println(username);       //test
		    
		        //使用getParameterValues多选框的内容
		        String[] checkboxes = req.getParameterValues("checkbox");
		        for (String v:
		             checkboxes) {
		            System.out.println(v);      //1 2 3
		        }
		    
		        //2. 获取请求头信息
		        String header = req.getHeader("User-Agent");
		        System.out.println("user-agent:"+ header);  //user-agent:Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36
		    
		        //3. 转发一个页面/资源
		        //先获取一个请求转发器
		        RequestDispatcher requestDispatcher = req.getRequestDispatcher("success.html");
		        requestDispatcher.forward(req,resp);
		    
		        //4. 作为域对象共享数据 4个 application request
		    }

## 请求的转发与重定向 ##

### 请求的转发 ###

1. Servlet接收到浏览器端请求后，进行一定的处理，先不进行响应，而是在服务器端内部“转发”给其他Servlet程序继续处理。在这种情况下浏览器端只发出了一次请求，浏览器地址栏不会发生变化，用户也感知不到请求被转发了。
2. 转发请求的Servlet和目标Servlet共享同一个request对象。
3. 实现转发的API

![](http://120.77.237.175:9080/photos/javaweb/05.png)

### 请求的重定向 ###

1. Servlet接收到浏览器端请求并处理完成后，给浏览器端一个特殊的响应，这个特殊的响应要求浏览器去请求一个新的资源，整个过程中浏览器端会发出两次请求，且浏览器地址栏会改变为新资源的地址。
2. 重定向的情况下，原Servlet和目标资源之间就不能共享请求域数据了。
3. 实现重定向的API

![](http://120.77.237.175:9080/photos/javaweb/06.png)


		  @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       //1 . 重定向到页面,就是告诉浏览器重新请求一个资源
	        resp.sendRedirect("success.html");
	
	        //2. 转发页面
	        //转发:服务器处理完以后转交到另外一个资源.当我们转发的资源是一个页面资源(静态资源),服务器会给浏览器返回这个资源
	        //当转交给下一个servlet的,servlet可以继续处理
	       // req.getRequestDispatcher("success.html").forward(req,resp);
	    }


		|                         | 转发                      | 重定向              |
		| ----------------------- | ------------------------- | ------------------- |
		| 浏览器地址              | 不改变                    | 改变                |
		| 发送请求次数            | 1                         | 2                   |
		| API            		| Request对象      			 | Response对象     |
		| 位置                    | 服务器内部                | 浏览器中完成        |
		| 能否共享request对象数据 | 能                        | 不能                |
		| 目标资源                | 必须是当前web应用中的资源 | 不局限于当前web应用 |
		| WEB-INF下的资源         | 能访问                    | 不能访问            |

## 乱码问题 ##

### 响应乱码 ###

在返回response设置,有三种处理方式

	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	    //在第一次操作response之前,设置好内容类型和字符编码
	    //第一种方式:setContentType(),setCharacterEncoding()
	    //告诉浏览器传输的数据的内容类型
	    //resp.setContentType("text/html");
	    //告诉浏览器编码
	    //resp.setCharacterEncoding("utf-8");
	
	    //2. 直接设置响应头Content-Type=text/html; charset=utf-8
	    //resp.setHeader("content-type","text/html; charset=utf-8");
	
	    //3. 设置Content-TyPE字段的值
	    resp.setContentType("text/html; charset=utf-8");
	    PrintWriter writer = resp.getWriter();
	    writer.println("请求成功");
	}

### POST乱码 ###

- 原因:浏览器将数据编码并提交上来,但是服务器并不知道编码规则

- 解决方法:让服务器知道编码规则即可,重新设置请求的编码格式

        req.setCharacterEncoding("utf-8");

### GET乱码 ###

- 原因:浏览器将地址栏也编码,服务器不知道,而且8080端口接受到url以后,已经按照默认的编码接收,所以在 req.setCharacterEncoding("utf-8");没用了
- 解决方法:修改Tomcat的server.xml配置文件 ,在8080端口配置处添加URIEncoding="UTF-8"

  		<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8"/>

## 路径问题 ##

1. Web应用下资源的虚拟路径访问具体资源

	1. **如果资源是静态的，那么Tomcat会返回资源本身**
	2. **如果资源是动态的，例如Servlet，那么Tomcat会先执行Servlet程序，返回Servlet程序的运行结果**

2. **相对路径不靠谱**

	在请求转发模式下，超链接地址如果使用相对路径，以自身为基准，会导致浏览器URL地址解析错误，所以应避免使用相对路径
3. **使用绝对路径(以'/'开头的路径)**

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        //转发到a页面
	        //相对路径:相对与当前资源的路径
	        //经常可能产生一些问题,转发会导致相对路径出现问题
	        //req.getRequestDispatcher("pages/a.html").forward(req,resp);
	       // resp.sendRedirect("pages/a.html");
	    
	        //绝对路径:以/开如 代表的是项目的根目录
	        //http://localhost:8080/javaweb/pages/a.html
	        //req.getRequestDispatcher("pages/a.html").forward(req,resp);
	    
	        //1. 转发以后使用绝对路径来写   /   表示项目 的根目录开始
	        //转发，代表从项目的根目录      http://localhost:8080/javaweb/
	        //req.getRequestDispatcher("/index.jsp").forward(req,resp);
	    
	        //2. 重定向 代表从tomcat的根目录-服务器的根   http://localhost:8080开始
	        //同项目的有可能项目名不同,最好动态获取项目的根目录,项目路径
	    
	        ServletContext servletContext = getServletContext();
	        String path = servletContext.getContextPath();
	    
	        String contextPath = req.getContextPath();
	        System.out.println(path);
	        System.out.println(contextPath);
	    
	        resp.sendRedirect(path + "/pages/a.html");
	    
	        //由浏览器解析：绝对路径都是从web，即服务器根开始
	        //由服务器解析：绝对路径都是从项目根开始
	    }

## base标签 ##

在web页面里定义了<base>标签后,就可以在页面里使用相对路径指定要访问的资源或者路径

	<!DOCTYPE html>
	<html>
	<head>
	    <title>README</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<!--使用base标签 作用就是指定页面上所有的路径的基础路径.所有相对路径都是以我指定的基础路径开发,只有相对路径的写法,会按照base标签指定的基础路径来拼接新的路径.指定所有相对路径的起始路径.以后的相对路径参考的都是base标签指定的路径而不是当前资源-->
	<base href="http://localhost:8080/javaweb/">
	<!--这里可以进行动态获取不用指定,利用下面request的作用域-->
	<base href="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
	<!--注意:链接最后还有有一个/不要漏了,不然会不起作用-->

## 类加载器加载资源 ##

放在资源文件夹resources里的配置文件,编译后都会放在编译目录的当前文件夹下

# JSP #

## 简介 ##

**Java Server Page**

1. JSP的本质是一个Servlet,Servlet能做的事情JSP都能做
2. JSP能够以HTML页面的方式呈现数据,是一个可以嵌入Java代码的HTML
3. JSP不同于HTML,不能使用浏览器直接打开,而必须运行在Servlet容器中

**HelloWorld**

	<%@ page import="java.util.Date" %>
	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<html>
	<head>
	    <title>我是JSP</title>
	</head>
	<body>
	<%--JSP脚本片段,java代码片段--%>
	<%--在页面直接输出hello world--%>
	<%="hello world"%>
	<br>
	<%--JSP表达式 在页面输出内容--%>
	<%--可以看到当引用所需的类时,会自动导入相应的包--%>
	<%--在页面直接输出日期--%>
	<%=new Date()%>
	</body>
	</html>

## JSP运行原理 ##

### Web服务器是如何调用并执行一个jsp页面的 ###

浏览器向服务器发请求，不管访问的是什么资源，其实都是在访问Servlet，所以当访问一个jsp页面时，其实也是在访问一个Servlet，服务器在执行jsp的时候，首先把jsp翻译成一个Servlet，所以我们访问jsp时，其实不是在访问jsp，而是在访问jsp翻译过后的那个Servlet，例如下面的代码：

**index.jsp**

	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	  <head>
	    <base href="<%=basePath%>">
	    
	    <title>First Jsp</title>
	    
	  </head>
	  
	  <body>
	    <%
	        out.print("Hello Jsp");
	    %>
	  </body>
	</html>

当我们通过浏览器访问index.jsp时，服务器首先将index.jsp翻译成一个index\_jsp.class，在Tomcat服务器的**work\Catalina\localhost\项目名\org\apache\jsp**目录下可以看到index_jsp.class的源代码文件index_jsp.java，index_jsp.java的代码如下：

		package org.apache.jsp;
		
		import javax.servlet.*;
		import javax.servlet.http.*;
		import javax.servlet.jsp.*;
		import java.util.*;
		
		public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
		    implements org.apache.jasper.runtime.JspSourceDependent {
		
		  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();
		
		  private static java.util.List _jspx_dependants;
		
		  private javax.el.ExpressionFactory _el_expressionfactory;
		  private org.apache.AnnotationProcessor _jsp_annotationprocessor;
		
		  public Object getDependants() {
		    return _jspx_dependants;
		  }
		
		  public void _jspInit() {
		    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
		    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
		  }
		
		  public void _jspDestroy() {
		  }
		
		  public void _jspService(HttpServletRequest request, HttpServletResponse response)
		        throws java.io.IOException, ServletException {
		
		    PageContext pageContext = null;
		    HttpSession session = null;
		    ServletContext application = null;
		    ServletConfig config = null;
		    JspWriter out = null;
		    Object page = this;
		    JspWriter _jspx_out = null;
		    PageContext _jspx_page_context = null;
	​	
		    try {
		      response.setContentType("text/html;charset=UTF-8");
		      pageContext = _jspxFactory.getPageContext(this, request, response,
		                  null, true, 8192, true);
		      _jspx_page_context = pageContext;
		      application = pageContext.getServletContext();
		      config = pageContext.getServletConfig();
		      session = pageContext.getSession();
		      out = pageContext.getOut();
		      _jspx_out = out;
		
		      out.write('\r');
		      out.write('\n');
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		      out.write("\r\n");
		      out.write("\r\n");
		      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
		      out.write("<html>\r\n");
		      out.write("  <head>\r\n");
		      out.write("    <base href=\"");
		      out.print(basePath);
		      out.write("\">\r\n");
		      out.write("    \r\n");
		      out.write("    <title>First Jsp</title>\r\n");
		      out.write("\t\r\n");
		      out.write("  </head>\r\n");
		      out.write("  \r\n");
		      out.write("  <body>\r\n");
		      out.write("    ");
		
		        out.print("Hello Jsp");
		    
		      out.write("\r\n");
		      out.write("  </body>\r\n");
		      out.write("</html>\r\n");
		    } catch (Throwable t) {
		      if (!(t instanceof SkipPageException)){
		        out = _jspx_out;
		        if (out != null && out.getBufferSize() != 0)
		          try { out.clearBuffer(); } catch (java.io.IOException e) {}
		        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
		      }
		    } finally {
		      _jspxFactory.releasePageContext(_jspx_page_context);
		    }
		  }
		}

可以看到，index_jsp这个类是继承 org.apache.jasper.runtime.HttpJspBase这个类的，通过查看Tomcat服务器的源代码，可以知道在apache-tomcat-6.0.20-src\java\org\apache\jasper\runtime目录下存HttpJspBase这个类的源代码文件

**HttpJspBase这个类的源代码**

		/*
		 * Licensed to the Apache Software Foundation (ASF) under one or more
		 * contributor license agreements.  See the NOTICE file distributed with
		 * this work for additional information regarding copyright ownership.
		 * The ASF licenses this file to You under the Apache License, Version 2.0
		 * (the "License"); you may not use this file except in compliance with
		 * the License.  You may obtain a copy of the License at
		 * 
		 *      http://www.apache.org/licenses/LICENSE-2.0
		 * 
		 * Unless required by applicable law or agreed to in writing, software
		 * distributed under the License is distributed on an "AS IS" BASIS,
		 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
		 * See the License for the specific language governing permissions and
		 * limitations under the License.
		 */
		
		package org.apache.jasper.runtime;
		
		import java.io.IOException;
		
		import javax.servlet.ServletConfig;
		import javax.servlet.ServletException;
		import javax.servlet.http.HttpServlet;
		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;
		import javax.servlet.jsp.HttpJspPage;
		import javax.servlet.jsp.JspFactory;
		
		import org.apache.jasper.compiler.Localizer;
		
		/**
		 * This is the super class of all JSP-generated servlets.
		 *
		 * @author Anil K. Vijendran
		 */
		public abstract class HttpJspBase 
		    extends HttpServlet 
		    implements HttpJspPage 


​	
		    
		{
		    
		    protected HttpJspBase() {
		    }
		
		    public final void init(ServletConfig config) 
		    throws ServletException 
		    {
		        super.init(config);
		    jspInit();
		        _jspInit();
		    }
		    
		    public String getServletInfo() {
		    return Localizer.getMessage("jsp.engine.info");
		    }
		
		    public final void destroy() {
		    jspDestroy();
		    _jspDestroy();
		    }
		
		    /**
		     * Entry point into service.
		     */
		    public final void service(HttpServletRequest request, HttpServletResponse response) 
		    throws ServletException, IOException 
		    {
		        _jspService(request, response);
		    }
		    
		    public void jspInit() {
		    }
		
		    public void _jspInit() {
		    }
		
		    public void jspDestroy() {
		    }
		
		    protected void _jspDestroy() {
		    }
		
		    public abstract void _jspService(HttpServletRequest request, 
		                     HttpServletResponse response) 
		    throws ServletException, IOException;
		}

　HttpJspBase类是继承HttpServlet的，所以HttpJspBase类是一个Servlet，而index\_jsp又是继承HttpJspBase类的，所以index\_jsp类也是一个Servlet，所以当浏览器访问服务器上的index.jsp页面时，其实就是在访问index\_jsp这个Servlet，index\_jsp这个Servlet使用\_jspService这个方法处理请求。

### Jsp页面中的html排版标签是如何被发送到客户端的？ ###

浏览器接收到的这些数据,都是在_jspService方法中使用如下的代码输出给浏览器的：

	out.write('\r');
  	out.write('\n');
	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	  out.write("\r\n");
	  out.write("\r\n");
	  out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
	  out.write("<html>\r\n");
	  out.write("  <head>\r\n");
	  out.write("    <base href=\"");
	  out.print(basePath);
	  out.write("\">\r\n");
	  out.write("    \r\n");
	  out.write("    <title>First Jsp</title>\r\n");
	  out.write("\t\r\n");
	  out.write("  </head>\r\n");
	  out.write("  \r\n");
	  out.write("  <body>\r\n");
	  out.write("    ");
	
	    out.print("Hello Jsp");
	
	  out.write("\r\n");
	  out.write("  </body>\r\n");
	  out.write("</html>\r\n");

　在jsp中编写的java代码和html代码都会被翻译到_jspService方法中去，在jsp中编写的java代码会原封不动地翻译成java代码，如<%out.print("Hello Jsp");%>直接翻译成out.print("Hello Jsp");，而HTML代码则会翻译成使用out.write("<html标签>\r\n");的形式输出到浏览器。在jsp页面中编写的html排版标签都是以out.write("<html标签>\r\n");的形式输出到浏览器，浏览器拿到html代码后才能够解析执行html代码。

### Jsp页面中的java代码服务器是如何执行 ###

在jsp中编写的java代码会被翻译到\_jspService方法中去，当执行\_jspService方法处理请求时，就会执行在jsp编写的java代码了，所以Jsp页面中的java代码服务器是通过调用\_jspService方法处理请求时执行的。

1. index.jsp页面被翻译成一个index_jsp.java --->index_jsp.class
2. HttpJspBase继承HttpServlet,所以incex_jsp就是一个Servlet
3. 每次请求都调用_jspService()

![](http://120.77.237.175:9080/photos/javaweb/07.png)

## JSP基码语法 ##

### JSP模版元素 ###

- JSP页面中的HTML内容称之为JSP模版元素。
- JSP模版元素定义了网页的基本骨架，即定义了页面的结构和外观。

## JSP表达式 ##

JSP脚本表达式（expression）用于将程序数据输出到客户端

语法：**<%= 变量或表达式 %>**

	//举例：输出当前系统时间:
	<%=new Date()%>

- JSP引擎在翻译脚本表达式时，会将程序数据转成字符串，然后在相应位置用out.print(…) 将数据输给客户端。
- JSP脚本表达式中的变量或表达式后面**不能有分号（;）**

## JSP脚本片断 ##

JSP脚本片断(scriptlet)用于在JSP页面中编写多行Java代码,脚本片段会被原封不动的复制到.java文件里。语法：

	　　　　<%
	        　　　　多行java代码
	　　　　%>

**在<% %>中可以定义变量、编写语句，不能定义方法(因为在方法体内是不能定义方法)**

	<%
   		 int age = 16;
	    if (age >= 16)
	    {
	%>
         <h1>11111<h1/>
	<%  } else {
	%>
        <h1>22222</h1>
	<% 
	 }
	%>

**注意事项**：

- JSP脚本片断中只能出现java代码，不能出现其它模板元素， JSP引擎在翻译JSP页面中，会将JSP脚本片断中的Java代码将被原封不动地放到Servlet的_jsp service()方法中。
- JSP脚本片断中的Java代码必须严格遵循Java语法，例如，每执行语句后面必须用分号（;）结束。
- 在一个JSP页面中可以有多个脚本片断，在两个或多个脚本片断之间可以嵌入文本、HTML标记和其他JSP元素。
- 多个脚本片断中的代码可以相互访问，犹如将所有的代码放在一对<%%>之中的情况。如：out.println(x);
- 单个脚本片断中的Java语句可以是不完整的，但是，多个脚本片断组合后的结果必须是完整的Java语句，例如：

		<%
		 int a = 1;
		%>
		 <%
		System.out.println(a);
		%>

## JSP声明(一般不采用) ##

**JSP页面中编写的所有代码，默认会翻译到servlet的service方法中， 而JSP声明中的java代码被翻译到_jsp的service方法的外面**。语法：

	　　　　<%！
	    　　　　java代码
	　　　　%>

所以，**JSP声明可用于定义JSP页面转换成的Servlet程序的静态代码块、成员变量和方法** 。

多个静态代码块、变量和函数可以定义在一个JSP声明中，也可以分别单独定义在多个JSP声明中。

JSP隐式对象的作用范围仅限于Servlet的_jsp的service方法，所以在JSP声明中不能使用这些隐式对象。

	 <%!
	    private String name = "李四";
	    private void test()
	    {
	        System.out.println(name);
	    }
	 %>
	 <% test();%>

## JSP注释 ##

在JSP中，注释有两大类：

- 显式注释：直接使用HTML风格的注释：<!- - 注释内容- ->

- 隐式注释：直接使用JAVA的注释：//、/*……*/

- JSP自己的注释：<%- - 注释内容- -%>

		|                 | JSP原文件 | .java文件 | HTML页面 |
		| --------------- | ---------| ---------| -------- |
		| <%--JSP注释--%> | 可见      | 不可见    | 不可见   |
		| <!--HTML注释--> | 可见      | 可见      | 可见     |
		| //java注释      | 可见      | 可见      | 不可见   |
	

HTML的注释在浏览器中查看源文件的时候是可以看得到的，而JAVA注释和JSP注释在浏览器中查看源文件时是看不到注释的内容的，这就是这三种注释的区别。

	- <%--JSP注释--%> 翻译成.java被文件忽略
	- <!--HTML注释-->	浏览器输出时候忽略
	- //java注释	翻译成.class文件时被忽略

## JSP指令 ##

### JSP指令简介 ###

JSP指令（directive）是为JSP引擎而设计的，它们并不直接产生任何可见输出，而只是告诉引擎如何处理JSP页面中的其余部分。

在JSP 2.0规范中共定义了三个指令：

- page指令
- Include指令
- taglib指令

JSP指令的基本语法格式：**<%@ 指令 属性名="值" %>**

如果一个指令有多个属性，这多个属性可以写在一个指令中，也可以分开写。
	
	<%@ page import="java.util.Date" %>
	<%@ page  pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" errorPage="/jsp/error.jsp" session="true" isELIgnored="false"  %>

### Page指令 ###

page指令用于定义JSP页面的各种属性，无论page指令出现在JSP页面中的什么地方，它作用的都是整个JSP页面，为了保持程序的可读性和遵循良好的编程习惯，page指令最好是放在整个JSP页面的起始位置

属性:

1. import:	用来在页面导包
2. pageEncoding:	指定页面使用的字符集,也是告诉jsp引擎使用指定的编译翻译
3. contentType:	设置响应头,页面如何响应给浏览器
4. errorPage:	指定页面发生错误去向的页面
5. isErrorPage:	表示当前页面是一个错误页面
6. session:	默认session="true",当前页面是否参与会话,是否可以使用session对象
7. isELIgnored:	是否忽略el表达式,默认false代表不忽略,true忽略
8. info:	定义页面的信息(描述:在getServletInfo()里获取定义的信息,可参考接口Servlet)

在jsp/test1.jsp定义错误:

	 <% int b = 10/0;%>

如果页面报错会跳到指定的errorPage页面,同时在errorPage可以进行捕获异常,定义isErrorPage="true",如下

	<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
	<html>
	<head>
	    <title>Title</title>
	</head>
	<body>
	this is error page
	
	<%--捕获异常信息,在页面进行打印输出--%>
	<%= exception.getMessage()%>
	
	</body>
	</html>

### include指令 ###

	<%@ 指令名 属性名=属性值%>
	<%@ include 属性名=属性值%>

**可以把另外一个页面包含进来,采用的方式是将整个页面复制到servlet.service()方法里面**

**jsp引擎不翻译和编译要包含的页面**

@include可以包含任意的文件，当然，只是把文件的内容包含进来,**静态包含**

**include指令用于引入其它JSP页面，如果使用include指令引入了其它JSP页面，那么JSP引擎将把这两个JSP翻译成一个servlet。所以include指令引入通常也称之为静态引入。**

语法：**<%@ include file="relativeURL"%>**，其中的file属性用于指定被引入文件的路径。路径以“/”开头，表示代表当前web应用。**可以包含WEB-INF下的页面**

**include指令细节注意问题**：

1. **被引入的文件必须遵循JSP语法**。
3. **被引入的文件可以使用任意的扩展名，即使其扩展名是html，JSP引擎也会按照处理jsp页面的方式处理它里面的内容，为了见明知意，JSP规范建议使用.jspf（JSP fragments(片段)）作为静态引入文件的扩展名**。
4. **由于使用include指令将会涉及到2个JSP页面，并会把2个JSP翻译成一个servlet，所以这2个JSP页面的指令不能冲突（除了pageEncoding和导包除外）**。

		<%@ page contentType="text/html;charset=UTF-8" language="java" %>
		<html>
		<head>
		    <title>Title</title>
		</head>
		<body>
		this is test2 page
		<%--不会另外再编译和error.jsp文件--%>
		<%@include file="error.jsp"%>
		</body>
		</html>

### taglib指令 ###

在页面引入标签库,	<%@ taglib 属性名=属性值 %>

## JSP标签 ##

### JSP标签介绍 ###

JSP标签也称之为Jsp Action(JSP动作)元素，它用于在Jsp页面中提供业务逻辑功能，避免在JSP页面中直接编写java代码，造成jsp页面难以维护。

### JSP常用标签 ###

- <jsp:include>标签  
- <jsp:forward>标签  
- <jsp:param>标签

### jsp:include标签 ###

**<jsp:include>: 也是在页面包含另外一个页面 动态包含**

page:	表示要包含的页面路径,不是把整个页面复制过来,再一行行写出去

在_jsp.service()方法里可以看到动态包含不是调用our.write()直接打印出来,而是调用
**org.apache.jasper.runtime.JspRuntimeLibrary.include(request,response,"/jsp/error.jsp",out,false)**

把要包含的页面会先翻译出来,再编译出来,再包含

	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<html>
	<head>
	    <title>Title</title>
	</head>
	<body>
	this is test3 jsp
	<jsp:include page="error.jsp"></jsp:include>
	</body>
	</html>

#### <jsp:include>标签与include指令的区别 ####

<jsp:include>标签是动态引入， <jsp:include>标签涉及到的2个JSP页面会被翻译成2个servlet，这2个servlet的内容在执行时进行合并。

**动态包含,要包含的文件是需要翻译和编译的,被包含的页面内容基本固定的情况下使用静态包含**

而include指令是静态引入，涉及到的2个JSP页面会被翻译成一个servlet，其内容是在源文件级别进行合并。

**静态包含,要包含的文件是不用翻译和编译,被包含的页面内容基本固定的情况下使用静态包含**

### jsp:forward标签 ###

<jsp:forward>标签用于把请求转发给另外一个资源。

	<%--转发到指定页面--%>
	<jsp:forward page="error.jsp"></jsp:forward>

### jsp:param标签 ###

当使用<jsp:include>和<jsp:forward>标签引入或将请求转发给其它资源时，可以使用<jsp:param>标签向这个资源传递参数。

在test3.jsp定义:

	<jsp:forward page="error.jsp">
	    <jsp:param name="name" value="wangwu"/>
	    <jsp:param name="age" value="16"/>
	</jsp:forward>

在error.jsp定义

	<%--打印输出获取页面转发过来的参数--%>
	<%=request.getParameter("name")%>>
	<%=request.getParameter("age")%>>

注意:在浏览器是看不到要转发的参数的,因为是在service()方法里内部进行参数转发到另外一个页面

**注意,以下这个链接是一个无效连接,只是从浏览器访问是无法访问到的,因为JSP文件在编译时是在service()方法里直接打印成a标签出来,不是经由编译后使用java源码进行目录访问**
	
	<a href="WEB-INF/aaa.jsp"></a>

## JSP九大隐含对象 ##

隐含对象:	在页面直接可以使用的对象

**五大常规对象**

- **Throwable exception = null;	代表捕获异常对象**
- **ServletConfig config = null;	代表servlet配置信息**
	- servlet	-->	jsp页面对应的servlet
	- config		-->	jsp页面对应的servlet的配置信息
	- config.getServletName()--->JSP
- **JspWriter out = null;		代表可以在页面输出数据的out对象**
	- out.write();
- **Object page = this;		代表当前jsp,根本没用**
- **HttpServletResponse response		代表当次响应的对象**

**四大域对象:域对象用来在其他资源共享数据**

- **PageContext pageContext = null;		代表当前页面对象**
	- 获取其它隐含对象 pageContext.getXX();
	- 作为域对象共享数据.只能在当前页面共享数据,离开页面就无法共享
		- String key ,Objec value
		- 通过调用域对象.setAttribute(key,value),给相应的域中设置内容
		- 域对象.getAttrubute(key),获取域中的内容
		- 域对象.removeAttribute(key),移除域中的内容
- **HttpServletRequest request,		代表封装当次请求详细信息的对象**
	
	- 在同一个请求对象中共享数据,只要是同一次请求,就可以共享数据
		- http://localhost:8080/javaweb/
		- request.getScheme()	获取连接前缀,如http,https
		- request.getServerName()	获取主域名,如localhost 
		- request.getServerPort()	获取端口号,如8080
		- request.getContextPath()	获取项目路径,如/javaweb

- **HttpSession session = null;		代表会话对象**
	
	- 同一次会话共享数据 浏览器找开-开始会话		浏览器关闭-结束会话
- **ServletContext application = null;			代表整个web应用**
	
	-  application代表当前web应用.只要在同一个web应用中都可以共享数据.web应用只要不卸载都可以访问

**各作用域范围**

	| 域对象      | 作用范围    | 起如时间    | 结束时间    |
	| ----------- | ----------- | ----------- | ----------- |
	| pageContext | 当前JSP页面 | 页面加载    | 离开页面    |
	| request     | 同一个请求  | 收到请求    | 响应        |
	| session     | 同一个会话  | 开始会话    | 结束会话    |
	| application | 当前web应用 | web应用加载 | web应用卸载 |



### page对象 ###

page对象表示当前一个JSP页面，可以理解为一个对象本身，即：把一个JSP当作一个对象来看待。page对象在开发中几乎不用，了解一下即可

### out对象 ###

- out对象用于向客户端发送文本数据。
- out对象是通过调用pageContext对象的getOut方法返回的，其作用和用法与ServletResponse.getWriter方法返回的PrintWriter对象非常相似。
- JSP页面中的out对象的类型为JspWriter，JspWriter相当于一种带缓存功能的PrintWriter，设置JSP页面的page指令的buffer属性可以调整它的缓存大小，甚至关闭它的缓存。
- 只有向out对象中写入了内容，且满足如下任何一个条件时，out对象才去调用ServletResponse.getWriter方法，并通过该方法返回的PrintWriter对象将out对象的缓冲区中的内容真正写入到Servlet引擎提供的缓冲区中：

	- 设置page指令的buffer属性关闭了out对象的缓存功能
	- out对象的缓冲区已满
	- 整个JSP页面结束


out对象的工作原理图

![](http://120.77.237.175:9080/photos/javaweb/08.png)

	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<html>
	<head>
	    <title>Title</title>
	</head>
	<body>
	<%=config.getServletName()%>
	<%
	 out.write("你好");
	 
	%>
	<%
	response.getWriter().write("123456");
	%>
	</body>
	</html>

页面显示,**注意打印输出response.getWriter().write()是在out.write()前面的**

![](http://120.77.237.175:9080/photos/javaweb/09.png)
	

### pageContext对象 ###

pageContext对象是JSP技术中最重要的一个对象，它代表JSP页面的运行环境，这个对象不仅封装了对其它8大隐式对象的引用，它自身还是一个域对象(容器)，可以用来保存数据。并且，这个对象还封装了web开发中经常涉及到的一些常用操作，例如引入和跳转其它资源、检索其它域对象中的属性等。

#### 通过pageContext获得其他对象 ####

- getException方法返回exception隐式对象
- getPage方法返回page隐式对象
- getRequest方法返回request隐式对象
- getResponse方法返回response隐式对象
- getServletConfig方法返回config隐式对象
- getServletContext方法返回application隐式对象
- getSession方法返回session隐式对象
- getOut方法返回out隐式对象

#### 测试各作用域范围 ####

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
	
	pageContest name is <%= pageContext.getAttribute("pageName")%><br>
	request name is <%= request.getAttribute("requestName")%><br>
	session name is <%= session.getAttribute("sessionName")%><br>
	application name is <%= application.getAttribute("applicationName")%><br>
	</body>
	</html>

打开scope.jsp当前页面都可以成功显示的,但如果使用a标准跳转链接后到scope2.jsp

	pageContest name is null
	request name is null
	session name is session
	application name is application

pageContest只能作用在当前页面,request只能作用在同一个请求,所以如果要想获取request的请求值,使用转发才可以<jsp:forward page="scope2.jsp"></jsp:forward>

	pageContest name is null
	request name is request
	session name is session
	application name is application

当关闭浏览器时session的值会消失,当web应用关闭时,application值才会消失

# EL表达式 #

## EL表达式简介 ##

EL 全名为Expression Language。简化开发,EL主要作用：

1. 获取数据

	EL表达式主要用于替换JSP页面中的脚本表达式，以从各种类型的web域 中检索java对象、获取数据。(某个web域 中的对象，访问javabean的属性、访问list集合、访问map集合、访问数组)

2. 执行运算

	利用EL表达式可以在JSP页面中执行一些基本的关系运算、逻辑运算和算术运算，以在JSP页面中完成一些简单的逻辑运算。${user==null}

3. 获取web开发常用对象

	EL 表达式定义了一些隐式对象，利用这些隐式对象，web开发人员可以很轻松获得对web常用对象的引用，从而获得这些对象中的数据。

4. 调用Java方法

	EL表达式允许用户开发自定义EL函数，以在JSP页面中通过EL表达式调用Java类的方法。

## 获取数据 ##

使用EL表达式获取数据语法：

- **${标识符}**
- **${对象.属性名}**
- **${map.key}**
- **${map['key']}**

EL表达式语句在执行时，会调用pageContext.findAttribute方法，用标识符为关键字，分别从page、request、session、application四个域中查找相应的对象，找到则返回相应对象，找不到则返回”” （注意，不是null，而是空字符串）。

EL表达式可以很轻松获取JavaBean的属性，或获取数组、Collection、Map类型集合的数据

**EL表达式如果获取域中的属性,直接写属性名,会从四个域从小到大找.找到级停止,EL表达式可以连点操作**

EL	11个隐含对象(pageContext,pageScope,reuqestScope,sessionScope,applicationScope,param,paramValues,header,headerValues,cookie,initParam)

- 四个域对象.是从这四个域对象中取值
- pageContext域中的数据**pageScope**(封装了pageContext域中所有的共享数据(setAttr,getAttr),是一个Map)
- request域中的数据**requestScope**(封装了request域中所有的共享数据(setAttr,getAttr),是一个Map)
- session域中的数据**sessionScope**(封装了session域中所有的共享数据(setAttr,getAttr),是一个Map)
- application域中的数据**applicationScope**(封装了application域中所有的共享数据(setAttr,getAttr),是一个Map)



		<%pageContext.setAttribute("page","page1");%>
		<%request.setAttribute("request","request2");%>
		<%session.setAttribute("session","session3");%>
		<%application.setAttribute("application","application4");%>
		
		el表达式获取属性值<br>
		page:${page}<br>				//page:page1
		request:${request}<br>			//request:request2
		session:${session}<br>			//session:session3
		application:${application}<br>	//application:application4

		<%
	    Customer customer = new Customer();
	    customer.setName("张三");
		%>
		<%pageContext.setAttribute("cus",customer);%>
		<%request.setAttribute("cus","request2");%>
		<%session.setAttribute("cus","session3");%>
		<%application.setAttribute("cus","application4");%>
		
		el表达式获取属性值<br>
		//根据属性名从小到大找
		page:${cus}<br>		//page:Customer{id=null, name='张三', age=null}
		request:${cus}<br>	//request:Customer{id=null, name='张三', age=null}
		session:${sessionScope.cus}<br>		//session:session3
		application:${applicationScope.cus}<br>		//application:application4

		page:${pageScope.cus.name}<br>		//page:张三
		request:${requestScope.cus}<br>		//request:request2
		session:${sessionScope.cus}<br>		//session:session3
		application:${applicationScope.cus}<br>		//application:application4

		<!--注意:EL表达式只可以取到11个隐藏域的属性,自定义的是无法取出的->
		<%
		String str = "你好";
		%>

		jspl:<%=str%>		//	你好
		el:${str}			//什么都没有

- 如何获取带有特殊命名的属性(因为会产生歧义)使用['属性名']	获取Map中的数据可以使用.也可以使用[Key]

		<%
	    Customer customer = new Customer();
	    customer.setName("张三");
		%>
		<%pageContext.setAttribute("cus-s",customer);%>
		el表达式获取属性值<br>
		pageContext: ${pageScope['cus-s']}<br>		//pageContext: Customer{id=null, name='张三', age=null}
		pageContext: ${pageScope['cus-s'].name}<br>		//pageContext: 张三
		pageContext: ${pageScope['cus-s']['name']}<br>	//pageContext: 张三

- el取出域中属性获取map的key值,如果没有,"",如果有显示相应的值

		<%
		pageContext.setAttribute("page","page1");
		%>
		
		jstl:<%=pageContext.getAttribute("page1")%><br>		//jstl:null
		el:${page1}		//el:

- 一个非map对象 pageContext就代表的是jsp隐含对象中的pageContext,取出其它隐含元素

		<%--el中的其它对象 pageContext可以出jsp页面其它的隐含对象,然后可以取出所有隐含对象中的属性了--%>
		jstl:<%=pageContext.getRequest().getScheme()%>
		el:${pageContext.request.scheme}

- HTTP相关

		param (封装了所有的请求参数的key-value)对应一个请求参数 request.getParam("username) <br>
		paramValues 对应一组请求参数<br>
		header 请求头 request.getHeader("User-Agent")<br>
		headerValues 请求头返回字符数组<br>
		cookie 获取某个cookie对象 取出cookie的值<br>
		
		请求参数:${param.username}<br>			//请求参数:张三
		请求头:${header['User-Agent']}<br>		//请求头:Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36
		cookie:${cookie.JSESSIONID.name} || ${cookie.JSESSIONID.value}		//cookie:JSESSIONID || 0629FD8FD175F36D0E4B4C345EB7F8A7

- WEB配置相关

		<%--initParam 获取web.xml的初始化参数--%>
		<%--注意取的不是Servlet的初始他参数,因为JSP页面都是继承Tomcat下的org.apache.jasper.servlet.JspServlet,命名为JSP--%>
		web.xml初始化参数:${initParam.user}<br>		//张三

## EL表达式 ##

语法：${运算表达式}，EL表达式支持如下运算符

1. 关系运算符

	![](http://120.77.237.175:9080/photos/javaweb/10.png)
2. 逻辑运算符

	![](http://120.77.237.175:9080/photos/javaweb/11.png)
3. empty运算符：检查对象是否为null(空)
4. 二元表达式：${user!=null?user.name :""}
5. [ ] 和 . 号运算符

示例

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
	</body>
	</html>

补充

	<%--在实际项目中可以使用EL表达式减少和前端代码的交互--%>
	${pageContext.request.contextPath}      <%--/javaweb--%>
	${pageContext.request.scheme}           <%--http--%>

	<%
	pageContext.setAttribute("ctx",request);
	%>
	<base href="${ctx.scheme}//:${ctx.serverName}:${ctx.serverPort}${ctx.contextPath}">


# JSTL #

## JSTL简介 ##

JSP的标准标签库(里面有很多的标签可以使用),极大的简化开发

## 使用 ##

### 导包 ###

  	<!--JSTL 标签-->
    <dependency>
        <groupId>org.apache.taglibs</groupId>
        <artifactId>taglibs-standard-impl</artifactId>
        <version>1.2.5</version>
    </dependency>
    <dependency>
        <groupId>org.apache.taglibs</groupId>
        <artifactId>taglibs-standard-spec</artifactId>
        <version>1.2.5</version>
    </dependency>

### 在页面导入标签库 ###

	page include taglib(导入标签库的指令)

#### 核心标签库 c标签库 ####

	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	用来导入标签库	prefix定义标签的前缀<jsp:forword> uri:标签库的唯一识别符
	
#### 使用核心标签库 ####

##### out用于计算一个表达式并将结果输出到当前页面 #####

	value:代表要输出的内容 可以是el表达式
	default:表示默认值(当值为null时,才会输出默认值)
	escapeXml:表示是否转义特殊字符,默认转义

	<%
	    pageContext.setAttribute("msg","你好");
	%>
	<c:out value="${msg}" default="hello" escapeXml="true"></c:out>		//你好

	<%
	    pageContext.setAttribute("msg",null);
	%>
	<c:out value="${msg}" default="hello" escapeXml="true"></c:out>		//hello

	<%
	    pageContext.setAttribute("msg","<h1>你好</h1>");
	%>
	<c:out value="${msg}" default="hello" escapeXml="false"></c:out>	//你好(大写)
##### set用于添加或修改域中的属性 #####

	<c:set var="" property="" scope="" target="" value=""></c:set>

1. 给域中设置一个属性

		var:表示要设置的属性的key
		value:表示要设置的属性的value
		scope:表示要设置在哪个域中 pageScope
				scope="page"
				scope="request"
				scope="session"
				scope="application"
	
		<c:set var="tip" scope="request" value="我是提示信息"></c:set>
		${requestScope.tip}			//我是提示信息

2. 修改对象的某个属性值

		<c:set property="name" target="${user}" value="李四"></c:set>
		property:代表要修改的属性名
		value:修改后的值
		target:代表修改哪个对象
	
		<%
		    Student student = new Student("张三",16);
		    pageContext.setAttribute("student",student);
		%>
		<c:set property="username" value="李四" target="${student}"></c:set>
		<%=student.getUsername()%>		//李四

##### remove用来移除域中的属性 #####

	<c:remove var="" scope="" />
	var:要移除的属性key
	scope:要移除哪个域中的属性,如果不指定移除哪个域中的属性,那么就全部移除

	<%
    pageContext.setAttribute("student",student);
    request.setAttribute("student",student);
    session.setAttribute("student",student);
    application.setAttribute("student",student);
	%>
	<c:remove var="student"/>
	page:${pageScope.student.username}<br/>		//空值
	request:${requestScope.student.username}<br/>	//空值
	session:${sessionScope.student.username}<br/>	//空值
	application:${applicationScope.student.username}<br/>	//空值

	<c:remove var="student" scope="page"/>
	page:${pageScope.student.username}<br/>		//空值
	request:${requestScope.student.username}<br/>	//李四
	session:${sessionScope.student.username}<br/>	//李四
	application:${applicationScope.student.username}<br/>	//李四

##### if用于实现if语句的判断功能 #####
	
	test:就是判断条件 如果是true执行标签体里面的内容,否则不执行
	scope:指定作用域,就是将判断结果保存在指定的域中,方便以后使用
	var:指定key

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

##### choose,when,othersise这是一套组合  #####
	
	<%--当所有when的条件都不满足时,才会进入otherwise--%>
	<%--当agen=16时,页面输出你正在读高中-->
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


##### forEache用来做循环遍历 #####

	var:指定遍历的当前条目的变量名 
	begin:指定遍历的开始位置
	end:指定遍历的结束位置
	items:要遍历的东西	list	array...
	step:步长,一次跳过多少
	varStatus:遍历状态,里面封装了当前遍历的所有状态信息
		varStatus="status"
		status.begin:开始的索引
		status.end:结束的索引
		status.step:遍历的步长
		status.count:当前遍历的个数
		status.index:当前遍历到的索引
		status.last:是否最后一个

	<!--遍历1-10的数字-->
	<c:forEach var="num" begin="1" end="10" step="1">
	    ${num}
	</c:forEach>

	<!--遍历list集合-->
	<%
	    ArrayList<Student> list = new ArrayList<>();
	    list.add(new Student("张三",12));
	    list.add(new Student("李四",13));
	    list.add(new Student("王五",14));
	    list.add(new Student("陈二",15));
	%>
	<hr>
	<c:forEach items="<%=list%>" var="student">
	    ${student.username}----${student.age}<br/>
	</c:forEach>
		
	<!--遍历list集合(也可以通过作用域进行遍历)-->
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


	<c:forEach items="${requestScope.list}" var="student" varStatus="status">
	    ${student.username}----${student.age}----${status}<br/>
	</c:forEach>

	
	<%--
	    张三----12----javax.servlet.jsp.jstl.core.LoopTagSupport$1Status@50a4d770
	    李四----13----javax.servlet.jsp.jstl.core.LoopTagSupport$1Status@50a4d770
	    王五----14----javax.servlet.jsp.jstl.core.LoopTagSupport$1Status@50a4d770
	    陈二----15----javax.servlet.jsp.jstl.core.LoopTagSupport$1Status@50a4d770
	--%>
	<!--当调用staus时,打印出类信息,类如图下-->
![](http://120.77.237.175:9080/photos/javaweb/12.png)

	<c:forEach begin="0" end="5" step="2" items="${requestScope.list}" var="student" varStatus="status">
	    ${student.username}----${student.age}----${status.begin}----${status.end}----${status.step}----${status.count}----${status.index}<br/>
	</c:forEach>

	<!--可以看出赋值的status可以输出当前遍历的状态信息--->
	<!--
		name----age----begin----end---step----count----index
		张三----12----0----5----2----1----0
		王五----14----0----5----2----2----2
	-->

##### url使用可选的参数来创造一个URL #####

	value="index.jsp"指定要改装的路径
	把改装后的返回值放到request域中uri属性中,方便后面获取

	<c:url value="/index.jsp" var="uri" scope="request"></c:url>
	${uri}				<!--/javaweb/index.jsp-->
	<!--可以直接获取到目录路径,简化页面链接-->

##### redirect重定向至一个新的URL #####

	<c:redirect url="/index.jsp"></c:redirect>	<!--直接重定向到项目首页-->

	<!--可以结合错误信息重定向到错误页面-->
	<c:if test="${msg}" scope="request">
	    <c:redirect url="/error.jsp"></c:redirect>
	</c:if>

#### JSTL函数库 ####

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

#### 自定义标签(了解) ####

1. 编写标签库的描述文件.描述标签的详细信息,库描述文件放在WEB-INF下,**mytag.tld**

		<?xml version="1.0" encoding="UTF-8"?>
	
		<taglib xmlns="http://java.sun.com/xml/ns/javaee"
		        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-jsptaglibrary_2_1.xsd"
		        version="2.1">
		
		    <!--标签库的版本-->
		    <tlib-version>1.0</tlib-version>
		    <!--指定下面所有当前标签库标签的前缀-->
		    <short-name>mytag</short-name>
		    <!--标签库的唯一村识,域名/mytag-->
		    <uri>/mytag</uri>
		
		    <!--定义一个可以使用的标签-->
		    <tag>
		        <!--定义标签名-->
		        <name>hello</name>
		        <!--定义标签的实现类,必须写全类名-->
		        <tag-class>com.jstl.tag.MyTagFunction</tag-class>
		        <!--
		            empty:是一个空标签,就是没有标签体,代表当前是一个自结束标签
		            scriptless:不可以传jsp表达式,el及其它正常
		            JSP:scriptless可以传Jsp也可以,还可以传入jsp表达式
		            tagdependent:传入是什么就是什么
		        -->
		        <body-content>empty</body-content>
		        <!--使用attrubute属性-->
		        <attribute>
		            <!--name指定属性名-->
		            <name>msg</name>
		            <!--这个属性是必须-->
		            <required>true</required>
		            <!--runtime expression value(传入el表达式是否解析)-->
		            <rtexprvalue>true</rtexprvalue>
		        </attribute>
		    </tag>
		
		</taglib>

2. 标签的实现类实现SimpleTag接口类

		public class MyTagFunction implements SimpleTag {
		
		    private String msg;
		    private PageContext pc;
		
		    public String getMsg() {
		        return msg;
		    }
		
		    public void setMsg(String msg) {
		        this.msg = msg;
		        System.out.println("接收到的属性:"+msg);
		    }
		
		    /*执行标签*/
		    @Override
		    public void doTag() throws JspException, IOException {
		        System.out.println("doTag");
		        pc.getOut().write("<h1>"+this.msg+"</h1>");
		    }
		
		    /*设置父标签 服务器自动传进来*/
		    @Override
		    public void setParent(JspTag jspTag) {
		
		    }
		
		    /*获取父标签(只特指自定义标签)*/
		    @Override
		    public JspTag getParent() {
		        System.out.println("getParent");
		        return null;
		    }
		
		    /*设置jspContext == pageContext 服务器自动传入*/
		    @Override
		    public void setJspContext(JspContext jspContext) {
		        System.out.println("setJspContext");
		        System.out.println(jspContext); /*org.apache.jasper.runtime.PageContextImpl*/
		        this.pc = (PageContext) jspContext;
		    }
		
		    /*设置标签体 服务器自动传入*/
		    @Override
		    public void setJspBody(JspFragment jspFragment) {
		        System.out.println("setJspBody");
		    }
		}

3. 在页面引入

	<%@ taglib prefix="mytag" uri="/mytag" %>

4. 使用自定义标签

	<mytag:hello msg="hello"/>		//在页面显示hello


# Cookie #

## 创建Cookie ##

	public class CookieServlet extends HttpServlet {
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        this.doPost(req, resp);
	    }
	
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       if(req.getParameter("method").equals("add"))
	       {
	           Cookie cookie = new Cookie("username","张三");
	            resp.addCookie(cookie);
	            resp.getWriter().write("add cookie success!");
	       }
	    }
	}

- **注意:创建Cookie必须response返回给浏览器**
- **默认关闭浏览器会销毁Cookie**

## 获取Cookie ##

	Cookie[] cookies = req.getCookies();
    for(Cookie cookie:cookies)
    {
        System.out.println(cookie.getName()+"------"+cookie.getValue());
    }
	
	//username------张三

## 删除Cookie ##

1. 默认Cookie是在会话期间有效(浏览器一直不关)
2. Cookie可以修改默认的存活时间

		//负数 不保存Cookie,即使发给浏览器也不会保存
		//正数 Cookie的最大存在时间 单位:秒
		//0 表示删除Cookie
		cookie.setMaxAge(0);
		//浏览器信息Set-Cookie: username=张三; Max-Age=0; Expires=Thu, 01-Jan-1970 00:00:10 GMT

## 持久化Cookie ##

	//表示一小时后Cookie才会被删除(浏览器会删除)
	cookie.setMaxAge(60*60);

## 设置Cookiek路径 ##

告诉浏览器访问哪些资源会携带这个Cookie.默认:访问当前项目下的任何东西都会携带Cookie

 	Cookie cookie = new Cookie("msg","你好");
    cookie.setPath("/hello");		//设置Cookie的保存路径(浏览器只有访问到该目录下才可访问到该Cookie)
    resp.addCookie(cookie);
    System.out.println("cookie path set success!");

![](http://120.77.237.175:9080/photos/javaweb/13.png)

## 修改Cookie ##

浏览器如何识别是否同一Cookie?		根据Cookie的name,修改Cookie的同名key,就可修改其值

  	Cookie[] cookies = req.getCookies();
    for (Cookie cookie:cookies)
    {
        if (cookie.getName().equals("username"))
        {
            cookie.setValue("李四");
            resp.addCookie(cookie);
        }
    }

或

	Cookie cookie = new Cookie("username", "李四");
    resp.addCookie(cookie);


# Session #

![](http://120.77.237.175:9080/photos/javaweb/14.png)

## 获取Session对象 ##
	public class SessionServlet extends HttpServlet {
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        this.doPost(req,resp);
	    }
	
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        String method = req.getParameter("method");
	        /*获取Session*/
	        HttpSession session = req.getSession();
	        if (method.equals("add"))
	        {
	            //判断Session是否新创建,
	            boolean aNew = session.isNew();
	            resp.getWriter().print("session is new :"+aNew);
	        }
	    }
	
	}

## 给Session保存内容 ##

	HttpSession session = req.getSession();
 	String id = session.getId();
    session.setAttribute("sessionid",id);
    resp.getWriter().print("sessionid is "+id);

## 获取Seesion中内容 ##

	HttpSession session = req.getSession();
	String sessionid = (String) session.getAttribute("sessionid");
    resp.getWriter().print("sessionid get "+sessionid);
	
![](http://120.77.237.175:9080/photos/javaweb/15.jpg)

## 获取Session有效时间 ##

 	//获取session的最大存话时间 以秒为单位
    //session默认是30分钟,为什么新会话开启会返回新session
    //因为获取session根据cookie带来的jsessionid来获取.cookie默认关闭浏览器就没了
    //再来获取session,返回新的session,旧的session还在.只是找不到而已
    int maxInactiveInterval = session.getMaxInactiveInterval();
    resp.getWriter().print("max time is "+maxInactiveInterval);		//1800

可以通过在web.xmlw修改其默认值

    <session-config>
        <session-timeout>1</session-timeout>		//60秒过期
    </session-config>


## 修改Session有效时间 ##

	 /*1传入负数:代表永不过期*/
    /*2传入正数:代表多少秒后过期,距离最后一次使用session时间*/
	HttpSession session = req.getSession();
    session.setMaxInactiveInterval(3);		//3秒后过期
    resp.getWriter().print("set session time success!");

创建新的Session,再修改有效时间,重新再创建Session返回false

## 强制Session失效 ##

	HttpSession session = req.getSession();
	session.invalidate();
    resp.getWriter().print("session is invalid");

## 生命周期 ##

- 创建:第一次用session会创建一个新的session
- 销毁:
	1. 默认30分钟后session销毁
