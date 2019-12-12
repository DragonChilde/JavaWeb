# XML #


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