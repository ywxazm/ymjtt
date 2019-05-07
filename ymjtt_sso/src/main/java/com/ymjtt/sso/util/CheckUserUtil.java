package com.ymjtt.sso.util;

/**
 * @auther ywx
 * @date 2019/5/6 10:06
 **/
public class CheckUserUtil {

    private static final String phoneRegex = "1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}";
    private static final String emailRegex = "[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /**
     * 判断用户别名的类别
     * @author  ywx
     * @date    2019/5/5 15:31
     * @param   [userNick]
     * @return  com.ymjtt.sso.domain.UserDo   邮件/电话/用户名
     */
    private int getUserDo(String userNick) {
        userNick = userNick.trim();
        if (userNick.matches(emailRegex)) {
            return 1;
        } else if (userNick.matches(phoneRegex)) {
            return 2;
        }else {
            return 3;
        }
    }
}
