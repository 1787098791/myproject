package com.freedom.myproject.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	


	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016093000629966";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key ="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDFYLvGi4H+w3vI2iD6yNROgB1lkHXmO7yZjQbIkSLC3tA+Wet1EEGESYoxa88uj0AZaip9s8qfS8gBINKwvZp4ItkCA+nERq+np9R1Ca98xnp3GzsuwmVSqFafw+F9QPjS+H28YXw854zzUn0oQclGOV2wu0M3126zO7rDStAvax3xo8YVkJu3zFfGC3TPO/vyH5JSg4R2l5Hc5P5YKRj9w4y/dZ1GuoTFGTUWez2x5MVSdyqhqO2Jt1dcM1Wb0G/WB7/bIis5/a7yJu78joz88rCoBgDaghiBJUvVlj7iWJML3sdGt6yJUrBaow/PNky19uHmqGINXUSHbx5qixRpAgMBAAECggEBALLTZq2HHhslH5SlVHYC2J/a4Udwr/ENtF1mcXvsSxNFbmTfcT3ZoZJqUoNeSxLDBO9BygejinPy491p0JoC3t3zrKtvwi9rX6BFpW74fH9PEAuzp1ZBGC3giMMtZLHn2MjEDJ9Nr5M6svCNjwmXOPWf6evCbi88x3YqKpbfxGkASzOazn5J5M2G8MMQKG4u5/hfRr2fnMgsBVia/nbcGUtvMB1aJMbeTn78pyPoEDhDeaj1oFNjXusPLb3IuCWQPlUzd7iJ8R0eMDafxRNkYPsUEosU2sVcOHBWfsqwi9ZIR9a6SGQHqhK/UuJAJ6qPpS05IT9HZJyyvKpjRSe8DwECgYEA6hoPze2QKkYxwBOoff05j0zBsQ3b0rU4DsOrDo29NHjYOISA6rH9rr5uDezlQ4GXo1sdWuWndJplh7ZfCHyXQdA9ksGlB/W2bbmXFFywKWPN34dxRjjQdZI7TRKDES66/HBkmxFQRfsFQiRU37rgdKhN7ywiQqLEo70t0e8FXeECgYEA19dCSNXwx4Cid+MVpj4UEyKVJuyUXmdBoJg8QYTVHeklI4AJbkMQ1767TZViPVMthGuU32BPvahA+LXERYqPIySYhqpOmOq3MyCvbqcNzN9yIZP1IQb5TDO5jSRaMtDTNYbYtGWWl9aNP/ydLHNPgMKk3PrfpVMnR2emB1yQt4kCgYEAm/zzrP7wAgs12UwACLtz4DWAFs8qO7O+/6+HyrHNcipXwQkhscEaTZbcVHEWry2p8hKjc871DvBWWOC/YDNMwnDUpnZb1fr3LRPuRIYiZ+H1600fwG/ZD4557zg3l3pw5AasMtuNhqPl9BWrbEzkhTckfwsEZSx6JLTI1EsVY2ECgYAPMvpY9K/S7K0EMjqJDWAOhOXp6sj76NuHOpErr/Xt+EgEOvTvDcv21ViNOxLC7MOMyqKeJEMZQOrqWKpslzhDKkWPXhjQzd5+vDN+YEcpgSahVLDzVXFiu1W6qa+sWmY1I7zI30p+gL5LnjdXNPuJPqurYAM1uihO7+6SICOUuQKBgE7yfD79aotm9CL/130Kf4wSeYz9nvqxRJFbkSM9AE2YJe+P5A6DhEagDiJm0ieOKvlOAkjpviP9d/yNmZN0FIcHBOHBqwoeOnJ44EDmEKt8BqmFitMzt5tvF/dnILxsr3X588ZTSr7YRFBZku+2CpGiA8cTfRCDryGLAzX02rXG";
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlkpvFXZrEpYZfpYxF5N95k7OCG4o2QlUWabTC9BpG+44nBqxBlYHoatWXZwlcAbylcGAVAmaczrXCLmBLpiTpl8iPQKCqPQEUCqOJohiFKDcAuvJs6EvnJOvYA8S7e8FGev4DzDThoOd5sZLT5wEPKrqvVWgSdJtSINMesPvqkJYS8zw7yHtW/AM5cKJ2gkC63/cFw6zneqg6qR3xRnhicqogMn84itANTRCwOsZShu0JcMzb0JFY38v8XLgjnB3LDBoZDgzMJETdLVrAyjFpb3vtOxkm6+iKTIopVVJQR2ysdLICyRI8+kKi6xDzw/rawXdsEZbHS6NTnWU2PelpwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://10.3.136.11:8080/notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://10.3.136.11:8080/return_url";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";



    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

