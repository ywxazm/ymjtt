package com.ymjtt.test.web.util;

import com.alibaba.druid.util.StringUtils;
import com.ymjtt.common.util.consts.CommonConsts;
import com.ymjtt.common.util.json.JSONConvertUtil;
import com.ymjtt.common.util.file.FileNameUtils;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @auther ywx
 * @date 2018/11/8 22:01
 * @info 操作fastDFS工具类
 **/
public class FastDFSUtil {

    @Value("util/fastdfs.properties")        //自动注入不支持静态域
    private String confFile;
    @Value("group1")
    private String groupName;
    private String downPath;

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
        confFile = FastDFSUtil.class.getClassLoader().getResource(confFile).getPath();
        ClientGlobal.init(confFile);
        tracker = new TrackerClient();
        trackerServer = tracker.getConnection();
        storageServer = null;
        storageClient = new StorageClient(trackerServer, storageServer);
    }

    /**
     * 设置下载路径
     * @param downFilePath
     * @throws Exception
     */
    public void setDownPath(String downFilePath) throws Exception {
        downPath = downFilePath;
    }

    /**
     * 获取下载路径
     * @return
     * @throws Exception
     */
    public String getDownPath() throws Exception {
        return downPath;
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
        if (fileName.lastIndexOf(CommonConsts.POINT_STR) == -1) {
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

        String fileInfo[] = storageClient.upload_file(fileName, FileNameUtils.getExtensionName(fileName), nvp);
        StringBuffer sb = new StringBuffer();
        for (String s : fileInfo) {
            sb.append(s).append(CommonConsts.SPRIT_STR);
        }

        return sb.toString();
    }

    /**
     * 下载文件
     * @param fastDFSPath
     * @throws IOException
     * @throws MyException
     */
    public void fastDFSDown(String fastDFSPath) throws IOException, MyException {
        byte[] fileByte = storageClient.download_file(groupName, fastDFSPath);
        if (null == fileByte) {
            throw new FileNotFoundException("found not file: " + fastDFSPath + ", at fastDFS server");
        }
        IOUtils.write(fileByte, new FileOutputStream(downPath + FileNameUtils.createFileName(FileNameUtils.getExtensionName(fastDFSPath))));
    }

    /**
     * 获取FileInfo
     * @param fastDFSPath
     * @return
     * @throws IOException
     * @throws MyException
     */
    public FileInfo fastDFSGetFileInfo(String fastDFSPath) throws IOException, MyException {
        return storageClient.get_file_info(groupName, fastDFSPath);
    }

    /**
     * 获取FileMate
     * @param fastDFSPath
     * @return
     * @throws IOException
     * @throws MyException
     */
    public Map<String, String> fastDFSGetFileMate(String fastDFSPath) throws IOException, MyException {
        NameValuePair nvps [] = storageClient.get_metadata(groupName, fastDFSPath);
        Map<String, String> map = null;
        if (null != map && map.size() != 0) {
            for (NameValuePair nvp : nvps) {
                map.put(nvp.getName(), nvp.getValue());
            }
        }
        return map;
    }

    /**
     * 删除fastDFS中的文件
     * @param fastDFSPath
     * @return  0: success   others: fail
     * @throws IOException
     * @throws MyException
     */
    public Integer fastDFSRemove(String fastDFSPath) throws IOException, MyException {
        return storageClient.delete_file(groupName, fastDFSPath);
    }

}
