package cn.glfs.ltzf.payments.app.model;


import cn.glfs.ltzf.utils.SignUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PrepayRequest {
    // 微信开放平台审核通过的移动应用appid
    @JsonProperty("app_id")
    private String appId;
    @JsonProperty("mch_id")
    private String mchId;
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    @JsonProperty("total_fee")
    private String totalFee;
    private String body;
    private String timestamp = String.valueOf(System.currentTimeMillis()/1000);
    @JsonProperty("notify_url")
    private String notifyUrl;
    @JsonProperty("attach")
    private String attach;
    @JsonProperty("time_expire")
    private String timeExpire;

    public String createSign(String partnerKey){
        Map<String, String> map = new HashMap<>();
        map.put("app_id",getAppId());
        map.put("mch_id",getMchId());
        map.put("out_trade_no",getOutTradeNo());
        map.put("total_fee",getTotalFee());
        map.put("body",getBody());
        map.put("timestamp",getTimestamp());
        map.put("notify_url",notifyUrl);
        return SignUtils.createSign(map,partnerKey);
    }
}