package com.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Lee
 * @create 2020/3/16 15:24
 */
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = -389687429648305775L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.先创建一个工厂实例
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2.创建一个专门用来处理Servlet文件上传的对象
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        //3.解析文件上传请求,把表单的所有部件封装返回
        //返回的List,封装什么?FileItem
        //FileItem,就是封装了请求的流中每个部件.每一个部件就对应一个FileItem
        List<FileItem> fileItems = null;
        try {
            fileItems = fileUpload.parseRequest(req);
            //4. 解析每一个部件FileItem
            for (FileItem item: fileItems) {
                /*
                * name=null, StoreLocation=null, size=4 bytes, isFormField=true, FieldName=name
                    name=u=3081163734,2566680523&fm=26&gp=0.jpg, StoreLocation=D:\\Server\\apache-tomcat-9.0.27\\temp\\upload_18226eda_6a52_4d77_9592_7c8668d25c50_00000001.tmp, size=35670 bytes, isFormField=false, FieldName=file
                    name=null, StoreLocation=null, size=6 bytes, isFormField=true, FieldName=鎻愪氦
                */
                /*判断是否为文件上传的表单项isFormField->true代表就是一个普通表单项,false代表文件上传项*/
                if(item.isFormField())
                {
                    //处理表单项
                    //true代表是简单的key-value
                    //getFieldName值就是获取的表单项的name值
                    String fieldName = item.getFieldName(); //获取表单的name值
                    //获取文件名(获取不到)
                    String name = item.getName();
                    String value = item.getString("utf-8");//获取表单项的value值
                    System.out.println(value);

                } else {
                    //false代表是一个文件
                    //getFieldName值的就是获取的表单项的name值
                    String fieldName = item.getFieldName();
                    //获取文件名
                    String name = item.getName();
                    //如果是文件流,getInputStream获取文件项的文件流
                    InputStream inputStream = item.getInputStream();
                    //将文件写到项目中,动态获取文件夹路径
                    ServletContext servletContext = getServletContext();
                    //保存路径.动态获取服务器某个文件夹的路径,/代表当前项目
                    String contextPath = servletContext.getRealPath("/uploads");
                    //根据时间戳和UUID重新定义文件名
                    String uuid = UUID.randomUUID().toString();
                    Instant now = Instant.now();
                    long epochSecond = now.toEpochMilli();
                    System.out.println(epochSecond);
                    //获取上传的文件名后缀
                    int indexOf = name.indexOf(".");
                    //截取后缀名
                    String substring = name.substring(indexOf);
                    //拼接新的文件名
                    String newFileName = uuid + "-" + epochSecond+substring;
                    //F:\\Code\\JavaWeb\\out\\artifacts\\javaweb\\uploadsbc28cb96-05e2-45fa-900b-31644afbcb4b-1584351724383.jpg
                    FileOutputStream outputStream = new FileOutputStream(contextPath+"/"+newFileName);
                    System.out.println(contextPath);
                    //将输入流的内容写在输出流里
                    IOUtils.copy(inputStream,outputStream);
                    //闭关输入输出流
                    outputStream.close();
                    inputStream.close();

                }

            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath()+"/jsp/fileupload.jsp");

    }
}
