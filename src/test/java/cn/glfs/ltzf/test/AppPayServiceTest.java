package cn.glfs.ltzf.test;


import cn.glfs.ltzf.factory.Configuration;
import cn.glfs.ltzf.factory.PayFactory;
import cn.glfs.ltzf.factory.defaults.DefaultPayFactory;
import cn.glfs.ltzf.payments.app.AppPayService;
import cn.glfs.ltzf.payments.app.model.PrepayRequest;
import cn.glfs.ltzf.payments.app.model.PrepayResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class AppPayServiceTest {

    private AppPayService appPayService;

    @Before
    public void init() {
        Configuration configuration = new Configuration(
                "1107245", "1676472264", "dedba96426f8f9ca3ad9738f55f3399a"
        );

        PayFactory payFactory = new DefaultPayFactory(configuration);
        this.appPayService = payFactory.appPayService();
    }

    @Test
    public void test_prepay() throws IOException {
        // 1.发起支付请求
        PrepayRequest request = new PrepayRequest();
        request.setAppId("wxfa7dbd439a7773b0");
        request.setMchId("1676472264");
        request.setOutTradeNo("12342131");//RandomStringUtils.randomNumeric(8)
        request.setTotalFee("0.01");
        request.setBody("QQ公仔");
        request.setNotifyUrl("https://www.glfskk.top/");

        // 2. 创建支付订单
        PrepayResponse response = appPayService.prepay(request);

        log.info("请求参数: {}", JSON.toJSONString(request));
        log.info("应答结果: {}", JSON.toJSONString(response));
    }
}
