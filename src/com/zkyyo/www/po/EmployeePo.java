package com.zkyyo.www.po;

import java.sql.Date;

public class EmployeePo {
    private int eUserId;
    private String ePassword;
    private String eName;
    private int eDeptId;
    private String eMobile;
    private double eSalary;
    private String eEmail;
    private Date eEmployDate;

    public EmployeePo(int eUserId, String ePassword, String eName, int eDeptId,
                      String eMobile, double eSalary, String eEmail, Date eEmployDate) {
        this.eUserId = eUserId;
        this.ePassword = ePassword;
        this.eName = eName;
        this.eDeptId = eDeptId;
        this.eMobile = eMobile;
        this.eSalary = eSalary;
        this.eEmail = eEmail;
        this.eEmployDate = eEmployDate;
    }

    /**
     * 作为默认构造函数，用于添加新员工
     */
    public  EmployeePo() {

    }

    public int geteUserId() {
        return eUserId;
    }
    public void seteUserId(int eUserId) {
        this.eUserId = eUserId;
    }
    public String geteName() {
        return eName;
    }
    public void seteName(String eName) {
        this.eName = eName;
    }
    public String getePassword() {
        return ePassword;
    }
    public void setePassword(String ePassword) {
        this.ePassword = ePassword;
    }
    public int geteDeptId() {
        return eDeptId;
    }
    public void seteDeptId(int eDeptId) {
        this.eDeptId = eDeptId;
    }
    public String geteMobile() {
        return eMobile;
    }
    public void seteMobile(String eMobile) {
        this.eMobile = eMobile;
    }
    public double geteSalary() { return eSalary; }
    public void seteSalary(double eSalary) {
        this.eSalary = eSalary;
    }
    public String geteEmail() {
        return eEmail;
    }
    public void seteEmail(String eEmail) {
        this.eEmail = eEmail;
    }
    public Date geteEmployDate() {
        return eEmployDate;
    }
    public void seteEmployDate(Date eEmployDate) {
        this.eEmployDate = eEmployDate;
    }

    @Override
    public String toString() {
        return "EmployeePo{" +
                "eUserId=" + eUserId +
                ", ePassword='" + ePassword + '\'' +
                ", eName='" + eName + '\'' +
                ", eDeptId=" + eDeptId +
                ", eMobile='" + eMobile + '\'' +
                ", eSalary=" + eSalary +
                ", eEmail='" + eEmail + '\'' +
                ", eEmployDate='" + eEmployDate + '\'' +
                '}';
    }
}
