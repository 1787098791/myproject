package com.freedom.myproject.service.imp;

import com.freedom.myproject.mapper.UtilsMapper;
import com.freedom.myproject.po.ComplaintMessage;
import com.freedom.myproject.po.RemindMessage;
import com.freedom.myproject.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilsServiceImp implements UtilsService {
@Autowired
    UtilsMapper utilsMapper;

    /**
     * 添加投诉内容
     * @param complaintMessage
     * @return
     */
    public int insertComplaintMessage(ComplaintMessage complaintMessage) {
        int i = utilsMapper.insertComplaintMessage(complaintMessage);
        return i;
    }

    /**
     * 帐户余额消息提醒
     * @param remindMessage
     * @return
     */
    public int insertRemindMessage(RemindMessage remindMessage) {
        int i = utilsMapper.insertRemindMessage(remindMessage);
        return i;
    }
}
