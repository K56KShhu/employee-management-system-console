package com.zkyyo.www.serve;

import java.util.ArrayList;

import com.zkyyo.www.dao.EmployeeDao;
import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.util.QueryUtil;
import com.zkyyo.www.util.ScannerUtil;
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
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    EmployeeView.employeeManage(handler);
                    break;
                case 1:
                    System.out.println("请输入员工号");
                    int searchNumber = ScannerUtil.scanNum();
                    EmployeePo foundEp = QueryUtil.queryEmployeeByNumber(searchNumber);

                    if (foundEp == null) {
                        System.out.println("查无此人");
                         EmployeeView.employeeManage(handler);
                    } else {
                        System.out.println("信息如下");//
                        System.out.println("员工号 = " + foundEp.geteNumber());
                        System.out.println("姓名 = " + foundEp.geteName());
                        System.out.println("部门号 = " + foundEp.geteDepartmentId());
                        System.out.println("电话 = " + foundEp.geteMobile());
                        System.out.println("薪水 = " + foundEp.geteSalary());
                        System.out.println("邮箱 = " + foundEp.geteEmail());
                        System.out.println("就职时间 = " + foundEp.geteEmployDate());
                        EmployeeView.employeeManage(handler);
                    }
                    break;
                case 2:
                    System.out.println("请输入员工名");
                    String searchName = ScannerUtil.scanString();
                    EmployeePo foundEp2 = QueryUtil.queryEmployeeByName(searchName);
                    if (foundEp2 == null) {
                        System.out.println("查无此人");
                        EmployeeView.employeeManage(handler);
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
                        EmployeeView.employeeManage(handler);
                    }
                    break;
                case 3:
                    ArrayList<EmployeePo> eps;
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
                    EmployeeView.employeeManage(handler);
                    break;
                default:
                    System.out.println("bad number");
            }
        } while (true);
    }

    public static void updateEmployeeInfo(EmployeePo handler) {
        boolean isUpdate = false;

        System.out.println("请输入需要需改的员工号");
        int number = ScannerUtil.scanNum();

        EmployeePo foundEp = QueryUtil.queryEmployeeByNumber(number);
        if (foundEp == null) {
            System.out.println("查无此人");
            EmployeeView.employeeManage(handler);
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
                int choice = ScannerUtil.scanNum();
                if (choice == 0)
                    EmployeeView.employeeManage(handler);
                else {
                    System.out.println("请输入修改后的信息:");
                    switch (choice) {
                        case 1:
                            int newDepartmentId = ScannerUtil.scanNum();
                            foundEp.seteDepartmentId(newDepartmentId);
                            isUpdate = EmployeeDao.updateEmployee(number, 1, foundEp);
                            break;
                        case 2:
                            String newMobile = ScannerUtil.scanString();
                            foundEp.seteMobile(newMobile);
                            isUpdate = EmployeeDao.updateEmployee(number, 2, foundEp);
                            break;
                        case 3:
                            double newSalary = ScannerUtil.scanSalary();
                            foundEp.seteSalary(newSalary);
                            isUpdate = EmployeeDao.updateEmployee(number, 3, foundEp);
                            break;
                        case 4:
                            String newEmail = ScannerUtil.scanEmail();
                            foundEp.seteEmail(newEmail);
                            isUpdate = EmployeeDao.updateEmployee(number, 4, foundEp);
                            break;
                        case 5:
                            java.sql.Date newEmployDate = ScannerUtil.scanSqlDate();
                            foundEp.seteEmployDate(newEmployDate);
                            isUpdate = EmployeeDao.updateEmployee(number, 5, foundEp);
                            break;
                        case 6:
                            String newPwd = ScannerUtil.scanPwd();
                            foundEp.setePassword(newPwd);
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
                    EmployeeView.employeeManage(handler);
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
        boolean isUpdated = false;
        System.out.println("请输入待添加员工的信息(带*为必选):");

        System.out.println("*员工号: ");
        int number = ScannerUtil.scanNum();
        newEp.seteNumber(number);

        //密码可能含有空格
        System.out.println("*密码: ");
        String pwd = ScannerUtil.scanPwd();
        newEp.setePassword(pwd);

        System.out.println("*姓名: ");
        String name = ScannerUtil.scanString();
        newEp.seteName(name);

        System.out.println("*部门号: ");
        int departmentId = ScannerUtil.scanNum();
        newEp.seteDepartmentId(departmentId);

        System.out.println("*薪水: ");
        double salary = ScannerUtil.scanSalary();
        newEp.seteSalary(salary);

        System.out.println(" 电话: ");
        String mobile = ScannerUtil.scanString();
        newEp.seteMobile(mobile);

        System.out.println(" 邮箱: ");
        String email = ScannerUtil.scanEmail();
        newEp.seteEmail(email);

        System.out.println("*就职时间: ");
        java.sql.Date sqlDate = ScannerUtil.scanSqlDate();
        newEp.seteEmployDate(sqlDate);

        isUpdated = EmployeeDao.addEmployee(newEp);
        if (isUpdated) {
            System.out.println("添加员工成功");
            EmployeeView.employeeManage(handler);
        }
        else {
            System.out.println("添加员工失败");
            EmployeeView.employeeManage(handler);
        }
    }

    /**
     * 删除员工
     * @param handler 操作者
     */
    public static void deleteEmployee(EmployeePo handler) {
        System.out.println("请输入待删除员工的员工号:");
        int deletedNumber = ScannerUtil.scanNum();

        EmployeePo foundEp = QueryUtil.queryEmployeeByNumber(deletedNumber);
        if (foundEp == null) {
            System.out.println("查无此人");
            EmployeeView.employeeManage(handler);
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
                String choice = ScannerUtil.scanString();
                String firstLetter = choice.substring(0, 1);

                if (firstLetter.equalsIgnoreCase("y")) {
                    boolean isDeleted = EmployeeDao.deleteEmployee(deletedNumber);
                    if (isDeleted) {
                        System.out.println("你已成功删除该员工");
                        EmployeeView.employeeManage(handler);
                    }
                    else {
                        System.out.println("删除员工操作失败");
                        EmployeeView.employeeManage(handler);
                    }
                } else if (choice.equalsIgnoreCase("n")) {
                    EmployeeView.employeeManage(handler);
                } else {
                    System.out.println("请输入正确的选项:");
                }
            } while (true);
        }
    }

}
