package com.ymjtt.common.util.fastdfs;

import com.ymjtt.common.util.file.FileUtils;
import com.ymjtt.common.util.file.JarFileUtils;
import com.ymjtt.common.util.json.JSONConvertUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther ywx
 * @date 2018/11/8 22:01
 * @info 操作fastDFS工具类
 **/
@SuppressWarnings("FieldCanBeLocal")
@Component
public class FastDFSUtil {

    private static final Logger logger = LoggerFactory.getLogger(FastDFSUtil.class);

    @Value("util/fastdfs.properties")        //自动注入不支持静态域
    private String confFile;

    @Value("${nginx_server}")
    private String nginxServer;

    private TrackerClient tracker;
    private TrackerServer trackerServer;
    private StorageServer storageServer;
    private StorageClient storageClient;

    /**
     * 初始化, 在初始化本类时执行
     * @throws IOException
     * @throws MyException
     */
   public void init() throws IOException, MyException {
        logger.debug("FastDFS init()...");
        String newPath = System.getProperty("user.dir") + "/fastdfs.properties";
        confFile = FastDFSUtil.class.getClassLoader().getResource(confFile).getPath();
        JarFileUtils.resetFilePath(confFile, newPath);
        ClientGlobal.init(newPath);
        tracker = new TrackerClient();
        trackerServer = tracker.getConnection();
        storageServer = null;
        storageClient = new StorageClient(trackerServer, storageServer);
    }

    /**
     * 图片上传(支持多张图片上传)
     * @author  ywx
     * @date    2018/11/27 21:44
     * @param   [multipartFiles, request]
     * @return
     */
    public String upload(HttpServletRequest request, MultipartFile[] multipartFiles) throws IOException, MyException {
        StringBuilder paths = new StringBuilder();
        if (null != multipartFiles && multipartFiles.length > 0) {
            for (MultipartFile multipartFile : multipartFiles) {
                if (null != multipartFile) {
                    String filename = multipartFile.getOriginalFilename();
                    String path = fastDFSSave(multipartFile.getBytes(), FileUtils.getExtensionName(filename), null);
                    if(null != path) {
                        paths.append(nginxServer).append(path).append(",");
                    }
                }
            }
            return paths.substring(0, paths.length() - 1);
        }
        return null;
    }

    /**
     * 删除fastDFS中的文件(支持多文件删除)
     * 请求格式: ?filePath=group1/M00/00/00/wKi-gVwUbVmACp5oAACgIuTmy1Q005.jpg/,group1/M00/00/00/wKi-gVwUbVmAYa49AAA0aLoff8c550.jpg/,group1/M00/00/00/wKi-gVwUbVmAR_5tAADEqvzar4g503.jpg/
     * @author  ywx
     * @date    2018/12/14 16:22
     * @param   [fastDFSPath]
     * @return  com.ymjtt.common.vo.YmjttResultVO  0: success   others: fail
     */
    public Map<String, String> remove(String filePath) throws IOException, MyException {
        Map<String, String> resultMap = new HashMap<>();
        StringBuilder successPath = new StringBuilder();
        StringBuilder failPath = new StringBuilder();

        if (!StringUtils.isEmpty(filePath)) {
            filePath = filePath.replace(nginxServer, "");
            String[] paths = filePath.split(",");
            for (String path : paths) {

                String groupN = path.substring(0, filePath.indexOf("/"));
                String fileName = path.substring(filePath.indexOf("/") + 1);

                if ((storageClient.delete_file(groupN, fileName) == 0)) {
                    successPath.append(path);
                } else {
                    failPath.append(path);
                }
            }
        }else {
            return null;
        }

        resultMap.put("SuccessRemove", successPath.toString());
        resultMap.put("FailRemove", failPath.toString());
        return resultMap;
    }

