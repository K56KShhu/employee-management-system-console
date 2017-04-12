package com.zkyyo.www.view;

import com.zkyyo.www.dao.EmployeeDao;
import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.serve.EmployeeServe;
import com.zkyyo.www.util.ScannerUtil;

/**
 * 员工管理界面，以后会加入评价系统
 */
public class EmployeeView {
    public static void employeeManage(EmployeePo handler) {
        System.out.println("********员工个人信息管理********");
        System.out.println("1. 查询员工信息");
        System.out.println("2. 修改员工信息");
        System.out.println("3. 添加员工");
        System.out.println("4. 删除员工");
        System.out.println("0. 返回");
        System.out.println("******************************");
        EmployeeServe epys = EmployeeServe.getInstance();

        do {
            System.out.print(handler.geteName() + "@" + "employee-manage:~$ ");
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    MainView.functionsChoice(handler);
                    break;
                case 1:
                    employeeQueryWays(handler);
                    break;
                case 2:
                    epys.updateEmployeeInfo(handler);
                    break;
                case 3:
                    epys.addEmployee(handler);
                    break;
                case 4:
                    epys.deleteEmployee(handler);
                    break;
                default:
                    System.out.println(choice + ": command not found");
                    break;
            }
        } while (true);
    }

    public static void employeeQueryWays(EmployeePo handler) {
        System.out.println("1. 精确查询 > 通过员工号");
        System.out.println("-  --------------------");
        System.out.println("2. 模糊搜索 > 通过员工号");
        System.out.println("3. 模糊搜索 > 通过员工名字");
        System.out.println("-  --------------------");
        System.out.println("4. 显示所有员工");
        System.out.println("0. 返回");
        System.out.println("******************************");
        EmployeeServe epys = EmployeeServe.getInstance();

        do {
            System.out.print(handler.geteName() + "@" + "employee-manage:~$ ");
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    employeeManage(handler);
                    break;
                case 1:
                    epys.queryEmployee(1, handler);
                    break;
                case 2:
                    epys.queryEmployee(2, handler);
                    break;
                case 3:
                    epys.queryEmployee(3, handler);
                    break;
                case 4:
                    epys.queryEmployee(4, handler);
                default:
                    System.out.println(choice + ": command not found");
                    break;
            }
        } while (true);
    }
}
