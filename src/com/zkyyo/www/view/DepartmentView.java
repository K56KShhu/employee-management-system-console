package com.zkyyo.www.view;

import com.zkyyo.www.dao.DepartmentDao;
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
                    MainView.functionsChoice(handler);
                    break;
                case 1:
                    departmentQueryWays(handler);
                    break;
                case 2:
                    DepartmentServe.updateDept(handler);
                    break;
                case 3:
                    DepartmentServe.addDept(handler);
                    break;
                case 4:
                    DepartmentServe.deleteDept(handler);
                    break;
                default:
                    System.err.println("无效选项,请重新输入:");
                    break;
            }
        } while (true);
    }

    public static void departmentQueryWays(EmployeePo handler) {
        System.out.println("1. 通过部门号查询");
        System.out.println("2. 通过部门名字查询");
        System.out.println("3. 通过员工号查询");
        System.out.println("4. 通过员工名查询");
        System.out.println("5. 显示所有部门");
        System.out.println("请输入选择(0返回部门信息管理界面):");

        do {
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    departmentManage(handler);
                    break;
                case 1:
                    DepartmentServe.queryDepartment(1, handler);
                    break;
                case 2:
                    DepartmentServe.queryDepartment(2, handler);
                    break;
                case 3:
                    DepartmentServe.queryDepartment(3, handler);
                    break;
                case 4:
                    DepartmentServe.queryDepartment(4, handler);
                    break;
                case 5:
                    DepartmentServe.queryDepartment(5, handler);
                    break;
                default:
                    System.err.println("无效选项,请重新输入:");
                    break;
            }
        } while (true);
    }
}
