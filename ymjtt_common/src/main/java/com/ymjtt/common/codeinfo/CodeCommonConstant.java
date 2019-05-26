package com.ymjtt.common.codeinfo;

/**
 * @auther ywx
 * @date 2019/5/9 16:47
 **/
public class CodeCommonConstant {

    public static final Integer COMMON_FAIL = 50000;
    public static final Integer COMMON_SUCCESS = 20000;

    public static CodeInfo FAIL = new CodeInfo(COMMON_FAIL, "Fail");
    public static CodeInfo SUCCESS = new CodeInfo(COMMON_SUCCESS, "Success");

    public static CodeInfo SAVE_FAIL = new CodeInfo(50001, "Save Fail");
    public static CodeInfo REMOVE_FAIL = new CodeInfo(50002, "Remove Fail");
    public static CodeInfo UPDATE_FAIL = new CodeInfo(5003, "Update Fail");
    public static CodeInfo GET_FAIL = new CodeInfo(50004, "Get Fail");
    public static CodeInfo LIST_FAIL = new CodeInfo(50005, "List Fail");

}
