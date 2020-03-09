package com.freedom.myproject.controller;

import com.freedom.myproject.dto.*;
import com.freedom.myproject.po.ComplaintMessage;
import com.freedom.myproject.po.RechargeOrder;
import com.freedom.myproject.service.InsideStaffService;
import com.freedom.myproject.service.PlatCustomersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class InsideStaffController {

    private  static final Logger logger= LoggerFactory.getLogger(InsideStaffController.class);
    @Autowired
    InsideStaffService insideStaffService;
    @Autowired
    PlatCustomersService platCustomersService;

    /**
     * 内部人员登录
     * @param session
     * @param insideStaffDto
     * @return
     */
    @ResponseBody
    @RequestMapping("staffLoginInside")
    public Object insideStaffLogin(HttpSession session, @RequestBody InsideStaffDto insideStaffDto){
        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000*10);
                        //每隔一段时间让所有企业帐户余额-100
                        int i = platCustomersService.updateBalance();
                        //余额定期减少后，查询所有余额<0的企业用户
                        List<Integer> list = platCustomersService.selectAllCustomersBalanceLessThanzero();
                        if (list.size()!=0) {
                            //改变他们的state状态值（改为0）
                            int j = platCustomersService.updateStateBalanceLessThanzero(list);
                            if (j!=0){
                                //state改变成功后，踢人下线，清理企业帐户和与之对应的人员的session
                                platCustomersService.clearSession(list);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        InsideStaffDto insideStaffDto1 = insideStaffService.insideLogin(insideStaffDto);
        if (insideStaffDto1!=null){
            session.setAttribute("insideStaff",insideStaffDto1);
            logger.debug("this is debug 2333333333333333");
            return true;
        }else {
            return false;
        }
    }
    /**
     * 退出登录
     * @return
     */
    @ResponseBody
    @RequestMapping("exitInside")
    public Object exit(HttpSession session){
        //清除session里内容
        session.removeAttribute("insideStaff");
        return true;
    }

    /**
     * 分页查询所有内部员工信息
     * @return
     */
    @ResponseBody
    @RequestMapping("selectAllStaffByPageInside")
    public Object selectAllStaffInside(@RequestParam(defaultValue="1") Integer pageNo){
        Integer pageSize=6;//每页显示记录数
        //分页查询
        //PageHelper:mybatis分页查询插件工具类
        PageHelper.startPage(pageNo, pageSize);
        List<InsideStaffDto> insideStaffDtos = insideStaffService.selectAllInsideStaff();
        //创建页面对象，存放UserInfo泛型的对象。将userList存放进去。
        PageInfo<InsideStaffDto> pageInfo=new PageInfo<InsideStaffDto>(insideStaffDtos);
        return pageInfo;

    }


    /**
     * 根据ID查询内部人员信息
     * @param insideId
     * @return
     */
    @ResponseBody
    @RequestMapping("selectStaffByIdInside")
    public Object selectStaffByIdInside(@RequestParam int insideId){
        InsideStaffDto insideStaffDto = insideStaffService.selectStaffByIdInside(insideId);
        return insideStaffDto;
    }

    /**
     * 修改内部人员信息
     * @param insideStaffDto
     * @return
     */
    @ResponseBody
    @RequestMapping("updateStaffInfoInside")
    public Object updateStaffInfoInside(@RequestBody InsideStaffDto insideStaffDto){
        int i = insideStaffService.updateStaffInfoInside(insideStaffDto);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 根据Id修改管理人员的密码
     * @param insideStaffDto
     * @return
     */
    @ResponseBody
    @RequestMapping("updateStaffPasswordInside")
    public Object updateStaffPasswordById(@RequestBody InsideStaffDto insideStaffDto){
        int i = insideStaffService.updateStaffPasswordById(insideStaffDto);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 根据Id删除内部人员信息
     * @param insideId
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteStaffByIdInside")
    public Object deleteStaffByIdInside(@RequestParam int insideId){
        int i = insideStaffService.deleteStaffById(insideId);
        if (i==1){
        return true;
        }else {
            return false;
        }
    }

    /**
     * 根据Id查询销售记录
     * @param insideId
     * @return
     */
    @ResponseBody
    @RequestMapping("selectSalesInfoByIdInside")
    public Object selectSalesInfoByIdInside(@RequestParam int insideId){
        List<InsideStaffSalesDto> insideStaffSalesDtos = insideStaffService.selectSalesInfoById(insideId);
      return insideStaffSalesDtos;
    }
    /**
     * 根据记录Id删除销售记录
     * @param salRecordsId
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteSalesInfoByIdInside")
    public Object deleteSalesInfoById(@RequestParam int salRecordsId){
        int i = insideStaffService.deleteSalesInfoById(salRecordsId);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 根据记录Id查询对应记录
     * @param salRecordsId
     * @return
     */
    @ResponseBody
    @RequestMapping("selectSalesInfoBySalIdInside")
    public Object selectSalesInfoBySalIdInside(@RequestParam int salRecordsId){
        InsideStaffSalesDto insideStaffSalesDto = insideStaffService.selectSalesInfoBySalId(salRecordsId);
      return insideStaffSalesDto;
    }

    /**
     * 根据记录Id修改销售记录
     * @param insideStaffSalesDto
     * @return
     */
    @ResponseBody
    @RequestMapping("updateSalesInfoByIdInside")
    public Object updateSalesInfoByIdInside(@RequestBody InsideStaffSalesDto insideStaffSalesDto){
        int i = insideStaffService.updateSalesInfoById(insideStaffSalesDto);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 根据内部人员的Id新增销售记录
     * @param insideStaffSalesDto
     * @return
     */
    @ResponseBody
    @RequestMapping("insertSalesInfoByIdInside")
    public Object insertSalesInfoByIdInside(@RequestBody InsideStaffSalesDto insideStaffSalesDto){
        int i = insideStaffService.insertSalesInfoById(insideStaffSalesDto);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 平台用户登录名查重
     * @param insideUsername
     * @return
     */
    @ResponseBody
    @RequestMapping("checkLoginNameInside")
    public Object checkLoginNameInside(@RequestParam String insideUsername){
        try {
            insideUsername = URLDecoder.decode(insideUsername, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<InsideStaffDto> insideStaffDtos = insideStaffService.selectAllLoginNameInside(insideUsername);
        if (insideStaffDtos.size()==0){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 新增内部系统管理员
     * @param insideStaffDto)
     * @return
     */
    @ResponseBody
    @RequestMapping("insertSystemPerInside")
    public Object insertSystemPerInside(@RequestBody InsideStaffDto insideStaffDto) {
        int i = insideStaffService.insertSystemPerInside(insideStaffDto);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 新增内部销售人员
     * @param insideStaffDto)
     * @return
     */
    @ResponseBody
    @RequestMapping("insertSalesPerInside")
    public Object insertSalesPerInside(@RequestBody InsideStaffDto insideStaffDto) {
        int i = insideStaffService.insertSalesPerInside(insideStaffDto);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 查询所有投诉信息
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("selectAllCompMesInside")
    public Object selectAllCompMesInside() {
        List<ComplaintMessageDto> complaintMessages = insideStaffService.selectAllCompMesInside();
       return complaintMessages;
    }
    /**
     * 根据Id删除投诉信息
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteCompMesByIdInside")
    public Object deleteCompMesByIdInside(@RequestParam int complMesId) {
        int i = insideStaffService.deleteCompMesById(complMesId);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }
    /**
     * 分页查询企业用户订单信息
     * @return
     */
    @ResponseBody
    @RequestMapping("selectAllOrdersInside")
    //不传参则查询第一页
    public Object selectAllOrdersInside(@RequestParam(defaultValue="1") Integer pageNo){
        Integer pageSize=6;//每页显示记录数
        //分页查询
        //PageHelper:mybatis分页查询插件工具类
        PageHelper.startPage(pageNo, pageSize);
        List<RechargeOrderDto> rechargeOrderDtos = insideStaffService.selectAllOrdersInside();
        PageInfo<RechargeOrderDto> pageInfo=new PageInfo<RechargeOrderDto>(rechargeOrderDtos);
        return pageInfo;
    }




}
