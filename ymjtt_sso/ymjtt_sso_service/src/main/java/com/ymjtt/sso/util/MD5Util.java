package com.ymjtt.sso.util;

import org.springframework.util.DigestUtils;

/**
 * @auther ywx
 * @date 2019/5/6 14:58
 **/
public class MD5Util {

    public static String generatePwd(String salt, String oldPwd) {
        return DigestUtils.md5DigestAsHex((salt + oldPwd).getBytes());
    }

    public static String generatePwd(Long salt, String oldPwd) {
        return generatePwd(salt.toString(), oldPwd);
    }
}
