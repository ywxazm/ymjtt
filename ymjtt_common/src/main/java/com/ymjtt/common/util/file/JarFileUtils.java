package com.ymjtt.common.util.file;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 处理jar包中的文件
 * @auther ywx
 * @date 2018/12/19 8:27
 **/
public class JarFileUtils {

    /**
     * 读取jar中的文件
     * @author  ywx
     * @date    2018/12/18 22:50
     * @param   [file]
     * @return  byte[]
     */
    public static InputStream getJarInputStream(final String filePath) throws IOException {

        if (filePath.contains(".jar")) {
            URL url;
            if(filePath.trim().startsWith("jar:file:")) {
                url = new URL(filePath);
            }else if (filePath.trim().startsWith("file:")) {
                url = new URL("jar:" + filePath);
            }else {
                url = new URL("jar:file:" + filePath);
            }
            JarURLConnection jarConnection = (JarURLConnection) url
                    .openConnection();
            return  jarConnection.getInputStream();

        }else {

            File file = new File(filePath);
            return new FileInputStream(file);
        }
    }

    /**
     * 读取文件返回字符串
     * @author  ywx
     * @date    2018/12/19 8:33
     * @param   [filePath]
     * @return  java.lang.String
     */
    public static String readFile(final String filePath) {
        InputStream in = null;
        BufferedReader br = null;
        StringBuffer sb = null;

        try {
            in = getJarInputStream(filePath);
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String con;

            sb = new StringBuffer();
            while ((con = br.readLine()) != null) {
                    sb.append(con).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (in != null)
                    in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    /**
     * 写文件
     * @author  ywx
     * @date    2018/12/19 8:36
     * @param   [filePath, content]
     * @return  boolean
     */
    public static void writeFile(final String filePath, final String content) {
        boolean result = false;
        File f = new File(filePath);
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(f);
            byte[] b = content.getBytes();
            fs.write(b);
            fs.flush();
            fs.close();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件拷备
     * @author  ywx
     * @date    2018/12/19 8:59
     * @param   [jarFilePath, filePath]
     * @return  void
     */
    public static void resetFilePath(final String jarFilePath, final String filePath){
        String content = readFile(jarFilePath);
        writeFile(filePath, content);
    }

   /* public static void main(String[] args) {
        resetFilePath("/D:/Develop/MavenResp/com/ywx/ymjtt/ymjtt_common/1.0-SNAPSHOT/ymjtt_common-1.0-SNAPSHOT.jar!/util/fastdfs.properties", "D://a.properties");
    }*/
}
