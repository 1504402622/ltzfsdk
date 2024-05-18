package cn.glfs.ltzf.payments.jsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrepayResponse {

    private Integer code;
    private Data data;
    private String msg;
    @JsonProperty("request_id")
    private String requestId;

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        @JsonProperty("order_url")
        private String orderUrl;
        @JsonProperty("QRcode_url")
        private String QRcodeUrl;
    }

}