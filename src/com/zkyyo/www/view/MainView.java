package com.zkyyo.www.view;

import com.zkyyo.www.dao.EmployeeDao;
import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.util.ScannerUtil;

public class MainView {

    /**
     * 登录界面
     */
    public static void mainView() {
        System.out.println("********员工信息及部门管理系统********");
        System.out.println("1. 登录");
        System.out.println("2. 退出");
        System.out.println("***********************************");

        do {
            System.out.print("null@manage-system:~$ ");
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 1:
                    System.out.print("Enter Account: ");
                    int enterUserId = ScannerUtil.scanNum();
                    System.out.print("Enter Password: ");
                    String enterPassword = ScannerUtil.scanPwd();

                    EmployeePo ep = EmployeeDao.loginCheck(enterUserId);
                    if (ep == null) {
                        System.out.println("查无此员工号");
                        mainView();
                    } else {
                        if (enterPassword.equals(ep.getePassword()))
                            functionsChoice(ep);
                        else {
                            System.out.println("密码输入错误");
                            mainView();
                        }
                    }
                    break;
                case 2:
                    System.out.println("Bye");
                    System.exit(-1);
                    break;
                default:
                    System.out.println(choice + ": command not found");
                    break;
            }
        } while (true);
    }

    public static void functionsChoice(EmployeePo handler) {
        System.out.println("********功能********");
        System.out.println("1. 员工管理");
        System.out.println("2. 部门管理");
        System.out.println("3. 评价系统");
        System.out.println("0. 返回");
        System.out.println("*******************");

        do {
            System.out.print(handler.geteName() + "@" + "manage-system:~$ ");
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    mainView();
                    break;
                case 1:
                    EmployeeView.employeeManage(handler);
                    break;
                case 2:
                    DepartmentView.departmentManage(handler);
                    break;
                case 3:
                    EvaluationView.evaluationManage(handler);
                    break;
                default:
                    System.out.println(choice + ": command not found");
                    break;
            }
        } while (true);
    }
}