    /**
     * 上传文件
     * @param fileName
     * @param paramJson
     * @return
     * @throws IOException
     * @throws MyException
     */
    public String fastDFSSave(String fileName, String paramJson) throws IOException, MyException {
        if (fileName.lastIndexOf(".") == -1) {
            return null;
        }

        NameValuePair[] nvp = null;
        if (!StringUtils.isEmpty(paramJson)) {
            Map<String, String> paramMap = JSONConvertUtil.json2map(paramJson);
            List<NameValuePair> paramList = new ArrayList<>();
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                paramList.add(new NameValuePair(entry.getKey(), entry.getValue()));
            }
            nvp = (NameValuePair[]) paramList.toArray();
        }

        String fileInfo[] = storageClient.upload_file(fileName, FileUtils.getExtensionName(fileName), nvp);
        StringBuilder sb = new StringBuilder();
        for (String s : fileInfo) {
            sb.append(s).append("/");
        }

        return sb.toString();
    }

    /**
     *
     * @author  ywx
     * @date    2018/11/22 9:20
     * @param   [fileName, paramJson]
     * @return  java.lang.String
     */
    public String fastDFSSave(byte[] bytes, String extensionName, String paramJson) throws IOException, MyException {

        NameValuePair[] nvp = null;
        if (!StringUtils.isEmpty(paramJson)) {
            Map<String, String> paramMap = JSONConvertUtil.json2map(paramJson);
            List<NameValuePair> paramList = new ArrayList<>();
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                paramList.add(new NameValuePair(entry.getKey(), entry.getValue()));
            }
            nvp = (NameValuePair[]) paramList.toArray();
        }

        String fileInfo[] = storageClient.upload_file(bytes, extensionName, nvp);
        StringBuilder sb = new StringBuilder();
        for (String s : fileInfo) {
            sb.append(s).append("/");
        }
        String s = sb.toString();
        return s.substring(0, s.length() - 1);
    }

    /**
     * 下载文件至本地
     * @param fastDFSPath
     * @throws IOException
     * @throws MyException
     */
    public void fastDFSDown(String fastDFSPath, String downPath) throws IOException, MyException {
        byte[] fileByte = storageClient.download_file(fastDFSPath.substring(0, fastDFSPath.indexOf("/")), fastDFSPath.substring(fastDFSPath.indexOf("/") + 1));
        if (null == fileByte) {
            throw new FileNotFoundException("found not file: " + fastDFSPath + ", at fastDFS server");
        }
        IOUtils.write(fileByte, new FileOutputStream(downPath + FileUtils.createFileName(FileUtils.getExtensionName(fastDFSPath))));
    }

    /**
     * 获取文件字节
     * @author  ywx
     * @date    2018/12/17 23:04
     * @param   [filePath]
     * @return  byte[]
     */
    public byte[] getBytes(String filePath) throws IOException, MyException {
        filePath = filePath.replace(nginxServer, "");
        return storageClient.download_file(filePath.substring(0, filePath.indexOf("/")), filePath.substring(filePath.indexOf("/") + 1));
    }

    /**
     * 获取FileInfo
     * @param fastDFSPath
     * @return
     * @throws IOException
     * @throws MyException
     */
    public FileInfo fastDFSGetFileInfo(String fastDFSPath) throws IOException, MyException {
        return storageClient.get_file_info(fastDFSPath.substring(0, fastDFSPath.indexOf("/")), fastDFSPath.substring(fastDFSPath.indexOf("/") + 1));
    }

    /**
     * 获取FileMate
     * @param fastDFSPath
     * @return
     * @throws IOException
     * @throws MyException
     */
    public Map<String, String> fastDFSGetFileMate(String fastDFSPath) throws IOException, MyException {
        NameValuePair nvps [] = storageClient.get_metadata(fastDFSPath.substring(0, fastDFSPath.indexOf("/")), fastDFSPath.substring(fastDFSPath.indexOf("/") + 1));
        Map<String, String> map = null;
        if (null != map && map.size() != 0) {
            for (NameValuePair nvp : nvps) {
                map.put(nvp.getName(), nvp.getValue());
            }
        }
        return map;
    }

}
