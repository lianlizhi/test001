package com.kgc.exam.entity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

@Controller
@RequestMapping("pay")
public class PayController {

    @RequestMapping("payForGoods")
    public void payForGoods(@RequestParam(value = "name",defaultValue = "无名氏") String name,@RequestParam(value = "phoneNumber",defaultValue = "12357846662") String phoneNumber, String price, HttpServletRequest request, HttpServletResponse response) throws IOException{
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayAttr.gatewayUrl, AlipayAttr.app_id,
                AlipayAttr.merchant_private_key, "json", AlipayAttr.charset,
                AlipayAttr.alipay_public_key);
        // 创建唯一订单号
        int random = (int) (Math.random() * 10000);
        String dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // 订单号拼接规则：手机号后四位+当前时间后四位+随机数四位数
        String out_trade_no = phoneNumber.substring(7) + dateStr.substring(10)
                + random;
        // 拼接订单名称
		String subject = name + "的订单";
        // 总价格设置
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayAttr.return_url);//支付成功响应后跳转地址
        alipayRequest.setNotifyUrl(AlipayAttr.notify_url);//异步请求地址
        /*FAST_INSTANT_TRADE_PAY 二维码瞬时支付
         * out_trade_no 订单号 total_amount 订单金额  subject 订单名称
         */
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no
                + "\"," + "\"total_amount\":\"" + price + "\","
                + "\"subject\":\"" + subject + "\"," + "\"body\":\""
                + ""+ "\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = "请求无响应";
        try {
            //通过阿里客户端，发送支付页面请求
            result = alipayClient.pageExecute(alipayRequest).getBody();
            /*
             * 在此处使用response.getWriter().println()打印一个界面，
             * result是个字符串，其中的值是我们提交给支付宝的订单信息加上一
             * 段提交请求的js。订单信息以form表单的形式存储，js将表单提交
             */
            response.getWriter().println("<html>\n" +
                    "\t<head>\n" +
                    "\t\t<meta charset=\"utf-8\">\n" +
                    "\t\t<title></title>\n" +
                    "\t</head>\n" +
                    "\t<body>"+result+"</body>\n" +
                    "</html>");
            //刷新页面
            response.getWriter().flush();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } finally {
            try {
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}






