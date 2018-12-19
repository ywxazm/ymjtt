package com.ymjtt.manager.web.upload;

import com.ymjtt.common.result.CodeResult;
import com.ymjtt.common.result.ResultVO;
import com.ymjtt.common.util.fastdfs.FastDFSUtil;
import org.csource.common.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @auther ywx
 * @date 2018/12/19 9:51
 **/
@Controller
@RequestMapping("/manager/fileupload")
public class FileUpload {

    private static final Logger logger = LoggerFactory.getLogger(FileUpload.class);

    @Autowired
    private FastDFSUtil fastDFSUtil;

    /**
     * 文件上传
     * @author  ywx
     * @date    2018/12/19 10:08
     * @param   [request, multipartFiles]
     * @return  com.ymjtt.common.result.ResultVO
     */
    @ResponseBody
    @RequestMapping("/upload")
    public ResultVO upload(HttpServletRequest request, MultipartFile[] multipartFiles)
            throws IOException, MyException {
        logger.debug("the thread id = {}, FastDFSUtil.upload() file count: {}"
                , Thread.currentThread().getId(), multipartFiles.length);
        Long startTime = System.currentTimeMillis();

        String paths = null;
        if (null != multipartFiles && multipartFiles.length > 0) {
            paths = fastDFSUtil.upload(request, multipartFiles);
        }

        logger.debug("the thread id is {}, FastDFSUtil.upload() cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return null == paths ? ResultVO.buildErrorResult(CodeResult.UPDATE_FILE_FAIL)
                : ResultVO.buildSuccessResult(paths);
    }

    /**
     * 文件下载
     * @author  ywx
     * @date    2018/12/19 10:08
     * @param   [filePath]
     * @return  com.ymjtt.common.result.ResultVO
     */
    @ResponseBody
    @RequestMapping("/remove")
    public ResultVO remove(String filePath) throws IOException, MyException {
        logger.debug("the thread id = {}, FastDFSUtil.remove() param filePath = {}"
                , Thread.currentThread().getId(), filePath);
        Long startTime = System.currentTimeMillis();

        Map<String, String> resultMap = fastDFSUtil.remove(filePath);

        logger.debug("the thread id is {}, FastDFSUtil.remove() cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return null ==  resultMap ? ResultVO.buildErrorResult(CodeResult.REMOVE_FILE_FAIL)
                : ResultVO.buildSuccessResult(resultMap);
    }
}
