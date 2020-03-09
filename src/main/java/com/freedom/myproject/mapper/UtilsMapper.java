package com.freedom.myproject.mapper;

import com.freedom.myproject.po.ComplaintMessage;
import com.freedom.myproject.po.RemindMessage;

public interface UtilsMapper {
    /**
     * 添加投诉内容
     * @param complaintMessage
     * @return
     */
    int insertComplaintMessage(ComplaintMessage complaintMessage);

    /**
     * 帐户余额消息提醒
     * @param remindMessage
     * @return
     */
    int insertRemindMessage(RemindMessage remindMessage);
}
