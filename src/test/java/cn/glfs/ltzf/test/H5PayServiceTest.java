package cn.glfs.ltzf.test;


import cn.glfs.ltzf.factory.Configuration;
import cn.glfs.ltzf.factory.PayFactory;
import cn.glfs.ltzf.factory.defaults.DefaultPayFactory;

import cn.glfs.ltzf.payments.h5.H5PayService;
import cn.glfs.ltzf.payments.h5.model.PrepayRequest;
import cn.glfs.ltzf.payments.h5.model.PrepayResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class H5PayServiceTest {

    private H5PayService h5PayService;

    @Before
    public void init() {
        Configuration configuration = new Configuration(
                "1107245", "1676472264", "dedba96426f8f9ca3ad9738f55f3399a"
        );

        PayFactory payFactory = new DefaultPayFactory(configuration);
        this.h5PayService = payFactory.h5PayService();
    }

    @Test
    public void test_prepay() throws Exception {
        // 1.发起支付请求
        PrepayRequest request = new PrepayRequest();
        request.setMchId("1676472264");
        request.setOutTradeNo(RandomStringUtils.randomNumeric(8));//RandomStringUtils.randomNumeric(8)
        request.setTotalFee("0.01");
        request.setBody("QQ公仔");
        request.setNotifyUrl("https://www.glfskk.top/");

        // 2. 创建支付订单
        PrepayResponse response = h5PayService.prepay(request);

        log.info("请求参数: {}", JSON.toJSONString(request));
        log.info("应答结果: {}", JSON.toJSONString(response));
    }

}
