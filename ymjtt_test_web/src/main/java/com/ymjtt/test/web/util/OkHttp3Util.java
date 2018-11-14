package com.ymjtt.test.web.util;

import com.ymjtt.common.util.consts.CommonConsts;
import com.ymjtt.common.util.json.JSONConvertUtil;
import com.ymjtt.common.util.file.PropertiesCfg2ObjUtil;
import okhttp3.*;

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
public class OkHttp3Util {

    /**
     * post请求方式
     */
    private static final String REQUEST_TYPE_DEFAULT = "POST";
    private static final String REQUEST_TYPE_GET = "GET";

    /**
     * contextType
     */
    private static final String CONTEXT_TYPE_DEFAULT = "application/x-www-form-urlencoded;charset=utf-8";
    private static final String CONTEXT_TYPE_JSON = "application/json;charset=utf-8";
    private static final String CONTEXT_TYPE_MULTI = "multipart/form-data;charset=utf-8";
    private static final String CONTEXT_TYPE_TEXT = "text/xml;charset=utf-8";
    private static final String CONTEXT_TYPE_STREAM = "application/octet-stream";       //以流的形式传输

    /**
     * http连接属性配置文件
     */
    private static final String FILENAME = "util/okhttp.properties";

    /**
     * 配置文件加载
     */
    private static Map<String, String> cfgMap;

    /**
     * 为每一条线程维护一个client
     */
    private static ThreadLocal<OkHttpClient> client = new ThreadLocal<>();

    private OkHttp3Util() {
    }

    /**
     * 根据配置文件,第一次使用时初始化cfgMap
     */
    private static void init() throws IOException {
        if (null == cfgMap || cfgMap.size() == 0) {
            String connectTimeout = "connectTimeout";       //从连接池中获取可用连接超时
            String readTimeout = "readTimeout";             //连接目标超时
            String writeTimeout = "writeTimeout";           //等待响应超时（读取数据超时）

            cfgMap = PropertiesCfg2ObjUtil.getMapFromPropertiesFile(FILENAME);

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            client.set(builder.connectTimeout(Long.parseLong(cfgMap.get(connectTimeout)), TimeUnit.MILLISECONDS)
                    .readTimeout(Long.parseLong(cfgMap.get(readTimeout)), TimeUnit.MILLISECONDS)
                    .writeTimeout(Long.parseLong(cfgMap.get(writeTimeout)), TimeUnit.MILLISECONDS)
                    .build());
        }
    }

    /**
     * 同步方式
     *
     * @param url
     * @param paramJson
     * @param requestType
     * @return
     */
    public static Response syn(String url, String paramJson, String requestType, String contentType, String[] filePaths) throws IOException {
        assert(url != null):("okhttp3 url must not null");
        assert(requestType != null):("okhttp3 requestType must not null");
        assert(contentType != null):("okhttp3 contentType must not null");

        Request request = buildRequest(url, paramJson, requestType, contentType, filePaths);

        //调用
        Call call = client.get().newCall(request);
        return call.execute();
    }

    public static Response getSyn(String url) throws IOException {
        return syn(url, null, REQUEST_TYPE_GET, CONTEXT_TYPE_DEFAULT, null);
    }

    public static Response postSyn(String url, String paramJson) throws IOException {
        return syn(url, paramJson, REQUEST_TYPE_DEFAULT, CONTEXT_TYPE_JSON, null);
    }

    public static Response postSyn(String url, String paramJson, String contextType) throws IOException {
        return syn(url, paramJson, REQUEST_TYPE_DEFAULT, contextType, null);
    }

    public static Response postSyn(String url, String paramJson, String[] filePaths) throws IOException {
        return syn(url, paramJson, REQUEST_TYPE_DEFAULT, CONTEXT_TYPE_MULTI, filePaths);
    }

