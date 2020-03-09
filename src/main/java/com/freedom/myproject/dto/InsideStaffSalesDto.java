package com.freedom.myproject.dto;

public class InsideStaffSalesDto {
    private int salRecordsId;
    private int insideId;
    private String insideTrueName;
    private int targetSales;
    private int actualSales;
    private int unfinishedSales;
    private int month;

    public InsideStaffSalesDto() {
    }

    public int getSalRecordsId() {
        return salRecordsId;
    }

    public void setSalRecordsId(int salRecordsId) {
        this.salRecordsId = salRecordsId;
    }

    public int getInsideId() {
        return insideId;
    }

    public void setInsideId(int insideId) {
        this.insideId = insideId;
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

    public int getTargetSales() {
        return targetSales;
    }

    public void setTargetSales(int targetSales) {
        this.targetSales = targetSales;
    }

    public int getActualSales() {
        return actualSales;
    }

    public void setActualSales(int actualSales) {
        this.actualSales = actualSales;
    }

    public int getUnfinishedSales() {
        return unfinishedSales;
    }

    public void setUnfinishedSales(int unfinishedSales) {
        this.unfinishedSales = unfinishedSales;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
