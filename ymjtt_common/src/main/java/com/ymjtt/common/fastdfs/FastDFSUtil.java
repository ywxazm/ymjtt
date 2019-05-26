package com.ymjtt.common.fastdfs;

import com.ymjtt.common.exception.instance.FastDFSIntException;
import com.ymjtt.common.util.file.FileUtils;
import com.ymjtt.common.util.file.JarFileUtils;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @auther ywx
 * @date 2018/11/8 22:01
 * @info 操作fastDFS工具类
 **/
@Component
public class FastDFSUtil {

    private static final Logger logger = LoggerFactory.getLogger(FastDFSUtil.class);

    @Value("http://192.168.190.129/")
    private String nginxServer;

    @Value("properties/fastdfs.properties")
    private String confFile;

    private TrackerClient tracker;
    private TrackerServer trackerServer;
    private StorageServer storageServer;
    private StorageClient storageClient;

    @PostConstruct
    public void init() throws FastDFSIntException {
        try {
            String newPath = System.getProperty("user.home") + "/" + confFile;
            confFile = FastDFSUtil.class.getClassLoader().getResource(confFile).getPath();
            JarFileUtils.resetFilePath(confFile, newPath);
            ClientGlobal.init(newPath);
            tracker = new TrackerClient();
            trackerServer = tracker.getConnection();
            storageServer = null;
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (Exception e) {
            throw new FastDFSIntException("FastDfs Init Error");
        }
    }

    /**
     * 本地文件上传功能
     * @author  ywx
     * @date    2019/1/23 9:09
     * @param   [bytes, extName, paramMap]
     * @return  java.lang.String[]
     */
    public String save(String filePath, Map<String, String> paramMap) throws IOException, MyException {
        NameValuePair[] nvp = Map2Nvp(paramMap);
        String[] paths = storageClient.upload_file(filePath, FileUtils.getExtensionName(filePath), nvp);
        return nginxServer + paths[0] + "/" + paths[1];
    }

    /**
     * 字节码文件上传
     * @author  ywx
     * @date    2019/1/23 10:12
     * @param   [bytes, extensionName, paramJson]
     * @return  java.lang.String
     */
    public String save(byte[] bytes, String extensionName, Map<String, String> paramMap) throws IOException, MyException {
        NameValuePair[] nvp = Map2Nvp(paramMap);
        String paths[] = storageClient.upload_file(bytes, extensionName, nvp);
        return nginxServer + paths[0] + "/" + paths[1];
    }

    /**
     * MultipartFile类型文件上传
     * @author  ywx
     * @date    2018/11/27 21:44
     * @param   [multipartFiles, request]
     * @return
     */
    public String save(MultipartFile multipartFile, Map<String, String> paramMap) throws IOException, MyException {
        String filename = multipartFile.getOriginalFilename();
        return save(multipartFile.getBytes(), FileUtils.getExtensionName(filename), paramMap);
    }

    /**
     * 删除文件, 成功返回True
     * @author  ywx
     * @date    2019/1/23 10:34
     * @param   [filePath]
     * @return  boolean
     */
    public boolean remove(final String filePath) throws IOException, MyException {
        String path = filePath.replace(nginxServer, "");
        String groupN = path.substring(0, path.indexOf("/"));
        String fileName = path.substring(groupN.length() + 1);
        return 0 == storageClient.delete_file(groupN, fileName);
    }



    /*  -------------------------以下未完善---------------------------  */
    /**
     * 下载文件至本地
     * @param fastDFSPath
     * @throws IOException
     * @throws MyException
     */
    @Deprecated
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
    @Deprecated
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
    @Deprecated
    public org.csource.fastdfs.FileInfo fastDFSGetFileInfo(String fastDFSPath) throws IOException, MyException {
        return storageClient.get_file_info(fastDFSPath.substring(0, fastDFSPath.indexOf("/")), fastDFSPath.substring(fastDFSPath.indexOf("/") + 1));
    }

    /**
     * 获取FileMate
     * @param fastDFSPath
     * @return
     * @throws IOException
     * @throws MyException
     */
    @Deprecated
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

    /**
     * Map 转 NVP 格式
     * @author  ywx
     * @date    2019/1/23 10:16
     * @param   [paramMap]
     * @return  org.csource.common.NameValuePair[]
     */
    private NameValuePair[] Map2Nvp(Map<String, String> paramMap) {
        NameValuePair[] nvp = new NameValuePair[0];
        if (null != paramMap && !paramMap.isEmpty()) {
            nvp = new NameValuePair[paramMap.size()];
            int i = 0;
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                nvp[i] = new NameValuePair(entry.getKey(), entry.getValue());
                i++;
            }
        }
        return nvp;
    }

}
