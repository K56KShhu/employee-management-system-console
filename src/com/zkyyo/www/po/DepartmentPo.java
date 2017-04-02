package com.zkyyo.www.po;

public class DepartmentPo {
    private int deptId;
    private String deptName;
    private int deptPopulation;
    private String deptDesc;

    public DepartmentPo() {

    }

    public DepartmentPo(int deptId, String deptName, int deptPopulation, String deptDesc) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.deptPopulation = deptPopulation;
        this.deptDesc = deptDesc;
    }

    public int getDeptId() { return deptId; }
    public void setDeptId(int deptId) { this.deptId = deptId; }
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
    public int getDeptPopulation() { return deptPopulation; }
    public void setDeptPopulation(int deptPopulation) { this.deptPopulation = deptPopulation; }
    public String getDeptDesc() { return deptDesc; }
    public void setDeptDesc(String deptDesc) { this.deptDesc = deptDesc; }

    @Override
    public String toString() {
        return "DepartmentPo{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", deptPopulation=" + deptPopulation +
                ", deptDesc='" + deptDesc + '\'' +
                '}';
    }
}
