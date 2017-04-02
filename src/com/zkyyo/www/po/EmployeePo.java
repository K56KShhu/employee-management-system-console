package com.zkyyo.www.po;

import java.sql.Date;

public class EmployeePo {
    private int eNumber;
    private String ePassword;
    private String eName;
    private int eDepartmentId;
    private String eMobile;
    private double eSalary;
    private String eEmail;
    private Date eEmployDate;

    public EmployeePo(int eNumber, String ePassword, String eName, int eDepartmentId,
                      String eMobile, double eSalary, String eEmail, Date eEmployDate) {
        this.eNumber = eNumber;
        this.ePassword = ePassword;
        this.eName = eName;
        this.eDepartmentId = eDepartmentId;
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

    public int geteNumber() {
        return eNumber;
    }
    public void seteNumber(int eNumber) {
        this.eNumber = eNumber;
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
    public int geteDepartmentId() {
        return eDepartmentId;
    }
    public void seteDepartmentId(int eDepartmentId) {
        this.eDepartmentId = eDepartmentId;
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
                "eNumber=" + eNumber +
                ", ePassword='" + ePassword + '\'' +
                ", eName='" + eName + '\'' +
                ", eDepartmentId=" + eDepartmentId +
                ", eMobile='" + eMobile + '\'' +
                ", eSalary=" + eSalary +
                ", eEmail='" + eEmail + '\'' +
                ", eEmployDate='" + eEmployDate + '\'' +
                '}';
    }
}
