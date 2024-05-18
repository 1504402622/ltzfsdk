package cn.glfs.ltzf.test;


import cn.glfs.ltzf.factory.Configuration;
import cn.glfs.ltzf.factory.PayFactory;
import cn.glfs.ltzf.factory.defaults.DefaultPayFactory;
import cn.glfs.ltzf.payments.jsapi.JSPayService;
import cn.glfs.ltzf.payments.jsapi.model.PrepayRequest;
import cn.glfs.ltzf.payments.jsapi.model.PrepayResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class JSPayServiceTest {

    private JSPayService jsPayService;

    @Before
    public void init() {
        Configuration configuration = new Configuration(
                "1107245", "1676472264", "dedba96426f8f9ca3ad9738f55f3399a"
        );

        PayFactory payFactory = new DefaultPayFactory(configuration);
        this.jsPayService = payFactory.jsPayService();
    }

    @Test
    public void test_prepay() throws Exception {
        // 1. 请求参数
        PrepayRequest request = new PrepayRequest();
//        request.setAppId("1107245");//wx861d0c8b6e7eb1b8
        request.setMchId("1676472264");
        request.setOutTradeNo(RandomStringUtils.randomNumeric(8));
        request.setTotalFee("0.01");
//        request.setOpenid("o7l1z6WC5UAS0D56aNRGBP3irAOo");
        request.setBody("QQ公仔");
        request.setNotifyUrl("https://www.glfskk.top/");

        // 2. 创建支付订单
        PrepayResponse response = jsPayService.prepay(request);

        log.info("请求参数: {}", JSON.toJSONString(request));
        log.info("应答结果: {}", JSON.toJSONString(response));
    }

}
