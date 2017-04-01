package com.zkyyo.www.serve;

import java.util.ArrayList;
import java.util.Scanner;

import com.zkyyo.www.dao.EmployeeDao;
import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.util.QueryUtil;
import com.zkyyo.www.view.EmployeeView;

public class EmployeeServe {

    /**
     * 精确查询员工个人信息
     * @param handler 记录操作者
     */
    public static void queryEmployeeInfo(EmployeePo handler) {
        System.out.println("1. 通过员工号查询");
        System.out.println("2. 通过员工名查询");
        System.out.println("3. 显示所有员工");
        System.out.println("请输入查询的方式(0返回员工个人信息管理界面): ");

        do {
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    EmployeeView.personInfoManage(handler);
                    break;
                case 1:
                    System.out.println("请输入员工号");
                    int searchNumber = in.nextInt();
                    EmployeePo foundEp = QueryUtil.queryEmployeeByNumber(searchNumber);

                    if (foundEp == null) {
                        System.out.println("查无此人");
                         EmployeeView.personInfoManage(handler);
                    } else {
                        System.out.println("信息如下");//
                        System.out.println("员工号 = " + foundEp.geteNumber());
                        System.out.println("姓名 = " + foundEp.geteName());
                        System.out.println("部门号 = " + foundEp.geteDepartmentId());
                        System.out.println("电话 = " + foundEp.geteMobile());
                        System.out.println("薪水 = " + foundEp.geteSalary());
                        System.out.println("邮箱 = " + foundEp.geteEmail());
                        System.out.println("就职时间 = " + foundEp.geteEmployDate());
                        EmployeeView.personInfoManage(handler);
                    }
                    break;
                case 2:
                    System.out.println("请输入员工名");
                    String searchName = in.next();
                    EmployeePo foundEp2 = QueryUtil.queryEmployeeByName(searchName);
                    if (foundEp2 == null) {
                        System.out.println("查无此人");
                        EmployeeView.personInfoManage(handler);
                    } else {
                        System.out.println("该员工信息如下");
                        System.out.println("员工号 = " + foundEp2.geteNumber());
                        System.out.println("姓名 = " + foundEp2.geteName());
                        System.out.println("部门号 = " + foundEp2.geteDepartmentId());
                        System.out.println("电话 = " + foundEp2.geteMobile());
                        System.out.println("薪水 = " + foundEp2.geteSalary());
                        System.out.println("邮箱 = " + foundEp2.geteEmail());
                        System.out.println("就职时间 = " + foundEp2.geteEmployDate());
                        //回到员工个人信息管理界面
                        EmployeeView.personInfoManage(handler);
                    }
                    break;
                case 3:
                    ArrayList<EmployeePo> eps = new ArrayList<EmployeePo>();
                    eps = EmployeeDao.queryAllEmployees();
                    System.out.println("所有员工信息如下");//
                    for (EmployeePo ep : eps) {
                        System.out.println("员工号 = " + ep.geteNumber());
                        System.out.println("姓名 = " + ep.geteName());
                        System.out.println("部门号 = " + ep.geteDepartmentId());
                        System.out.println("电话 = " + ep.geteMobile());
                        System.out.println("薪水 = " + ep.geteSalary());
                        System.out.println("邮箱 = " + ep.geteEmail());
                        System.out.println("就职时间 = " + ep.geteEmployDate());
                        System.out.println();
                    }
                    EmployeeView.personInfoManage(handler);
                    break;
                default:
                    System.out.println("bad number");
            }
        } while (true);
    }

    public static void updateEmployeeInfo(EmployeePo handler) {
        boolean isUpdate = false;

        System.out.println("请输入需要需改的员工号");
        Scanner in = new Scanner(System.in);
        int number = Integer.valueOf(in.nextLine());

        EmployeePo foundEp = QueryUtil.queryEmployeeByNumber(number);
        if (foundEp == null) {
            System.out.println("查无此人");
            EmployeeView.personInfoManage(handler);
        } else {
            System.out.println("信息如下");//
            System.out.println("员工号 = " + foundEp.geteNumber());
            System.out.println("姓名 = " + foundEp.geteName());
            System.out.println("部门号 = " + foundEp.geteDepartmentId());
            System.out.println("电话 = " + foundEp.geteMobile());
            System.out.println("薪水 = " + foundEp.geteSalary());
            System.out.println("邮箱 = " + foundEp.geteEmail());
            System.out.println("就职时间 = " + foundEp.geteEmployDate());
            System.out.println();

            System.out.println("1. 部门号");
            System.out.println("2. 电话");
            System.out.println("3. 薪水");
            System.out.println("4. 邮箱");
            System.out.println("5. 就职时间");
            System.out.println("6. 密码");
            System.out.println("请选择需要修改的信息(0返回员工个人信息管理界面):");

            do {
                int choice = Integer.valueOf(in.nextLine());
                if (choice == 0)
                    EmployeeView.personInfoManage(handler);
                else {
                    System.out.println("请输入修改后的信息:");
                    String updateInfo = in.nextLine();
                    switch (choice) {
                        case 1:
                            int newDepartmentId = Integer.valueOf(updateInfo);
                            foundEp.seteDepartmentId(newDepartmentId);
                            isUpdate = EmployeeDao.updateEmployee(number, 1, foundEp);
                            break;
                        case 2:
                            foundEp.seteMobile(updateInfo);
                            isUpdate = EmployeeDao.updateEmployee(number, 2, foundEp);
                            break;
                        case 3:
                            double newSalary = Double.valueOf(updateInfo);
                            foundEp.seteSalary(newSalary);
                            isUpdate = EmployeeDao.updateEmployee(number, 3, foundEp);
                            break;
                        case 4:
                            foundEp.seteEmail(updateInfo);
                            isUpdate = EmployeeDao.updateEmployee(number, 4, foundEp);
                            break;
                        case 5:
                            foundEp.seteEmployDate(updateInfo);
                            isUpdate = EmployeeDao.updateEmployee(number, 5, foundEp);
                            break;
                        case 6:
                            foundEp.setePassword(updateInfo);
                            isUpdate = EmployeeDao.updateEmployee(number, 6, foundEp);
                            break;
                        default:
                            System.out.println("bad number");
                    }
                }
                if (isUpdate)
                    System.out.println("修改成功, 选择相应选项可继续修改或0退出修改:");
                else {
                    System.out.println("修改失败, 即将退出修改");
                    EmployeeView.personInfoManage(handler);
                }
            } while (true);
        }
    }

    /**
     * 添加新员工
     * @param handler 操作者
     */
    public static void addEmployee(EmployeePo handler) {
        EmployeePo newEp = new EmployeePo();
        Scanner in = new Scanner(System.in);
        boolean isUpdated = false;
        System.out.println("请输入待添加员工的信息(带*为必选):");

        /*
        nextLine()会接收前面nextInt()留下的换行符
         */
        try {
            System.out.print("*员工号: ");
            int number = Integer.valueOf(in.nextLine());
            newEp.seteNumber(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        System.out.print("*密码: ");
        newEp.setePassword(in.nextLine());
        System.out.print("*姓名: ");
        newEp.seteName(in.nextLine());

        try {
            System.out.print("*部门号: ");
            int departmentId = Integer.valueOf(in.nextLine());
            newEp.seteDepartmentId(departmentId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            System.out.print("*薪水: ");
            double salary = Double.valueOf(in.nextLine());
            newEp.seteSalary(salary);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        System.out.print(" 电话: ");
        newEp.seteMobile(in.nextLine());

        System.out.print(" 邮箱: ");
        newEp.seteEmail(in.nextLine());

        System.out.print("*就职时间: ");
        newEp.seteEmployDate(in.nextLine());

        isUpdated = EmployeeDao.addEmployee(newEp);
        if (isUpdated) {
            System.out.println("添加员工成功");
            EmployeeView.personInfoManage(handler);
        }
        else {
            System.out.println("添加员工失败");
            EmployeeView.personInfoManage(handler);
        }
    }

    /**
     * 删除员工
     * @param handler 操作者
     */
    public static void deleteEmployee(EmployeePo handler) {
        System.out.println("请输入待删除员工的员工号:");

        Scanner in = new Scanner(System.in);
        int deletedNumber = in.nextInt();

        EmployeePo foundEp = QueryUtil.queryEmployeeByNumber(deletedNumber);

        if (foundEp == null) {
            System.out.println("查无此人");
            EmployeeView.personInfoManage(handler);
        }
        else {
            System.out.println("待删除员工信息如下");
            System.out.println("员工号 = " + foundEp.geteNumber());
            System.out.println("姓名 = " + foundEp.geteName());
            System.out.println("部门号 = " + foundEp.geteDepartmentId());
            System.out.println("电话 = " + foundEp.geteMobile());
            System.out.println("薪水 = " + foundEp.geteSalary());
            System.out.println("邮箱 = " + foundEp.geteEmail());
            System.out.println("就职时间 = " + foundEp.geteEmployDate());

            do {
                System.out.println("是否删除该员工[y/n]:");
                String choice = in.next();

                if (choice.equalsIgnoreCase("y")) {
                    boolean isDeleted = EmployeeDao.deleteEmployee(deletedNumber);
                    if (isDeleted) {
                        System.out.println("你已成功删除该员工");
                        EmployeeView.personInfoManage(handler);
                    }
                    else {
                        System.out.println("删除员工操作失败");
                        EmployeeView.personInfoManage(handler);
                    }
                } else if (choice.equalsIgnoreCase("n")) {
                    EmployeeView.personInfoManage(handler);
                } else {
                    System.out.println("请输入正确的选项:");
                }
            } while (true);
        }
    }

}
