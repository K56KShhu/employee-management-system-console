package com.zkyyo.www.view;

import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.serve.EmployeeServe;

import java.util.Scanner;

/**
 * 员工管理界面，以后会加入评价系统
 */
public class EmployeeView {
       public static void personInfoManage(EmployeePo handler) {
        System.out.println("********员工个人信息管理********");
        System.out.println("1. 查询员工信息");
        System.out.println("2. 修改员工信息");
        System.out.println("3. 添加员工");
        System.out.println("4. 删除员工");
        System.out.println("******************************");
        System.out.println("请选择(0回到功能选择界面)");

        do {
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    MainView.functionsChoice(handler);
                    break;
                case 1:
                    EmployeeServe.queryEmployeeInfo(handler);
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
            }
        } while (true);
    }
}
