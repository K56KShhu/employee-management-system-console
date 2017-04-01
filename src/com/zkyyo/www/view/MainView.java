package com.zkyyo.www.view;

import java.util.Scanner;

import com.zkyyo.www.dao.EmployeeDao;
import com.zkyyo.www.po.EmployeePo;

public class MainView {

    /**
     * 登录界面
     */
    public static void mainView() {
        System.out.println("********员工信息及部门管理系统********");
        System.out.println("1. 登录");
        System.out.println("2. 退出");
        System.out.println("***********************************");
        System.out.print("请选择: ");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                System.out.println("请输入员工号: ");
                int enterNumber = in.nextInt();
                System.out.println("请输入密码:  ");
                String enterPassword = in.next();

                EmployeePo ep = EmployeeDao.loginCheck(enterNumber);
                if (ep == null) {
                    System.out.println("查无此员工号");
                    System.exit(-1);
                } else {
                    if (enterPassword.equals(ep.getePassword()))
                        functionsChoice(ep);
                }
                break;
            case 2:
                System.exit(-1);
                break;
            default:
                System.out.println("bad number");
        }
        System.out.println("bye");
    }

    public static void functionsChoice(EmployeePo handler) {
        System.out.println("********功能********");
        System.out.println("1. 员工个人信息管理");
        System.out.println("*******************");
        System.out.println("你好, " + handler.geteName());
        System.out.println("请选择(0回到登录界面): ");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 0:
                mainView();
                break;
            case 1:
                EmployeeView.personInfoManage(handler);
            default:
                System.out.println("bad number");
        }
    }
}
