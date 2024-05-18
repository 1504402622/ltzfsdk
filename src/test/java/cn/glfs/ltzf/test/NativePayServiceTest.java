package cn.glfs.ltzf.test;

import cn.glfs.ltzf.factory.Configuration;
import cn.glfs.ltzf.factory.PayFactory;
import cn.glfs.ltzf.factory.defaults.DefaultPayFactory;
import cn.glfs.ltzf.payments.nativepay.NativePayService;
import cn.glfs.ltzf.payments.nativepay.model.*;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class NativePayServiceTest {

    private NativePayService nativePayService;

    @Before
    public void init() {
        Configuration configuration = new Configuration(
                "1107245", "1676472264", "dedba96426f8f9ca3ad9738f55f3399a"
        );

        PayFactory payFactory = new DefaultPayFactory(configuration);
        this.nativePayService = payFactory.nativePayService();
    }

    @Test
    public void test_prepay() throws Exception {
        // 1. 请求参数
        PrepayRequest request = new PrepayRequest();
        request.setMchId("1676472264");
        request.setOutTradeNo(RandomStringUtils.randomNumeric(8));//RandomStringUtils.randomNumeric(8)
        request.setTotalFee("0.01");
        request.setBody("QQ公仔");
        request.setNotifyUrl("https://www.glfskk.top/");

        // 2.发起支付请求并返回
        PrepayResponse response = nativePayService.prepay(request);

        log.info("请求参数:{}", JSON.toJSONString(request));
        log.info("应答结果:{}", JSON.toJSONString(response));

    }

    @Test
    public void test_queryOrderByOutTradeNo() throws Exception {
        // 1.请求参数
        QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
        request.setMchId("1676472264");
        request.setOutTradeNo("12345678");

        // 2.发起查询订单请求 返回 已/未 支付订单
        QueryOrderByOutTradeNoResponse response = nativePayService.queryOrderByOutTradeNo(request);

        log.info("请求参数:{}", JSON.toJSONString(request));
        log.info("应答结果:{}", JSON.toJSONString(response));
    }

    @Test
    public void test_refundOrder() throws Exception {
        // 1.请求参数
        RefundOrderRequest request = new RefundOrderRequest();
        request.setMchId("1676472264");
        request.setOutTradeNo("12345678");
        request.setOutRefundNo("87654321");// RandomStringUtils.randomNumeric(8)
        request.setRefundFee("0.01");
        request.setRefundDesc("测试退款");
        request.setNotifyUrl("https://gaga.plus/api");

        // 2.发起退款请求并返回
        RefundOrderResponse response = nativePayService.refundOrder(request);

        log.info("请求参数:{}", JSON.toJSONString(request));
        log.info("应答结果:{}", JSON.toJSONString(response));
    }

    @Test
    public void test_getRefundOrder() throws Exception {
        // 1.请求参数
        GetRefundOrderRequest request = new GetRefundOrderRequest();
        request.setMchId("1676472264");
        request.setOutRefundNo("87654321");

        // 2.发起查询退款订单请求并返回
        GetRefundOrderResponse response = nativePayService.getRefundOrder(request);

        log.info("请求参数:{}", JSON.toJSONString(request));
        log.info("应答结果:{}", JSON.toJSONString(response));
    }
}