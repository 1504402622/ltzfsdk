package cn.glfs.ltzf.payments.app;


import cn.glfs.ltzf.payments.app.model.*;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IAppPayApi {

    /**
     * 支付请求发送
     */
    @FormUrlEncoded
    @POST("api/wxpay/app")
    @Headers("content-type: application/x-www-form-urlencoded")
    Call<PrepayResponse> prepay(
            @Field("app_id") String appId,
            @Field("mch_id") String mchId,
            @Field("out_trade_no") String outTradeNo,
            @Field("total_fee") String totalFee,
            @Field("body") String body,
            @Field("timestamp") String timestamp,
            @Field("notify_url") String notifyUrl,
            @Field("attach") String attach,
            @Field("time_expire") String timeExpire,
            @Field("sign") String sign
    );

    /**
     * 订单退款（对已经支付的订单发起退款）
     */
    @POST("api/wxpay/refund_order")
    @FormUrlEncoded
    @Headers("content-type: application/x-www-form-urlencoded")
    Call<RefundOrderResponse> refundOrder(
            @Field("mch_id") String mchId,
            @Field("out_trade_no") String outTradeNo,
            @Field("out_refund_no") String outRefundNo,
            @Field("timestamp") String timestamp,
            @Field("refund_fee") String refundFee,
            @Field("refund_desc") String refundDesc,
            @Field("notify_url") String notifyUrl,
            @Field("sign") String sign
    );

    /**
     * 订单查询
     */
    @POST("api/wxpay/get_pay_order")
    @FormUrlEncoded
    @Headers("content-type: application/x-www-form-urlencoded")
    Call<QueryOrderByOutTradeNoResponse> getPayOrder(
            @Field("mch_id") String mchId,
            @Field("out_trade_no") String outTradeNo,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign
    );

    /**
     * 查询退款结果
     */
    @POST("api/wxpay/get_refund_order")
    @FormUrlEncoded
    @Headers("content-type: application/x-www-form-urlencoded")
    Call<GetRefundOrderResponse> getRefundOrder(
            @Field("mch_id") String mchId,
            @Field("out_refund_no") String outRefundNo,
            @Field("timestamp") String timestamp,
            @Field("sign") String sign
    );
}
