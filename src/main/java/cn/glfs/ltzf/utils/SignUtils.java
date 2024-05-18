package cn.glfs.ltzf.utils;

import org.apache.commons.codec.digest.DigestUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;

import static java.nio.charset.StandardCharsets.UTF_8;
/**
 * 签名工具类
 * 1.设所有发送或者接收到的数据为集合M，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA。
 * 2.在stringA最后拼接上 &key=密钥 得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值。
 */
public class SignUtils {

    public static String createSign(Map<String,String> params,String partnerKey){
        //生成签名钱先去重sign
        params.remove("sign");
        String stringA = packageSign(params, false);
        String stringSignTemp = stringA + "&key=" + partnerKey;
        return DigestUtils.md5Hex(stringSignTemp).toUpperCase();
    }
    public static String packageSign(Map < String, String > params, boolean urlEncoder){
        // 先将参数以器参数名的字典序升序进行排序
        TreeMap<String,String> sortedParams = new TreeMap<String,String>(params);
        // 遍历排序后的字典,将所有参数按”key=value“格式拼接在一起
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> param : sortedParams.entrySet()) {
            String value = param.getValue();
            if(StringUtils.isBlank(value)) continue;
            if(first) first = false;
            else sb.append("&");
            sb.append(param.getKey()).append("=");
            if(urlEncoder){
                try {
                    value = urlEncode(value);
                }catch (UnsupportedEncodingException e){
                }
            }
            sb.append(value);
        }
        return sb.toString();
    }

    private static String urlEncode(String src) throws UnsupportedEncodingException {
        return URLEncoder.encode(src, UTF_8.name()).replace("+", "%20");
    }
}
