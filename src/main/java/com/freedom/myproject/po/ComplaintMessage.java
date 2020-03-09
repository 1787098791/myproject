package com.freedom.myproject.po;

import java.io.Serializable;

public class ComplaintMessage implements Serializable {
    private int complMesId;
    private int entId;
    private String complMessage;

    public ComplaintMessage() {
    }

    public ComplaintMessage(int complMesId, int entId, String complMessage) {
        this.complMesId = complMesId;
        this.entId = entId;
        this.complMessage = complMessage;
    }

    public int getComplMesId() {
        return complMesId;
    }

    public void setComplMesId(int complMesId) {
        this.complMesId = complMesId;
    }

    public int getEntId() {
        return entId;
    }

    public void setEntId(int entId) {
        this.entId = entId;
    }

    public String getComplMessage() {
        if (complMessage==null){
            complMessage="";
        }
        return complMessage;
    }

    public void setComplMessage(String complMessage) {
        this.complMessage = complMessage;
    }
}
