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
        System.out.println("请选择: ");

        do {
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 1:
                    System.out.println("请输入员工号: ");
                    int enterUserId = ScannerUtil.scanNum();
                    System.out.println("请输入密码:  ");
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
                    System.out.println("bye");
                    System.exit(-1);
                    break;
                default:
                    System.err.println("无效选项,请重新输入:");
                    break;
            }
        } while (true);
    }

    public static void functionsChoice(EmployeePo handler) {
        System.out.println("********功能********");
        System.out.println("1. 员工个人信息管理");
        System.out.println("2. 部门信息管理");
        System.out.println("3. 评价系统");
        System.out.println("*******************");
        System.out.println("你好, " + handler.geteName());
        System.out.println("请选择(0回到登录界面): ");

        do {
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
                    System.err.println("无效选项,请重新输入:");
                    break;
            }
        } while (true);
    }
}
