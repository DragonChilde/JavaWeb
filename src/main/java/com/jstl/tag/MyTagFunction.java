package com.jstl.tag;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;
import java.io.IOException;

/**
 * @author Lee
 * @create 2020-02-18 11:44
 */
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
        System.out.println("setParent");
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
