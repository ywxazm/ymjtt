package com.ymjtt.sso.exception;

import com.ymjtt.common.exception.AbstractSpecialException;

import java.io.Serializable;

/**
 * @auther ywx
 * @date 2019/5/7 17:29
 **/
public class UserException extends AbstractSpecialException implements Serializable {

    public UserException(String msg) {
        super(msg);
    }

}
