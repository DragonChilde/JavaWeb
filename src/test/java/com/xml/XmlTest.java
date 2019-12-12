package com.xml;

import com.xml.bean.Customer;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

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
        List<Customer> list = new ArrayList<>();
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
            String customerName = element.elementText("name");

            System.out.println(customerName);
            Integer age =   Integer.parseInt(element.elementText("age"));

            Customer customer = new Customer(id, customerName, age);

            list.add(customer);
            List<Element> elementList = element.elements();
            for (Element element2:
                    elementList) {
                //System.out.println(element2.getName());     //name age
                //getText()获取文本标签里面的文本值
                //String test2 = element2.getText();  //张三   11|  李四  12
                //System.out.println(test2);

            }

        }
        System.out.println(list);

    }
}
