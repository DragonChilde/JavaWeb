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