package cn.glfs.ltzf.factory.defaults;



import cn.glfs.ltzf.factory.Configuration;
import cn.glfs.ltzf.factory.PayFactory;
import cn.glfs.ltzf.payments.app.AppPayService;
import cn.glfs.ltzf.payments.app.IAppPayApi;
import cn.glfs.ltzf.payments.h5.H5PayService;
import cn.glfs.ltzf.payments.h5.IH5PayApi;
import cn.glfs.ltzf.payments.jsapi.IJSPayApi;
import cn.glfs.ltzf.payments.jsapi.JSPayService;
import cn.glfs.ltzf.payments.jump_h5.IJumpH5PayApi;
import cn.glfs.ltzf.payments.jump_h5.JumpH5PayService;
import cn.glfs.ltzf.payments.nativepay.INativePayApi;
import cn.glfs.ltzf.payments.nativepay.NativePayService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class DefaultPayFactory implements PayFactory {

    private final Configuration configuration;
    private final OkHttpClient httpClient;
    public DefaultPayFactory(Configuration configuration){
        this.configuration = configuration;
        // 1.日志配置
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // 2.开启OkHttp客户端
        httpClient = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(configuration.getConnectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(configuration.getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(configuration.getReadTimeout(), TimeUnit.SECONDS)
                .build();
    }

    @Override
    public NativePayService nativePayService(){
        //构建API
        INativePayApi nativePayApi = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(INativePayApi.class);
        return new NativePayService(configuration, nativePayApi);
    }

    @Override
    public AppPayService appPayService() {
        IAppPayApi iAppPayApi = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(IAppPayApi.class);
        return new AppPayService(configuration,iAppPayApi);
    }

    @Override
    public H5PayService h5PayService() {
        IH5PayApi ih5PayApi = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(IH5PayApi.class);
        return new H5PayService(configuration,ih5PayApi);
    }

    @Override
    public JSPayService jsPayService() {
        IJSPayApi ijsPayApi = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(IJSPayApi.class);
        return new JSPayService(configuration,ijsPayApi);
    }

    @Override
    public JumpH5PayService jumpH5PayService() {
        IJumpH5PayApi iJumpH5PayApi = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(IJumpH5PayApi.class);
        return new JumpH5PayService(configuration,iJumpH5PayApi);
    }
}
