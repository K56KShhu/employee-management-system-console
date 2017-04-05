package com.zkyyo.www.serve;

import com.zkyyo.www.dao.DepartmentDao;
import com.zkyyo.www.po.DepartmentPo;
import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.util.QueryUtil;
import com.zkyyo.www.util.ScannerUtil;
import com.zkyyo.www.view.DepartmentView;

import java.util.ArrayList;

public class DepartmentServe {
    public static void addDept(EmployeePo handler) {
        DepartmentPo newDept = new DepartmentPo();
        boolean isAdded = false;
        System.out.println("开始建立新的部门,请输入必要的信息");

        System.out.println("部门号(必选):");
        int newDeptId = ScannerUtil.scanNum();
        newDept.setDeptId(newDeptId);

        System.out.println("部门名(必选):");
        String newDeptName = ScannerUtil.scanString(true);
        newDept.setDeptName(newDeptName);

        System.out.println("部门描述(可选,默认为无):");
        String newDesc = ScannerUtil.scanString(false);
        newDept.setDeptDesc(newDesc);

        System.out.println("建立日期(可选,默认为当前日期):");
        java.sql.Date newBuiltDate = ScannerUtil.scanSqlDate();
        newDept.setBuiltDate(newBuiltDate);

        isAdded = DepartmentDao.addDepartment(newDept);
        if (isAdded) {
            System.out.println("建立部门成功");
        } else {
            System.out.println("建立部门失败");
        }
        DepartmentView.departmentManage(handler);
    }

    public static void deleteDept(EmployeePo handler) {
        System.out.println("请输入需要删除的部门Id");
        int deletedDeptId = ScannerUtil.scanNum();

        DepartmentPo deletedDept = QueryUtil.queryDeptByDeptId(deletedDeptId);
        if (deletedDept == null) {
            System.out.println("该部门不存在");
            DepartmentView.departmentManage(handler);
        } else {
            System.out.println("信息如下");
            System.out.println("部门号: " + deletedDept.getDeptId());
            System.out.println("部门名: " + deletedDept.getDeptName());
            System.out.println("部门人数: " + deletedDept.getDeptPopulation());
            System.out.println("部门描述: " + deletedDept.getDeptDesc());
            System.out.println("建立时间: " + deletedDept.getBuiltDate());

            do {
                System.out.println("是否删除该部门[y/n]:");
                String choice = ScannerUtil.scanString(true);
                String firstLetter = choice.substring(0, 1);

                if (firstLetter.equalsIgnoreCase("y")) {
                    boolean isDeleted = DepartmentDao.deleteDept(deletedDeptId);
                    if (isDeleted) {
                        System.out.println("你已成功删除该部门");
                    } else {
                        System.out.println("删除部门操作失败");
                    }
                    DepartmentView.departmentManage(handler);
                } else if (firstLetter.equalsIgnoreCase("n")) {
                    DepartmentView.departmentManage(handler);
                } else {
                    System.out.println("请输入正确的选项:");
                }
            } while (true);

        }

    }

