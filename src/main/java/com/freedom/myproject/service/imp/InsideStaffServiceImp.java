package com.freedom.myproject.service.imp;

import com.freedom.myproject.dto.ComplaintMessageDto;
import com.freedom.myproject.dto.InsideStaffDto;
import com.freedom.myproject.dto.InsideStaffSalesDto;
import com.freedom.myproject.dto.RechargeOrderDto;
import com.freedom.myproject.mapper.InsideStaffMapper;
import com.freedom.myproject.po.ComplaintMessage;
import com.freedom.myproject.service.InsideStaffService;
import com.freedom.myproject.utils.FinalMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsideStaffServiceImp implements InsideStaffService{
    @Autowired
    InsideStaffMapper insideStaffMapper;

    /**
     * 内部员工登录
     * @param insideStaffDto
     * @return
     */
    public InsideStaffDto insideLogin(InsideStaffDto insideStaffDto) {
        insideStaffDto.setInsidePassword(FinalMD5.getFinalMD5(insideStaffDto.getInsidePassword()));
        InsideStaffDto staffDto = insideStaffMapper.insideLogin(insideStaffDto);
        return staffDto;
    }

    /**
     * 查询所有内部人员信息
     * @return
     */
    public List<InsideStaffDto> selectAllInsideStaff() {
        List<InsideStaffDto> insideStaffDtos = insideStaffMapper.selectAllInsideStaff();
        return insideStaffDtos;
    }

    /**
     * 根据ID查询内部人员信息
     * @param insideId
     * @return
     */
    public InsideStaffDto selectStaffByIdInside(int insideId) {
        InsideStaffDto insideStaffDto = insideStaffMapper.selectStaffByIdInside(insideId);
        return insideStaffDto;
    }

    /**
     * 修改内部人员消息
     * @param insideStaffDto
     * @return
     */
    public int updateStaffInfoInside(InsideStaffDto insideStaffDto) {
        int i = insideStaffMapper.updateStaffInfoInside(insideStaffDto);
        return i;
    }

    /**
     * 根据id修改系统管理人员的密码
     * @param insideStaffDto
     * @return
     */
    public int updateStaffPasswordById(InsideStaffDto insideStaffDto) {
        //密码加密
        insideStaffDto.setInsidePassword(FinalMD5.getFinalMD5(insideStaffDto.getInsidePassword()));
        int i = insideStaffMapper.updateStaffPasswordById(insideStaffDto);
        return i;
    }

    /**
     * 根据Id删除内部人员信息
     * @param insideId
     * @return
     */
    public int deleteStaffById(int insideId) {
        int i = insideStaffMapper.deleteStaffById(insideId);
        return i;
    }

    /**
     * 根据Id查询销售信息
     * @param insideId
     * @return
     */
    public List<InsideStaffSalesDto> selectSalesInfoById(int insideId) {
        List<InsideStaffSalesDto> insideStaffSalesDtos = insideStaffMapper.selectSalesInfoById(insideId);
        return insideStaffSalesDtos;
    }

    /**
     * 根据记录Id删除销售信息
     * @param salRecordsId
     * @return
     */
    public int deleteSalesInfoById(int salRecordsId) {
        int i = insideStaffMapper.deleteSalesInfoById(salRecordsId);
        return i;
    }
    /**
     * 根据记录Id查询对应的那条记录
     * @param salRecordsId
     * @return
     */
    public InsideStaffSalesDto selectSalesInfoBySalId(int salRecordsId) {
        InsideStaffSalesDto insideStaffSalesDto = insideStaffMapper.selectSalesInfoBySalId(salRecordsId);
        return insideStaffSalesDto;
    }

    /**
     * 根据记录Id更新信息
     * @param insideStaffSalesDto
     * @return
     */
    public int updateSalesInfoById(InsideStaffSalesDto insideStaffSalesDto) {
        int i = insideStaffMapper.updateSalesInfoById(insideStaffSalesDto);
        return i;
    }

    /**
     * 根据内部人员Id增加销售信息
     * @param insideStaffSalesDto
     * @return
     */
    public int insertSalesInfoById(InsideStaffSalesDto insideStaffSalesDto) {
        int i = insideStaffMapper.insertSalesInfoById(insideStaffSalesDto);
        return i;
    }

    /**
     * 内部人员重名验证
     * @param insideUsername
     * @return
     */
    public List<InsideStaffDto> selectAllLoginNameInside(String insideUsername) {
        List<InsideStaffDto> insideStaffDtos = insideStaffMapper.selectAllLoginNameInside(insideUsername);
        return insideStaffDtos;
    }

    /**
     * 新增内部系统管理员
     * @param insideStaffDto
     * @return
     */
    public int insertSystemPerInside(InsideStaffDto insideStaffDto) {
        insideStaffDto.setFlag1(1);
        insideStaffDto.setInsideRoleId(1);
        insideStaffDto.setInsidePassword(FinalMD5.getFinalMD5(insideStaffDto.getInsidePassword()));
        int i = insideStaffMapper.insertPerInside(insideStaffDto);
        return i;
    }

    /**
     * 新增内部销售人员
     * @param insideStaffDto
     * @return
     */
    public int insertSalesPerInside(InsideStaffDto insideStaffDto) {
        insideStaffDto.setFlag1(1);
        int i = insideStaffMapper.insertPerInside(insideStaffDto);
        return i;
    }

    /**
     * 查询所有投诉信息
     * @return
     */
    public List<ComplaintMessageDto> selectAllCompMesInside() {
        List<ComplaintMessageDto> complaintMessages = insideStaffMapper.selectAllCompMesInside();
        return complaintMessages;
    }

    /**
     * 根据Id删除投诉信息
     * @param complMesId
     * @return
     */
    public int deleteCompMesById(int complMesId) {
        int i = insideStaffMapper.deleteCompMesById(complMesId);
        return i;
    }

    /**
     * 查询所有订单
     * @return
     */
    public List<RechargeOrderDto> selectAllOrdersInside() {
        List<RechargeOrderDto> rechargeOrderDtos = insideStaffMapper.selectAllOrdersInside();
        return rechargeOrderDtos;
    }

}
