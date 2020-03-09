package com.freedom.myproject.service;

import com.freedom.myproject.dto.ComplaintMessageDto;
import com.freedom.myproject.dto.InsideStaffDto;
import com.freedom.myproject.dto.InsideStaffSalesDto;
import com.freedom.myproject.dto.RechargeOrderDto;
import com.freedom.myproject.po.ComplaintMessage;

import java.util.List;

public interface InsideStaffService {
    /**
     * 内部员工登录
     * @param insideStaffDto
     * @return
     */
    InsideStaffDto insideLogin(InsideStaffDto insideStaffDto);
    /**
     * 查询所有内部人员信息
     * @return
     */
    List<InsideStaffDto> selectAllInsideStaff();
    /**
     * 根据ID查询内部人员信息
     * @param insideId
     * @return
     */
    InsideStaffDto selectStaffByIdInside(int insideId);
    /**
     * 修改内部人员信息
     * @return
     */
    int updateStaffInfoInside(InsideStaffDto insideStaffDto);
    /**
     * 根据id修改系统管理人员的密码
     * @param insideStaffDto
     * @return
     */
    int updateStaffPasswordById(InsideStaffDto insideStaffDto);
    /**
     * 根据Id删除内部人员信息
     * @param insideId
     * @return
     */
    int deleteStaffById(int insideId);
    /**
     * 根据Id查询销售信息
     * @param insideId
     * @return
     */
    List<InsideStaffSalesDto> selectSalesInfoById(int insideId);
    /**
     * 根据记录Id删除销售记录
     * @param salRecordsId
     * @return
     */
    int deleteSalesInfoById(int salRecordsId);
    /**
     * 根据记录Id查询对应的那条记录
     * @param salRecordsId
     * @return
     */
    InsideStaffSalesDto selectSalesInfoBySalId(int salRecordsId);
    /**
     * 根据记录Id更新信息
     * @param insideStaffSalesDto
     * @return
     */
    int updateSalesInfoById(InsideStaffSalesDto insideStaffSalesDto);
    /**
     * 根据内部人员Id增加销售信息
     * @param insideStaffSalesDto
     * @return
     */
    int insertSalesInfoById(InsideStaffSalesDto insideStaffSalesDto);
    /**
     * 内部人员重名验证
     * @param insideUsername
     * @return
     */
    List<InsideStaffDto> selectAllLoginNameInside(String insideUsername);
    /**
     * 新增内部系统管理员
     * @param insideStaffDto
     * @return
     */
    int insertSystemPerInside(InsideStaffDto insideStaffDto);
    /**
     * 新增内部销售人员
     * @param insideStaffDto
     * @return
     */
    int insertSalesPerInside(InsideStaffDto insideStaffDto);

    /**
     * 查询所有投诉信息
     * @return
     */
    List<ComplaintMessageDto> selectAllCompMesInside();
    /**
     * 根据Id删除投诉信息
     * @param complMesId
     * @return
     */
    int deleteCompMesById(int complMesId);
    /**
     * 查询所有订单
     * @return
     */
    List<RechargeOrderDto> selectAllOrdersInside();
}
