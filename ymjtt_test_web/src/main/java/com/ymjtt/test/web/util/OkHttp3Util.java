package com.ymjtt.test.web.util;

import com.ymjtt.common.util.consts.CommonConsts;
import com.ymjtt.common.util.json.JSONConvertUtil;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.InitBinder;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author ywx
 * @date 2018/11/5 9:57
 * 提供基于Http协议的, 同步/异步远程调用
 */
@Component
public class OkHttp3Util {

    /**
     * 连接属性
     */
    @Value("${connectTimeout}")
    private static Long connectTimeout;
    @Value("${readTimeout}")
    private static Long readTimeout;
    @Value("${writeTimeout}")
    private static Long writeTimeout;
    @Value("${retryCount}")
    private static Long retryCount;

    /**
     * post请求方式
     */
    @Value("${requestTypeDefault}")
    private static String requestTypeDefault;

    @Value("${requestTypeGet}")
    private static String requestTypeGet;

    /**
     * contentType
     */
    @Value(value = "${contentTypeDefault}")
    private static String contentTypeDefault;
    @Value("${contentTypeJson}")
    private static String contentTypeJson;
    @Value("${contentTypeMulti}")
    private static String contentTypeMulti;
    @Value("${contentTypeText}")
    private static String contentTypeText;
    @Value("${contentTypeStream}")
    private static String contentTypeStream;       //以流的形式传输

    private static OkHttpClient client;

    private OkHttp3Util() {
    }

