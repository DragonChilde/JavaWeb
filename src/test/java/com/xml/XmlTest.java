package com.xml;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xml.bean.Customer;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lee
 * @create 2019/12/12 17:21
 */
public class XmlTest {

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
            System.out.println(text);   //张三    李四

            //attributeValue(name)获取标签里指定的属性史
            Integer id =  Integer.parseInt(element.attributeValue("id"));
            System.out.println(id);     //  1   2

            List<Element> elementList = element.elements();
            for (Element element2:
                    elementList) {
                System.out.println(element2.getName());     //name age
                //getText()获取文本标签里面的文本值
                String test2 = element2.getText();  //张三   11|  李四  12
                System.out.println(test2);
            }
        }

    }


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
    }

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
        System.out.println(id);

        //选取所有标签为name的节点
        List<Node> nodes = rootElement.selectNodes("//name");

        for (Node node1:
        nodes) {
            Element element1 = (Element) node1;
            System.out.println(element1.getText());
        }
    }
}
