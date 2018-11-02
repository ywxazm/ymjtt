package com.ymjtt.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymjtt.common.callback.OkHttp3Callback;
import com.ymjtt.common.consts.CommonConsts;
import com.ymjtt.common.dto.OkHttp3DTO;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class OkHttp3Util {

    private static final Logger logger = LoggerFactory.getLogger(OkHttp3Util.class);

    private static final String REQUEST_TYPE_POST = "POST";
    private static final String REQUEST_TYPE_GET = "GET";
    private static final String fileName = "okhttp.properties";
    private static int retryCount;
    private static int bytesSize;
    private static OkHttpClient client;

    public OkHttp3Util() {

        OkHttp3DTO okHttp3DTO = (OkHttp3DTO) FileCfg2ObjUtil.getObj(fileName, OkHttp3DTO.class);
        retryCount = okHttp3DTO.getRetryCount();
        bytesSize = okHttp3DTO.getBytesSize();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        client = builder.connectTimeout(okHttp3DTO.getConnectTimeout(), TimeUnit.MILLISECONDS).readTimeout(okHttp3DTO.getReadTimeout(), TimeUnit.MILLISECONDS).writeTimeout(okHttp3DTO.getWriteTimeout(), TimeUnit.MILLISECONDS).build();
    }


    /**
     * get请求，同步方式，获取网络数据
     *
     * @param url url
     * @return Response
     */
    public static Response getSyn(String url) {
        //1 构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        //2 将Request封装为Call
        Call call = client.newCall(request);
        //3 执行Call，得到response
        Response response = null;
        while (retryCount > 0) {
            try {
                response = call.execute();
                if (response.isSuccessful()){
                    retryCount--;
                }
            } catch (IOException ex) {
                logger.error("the thread {}, catch error {}", Thread.currentThread().getName(), ex.getMessage());
            }
        }
        return response;
    }


    /**
     * post请求，同步方式，提交数据
     *
     * @param url      url
     * @param paramMap Map参数
     * @return Response
     */
    public static Response postSyn(String url, Map<String, String> paramMap) {
        //1构造RequestBody
        RequestBody body;
        if (null != paramMap && paramMap.size() > 0) {
            body = setRequestBody(paramMap);
        } else {
            okhttp3.FormBody.Builder formEncodingBuilder = new okhttp3.FormBody.Builder();
            body = formEncodingBuilder.build();
        }
        //2 构造Request
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.post(body).url(url).build();
        //3 将Request封装为Call
        Call call = client.newCall(request);
        //4 执行Call，得到response
        Response response = null;
        while (retryCount > 0) {
            try {
                response = call.execute();
                if (response.isSuccessful()){
                    retryCount--;
                }
            } catch (IOException ex) {
                logger.error("the thread {}, catch error {}", Thread.currentThread().getName(), ex.getMessage());
            }
        }
        return response;
    }

    /**
     * post请求，同步方式，提交数据
     *
     * @param url       url
     * @param paramJson Json参数
     * @return Response
     */
    public static Response postSyn(String url, String paramJson) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> paramMap = new HashMap<>();
        try {
            paramMap = mapper.readValue(paramJson, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return postSyn(url, paramMap);
    }

    /**
     * post请求，同步方式，提交数据
     *
     * @param url url
     * @return Response
     */
    public static Response postSyn(String url) {
        return postSyn(url, new HashMap<>());
    }


    /**
     * get请求，异步方式, 无参
     *
     * @param url
     * @param okHttp3Callback
     * @return
     */
    public static void getAsyn(String url, final OkHttp3Callback okHttp3Callback) {
        Map<String, String> paramMap = new HashMap<>();
        if (url.contains(CommonConsts.QUESTION_MARK_STR)) {
            String params = url.substring(url.indexOf(CommonConsts.QUESTION_MARK_STR) + 1);
            String[] paramArray = params.split(CommonConsts.AND_STR);
            for (String keyAndValue : paramArray) {
                paramMap.put(keyAndValue.substring(0, keyAndValue.indexOf(CommonConsts.EQUALS_STR)), keyAndValue.substring(keyAndValue.indexOf(CommonConsts.EQUALS_STR) + 1));
            }
        }
        postAsyn(url, paramMap, okHttp3Callback, REQUEST_TYPE_GET);
    }

    /**
     * post请求，异步方式, Map入参
     *
     * @param url             utl
     * @param paramMap        Map参数
     * @param okHttp3Callback 回调接口
     */
    public static void postAsyn(String url, Map<String, String> paramMap, final OkHttp3Callback okHttp3Callback, String requestType) {
        //1构造RequestBody
        RequestBody requestBody = setRequestBody(paramMap);
        //2 构造Request
        Request.Builder requestBuilder = new Request.Builder();
        Request request;
        switch (requestType) {
            case REQUEST_TYPE_POST:
                request = requestBuilder.post(requestBody).url(url).build();
                break;
            case REQUEST_TYPE_GET:
                request = requestBuilder.get().url(url).build();
                break;
            default:
                request = requestBuilder.post(requestBody).url(url).build();
                break;
        }

        //3 将Request封装为Call
        Call call = client.newCall(request);

        int threadNumber = 1;
        final CountDownLatch countDownLatch = new CountDownLatch(threadNumber); //防止主线程结束, 其它线程全挂
        try {
            //4 执行Call
            while (retryCount > 0) {
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException ex) {
                        logger.error("Thread id:{}, postAsyn error, url: {}, msg [{}]", Thread.currentThread().getId(), call.request().url(), ex.getMessage());
                        okHttp3Callback.failed(call, ex);
                        //此处可添加哪此异常需要重试
                        retryCount = 0;
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        logger.info("Thread id: {},  postAsyn success, url: {}", Thread.currentThread().getId(), call.request().url());
                        okHttp3Callback.success(call, response);
                        if (response.isSuccessful()){
                            retryCount--;
                        }
                        countDownLatch.countDown();
                    }
                });
            }
            countDownLatch.await();
        } catch (InterruptedException ex) {
            logger.error("Thread id:{}, error msg: {}", Thread.currentThread().getId(), ex.getMessage());
        }
    }

    public static void postAsyn(String url, Map<String, String> paramMap, final OkHttp3Callback okHttp3Callback) {
        postAsyn(url, paramMap, okHttp3Callback, REQUEST_TYPE_POST);
    }

    public static void postAsyn(String url, String jsonParam, final OkHttp3Callback okHttp3Callback) {
        Map paramMap = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            paramMap = mapper.readValue(jsonParam, Map.class);
        } catch (IOException ex) {
            logger.error("Thread id:{}, error msg: {}", Thread.currentThread().getId(), ex.getMessage());
        }
        postAsyn(url, paramMap, okHttp3Callback, REQUEST_TYPE_POST);
    }


    /**
     * post的请求参数，构造RequestBody
     *
     * @param BodyParams
     * @return
     */
    private static RequestBody setRequestBody(Map<String, String> BodyParams) {
        RequestBody body;
        okhttp3.FormBody.Builder formEncodingBuilder = new okhttp3.FormBody.Builder();
        if (BodyParams != null) {
            Iterator<String> iterator = BodyParams.keySet().iterator();
            String key;
            while (iterator.hasNext()) {
                key = iterator.next();
                formEncodingBuilder.add(key, BodyParams.get(key));
            }
        }
        body = formEncodingBuilder.build();
        return body;
    }
}
