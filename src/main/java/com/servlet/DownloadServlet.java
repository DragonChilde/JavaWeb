package com.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Lee
 * @create 2020/3/17 14:48
 */
public class DownloadServlet extends HttpServlet {

    private static final long serialVersionUID = -1443602753364515982L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //本质就是告诉浏览器给的资源别直接打开,要下载
        //把要下载的资源的流传给浏览器
        //1.设置响应的文件类型,文件是什么类型就设置什么类型
        //response.setContentTyep("");
        //解决下载文件中文问题
        String s = new String("美眉.jpg".getBytes("gbk"), "iso8859-1");
        //获取要下载的资源的文件类型
        String mimeType = getServletContext().getMimeType("/美眉.jpg");
        //System.out.println(mimeType);   //image/jpeg
        resp.setContentType(mimeType);
        //2.设置资源的处理方式,设置处理方式响应头
        resp.setHeader("Content-Disposition","attachment;filename="+s);
        //3.可选,设置文件大小response.setContentLength(len)

        //获取下载文件的真实路径
        String realPath = getServletContext().getRealPath("/美眉.jpg");
        //把要下载的文件放进流里
        FileInputStream fileInputStream = new FileInputStream(realPath);
        //返回输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        //把输入流复制到输出流
        IOUtils.copy(fileInputStream,outputStream);
        outputStream.close();
        fileInputStream.close();

    }
}
