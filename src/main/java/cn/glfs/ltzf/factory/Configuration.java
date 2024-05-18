package cn.glfs.ltzf.factory;

import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 配置
 */
@Getter
public class Configuration {
    @Setter
    private String apiHost = "https://api.ltzf.cn/";

    //开发者id:
    private final String appId;
    // 商户号ID:
    private final String merchantId;
    // 商户秘钥:
    private final String partnerKey;
    public Configuration(String appId, String merchantId, String partnerKey) {
        this.appId = appId;
        this.merchantId = merchantId;
        this.partnerKey = partnerKey;
    }
    @Setter
    private OkHttpClient okHttpClient;
    /**
     * 设置日志拦截器的日志级别，控制 HTTP 请求和响应的日志输出内容
     * NONE：不输出任何日志信息，即关闭日志记录。
     * BASIC：仅输出请求方法、URL、响应状态码以及响应时间等基本信息。
     * HEADERS：除了 BASIC 级别的信息外，还输出请求和响应的头部信息。
     * BODY：除了 HEADERS 级别的信息外，还输出请求和响应的正文内容。
     */
    @Setter
    private HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.HEADERS;
    @Setter
    private long connectTimeout = 60;
    @Setter
    private long writeTimeout = 60;
    @Setter
    private long readTimeout = 60;
}
