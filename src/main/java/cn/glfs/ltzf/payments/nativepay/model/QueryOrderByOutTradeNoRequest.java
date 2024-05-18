package cn.glfs.ltzf.payments.nativepay.model;


import cn.glfs.ltzf.utils.SignUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class QueryOrderByOutTradeNoRequest {

    /** 商户号 */
    @Setter
    private String mchId;
    /** 商户订单号 */
    @Setter
    private String outTradeNo;
    /** 当前时间戳 */
    private final String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

    /** 创建签名 */
    public String createSign(String partnerKey) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("mch_id", getMchId());
        dataMap.put("out_trade_no", getOutTradeNo());
        dataMap.put("timestamp", getTimestamp());
        return SignUtils.createSign(dataMap, partnerKey);
    }

}