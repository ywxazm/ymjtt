package com.ymjtt.common.okhttp3;

import com.ymjtt.common.consts.CommonConsts;
import com.ymjtt.common.util.JSONConvertUtil;
import com.ymjtt.common.util.PropertiesCfg2ObjUtil;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(OkHttp3Util.class);

    /**
     * post请求方式
     */
    private static final String REQUEST_TYPE_GET = "GET";
    private static final String REQUEST_TYPE_POST = "POST";

    /**
     * contextType
     */
    private static final String CONTEXT_TYPE_DEFAULT = "application/x-www-form-urlencoded;charset=utf-8";
    private static final String CONTEXT_TYPE_JSON = "application/json;charset=utf-8";
    private static final String CONTEXT_TYPE_MULTI = "multipart/form-data;charset=utf-8";
    private static final String CONTEXT_TYPE_TEXT = "text/xml;charset=utf-8";

    /**
     * http连接属性配置文件
     */
    private static final String FILENAME = "okhttp.properties";

    /**
     * 操作对象
     */
    private static OkHttpClient client;

    private OkHttp3Util() {
    }

    /**
     * 根据配置文件,初始化client对象
     */
    private static void init() {
        OkHttp3DTO okHttp3DTO = (OkHttp3DTO) PropertiesCfg2ObjUtil.getObjFromPropertiesFile(FILENAME, OkHttp3DTO.class);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        client = builder.connectTimeout(okHttp3DTO.getConnectTimeout(), TimeUnit.MILLISECONDS).readTimeout(okHttp3DTO.getReadTimeout(), TimeUnit.MILLISECONDS).writeTimeout(okHttp3DTO.getWriteTimeout(), TimeUnit.MILLISECONDS).build();
    }

    /**
     * 同步方式
     *
     * @param url
     * @param paramJson
     * @param requestType
     * @return
     */
    private static Response syn(String url, String paramJson, String requestType, String contentType) {
        //构造Request
        Request request = buildRequest(url, paramJson, requestType, contentType);
        //调用
        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException ex) {
            logger.error("the thread {}, catch call error {}", Thread.currentThread().getName(), ex.getMessage());
        }
        return response;
    }

    /**
     * 同步方式  get请求  url中可有参,也可无参数, okhttp3框架内以utf8进行编码
     *
     * @param url url
     * @return Response
     */
    public static Response getSyn(String url) {
        return syn(url, null, REQUEST_TYPE_GET, null);
    }

    /**
     * 同步方式  post请求  无参
     *
     * @param url url
     * @return Response
     */
    public static Response postSyn(String url) {
        return syn(url, null, REQUEST_TYPE_POST, CONTEXT_TYPE_DEFAULT);
    }
    public static Response postSynAppointContextType(String url, String contentType) {
        return syn(url, null, REQUEST_TYPE_POST, contentType);
    }

    /**
     * 同步方式  post请求 json参数
     *
     * @param url       url
     * @param paramJson Json参数
     * @return Response
     */
    public static Response postSynAppointContextType(String url, String paramJson, String contentType) {
        return syn(url, paramJson, REQUEST_TYPE_POST, contentType);
    }
    public static Response postSyn(String url, String paramJson) {
        return syn(url, paramJson, REQUEST_TYPE_POST, CONTEXT_TYPE_DEFAULT);
    }

    /**
     * 同步方式  post请求 Map参数
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static Response postSynAppointContextType(String url, Map<String, String> paramMap, String contentType) {
        return syn(url, JSONConvertUtil.map2Json(paramMap), REQUEST_TYPE_POST, contentType);
    }
    public static Response postSyn(String url, Map<String, String> paramMap) {
        return syn(url, JSONConvertUtil.map2Json(paramMap), REQUEST_TYPE_POST, CONTEXT_TYPE_DEFAULT);
    }


    /**
     * 异步方式
     * @param url
     * @param paramJson
     * @param okHttp3Callback
     * @param requestType
     * @param contentType
     */
    private static void asyn(String url, String paramJson, final OkHttp3Callback okHttp3Callback, String requestType, String contentType) {
        //构造Request
        Request request = buildRequest(url, paramJson, requestType, contentType);
        //调用
        Call call = client.newCall(request);
        int threadNumber = 1;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNumber); //防止主线程结束, 其它线程全挂
        try {
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException ex) {
                    logger.error("Thread id:{}, postAsyn error, url: {}, msg [{}]", Thread.currentThread().getId(), call.request().url(), ex.getMessage());
                    okHttp3Callback.failed(call, ex);
                    countDownLatch.countDown();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    logger.info("Thread id: {},  postAsyn success, url: {}", Thread.currentThread().getId(), call.request().url());
                    okHttp3Callback.success(call, response);
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
        } catch (InterruptedException ex) {
            logger.error("Thread id:{}, error msg: {}", Thread.currentThread().getId(), ex.getMessage());
        }
    }

    /**
     * 异步方式  get请求  无参
     *
     * @param url
     * @param okHttp3Callback
     */
    public static void getAsynAppointContextType(String url, String contentType, final OkHttp3Callback okHttp3Callback) {
        asyn(url, null, okHttp3Callback, REQUEST_TYPE_GET, contentType);
    }
    public static void getAsyn(String url, final OkHttp3Callback okHttp3Callback) {
        asyn(url, null, okHttp3Callback, REQUEST_TYPE_GET, CONTEXT_TYPE_DEFAULT);
    }

    /**
     * 异步方式  post请求  map参数
     *
     * @param url
     * @param paramMap
     * @param okHttp3Callback
     */
    public static void postAsynAppointContextType(String url, Map<String, String> paramMap, String contentType, final OkHttp3Callback okHttp3Callback) {
        asyn(url, JSONConvertUtil.map2Json(paramMap), okHttp3Callback, REQUEST_TYPE_POST, contentType);
    }
    public static void postAsyn(String url, Map<String, String> paramMap, final OkHttp3Callback okHttp3Callback) {
        asyn(url, JSONConvertUtil.map2Json(paramMap), okHttp3Callback, REQUEST_TYPE_POST, CONTEXT_TYPE_DEFAULT);
    }

    /**
     * 异步方式  post请求  json参数
     *
     * @param url
     * @param jsonParam
     * @param okHttp3Callback
     */
    public static void postAsynAppointContextType(String url, String jsonParam, String contentType, final OkHttp3Callback okHttp3Callback) {
        asyn(url, jsonParam, okHttp3Callback, REQUEST_TYPE_POST, contentType);
    }
    public static void postAsyn(String url, String jsonParam, final OkHttp3Callback okHttp3Callback) {
        asyn(url, jsonParam, okHttp3Callback, REQUEST_TYPE_POST, CONTEXT_TYPE_DEFAULT);
    }


    /**
     * 构建Request
     *
     * @param url
     * @param paramJson
     * @param requestType
     * @return
     */
    private static Request buildRequest(String url, String paramJson, String requestType, String contentType) {
        //初始化
        init();
        //将参数设置到RequestBody
        RequestBody body;
        if (null != paramJson) {
            body = buildRequestBody(paramJson, contentType);
        } else {
            okhttp3.FormBody.Builder formEncodingBuilder = new okhttp3.FormBody.Builder();
            body = formEncodingBuilder.build();
        }

        //请求方式get/post
        Request.Builder requestBuilder = new Request.Builder();
        Request request;
        switch (requestType) {
            case REQUEST_TYPE_POST:
                request = requestBuilder.post(body).url(url).build();
                break;
            case REQUEST_TYPE_GET:
                request = requestBuilder.get().url(url).build();
                break;
            default:
                request = requestBuilder.post(body).url(url).build();
                break;
        }
        return request;
    }

    /**
     * json请求参数，构造RequestBody
     *
     * @param paramJson
     * @return
     */
    private static RequestBody buildRequestBody(String paramJson, String contentType) {
        if (null == contentType) {
            throw new IllegalArgumentException("paramJson must not null");
        }

        switch (contentType) {
            case CONTEXT_TYPE_JSON : return FormBody.create(MediaType.parse(contentType), paramJson);
            case CONTEXT_TYPE_MULTI : return FormBody.create(MediaType.parse(contentType), paramJson);
            case CONTEXT_TYPE_TEXT : return FormBody.create(MediaType.parse(contentType), paramJson);
            case CONTEXT_TYPE_DEFAULT : return map2RequestBody(JSONConvertUtil.json2map(paramJson), contentType);
            default:throw new IllegalArgumentException("find not contentType argument");
        }
    }

    /**
     * json参数转Map, 再返回RequestBody
     * @param paramMap
     * @param contentType
     * @return
     */
    private static RequestBody map2RequestBody(Map<String, String> paramMap, String contentType) {
        StringBuilder paramStr = new StringBuilder();
        for (String key : paramMap.keySet()) {
            paramStr.append(key).append(CommonConsts.EQUALS_STR).append(paramMap.get(key)).append(CommonConsts.AND_STR);
        }
        //create()中,如果contentType不带字符集合,默认对参数进行utf8编码
        return FormBody.create(MediaType.parse(contentType), paramStr.substring(0, paramStr.length() - 1));
    }
}
