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
    public static CodeResult SERVER_ERROR = new CodeResult(500, "Server Error");

    /* 后台管理模块共用异常 */
    public static CodeResult BACK_MANAGER_ERROR = new CodeResult(500100, "Back Manager Error");
    public static CodeResult QUERY_DATA_NULL = new CodeResult(500101, "Query Data Is Null");
    public static CodeResult ID_NOT_ALLOW_NULL = new CodeResult(500102, "ID Not Allow Null");
    public static CodeResult REMOVE_PRODUCT_FAIL = new CodeResult(500103, "Remove Product Fail");
    public static CodeResult ADD_PRODUCT_FAIL = new CodeResult(500104, "Add Product Fail");
    public static CodeResult UPDATE_PRODUCT_FAIL = new CodeResult(500105, "Update Product Fail");
    public static CodeResult REMOVE_A_LITTLE_PRODUCT_FAIL = new CodeResult(500105, "Remove A Little Product Fail");

    /* 图片处理异常 */
    public static CodeResult UPDATE_FILE_FAIL = new CodeResult(500901, "Update File Fail");
    public static CodeResult UPDATE_FILE_IS_NULL = new CodeResult(500902, "Update File Not Allow Is Null");
    public static CodeResult REMOVE_FILE_FAIL = new CodeResult(500903, "Remove File Fail");
    public static CodeResult REMOVE_FILE_PATH_IS_NULL = new CodeResult(500904, "Remove FilePath Not Allow Is Null");

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
