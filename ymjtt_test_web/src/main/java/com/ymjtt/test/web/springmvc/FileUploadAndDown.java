package com.ymjtt.test.web.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @auther ywx
 * @date 2018/11/8 8:30
 **/
@Controller
public class FileUploadAndDown {

    /**
     * 上传文件
     * @param response
     * @param request
     * @param uploadFiles
     * @throws IOException
     */
    @RequestMapping(value = "/fileUpload")
    public void forOKHttp3Test07(HttpServletResponse response, HttpServletRequest request, String name, Integer age, MultipartFile[] uploadFiles) throws IOException {
        if (null != uploadFiles) {
            for (MultipartFile uploadFile : uploadFiles) {
                if (null != uploadFile) {
                    String filename = uploadFile.getOriginalFilename();
                    File file = new File("E:/", filename);
                    uploadFile.transferTo(file);

                    System.out.println("上传文件大小:" + request.getContentLength());
                    System.out.println("已完成上传文件大小:" + file.length());
                }
            }

            response.getWriter().write("call fileUpload success, name=" + name + ",  age=" + age);
        }else {
            response.getWriter().write("call fileUpload fail, name=" + name + ",  age=" + age);
        }
    }

    /**
     * 下载文件, 直接使用 http://localhost:9001/test_web/fileDown?fileName=JAVA并发编程实战.pdf
     * @param response
     * @param request
     * @param fileName
     * @throws IOException
     */
    @RequestMapping(value = "/fileDown")
    public void forOKHttp3Test08(HttpServletResponse response, HttpServletRequest request, String fileName) throws IOException {
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis;
        BufferedOutputStream bos;

        //获取项目根目录
        String ctxPath = request.getSession().getServletContext().getRealPath("");

        //获取下载文件露肩
        String downLoadPath = ctxPath + "/uploadFile/" + fileName;

        //获取文件的长度
        long fileLength = new File(downLoadPath).length();

        //设置文件输出类型
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename="
                + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        //设置输出长度
        response.setHeader("Content-Length", String.valueOf(fileLength));
        //获取输入流
        bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        //输出流
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        //关闭流
        bis.close();
        bos.close();
    }


}
