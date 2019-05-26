package com.ymjtt.manager.user.web;

import com.github.pagehelper.PageInfo;
import com.ymjtt.common.fastdfs.FastDFSUtil;
import com.ymjtt.common.vo.DataGridVO;
import com.ymjtt.common.util.objPackage.BeanUtil;
import com.ymjtt.common.vo.ResultInfoVO;
import com.ymjtt.manager.user.service.UserService;
import com.ymjtt.manager.user.xdo.UserDo;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * 用户
 * @author  ywx
 * @date    2018/11/21 15:53
 */
@Service
@RequestMapping("/manager/user")
public class UserControl {

    @Value("${initPassword}")
    private String initPassword;

    @Autowired
    private UserService userService;

    @Autowired
    private BeanUtil<UserDo> beanUtil;

    @Autowired
    FastDFSUtil fastDFSUtil;

    @ResponseBody
    @RequestMapping(value = "/list")
    public DataGridVO<UserDo> list(UserDo userDo, Integer page, Integer rows) {
        PageInfo<UserDo> pageInfo = userService.listDO(userDo, page, rows);
        return new DataGridVO<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ResultInfoVO get(Long id) {
        return ResultInfoVO.buildSuccessInfo(userService.getDO(id));
    }

    @ResponseBody
    @RequestMapping(value = "/remove")
    public ResultInfoVO remove(Long id) throws IOException, MyException {
        UserDo userDo = userService.getDO(id);
        if (null == userDo) {
            return ResultInfoVO.buildFailInfo("Have No Id = " + id + " UserDo");
        }

        if (!StringUtils.isEmpty(userDo.getImage())) {
            if (!fastDFSUtil.remove(userDo.getImage())) {
                return ResultInfoVO.buildFailInfo("Remove Obj Image Fail");
            }
        }

        return userService.removeDO(id) ? ResultInfoVO.buildSuccessInfo() : ResultInfoVO.buildFailInfo();
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public ResultInfoVO save(HttpServletRequest request,  MultipartFile[] multipartFiles) throws IOException, MyException, InvocationTargetException, IllegalAccessException {
        UserDo userDo = beanUtil.buildObj(request, new UserDo());

        if (null != multipartFiles && multipartFiles.length != 0) {
            if (multipartFiles.length > 1) {
                throw new IllegalArgumentException("User Image Count Must = 1");
            }

            String imgPath = fastDFSUtil.save(multipartFiles[0], new HashMap<>());
            userDo.setImage(imgPath);
        }

        userDo.setPwd(DigestUtils.md5DigestAsHex(initPassword.getBytes(Charset.defaultCharset())));
        return userService.saveDO(userDo) ? ResultInfoVO.buildSuccessInfo() : ResultInfoVO.buildFailInfo();
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public ResultInfoVO update(HttpServletRequest request,  MultipartFile[] multipartFiles) throws IOException, MyException, InvocationTargetException, IllegalAccessException {
        UserDo userDo = beanUtil.buildObj(request, new UserDo());

        if (null != multipartFiles && multipartFiles.length != 0) {
            if (multipartFiles.length > 1) {
                throw new IllegalArgumentException("User Image Count Must = 1");
            }

            UserDo u = userService.getDO(Long.parseLong(request.getParameter("userId")));
            boolean removeImgResult = true;
            if (!StringUtils.isEmpty(u.getImage())) {
                removeImgResult = fastDFSUtil.remove(u.getImage());
            }

            if (removeImgResult) {
                String imgPath = fastDFSUtil.save(multipartFiles[0], new HashMap<>());
                userDo.setImage(imgPath);
            }else {
                return ResultInfoVO.buildFailInfo("FastDFS Remove Old Image Fail");
            }
        }
        return userService.updateDO(userDo) ? ResultInfoVO.buildSuccessInfo() : ResultInfoVO.buildFailInfo();
    }


    //Others
    /**
     * 重置密码
     * @author  ywx
     * @date    2019/1/28 9:54
     * @param   [id, oldPassword, newPassword]  用户ID
     * @return  com.ymjtt.common.result.ResultInfoVO
     */
    @ResponseBody
    @RequestMapping(value = "/resetPassword")
    public ResultInfoVO resetPassword(Long id, String oldPassword, String newPassword) {
        UserDo userDo = userService.getDO(id);
        String dbPassword = userDo.getPwd();
        byte[] oldBytes = DigestUtils.md5Digest(oldPassword.trim().getBytes(Charset.defaultCharset()));
        if (new String(oldBytes).equals(dbPassword)) {
            userDo.setPwd(DigestUtils.md5DigestAsHex(initPassword.getBytes(Charset.defaultCharset())));
            return userService.updateDO(userDo) ? ResultInfoVO.buildSuccessInfo() : ResultInfoVO.buildFailInfo();
        }
        return ResultInfoVO.buildFailInfo();
    }

    /**
     * 重置为初始密码
     * @author  ywx
     * @date    2019/1/28 9:54
     * @param   [id] 用户ID
     * @return  com.ymjtt.common.result.ResultInfoVO
     */
    @ResponseBody
    @RequestMapping(value = "/initPassword")
    public ResultInfoVO initPassword(Long id) {
        UserDo userDo = new UserDo();
        userDo.setUserId(id);
        userDo.setPwd(DigestUtils.md5DigestAsHex(initPassword.getBytes(Charset.defaultCharset())));
        return userService.updateDO(userDo) ? ResultInfoVO.buildSuccessInfo() : ResultInfoVO.buildFailInfo();
    }

    /**
     * 注销/激活
     * @author  ywx
     * @date    2019/1/29 14:39
     * @param   [id]
     * @return  com.ymjtt.common.result.ResultInfoVO
     */
    @ResponseBody
    @RequestMapping(value = "/cancelOrActive")
    public ResultInfoVO cancelOrActive(Long id, Integer newStatus) {
        UserDo userDo = new UserDo();
        userDo.setUserId(id);
        userDo.setStatus(newStatus);
        return userService.updateDO(userDo) ? ResultInfoVO.buildSuccessInfo() : ResultInfoVO.buildFailInfo();
    }


}
