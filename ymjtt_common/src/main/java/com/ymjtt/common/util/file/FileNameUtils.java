package com.ymjtt.common.util.file;

import com.ymjtt.common.util.consts.CommonConsts;
import com.ymjtt.common.util.date.DateUtils;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @auther ywx
 * @date 2018/11/14 9:55
 * @info 文件名称 工具
 **/
public class FileNameUtils {

    /**
     * 生成 文件名 = 当前时间 + 2位随机数 + 扩展名
     * @param extensionName
     * @return
     */
    public static String createFileName(String extensionName) {
        int num = new Random().nextInt(99);
        LocalDateTime localDateTime = DateUtils.getCurrentDateTime();
        if (num < 10) {
            return  DateUtils.LocalDateTime2String(localDateTime, DateUtils.TIME_NOFUll_FORMAT)
                    + CommonConsts.ZERO_STR + num + CommonConsts.POINT_STR + extensionName;
        }
        return DateUtils.LocalDateTime2String(localDateTime, DateUtils.TIME_NOFUll_FORMAT) + num
                + CommonConsts.POINT_STR + extensionName;
    }

    /**
     * 获取文件扩展名
     * @param fileName
     * @return
     */
    public static String getExtensionName(String fileName) {
        return fileName.substring(fileName.lastIndexOf(CommonConsts.POINT_STR) + 1);
    }
}
