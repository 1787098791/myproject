package com.freedom.myproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

public class PlatCustomersInfoDto {
    private int entId;
    private String loginName;
    private String password;
    private String entName;
    private String connectName;
    private String address;
    private String email;
    private String phoneNumber;
    private int status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registTime;
    private Double balance;
    private int state;
    private List<Integer> entIdList;


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public PlatCustomersInfoDto() {
    }

    public int getEntId() {
        return entId;
    }

    public void setEntId(int entId) {
        this.entId = entId;
    }

    public String getLoginName() {
        if (loginName==null){
            loginName="";
        }
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        if (password==null){
            password="";
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEntName() {
        if (entName==null){
            entName="";
        }
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getConnectName() {
        if (connectName==null){
            connectName="";
        }
        return connectName;
    }

    public void setConnectName(String connectName) {
        this.connectName = connectName;
    }

    public String getAddress() {
        if (address==null){
            address="";
        }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        if (email==null){
            email="";
        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        if (phoneNumber==null){
            phoneNumber="";
        }
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Integer> getEntIdList() {
        return entIdList;
    }

    public void setEntIdList(List<Integer> entIdList) {
        this.entIdList = entIdList;
    }
}
