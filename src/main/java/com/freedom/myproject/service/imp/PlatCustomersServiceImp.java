package com.freedom.myproject.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.freedom.myproject.dto.PlatCustomersInfoDto;
import com.freedom.myproject.dto.RechargeOrderDto;
import com.freedom.myproject.mapper.PlatCustomersMapper;
import com.freedom.myproject.po.RechargeOrder;
import com.freedom.myproject.service.PlatCustomersService;
import com.freedom.myproject.utils.FinalMD5;
import com.freedom.myproject.utils.HttpClientUtils;
import com.freedom.myproject.utils.TestMail2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PlatCustomersServiceImp implements PlatCustomersService {
    @Autowired
    PlatCustomersMapper platCustomersMapper;

    /**
     * 平台用户注册
     * @param platCustomersInfoDto
     * @return
     */
    public int insertPlatCustomers(PlatCustomersInfoDto platCustomersInfoDto) {
        //给外部来的平台的企业级别帐户一个status字段，用以区分企业帐户和个人帐户
        platCustomersInfoDto.setStatus(1);
        //给帐户初始状态设为1
        platCustomersInfoDto.setState(1);
        //初始余额300
        platCustomersInfoDto.setBalance(300.00);
        //密码加密
        platCustomersInfoDto.setPassword(FinalMD5.getFinalMD5(platCustomersInfoDto.getPassword()));
        //注册时间
        Date date = new Date();
        platCustomersInfoDto.setRegistTime(date);
        int i = platCustomersMapper.insertPlatCustomers(platCustomersInfoDto);
        return i;
    }

    /**
     * 注册时登录名查重
     * @param loginName
     * @return
     */
    public List<PlatCustomersInfoDto> checkLoginName(String loginName) {
        List<PlatCustomersInfoDto> platCustomersInfoDtos = platCustomersMapper.checkLoginName(loginName);
        return platCustomersInfoDtos;
    }

    /**
     * 平台用户登录
     * @param platCustomersInfoDto
     * @return
     */
    public PlatCustomersInfoDto loginPlatCustomers(PlatCustomersInfoDto platCustomersInfoDto) {
        //登录时密码加密
        platCustomersInfoDto.setPassword(FinalMD5.getFinalMD5(platCustomersInfoDto.getPassword()));
        PlatCustomersInfoDto customersInfoDto = platCustomersMapper.loginPlatCustomers(platCustomersInfoDto);
        return customersInfoDto;
    }

    /**
     * 余额每隔一定时间减少
     * @return
     */
    public int updateBalance() {
        int i = platCustomersMapper.updateBalance();
        return i;
    }

    /**
     * 查询所有余额<0的企业用户的信息
     * @return
     */
    public List<Integer> selectAllCustomersBalanceLessThanzero() {
        List<PlatCustomersInfoDto> platCustomersInfoDtos = platCustomersMapper.selectAllCustomersBalanceLessThanzero(0);
        ArrayList<Integer> list = new ArrayList<Integer>();
            for (PlatCustomersInfoDto platCustomersInfoDto:platCustomersInfoDtos) {
                list.add(platCustomersInfoDto.getEntId());
            }
        return list;
    }
    /**
     * 批量修改企业用户的帐户状态
     * @param
     */
    public int updateStateBalanceLessThanzero(List<Integer> list) {
        PlatCustomersInfoDto platCustomersInfoDto = new PlatCustomersInfoDto();
        //企业帐户余额<0的设置state为0
        platCustomersInfoDto.setState(0);
        platCustomersInfoDto.setEntIdList(list);
        int i = platCustomersMapper.updateStateBalanceLessThanzero(platCustomersInfoDto);
        if (i!=0){
            int j = platCustomersMapper.updateStaffState(platCustomersInfoDto);
            return j;
        }
        return i;
    }



    /**
     * 清除session,踢人下线
     * @param list
     */
    public void clearSession(List<Integer> list) {
        String jsonstr = JSONArray.toJSONString(list);
        HttpClientUtils.doPostJson("http://10.3.136.18:8080/clearsession",jsonstr);
    }

    /**
     * 重置密码
     * @param platCustomersInfoDto
     * @return
     */
    public boolean updatePassword(PlatCustomersInfoDto platCustomersInfoDto) {
        //获取随机的6位数密码
        String password="";
        Random random = new Random();
        for (int i=0;i<=5;i++){
           password+=random.nextInt(10);
       }
       platCustomersInfoDto.setPassword(FinalMD5.getFinalMD5(password));
        //先将重置的密码入库
        int i = platCustomersMapper.resetPassword(platCustomersInfoDto);
        if (i==1){
            //发邮箱
            TestMail2.sendMail(platCustomersInfoDto.getLoginName(),platCustomersInfoDto.getEmail(),password);
            return true;
        }
        return false;
    }

    /**
     * 查询平台用户信息
     * @param entId
     * @return
     */
    public PlatCustomersInfoDto selectPlatCustomersInfo(int entId) {
        PlatCustomersInfoDto platCustomersInfoDto = platCustomersMapper.selectPlatCustomersInfo(entId);
        //System.out.println(platCustomersInfoDto.getStatus()+"233333");
        return platCustomersInfoDto;
    }

    /**
     * 修改平台用户信息
     * @param platCustomersInfoDto
     * @return
     */
    public int updatePlatCustomersInfo(PlatCustomersInfoDto platCustomersInfoDto) {
        int i = platCustomersMapper.changePlatCustomersInfo(platCustomersInfoDto);
        return i;
    }

    /**
     * 修改平台用户密码
     * @param platCustomersInfoDto
     * @return
     */
    public int updatePlatCustomersPassword(PlatCustomersInfoDto platCustomersInfoDto) {
        platCustomersInfoDto.setPassword(FinalMD5.getFinalMD5(platCustomersInfoDto.getPassword()));
        int i = platCustomersMapper.changePlatCustomersPassword(platCustomersInfoDto);
        return i;
    }

    /**
     * 使用插件分页
     * @return
     */
    public List<PlatCustomersInfoDto> selectAllCustomers() {
        List<PlatCustomersInfoDto> platCustomersInfoDtos = platCustomersMapper.selectAllCustomers();
        return platCustomersInfoDtos;
    }

    /**
     * 改变帐户状态
     * @param platCustomersInfoDto
     * @return
     */
    public int updateState(PlatCustomersInfoDto platCustomersInfoDto) {
        int i = platCustomersMapper.changeState(platCustomersInfoDto);
        //公司帐户状态该表其员工的账户状态也要改变
        if (i==1) {
            int is = platCustomersMapper.changeState2(platCustomersInfoDto);
        }
        return i;
    }

    /**
     * 增加客户充值记录
     * @param rechargeOrder
     * @return
     */
    public int insertRechargeOrder(RechargeOrder rechargeOrder) {
        int i = platCustomersMapper.insertRechargeOrder(rechargeOrder);
        return i;
    }

    /**
     * 根据订单号(订单号唯一)获取订单信息
     * @param orderNumber
     * @return
     */
    public RechargeOrder selectRechargeOrder(String orderNumber) {
        RechargeOrder rechargeOrder = platCustomersMapper.selectRechargeOrder(orderNumber);
        return rechargeOrder;
    }

    /**
     * 修改客户充值记录(订单)状态
     * @param rechargeOrder
     * @return
     */
    public int updateRechargeOrder(RechargeOrder rechargeOrder) {
        int i = platCustomersMapper.updateRechargeOrder(rechargeOrder);
        //修改成功则要给对应的公司的帐户余额+充值金额
        if(i==1){
            int entId = rechargeOrder.getEntId();
            double rechargeAmount = rechargeOrder.getRechargeAmount();
            PlatCustomersInfoDto platCustomersInfoDto = new PlatCustomersInfoDto();
            platCustomersInfoDto.setBalance(rechargeAmount);
            platCustomersInfoDto.setEntId(entId);
            int j = platCustomersMapper.updatebalanceByEntId(platCustomersInfoDto);
            System.out.println("j:"+j);
            System.out.println(platCustomersInfoDto.getBalance());
            System.out.println(platCustomersInfoDto.getEntId());
            if(j==1){

                //查询对应entId的公司余额的状态(改为可用状态)
                //如果balance>0,则改变对应公司的账户状态以及公司员工的账户状态(改为可用状态)
                PlatCustomersInfoDto platCustomersInfoDto1 = platCustomersMapper.selectPlatCustomersBalanceById(entId);
                if (platCustomersInfoDto1.getBalance()>0){
                    PlatCustomersInfoDto platCustomersInfoDto2 = new PlatCustomersInfoDto();
                    platCustomersInfoDto2.setEntId(entId);
                    platCustomersInfoDto2.setState(1);
                    //修改企业帐户状态
                    platCustomersMapper.changeState(platCustomersInfoDto2);
                    //修改企业员工帐户状态
                    platCustomersMapper.changeState2(platCustomersInfoDto2);
                }
            }

        }
        return i;
    }

    /**
     * 根据entID查询企业用户的所有订单
     * @param rechargeOrderDto
     * @return
     */
    public List<RechargeOrder> selectCustomersOrderById(RechargeOrderDto rechargeOrderDto) {
        List<RechargeOrder> rechargeOrders = platCustomersMapper.selectCustomersOrderById(rechargeOrderDto);
        return rechargeOrders;
    }

    /**
     * 根据rechargeId删除订单
     * @param rechargeId
     * @return
     */
    public int deleteCustomersOrderById(int rechargeId) {
        int i = platCustomersMapper.deleteCustomersOrderById(rechargeId);
        return i;
    }


}
