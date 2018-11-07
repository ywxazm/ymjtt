package com.ymjtt.common.okhttp3;

/**
 * *.properties可转换为本类实例
 * @auther ywx
 * @date 2018/11/1 8:53
 **/
public class OkHttp3DTO {

    private int retryCount;         //重试次数
    private int bytesSize;          //读取文件大小
    private int connectTimeout;     //从连接池中获取可用连接超时
    private int readTimeout;        //连接目标超时
    private int writeTimeout;       //等待响应超时（读取数据超时）

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getBytesSize() {
        return bytesSize;
    }

    public void setBytesSize(int bytesSize) {
        this.bytesSize = bytesSize;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OkHttp3DTO that = (OkHttp3DTO) o;

        if (retryCount != that.retryCount) return false;
        if (bytesSize != that.bytesSize) return false;
        if (connectTimeout != that.connectTimeout) return false;
        if (readTimeout != that.readTimeout) return false;
        return writeTimeout == that.writeTimeout;
    }

    @Override
    public int hashCode() {
        int result = retryCount;
        result = 31 * result + bytesSize;
        result = 31 * result + connectTimeout;
        result = 31 * result + readTimeout;
        result = 31 * result + writeTimeout;
        return result;
    }

    @Override
    public String toString() {
        return "OkHttp3DTO{" +
                "retryCount=" + retryCount +
                ", bytesSize=" + bytesSize +
                ", connectTimeout=" + connectTimeout +
                ", readTimeout=" + readTimeout +
                ", writeTimeout=" + writeTimeout +
                '}';
    }
}
