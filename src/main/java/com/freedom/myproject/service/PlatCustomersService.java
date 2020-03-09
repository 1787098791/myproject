package com.freedom.myproject.service;

import com.freedom.myproject.dto.PlatCustomersInfoDto;
import com.freedom.myproject.dto.RechargeOrderDto;
import com.freedom.myproject.po.RechargeOrder;

import java.util.List;

public interface PlatCustomersService {
    /**
     * 平台客户注册
     * @return
     */
    public int insertPlatCustomers(PlatCustomersInfoDto platCustomersInfoDto);
    /**
     * 登录名查重
     * @param loginName
     * @return
     */
    public List<PlatCustomersInfoDto> checkLoginName(String loginName);
    /**
     * 平台客户登录
     * @param platCustomersInfoDto
     * @return
     */
    public PlatCustomersInfoDto loginPlatCustomers(PlatCustomersInfoDto platCustomersInfoDto);
    /**
     * 定时修改余额
     * @return
     */
    public int updateBalance();
    /**
     * 查询所有余额<0的企业用户的信息
     * @return
     */
    List<Integer> selectAllCustomersBalanceLessThanzero();
    /**
     * 批量修改企业用户的帐户状态
     * @param
     * @return
     */
    int updateStateBalanceLessThanzero(List<Integer> list);

    /**
     * 清除session,踢人下线
     * @param list
     */
    void clearSession(List<Integer> list);
    /**
     * 根据登录名在给客户发重置邮箱时修改密码
     * @param platCustomersInfoDto
     * @return
     */
    public boolean updatePassword(PlatCustomersInfoDto platCustomersInfoDto);
    /**
     * 根据id查询已经登录的平台客户的信息
     * @param entId
     * @return
     */
    public PlatCustomersInfoDto selectPlatCustomersInfo(int entId);
    /**
     * 修改平台用户信息
     * @param platCustomersInfoDto
     * @return
     */
    public int updatePlatCustomersInfo(PlatCustomersInfoDto platCustomersInfoDto);
    /**
     * 修改平台用户密码
     * @param platCustomersInfoDto
     * @return
     */
    public int updatePlatCustomersPassword(PlatCustomersInfoDto platCustomersInfoDto);
    /**
     * 使用插件查询平台用户信息
     * @return
     */
    List<PlatCustomersInfoDto> selectAllCustomers();
    /**
     * 改变企业帐户的状态
     * @param platCustomersInfoDto
     * @return
     */
    int updateState(PlatCustomersInfoDto platCustomersInfoDto);

    /**
     * 增加客户充值记录
     * @param rechargeOrder
     * @return
     */
    int insertRechargeOrder(RechargeOrder rechargeOrder);
    /**
     * 根据订单号(订单号唯一)获取订单信息
     * @param orderNumber
     * @return
     */
    RechargeOrder selectRechargeOrder(String orderNumber);
    /**
     * 修改客户充值记录(订单)状态
     * @param rechargeOrder
     * @return
     */
    int updateRechargeOrder(RechargeOrder rechargeOrder);
    /**
     * 根据entID查询企业用户的所有订单
     * @param rechargeOrderDto
     * @return
     */
    List<RechargeOrder> selectCustomersOrderById(RechargeOrderDto rechargeOrderDto);
    /**
     * 根据rechargeId删除订单
     * @param rechargeId
     * @return
     */
    int deleteCustomersOrderById(int rechargeId);
}