    public static Response postSyn(String url, String[] filePaths) throws IOException {
        return syn(url, null, REQUEST_TYPE_DEFAULT, CONTEXT_TYPE_MULTI, filePaths);
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
    private static void asyn(String url, String paramJson, String requestType, String contentType, String[] filePaths, final OkHttp3Callback okHttp3Callback) throws InterruptedException, IOException {
        assert(url != null):("okhttp3 url must not null");
        assert(requestType != null):("okhttp3 requestType must not null");
        assert(contentType != null):("okhttp3 contentType must not null");
        assert(okHttp3Callback != null):("okhttp3 okHttp3Callback must not null");

        Request request = buildRequest(url, paramJson, requestType, contentType, filePaths);

        //调用
        Call call = client.get().newCall(request);
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

    public static void getAsyn(String url, final OkHttp3Callback okHttp3Callback) throws InterruptedException, IOException {
        asyn(url, null, REQUEST_TYPE_GET, null, null, okHttp3Callback);
    }

    public static void postAsyn(String url, String paramJson, final OkHttp3Callback okHttp3Callback) throws InterruptedException, IOException {
        asyn(url, paramJson, REQUEST_TYPE_DEFAULT, CONTEXT_TYPE_JSON, null, okHttp3Callback);
    }

    public static void postAsyn(String url, String paramJson, String contextType, final OkHttp3Callback okHttp3Callback) throws InterruptedException, IOException {
        asyn(url, paramJson, REQUEST_TYPE_DEFAULT, contextType, null, okHttp3Callback);
    }

    public static void postAsyn(String url, String paramJson, String[] filePaths, final OkHttp3Callback okHttp3Callback) throws InterruptedException, IOException {
        asyn(url, paramJson, REQUEST_TYPE_DEFAULT, CONTEXT_TYPE_MULTI, filePaths, okHttp3Callback);
    }

    public static void postAsyn(String url, String[] filePaths, final OkHttp3Callback okHttp3Callback) throws InterruptedException, IOException {
        asyn(url, null, REQUEST_TYPE_DEFAULT, CONTEXT_TYPE_MULTI, filePaths, okHttp3Callback);
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
    private static Request buildRequest(String url, String paramJson, String requestType, String contentType, String[] filePaths) throws IOException {
        //初始化client
        init();

        //构造RequestBody
        RequestBody requestBody;
        switch (contentType) {
            case CONTEXT_TYPE_DEFAULT : requestBody = buildRequestBodyOfDefault(url, paramJson); break;
            case CONTEXT_TYPE_JSON : requestBody = buildRequestBodyOfJson(url, paramJson); break;
            case CONTEXT_TYPE_MULTI : requestBody = buildRequestBodyOfMulti(url, paramJson, filePaths); break;
            case CONTEXT_TYPE_TEXT : requestBody = buildRequestBodyOfText(); break;
            default: throw new IllegalArgumentException("no support content_type is" + contentType);
        }

        //构造Request
        Request.Builder requestBuilder = new Request.Builder();
        switch (requestType) {
            case REQUEST_TYPE_DEFAULT:
                return requestBuilder.post(requestBody).url(url).build();
            case REQUEST_TYPE_GET:
                return requestBuilder.get().url(url).build();
            default:
                return  requestBuilder.post(requestBody).url(url).build();
        }
    }


    /**
     *  构建"application/x-www-form-urlencoded"
     * @param url
     * @param paramJson
     * @return RequestBody
     */
    private static RequestBody buildRequestBodyOfDefault(String url, String paramJson) throws IOException {
        if (null == paramJson) {
            return null;
        }

        Map<String, String> paramMap = JSONConvertUtil.json2map(paramJson);
        StringBuilder paramStr = new StringBuilder();
        for (String key : paramMap.keySet()) {
            paramStr.append(key).append(CommonConsts.EQUALS_STR).append(paramMap.get(key)).append(CommonConsts.AND_STR);
        }
        return RequestBody.create(MediaType.parse(CONTEXT_TYPE_DEFAULT), paramStr.substring(0, paramStr.length() - 1));
    }

    /**
     * 构建"application/json"
     * @param url
     * @param paramJson
     * @return RequestBody
     */
    private static RequestBody buildRequestBodyOfJson(String url, String paramJson) {
        if (null == paramJson) {
            return null;
        }
        return RequestBody.create(MediaType.parse(CONTEXT_TYPE_JSON), paramJson);
    }

    /**
     * 构建 "multipart/form-data"
     * @param url
     * @param paramJson
     * @param filePaths
     * @return
     */
    private static RequestBody buildRequestBodyOfMulti(String url, String paramJson, String[] filePaths) throws IOException {
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
                RequestBody fileBody = RequestBody.create(MediaType.parse(CONTEXT_TYPE_STREAM), file);
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