    /**
     * 根据配置文件,第一次使用时初始化cfgMap
     */
    @InitBinder
    private static void init() throws IOException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        client = builder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
                .build();
    }

    /**
     * 同步方式
     *
     * @param url
     * @param paramJson
     * @param requestType
     * @return
     */
    public static Response syn(String url, String paramJson, String requestType, String contentType, String[] filePaths)
            throws IOException {
        assert(url != null):("okhttp3 url must not null");
        assert(requestType != null):("okhttp3 requestType must not null");
        assert(contentType != null):("okhttp3 contentType must not null");

        Request request = buildRequest(url, paramJson, requestType, contentType, filePaths);

        //调用
        Call call = client.newCall(request);
        return call.execute();
    }

    public static Response getSyn(String url) throws IOException {
        return syn(url, null, requestTypeGet, null, null);
    }

    public static Response postSyn(String url, String paramJson) throws IOException {
        return syn(url, paramJson, requestTypeDefault, contentTypeJson, null);
    }

    public static Response postSyn(String url, String paramJson, String contextType) throws IOException {
        return syn(url, paramJson, requestTypeDefault, contextType, null);
    }

    public static Response postSyn(String url, String paramJson, String[] filePaths) throws IOException {
        return syn(url, paramJson, requestTypeDefault, contentTypeMulti, filePaths);
    }

    public static Response postSyn(String url, String[] filePaths) throws IOException {
        return syn(url, null, requestTypeDefault, contentTypeMulti, filePaths);
    }

    /**
     * 异步方式
     *
     * @param url
     * @param paramJson
     * @param okHttp3Callback
     * @param requestType
     * @param contentType
     */
    private static void asyn(String url, String paramJson, String requestType, String contentType, String[] filePaths
            , final OkHttp3Callback okHttp3Callback) throws InterruptedException, IOException {

        assert(url != null):("okhttp3 url must not null");
        assert(requestType != null):("okhttp3 requestType must not null");
        assert(contentType != null):("okhttp3 contentType must not null");
        assert(okHttp3Callback != null):("okhttp3 okHttp3Callback must not null");

        Request request = buildRequest(url, paramJson, requestType, contentType, filePaths);

        //调用
        Call call = client.newCall(request);
        int threadNumber = 1;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNumber); //防止主线程结束, 其它线程全挂
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException ex) {
                okHttp3Callback.failed(call, ex);
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                okHttp3Callback.success(call, response);
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }

    public static void getAsyn(String url, final OkHttp3Callback okHttp3Callback)
            throws InterruptedException, IOException {
        asyn(url, null, requestTypeGet, null, null, okHttp3Callback);
    }

    public static void postAsyn(String url, String paramJson, final OkHttp3Callback okHttp3Callback)
            throws InterruptedException, IOException {
        asyn(url, paramJson, requestTypeDefault, contentTypeJson, null, okHttp3Callback);
    }

    public static void postAsyn(String url, String paramJson, String contextType, final OkHttp3Callback okHttp3Callback)
            throws InterruptedException, IOException {
        asyn(url, paramJson, requestTypeDefault, contextType, null, okHttp3Callback);
    }

    public static void postAsyn(String url, String paramJson, String[] filePaths, final OkHttp3Callback okHttp3Callback)
            throws InterruptedException, IOException {
        asyn(url, paramJson, requestTypeDefault, contentTypeMulti, filePaths, okHttp3Callback);
    }

    public static void postAsyn(String url, String[] filePaths, final OkHttp3Callback okHttp3Callback)
            throws InterruptedException, IOException {
        asyn(url, null, requestTypeDefault, contentTypeMulti, filePaths, okHttp3Callback);
    }

    /**
     * 构建Request
     * @param url
     * @param paramJson
     * @param requestType
     * @param contentType
     * @param filePaths
     * @return
     */
    private static Request buildRequest(String url, String paramJson, String requestType, String contentType
            , String[] filePaths) throws IOException {

        //构造RequestBody
        RequestBody requestBody;
        switch (contentType) {
            case "application/x-www-form-urlencoded;charset=utf-8" : requestBody
                    = buildRequestBodyOfDefault(paramJson); break;
            case "application/json;charset=utf-8" : requestBody
                    = buildRequestBodyOfJson(paramJson); break;
            case "multipart/form-data;charset=utf-8" : requestBody
                    = buildRequestBodyOfMulti(paramJson, filePaths); break;
            case "text/xml;charset=utf-8" : requestBody
                    = buildRequestBodyOfText(); break;
            default: throw new IllegalArgumentException("no support content_type is" + contentType);
        }

        //构造Request
        Request.Builder requestBuilder = new Request.Builder();
        switch (requestType) {
            case "post":
                return requestBuilder.post(requestBody).url(url).build();
            case "get":
                return requestBuilder.get().url(url).build();
            default:
                return  requestBuilder.post(requestBody).url(url).build();
        }
    }


    /**
     *  构建"application/x-www-form-urlencoded"
     * @param paramJson
     * @return RequestBody
     */
    private static RequestBody buildRequestBodyOfDefault(String paramJson) throws IOException {
        if (null == paramJson) {
            return null;
        }

        Map<String, String> paramMap = JSONConvertUtil.json2map(paramJson);
        StringBuilder paramStr = new StringBuilder();
        for (String key : paramMap.keySet()) {
            paramStr.append(key).append(CommonConsts.EQUALS_STR).append(paramMap.get(key)).append(CommonConsts.AND_STR);
        }
        return RequestBody.create(MediaType.parse(requestTypeDefault), paramStr.substring(0, paramStr.length() - 1));
    }

    /**
     * 构建"application/json"
     * @param paramJson
     * @return RequestBody
     */
    private static RequestBody buildRequestBodyOfJson(String paramJson) {
        if (null == paramJson) {
            return null;
        }
        return RequestBody.create(MediaType.parse(contentTypeJson), paramJson);
    }

    /**
     * 构建 "multipart/form-data"
     * @param paramJson
     * @param filePaths
     * @return
     */
    private static RequestBody buildRequestBodyOfMulti(String paramJson, String[] filePaths) throws IOException {
        assert(filePaths != null && filePaths.length != 0):("okhttp3 filePaths must not null");

        String appointFileName = "uploadFiles";      //与springmvc接收参数一致
        MultipartBody.Builder builder = new MultipartBody.Builder();
        //添加参数
        Map<String, String> paramMap = JSONConvertUtil.json2map(paramJson);
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            builder.addFormDataPart(entry.getKey(),entry.getValue());
        }

        for (String filePath : filePaths) {
            File file = new File(filePath);
            if(file.exists()) {
                RequestBody fileBody = RequestBody.create(MediaType.parse(contentTypeStream), file);
                builder.setType(MultipartBody.FORM).addFormDataPart(appointFileName, file.getName(), fileBody);
            }
        }

        return builder.build();
    }

    /**
     * 构建 "text/xml"
     * @return
     */
    private static RequestBody buildRequestBodyOfText() {
        return null;
    }


    /**
     * @auther ywx
     * @date 2018/11/1 16:09
     **/
    public interface OkHttp3Callback {
        void failed(Call call, IOException e);
        void success(Call call, Response response) throws IOException;
    }
}