    public static void updateDept(EmployeePo handler) {
        boolean isUpdate = false;

        System.out.println("请输入需要修改的部门号:");
        int updatedDeptId = ScannerUtil.scanNum();
        DepartmentPo foundDept = QueryUtil.queryDeptByDeptId(updatedDeptId);
        if (foundDept == null) {
            System.out.println("查无此部门");
        } else {
            System.out.println("信息如下");
            System.out.println("部门号: " + foundDept.getDeptId());
            System.out.println("部门名: " + foundDept.getDeptName());
            System.out.println("部门人数: " + foundDept.getDeptPopulation());
            System.out.println("部门描述: " + foundDept.getDeptDesc());
            System.out.println("建立时间: " + foundDept.getBuiltDate());
            System.out.println();

            System.out.println("1. 部门名");
            System.out.println("2. 部门描述");
            System.out.println("3. 建立时间");

            do {
                System.out.println("请输入待修改的选项(0返回部门信息管理):");
                int choice = ScannerUtil.scanNum();
                switch (choice) {
                    //返回 部门信息管理
                    case 0:
                        DepartmentView.departmentManage(handler);
                        break;
                    //部门名
                    case 1:
                        System.out.println("请输入修改后的部门名(必选):");
                        String newDeptName = ScannerUtil.scanString(true);
                        foundDept.setDeptName(newDeptName);
                        isUpdate = DepartmentDao.updateDept(updatedDeptId, 1, foundDept);
                        break;
                    //部门描述
                    case 2:
                        System.out.println("请输入修改后的部门描述(可选):");
                        String newDeptDesc = ScannerUtil.scanString(false);
                        foundDept.setDeptDesc(newDeptDesc);
                        isUpdate = DepartmentDao.updateDept(updatedDeptId, 2, foundDept);
                        break;
                    //建立时间
                    case 3:
                        System.out.println("请输入修改后的部门建立时间(选择跳过即设置为当前时期):");
                        java.sql.Date newBuiltDate = ScannerUtil.scanSqlDate();
                        foundDept.setBuiltDate(newBuiltDate);
                        isUpdate = DepartmentDao.updateDept(updatedDeptId, 3, foundDept);
                        break;
                    default:
                        System.out.println("bad number");
                        break;
                }
                if (isUpdate) {
                    System.out.println("修改成功,继续修改或0返回部门信息管理:");
                } else {
                    System.out.println("修改失败,正在返回部门信息管理");
                    DepartmentView.departmentManage(handler);
                }
            } while (true);
        }
    }

    public static void queryDeptInfo(int type, EmployeePo handler) {
        DepartmentPo foundDept = null;

        switch (type) {
            case 1:
                System.out.println("请输入需要查询的部门号:");
                int deptId = ScannerUtil.scanNum();
                foundDept = QueryUtil.queryDeptByDeptId(deptId);

                if (foundDept == null) {
                    System.out.println("查无此部门");
                    DepartmentView.departmentManage(handler);
                } else {
                    System.out.println("信息如下");
                    System.out.println("部门号: " + foundDept.getDeptId());
                    System.out.println("部门名: " + foundDept.getDeptName());
                    System.out.println("部门人数: " + foundDept.getDeptPopulation());
                    System.out.println("部门描述: " + foundDept.getDeptDesc());
                    System.out.println("建立时间: " + foundDept.getBuiltDate());
                    DepartmentView.departmentManage(handler);
                }
                break;
            case 2:
                System.out.println("请输入需要查询的部门名:");
                String deptName = ScannerUtil.scanString(true);
                foundDept = QueryUtil.queryDeptByDeptName(deptName);

                if (foundDept == null) {
                    System.out.println("查无此部门");
                } else {
                    System.out.println("信息如下");
                    System.out.println("部门号: " + foundDept.getDeptId());
                    System.out.println("部门名: " + foundDept.getDeptName());
                    System.out.println("部门人数: " + foundDept.getDeptPopulation());
                    System.out.println("部门描述: " + foundDept.getDeptDesc());
                    System.out.println("建立时间: " + foundDept.getBuiltDate());
                }
                DepartmentView.departmentManage(handler);
                break;
            case 3:
                ArrayList<DepartmentPo> depts = DepartmentDao.queryAllDepts();
                if (depts.size() == 0) {
                    System.out.println("找不到任何部门");
                } else {
                    for (DepartmentPo dept : depts) {
                        System.out.println("部门号: " + dept.getDeptId());
                        System.out.println("部门名: " + dept.getDeptName());
                        System.out.println("部门人数: " + dept.getDeptPopulation());
                        System.out.println("部门描述: " + dept.getDeptDesc());
                        System.out.println("建立时间: " + dept.getBuiltDate());
                        System.out.println();
                    }
                }
                DepartmentView.departmentManage(handler);
                break;
        }
    }
}
