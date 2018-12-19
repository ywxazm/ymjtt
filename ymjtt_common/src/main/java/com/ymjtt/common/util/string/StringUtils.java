package com.ymjtt.common.util.string;

/**
 * @auther ywx
 * @date 2018/12/18 21:08
 **/
public class StringUtils {

    /**
     * 补0操作
     * @author  ywx
     * @date    2018/12/19 13:51
     * @param   [s, position]
     * @return  java.lang.String
     */
    public static String makeUpByZero(String s, int position) {
        if (null == s) {
            return s;
        } else if (s.length() >= position) {
            return s;
        }

        int length = s.length();
        StringBuilder sBuilder = new StringBuilder(s);
        for(int i = 1; i < position - length; i++) {
            sBuilder.insert(0, "0");
        }
        s = sBuilder.toString();
        return s;
    }
}
