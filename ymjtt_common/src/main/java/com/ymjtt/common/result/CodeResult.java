package com.ymjtt.common.result;

/**
 * 可预知的异常
 * @auther ywx
 * @date 2018/12/18 8:46
 **/
public class CodeResult {

    private Integer code;
    private String msg;

    /* 通用 */
    public static CodeResult DEFAULT_FAIL = new CodeResult(500, "Common Fail");
    public static CodeResult DEFAULT_SUCCESS = new CodeResult(200, "Common Success");

    /* 自定义异常 */
    public static CodeResult DB_NOT_CONTAIN_DATA = new CodeResult(6001, "DB Not Contain Data");

    /* CRUD公共部分 */
    /* 成功 */
    public static CodeResult SAVE_SUCCESS = new CodeResult(200, "Save Success");
    public static CodeResult REMOVE_SUCCESS = new CodeResult(200, "Remove Success");
    public static CodeResult UPDATE_SUCCESS = new CodeResult(200, "Update Success");
    public static CodeResult GET_SUCCESS = new CodeResult(200, "Get Success");
    public static CodeResult LIST_SUCCESS = new CodeResult(200, "List Success");
    /* 失败 */
    public static CodeResult SAVE_FAIL = new CodeResult(500001, "Save Fail");
    public static CodeResult REMOVE_FAIL = new CodeResult(500002, "Remove Fail");
    public static CodeResult UPDATE_FAIL = new CodeResult(500003, "Update Fail");
    public static CodeResult GET_FAIL = new CodeResult(500004, "Get Fail");
    public static CodeResult LIST_FAIL = new CodeResult(500005, "List Fail");

    /* 商品部分 */

    /* 图片处理异常 */
    public static CodeResult UPDATE_FILE_SUCCESS = new CodeResult(200, "Update File Success");
    public static CodeResult UPDATE_FILE_FAIL = new CodeResult(500901, "Update File Fail");
    public static CodeResult UPDATE_FILE_IS_NULL = new CodeResult(500902, "Update File Not Allow Is Null");
    public static CodeResult REMOVE_FILE_SUCCESS = new CodeResult(200, "Remove File Success");
    public static CodeResult REMOVE_FILE_FAIL = new CodeResult(500903, "Remove File Fail");
    public static CodeResult REMOVE_FILE_PATH_IS_NULL = new CodeResult(500904, "Remove FilePath Not Allow Is Null");

    /* 用户部分 */
    public static CodeResult USERNAME_OR_PASSWORD_ERROR = new CodeResult(500201, "UserName Or Password Error");

    /* cms */

    public CodeResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
