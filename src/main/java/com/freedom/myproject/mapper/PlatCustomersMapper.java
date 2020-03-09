package com.freedom.myproject.mapper;

import com.freedom.myproject.dto.PlatCustomersInfoDto;
import com.freedom.myproject.dto.RechargeOrderDto;
import com.freedom.myproject.po.RechargeOrder;

import java.util.List;

public interface PlatCustomersMapper {
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
    List<PlatCustomersInfoDto> selectAllCustomersBalanceLessThanzero(int value);

    /**
     * 批量修改企业用户的帐户状态
     * @param platCustomersInfoDto
     * @return
     */
    int updateStateBalanceLessThanzero(PlatCustomersInfoDto platCustomersInfoDto);

    /**
     * 批量修改企业对应员工的状态
     * @return
     */
    int updateStaffState(PlatCustomersInfoDto platCustomersInfoDto);

    /**
     * 根据登录名在给客户发重置邮箱时修改密码
     * @param platCustomersInfoDto
     * @return
     */
    public int resetPassword(PlatCustomersInfoDto platCustomersInfoDto);

    /**
     * 根据id查询已经登录的平台客户的信息
     * @param entId
     * @return
     */
    public  PlatCustomersInfoDto selectPlatCustomersInfo(int entId);

    /**
     * 修改平台用户信息
     * @param platCustomersInfoDto
     * @return
     */
    public int changePlatCustomersInfo(PlatCustomersInfoDto platCustomersInfoDto);
    /**
     * 修改平台用户密码
     * @param platCustomersInfoDto
     * @return
     */
    public int changePlatCustomersPassword(PlatCustomersInfoDto platCustomersInfoDto);

    /**
     * 使用插件查询所有平台用户信息
     * @return
     */
    List<PlatCustomersInfoDto> selectAllCustomers();

    /**
     * 改变企业帐户的状态
     * @param platCustomersInfoDto
     * @return
     */
    int changeState(PlatCustomersInfoDto platCustomersInfoDto);

    /**
     * 改变对应企业的员工的账户状态
     * @param platCustomersInfoDto
     * @return
     */
    int changeState2(PlatCustomersInfoDto platCustomersInfoDto);
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

    /**
     * 根据entId修改企业帐户余额
     * @param platCustomersInfoDto
     * @return
     */
    int updatebalanceByEntId(PlatCustomersInfoDto platCustomersInfoDto);

    /**
     * 根据entId查询企业帐户余额
     * @param entId
     * @return
     */
    PlatCustomersInfoDto selectPlatCustomersBalanceById(int entId);

}
