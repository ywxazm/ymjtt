package com.ymjtt.sso.service;

/**
 * @auther ywx
 * @date 2019/5/6 16:45
 **/
public interface AbstractForgetPwdService {

    boolean sendPwdMail(String userNick);

}
