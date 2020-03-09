package com.freedom.myproject.dto;

public class ComplaintMessageDto {
    private int complMesId;
    private int entId;
    private String complMessage;
    private String entName;

    public ComplaintMessageDto() {
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
        return complMessage;
    }

    public void setComplMessage(String complMessage) {
        this.complMessage = complMessage;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }
}
