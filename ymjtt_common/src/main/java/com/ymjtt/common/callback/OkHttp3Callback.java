package com.ymjtt.common.callback;

import okhttp3.Call;
import okhttp3.Response;

import java.io.IOException;

/**
 * @auther ywx
 * @date 2018/11/1 16:09
 **/
public interface OkHttp3Callback {

    void failed(Call call, IOException e);
    void success(Call call, Response response) throws IOException;

}
