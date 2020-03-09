package com.freedom.myproject.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.freedom.myproject.dto.PlatCustomersInfoDto;
import com.freedom.myproject.dto.RechargeOrderDto;
import com.freedom.myproject.po.RechargeOrder;
import com.freedom.myproject.service.PlatCustomersService;
import com.freedom.myproject.utils.AlipayConfig;
import com.freedom.myproject.utils.TestMail;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.tools.internal.ws.processor.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
@SuppressWarnings("all")
@Controller
public class PlatCustomersController {
    @Autowired
    PlatCustomersService platCustomersService;

    /**
     * 平台客户注册
     * @param platCustomersInfoDto
     * @return
     */
    @ResponseBody
    @RequestMapping("entpriseregist")
    public Object entregist(@RequestBody PlatCustomersInfoDto platCustomersInfoDto){
        int i = platCustomersService.insertPlatCustomers(platCustomersInfoDto);
        if (i==1){
            //i==1时代表注册成功，给用户发送邮箱
            System.out.println(platCustomersInfoDto.getLoginName()+":"+platCustomersInfoDto.getEmail());

           TestMail.sendMail(platCustomersInfoDto.getLoginName(),platCustomersInfoDto.getEmail());
            return true;
        }else{
            return false;
        }
    }

    /**
     * 平台用户登录名查重
     * @param loginName
     * @return
     */
    @ResponseBody
    @RequestMapping("checkLoginName")
    public Object checkLoginName(@RequestParam String loginName){
        try {
            loginName = URLDecoder.decode(loginName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<PlatCustomersInfoDto> platCustomersInfoDtos = platCustomersService.checkLoginName(loginName);
        if (platCustomersInfoDtos.size()==0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 平台用户登录
     * @param platCustomersInfoDto
     * @return
     */
    @ResponseBody
    @RequestMapping("entpriselogin")
    public Object entlogin(HttpSession session, @RequestBody PlatCustomersInfoDto platCustomersInfoDto){
        PlatCustomersInfoDto customersInfoDto = platCustomersService.loginPlatCustomers(platCustomersInfoDto);
        if (customersInfoDto!=null){
            //登录成功个人信息入session域
            session.setAttribute("entpriseCustomers",customersInfoDto);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 平台用户密码重置
     * @param platCustomersInfoDto
     * @return
     */
    @ResponseBody
    @RequestMapping("resetCustomersPassword")
    public Object resetCustomersPassword(@RequestBody PlatCustomersInfoDto platCustomersInfoDto){
        boolean result = platCustomersService.updatePassword(platCustomersInfoDto);
        return result;
    }

    /**
     * 查询平台用户信息
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("selectPlatCustomersInfo")
    public Object selectPlatCustomersInfo(HttpSession session){
        PlatCustomersInfoDto entpriseCustomers = (PlatCustomersInfoDto)session.getAttribute("entpriseCustomers");
        int entId = entpriseCustomers.getEntId();
        PlatCustomersInfoDto platCustomersInfoDto = platCustomersService.selectPlatCustomersInfo(entId);
        return platCustomersInfoDto;

    }
    /**
     * 用id查询平台用户信息
     * @param entId
     * @return
     */
    @ResponseBody
    @RequestMapping("selectPlatCustomersInfoById")
    public Object selectPlatCustomersInfoById(@RequestParam int entId){
        PlatCustomersInfoDto platCustomersInfoDto = platCustomersService.selectPlatCustomersInfo(entId);
        return platCustomersInfoDto;
    }

    /**
     * 修改平台用户信息
     * @param platCustomersInfoDto
     * @return
             */
    @ResponseBody
    @RequestMapping("changePlatCustomersInfo")
    public Object changePlatCustomersInfo(HttpSession session,@RequestBody PlatCustomersInfoDto platCustomersInfoDto){
        int i = platCustomersService.updatePlatCustomersInfo(platCustomersInfoDto);
        if (i==1){
            //信息修改成功，则将session域中的原信息覆盖
            PlatCustomersInfoDto customersInfoDto = platCustomersService.selectPlatCustomersInfo(platCustomersInfoDto.getEntId());
            session.setAttribute("entpriseCustomers",customersInfoDto);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 修改平台用户密码
     * @param platCustomersInfoDto
     * @return
     */
    @ResponseBody
    @RequestMapping("changePlatCustomersPassword")
    public Object changePlatCustomersPassword(@RequestBody PlatCustomersInfoDto platCustomersInfoDto){
        int i = platCustomersService.updatePlatCustomersPassword(platCustomersInfoDto);
        if (i==1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 退出登录
     * @return
     */
    @ResponseBody
    @RequestMapping("exit")
    public Object exit(HttpSession session){
        //清除session里所有内容
        session.removeAttribute("entpriseCustomers");
        return true;
    }

    /**
     * 分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping("selectAllCustomersByPageInside")
    public Object selectAllCustomersByPage(@RequestParam Integer pageNo){
        Integer pageSize=6;//每页显示记录数
        //分页查询
        //PageHelper:mybatis分页查询插件工具类
        PageHelper.startPage(pageNo, pageSize);
        List<PlatCustomersInfoDto> platCustomersInfoDtos = platCustomersService.selectAllCustomers();//获取所有用户信息
        PageInfo<PlatCustomersInfoDto> pageInfo=new PageInfo<PlatCustomersInfoDto>(platCustomersInfoDtos);
        return pageInfo;
    }

    /**
     * 改变帐户状态
     * @param platCustomersInfoDto
     * @return
     */
    @ResponseBody
    @RequestMapping("changeStateInside")
    public Object changeState(@RequestBody PlatCustomersInfoDto platCustomersInfoDto){
        int i = platCustomersService.updateState(platCustomersInfoDto);
        if (i==0){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 支付
     * @param request
     * @param response
     * @param entId
     * @param rechargeAmount
     * @return
     * @throws AlipayApiException
     * @throws IOException
     */
   @ResponseBody
   @RequestMapping(value = "pay",produces="text/html;charset=UTF-8")//produces处理返回的纯字符串乱码
    public Object viewOrder(HttpServletRequest request,  HttpServletResponse response,
                          @RequestParam int entId,double rechargeAmount,String subject ) throws AlipayApiException, IOException {
       //先生成商户唯一订单号，并将未支付状态的订单信息保存到数据库
       subject = URLDecoder.decode(subject, "utf-8");
       RechargeOrder rechargeOrder = new RechargeOrder();
       String orderNumber = UUID.randomUUID().toString().replaceAll("-", "");
       rechargeOrder.setOrderNumber(orderNumber);
       rechargeOrder.setEntId(entId);
       rechargeOrder.setRechargeAmount(rechargeAmount);
       rechargeOrder.setSubject(subject);
       rechargeOrder.setRechargeTime(new Date());
       rechargeOrder.setState(0);//状态设为0
       //提交订单
       int i = platCustomersService.insertRechargeOrder(rechargeOrder);
        //开启支付流程
       if (i == 1) {
           //获得初始化的AlipayClient
           AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

           //设置请求参数
           AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
           alipayRequest.setReturnUrl(AlipayConfig.return_url);
           alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

           //商户订单号，商户网站订单系统中唯一订单号，必填
           String out_trade_no = orderNumber;
           //付款金额，必填
           String total_amount = String.valueOf(rechargeAmount);
           //订单名称，必填
            //已经传入
           //商品描述，可空
           String body = "";

           alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                   + "\"total_amount\":\"" + total_amount + "\","
                   + "\"subject\":\"" + subject + "\","
                   + "\"body\":\"" + body + "\","
                   + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

           //请求
           String result = alipayClient.pageExecute(alipayRequest).getBody();//获得一个form表单和js函数的字符串
           //System.out.println(result);
       /* rep.setContentType("text/html;charset=" + AlipayConfig.charset);
        rep.getWriter().write(result);//直接将完整的表单html输出到页面
        rep.getWriter().flush();
        rep.getWriter().close();*/
           return result;
       } else {
           return "error";
       }
   }

   @RequestMapping("return_url")
   public String return_url(HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
       //获取支付宝GET过来反馈信息
       Map<String,String> params = new HashMap<String,String>();
       Map<String,String[]> requestParams = request.getParameterMap();
       for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
           String name = (String) iter.next();
           String[] values = (String[]) requestParams.get(name);
           String valueStr = "";
           for (int i = 0; i < values.length; i++) {
               valueStr = (i == values.length - 1) ? valueStr + values[i]
                       : valueStr + values[i] + ",";
           }
           //乱码解决，这段代码在出现乱码时使用
               valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
               params.put(name, valueStr);
       }
       //验签
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            HttpSession session = request.getSession();
            if(signVerified) {
               //商户订单号
               String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

               //支付宝交易号
               String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

               //付款金额
               String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
               //根据商户订单号获取该订单的状态,改变其状态
                RechargeOrder rechargeOrder = platCustomersService.selectRechargeOrder(out_trade_no);
                if (rechargeOrder.getState()!=1){
                    RechargeOrder rechargeOrder1 = new RechargeOrder();
                    rechargeOrder1.setState(1);
                    rechargeOrder1.setRechargeTime(new Date());
                    rechargeOrder1.setZfbTradeNumber(trade_no);
                    rechargeOrder1.setOrderNumber(out_trade_no);
                    rechargeOrder1.setEntId(rechargeOrder.getEntId());
                    rechargeOrder1.setRechargeAmount(rechargeOrder.getRechargeAmount());
                    platCustomersService.updateRechargeOrder(rechargeOrder1);
                }
                session.setAttribute("out_trade_no",out_trade_no);
                session.setAttribute("trade_no",trade_no);
                session.setAttribute("total_amount",total_amount);
            }else {
                session.setAttribute("reason","验证失败");
            }
            return "return.jsp";
   }

    @RequestMapping("notify_url")
    public void notify_url(HttpServletResponse response,HttpServletRequest request) throws IOException, AlipayApiException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        HttpSession session = request.getSession();
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            //根据商户订单号获取该订单的状态,改变其状态
            RechargeOrder rechargeOrder = platCustomersService.selectRechargeOrder(out_trade_no);
                if (rechargeOrder.getState()!=1) {
                    RechargeOrder rechargeOrder1 = new RechargeOrder();
                    rechargeOrder1.setState(1);
                    rechargeOrder1.setRechargeTime(new Date());
                    rechargeOrder1.setZfbTradeNumber(trade_no);
                    rechargeOrder1.setOrderNumber(out_trade_no);
                    rechargeOrder1.setEntId(rechargeOrder.getEntId());
                    platCustomersService.updateRechargeOrder(rechargeOrder1);
                }
            if (trade_status.equals("TRADE_FINISHED")) {
            }else if (trade_status.equals("TRADE_SUCCESS")){
            }
            response.setContentType("text/html;charset="+AlipayConfig.charset);
            PrintWriter writer = response.getWriter();
            writer.write("success");//支付宝调用，给支付宝打印success或者success
            writer.flush();
            writer.close();
        }else {
            response.setContentType("text/html;charset="+AlipayConfig.charset);
            PrintWriter writer = response.getWriter();
            writer.write("fail");
            writer.flush();
            writer.close();
        }

    }

    /**
     * 分页查询企业用户订单信息
     * @return
     */
    @ResponseBody
    @RequestMapping("selectCustomeraOrderById")
    public Object selectCustomeraOrderById(@RequestBody RechargeOrderDto rechargeOrderDto){
        Integer pageSize=6;//每页显示记录数
        //分页查询
        //PageHelper:mybatis分页查询插件工具类
        PageHelper.startPage(rechargeOrderDto.getPageNo(), pageSize);
        List<RechargeOrder> rechargeOrders = platCustomersService.selectCustomersOrderById(rechargeOrderDto);//获取对应entId的订单信息
        PageInfo<RechargeOrder> pageInfo=new PageInfo<RechargeOrder>(rechargeOrders);
        return pageInfo;
    }

    /**
     * 根据rechargeId删除用户订单信息
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteCustomersOrderById")
    public Object deleteCustomersOrderById(@RequestParam int rechargeId){
        int i = platCustomersService.deleteCustomersOrderById(rechargeId);
        if (i==1){
            return true;
        }else{
            return false;
        }
    }


}
