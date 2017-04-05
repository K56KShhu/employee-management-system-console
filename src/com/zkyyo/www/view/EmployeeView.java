package com.zkyyo.www.view;

import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.serve.EmployeeServe;
import com.zkyyo.www.util.ScannerUtil;

/**
 * 员工管理界面，以后会加入评价系统
 */
public class EmployeeView {
    public static void employeeManage(EmployeePo handler) {
        System.out.println("********员工个人信息管理********");
        System.out.println("1. 查询员工信息");   //通过员工号,员工名 精确
        System.out.println("2. 修改员工信息");   //通过员工号, 精确
        System.out.println("3. 添加员工");      //通过员工号, 精确
        System.out.println("4. 删除员工");      //通过员工号, 精确
        System.out.println("******************************");
        System.out.println("请选择(0回到功能选择界面)");

        do {
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    MainView.functionsChoice(handler);
                    break;
                case 1:
                    employeeQueryWays(handler);
                    break;
                case 2:
                    EmployeeServe.updateEmployeeInfo(handler);
                    break;
                case 3:
                    EmployeeServe.addEmployee(handler);
                    break;
                case 4:
                    EmployeeServe.deleteEmployee(handler);
                    break;
                default:
                    System.out.println("bad number");
                    break;
            }
        } while (true);
    }

    public static void employeeQueryWays(EmployeePo handler) {
        System.out.println("1. 通过员工号查询");
        System.out.println("2. 通过员工名查询");
        System.out.println("3. 显示所有员工");
        System.out.println("请输入查询的方式(0返回员工个人信息管理界面): ");

        do {
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    employeeManage(handler);
                    break;
                case 1:
                    EmployeeServe.queryEmployeeInfo(1, handler);
                    break;
                case 2:
                    EmployeeServe.queryEmployeeInfo(2, handler);
                    break;
                case 3:
                    EmployeeServe.queryEmployeeInfo(3, handler);
                    break;
                default:
                    System.out.println("bad number");
                    break;
            }
        } while (true);
    }
}
