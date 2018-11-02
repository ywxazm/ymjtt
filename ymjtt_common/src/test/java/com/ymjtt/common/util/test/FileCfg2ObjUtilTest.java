package com.ymjtt.common.util.test;

import com.ymjtt.common.dto.OkHttp3DTO;
import com.ymjtt.common.util.FileCfg2ObjUtil;
import org.junit.Test;

/**
 * @auther ywx
 * @date 2018/11/1 9:23
 **/
public class FileCfg2ObjUtilTest {

    @Test
    public void test01() {
        OkHttp3DTO okHttp3DTO = (OkHttp3DTO)FileCfg2ObjUtil.getObj("okhttp.properties", OkHttp3DTO.class);
        System.out.println(okHttp3DTO);
    }
}
