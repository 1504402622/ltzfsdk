package cn.glfs.ltzf.payments.jump_h5.model;

import cn.glfs.ltzf.utils.SignUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class GetRefundOrderRequest {

    // 在将 Java 对象转换为 JSON 数据时，使用指定的 "mch_id" 作为字段名,反序列化再变成 mchid
    @JsonProperty("mch_id")
    private String mchId;
    @JsonProperty("out_refund_no")
    private String outRefundNo;
    private String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

    /** 创建签名 */
    public String createSign(String partnerKey){
        Map<String,String> dataMap = new HashMap<>();
        dataMap.put("mch_id", getMchId());
        dataMap.put("out_refund_no", getOutRefundNo());
        dataMap.put("timestamp", getTimestamp());
        return SignUtils.createSign(dataMap,partnerKey);
    }
}
