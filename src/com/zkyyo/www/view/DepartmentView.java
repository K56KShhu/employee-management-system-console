package com.zkyyo.www.view;

import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.serve.DepartmentServe;
import com.zkyyo.www.util.ScannerUtil;

public class DepartmentView {
    public static void departmentManage(EmployeePo handler) {
        System.out.println("********部门信息管理********");
        System.out.println("1. 查询部门");
        System.out.println("2. 修改部门");
        System.out.println("3. 增加部门");
        System.out.println("4. 删除部门");
        System.out.println("**************************");
        System.out.println("请输入选项(0返回功能选择界面):");

        do {
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    MainView.mainView();
                    break;
                case 1:
                    break;
                case 2:
                    DepartmentServe.updateDept();
                    break;
                case 3:
                    DepartmentServe.addDept();
                    break;
                case 4:
                    DepartmentServe.deleteDept();
                    break;
                default:
                    System.out.println("bad number");
            }
        } while(true);
    }

    public static void departmentQueryWays(EmployeePo handler) {
        System.out.println("1. 通过部门号查询");
        System.out.println("2. 通过部门名字查询");
        System.out.println("3. 显示所有部门");
        System.out.println("请输入选择(0返回部门信息管理界面):");

        do {
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    departmentManage(handler);
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("bad number");
            }
        } while(true);
    }
}
