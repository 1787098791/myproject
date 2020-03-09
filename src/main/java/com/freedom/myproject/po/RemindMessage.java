package com.freedom.myproject.po;

import com.mysql.jdbc.Driver;

import java.io.Serializable;
import java.sql.DriverManager;

public class RemindMessage implements Serializable {
    private int remindMessId;
    private String remindMessageText;
    private int entId;

    public RemindMessage() {

    }

    public int getRemindMessId() {
        return remindMessId;
    }

    public void setRemindMessId(int remindMessId) {
        this.remindMessId = remindMessId;
    }

    public String getRemindMessageText() {
        if (remindMessageText==null){
            remindMessageText="";
        }
        return remindMessageText;
    }

    public void setRemindMessageText(String remindMessageText) {
        this.remindMessageText = remindMessageText;
    }

    public int getEntId() {
        return entId;
    }

    public void setEntId(int entId) {
        this.entId = entId;
    }
}
