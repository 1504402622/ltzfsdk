package cn.glfs.ltzf.payments.jump_h5.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) //因为得到的结果可能是失败也可能是成功，失败就只显示code，msg，requsetId 成功显示 全部
public class GetRefundOrderResponse {
    private Long code;
    private String msg;
    @JsonProperty("request_id")
    private String requestId;
    private Data data;
    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        @JsonProperty("refund_status")
        private Integer refundStatus;
        @JsonProperty("mch_id")
        private String mchId;
        @JsonProperty("out_trade_no")
        private String outTradeNo;
        @JsonProperty("pay_no")
        private String payNo;
        @JsonProperty("order_no")
        private String orderNo;
        @JsonProperty("out_refund_no")
        private String outRefundNo;
        @JsonProperty("pay_refund_no")
        private String payRefundNo;
        @JsonProperty("refund_fee")
        private String refundFee;
        @JsonProperty("user_received_account")
        private String userReceivedAccount;
        @JsonProperty("success_time")
        private String successTime;
    }
}


