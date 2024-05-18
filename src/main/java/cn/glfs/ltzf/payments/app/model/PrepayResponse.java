package cn.glfs.ltzf.payments.app.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrepayResponse {
    private Integer code;
    private String msg;
    @JsonProperty("request_id")
    private String requestId;
    private Data data;

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        @JsonProperty("appid")
        private String appId;
        @JsonProperty("partnerid")
        private String partnerId;
        @JsonProperty("prepayid")
        private String prepayId;
        @JsonProperty("package")
        private String package_field;
        @JsonProperty("noncestr")
        private String nonceStr;
        private String timestamp;
        private String sign;
    }
}