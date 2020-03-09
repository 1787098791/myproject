package com.freedom.myproject.dto;

public class InsideStaffDto {
    private int insideId;
    private String insideUsername;
    private String insidePassword;
    private String insideTrueName;
    private String insidePhoneNumber;
    private int flag1;
    private int insideRoleId;
    private int superInsideId;

    private String role;
    private String department;
    private String superName;

    public InsideStaffDto() {
    }

    public int getInsideId() {

        return insideId;
    }

    public void setInsideId(int insideId) {
        this.insideId = insideId;
    }

    public String getInsideUsername() {
        if (insideUsername==null){
            insideUsername="";
        }
        return insideUsername;
    }

    public void setInsideUsername(String insideUsername) {
        this.insideUsername = insideUsername;
    }

    public String getInsidePassword() {
        if (insidePassword==null){
            insidePassword="";
        }
        return insidePassword;
    }

    public void setInsidePassword(String insidePassword) {
        this.insidePassword = insidePassword;
    }

    public String getInsideTrueName() {
        if (insideTrueName==null){
            insideTrueName="";
        }
        return insideTrueName;
    }

    public void setInsideTrueName(String insideTrueName) {
        this.insideTrueName = insideTrueName;
    }

    public String getInsidePhoneNumber() {
        if (insidePhoneNumber==null){
            insidePhoneNumber="";
        }
        return insidePhoneNumber;
    }

    public void setInsidePhoneNumber(String insidePhoneNumber) {
        this.insidePhoneNumber = insidePhoneNumber;
    }

    public int getFlag1() {
        return flag1;
    }

    public void setFlag1(int flag1) {
        this.flag1 = flag1;
    }

    public int getInsideRoleId() {
        return insideRoleId;
    }

    public void setInsideRoleId(int insideRoleId) {
        this.insideRoleId = insideRoleId;
    }

    public int getSuperInsideId() {
        return superInsideId;
    }

    public void setSuperInsideId(int superInsideId) {
        this.superInsideId = superInsideId;
    }

    public String getRole() {
        if (role==null){
            role="";
        }
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        if (department==null){
            department="";
        }
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSuperName() {
        if (superName==null){
            superName="";
        }
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }
}
