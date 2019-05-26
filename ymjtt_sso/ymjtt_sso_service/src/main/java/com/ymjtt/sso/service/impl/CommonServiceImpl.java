package com.ymjtt.sso.service.impl;

import com.ymjtt.sso.service.CommonService;
import org.springframework.stereotype.Service;


/**
 * @auther ywx
 * @date 2019/5/16 15:31
 **/
@Service
public class CommonServiceImpl implements CommonService {

    /** 电话正则 */
    private static final String emailRule = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
    /** 邮箱正则 */
    private static final String phoneRule = "1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}";

    /**
     * 区别字符串是电话/邮箱/其它
     * @author  ywx
     * @date    2019/5/16 15:34
     * @param   [userNick]
     * @return  int   0: 电话    1: 邮箱    2:其它
     */
    @Override
    public int aliasType(String userNick) {
        if (userNick.matches(phoneRule)) {
            return 0;
        }else if (userNick.matches(emailRule)) {
            return 1;
        }
        return 2;
    }
}
