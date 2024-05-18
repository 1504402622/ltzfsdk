package cn.glfs.ltzf.factory;


import cn.glfs.ltzf.payments.app.AppPayService;
import cn.glfs.ltzf.payments.h5.H5PayService;
import cn.glfs.ltzf.payments.jsapi.JSPayService;
import cn.glfs.ltzf.payments.jump_h5.JumpH5PayService;
import cn.glfs.ltzf.payments.nativepay.NativePayService;

public interface PayFactory {
    /** 扫码支付 */
    NativePayService nativePayService();
    /** H5支付（非跳转现不可用） */
    H5PayService h5PayService();
    /** APP支付 */
    AppPayService appPayService();
    /** 公众号支付(便携版) */
    JSPayService jsPayService();
    /** H5支付[跳转模式] */
    JumpH5PayService jumpH5PayService();
}
