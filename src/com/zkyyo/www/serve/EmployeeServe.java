package com.zkyyo.www.serve;

import java.util.ArrayList;

import com.zkyyo.www.dao.EmployeeDao;
import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.util.QueryUtil;
import com.zkyyo.www.util.ScannerUtil;
import com.zkyyo.www.view.EmployeeView;

public class EmployeeServe {

    /**
     * 查询员工个人信息
     *
     * @param type    查询的方式
     * @param handler 操作者
     */
    public static void queryEmployeeInfo(int type, EmployeePo handler) {
        EmployeePo foundEp = null;

        switch (type) {
            case 1:
                System.out.println("请输入员工号");
                int searchedUserId = ScannerUtil.scanNum();
                foundEp = QueryUtil.queryEmployeeByUserId(searchedUserId);

                if (foundEp == null) {
                    System.out.println("查无此人");
                    EmployeeView.employeeManage(handler);
                } else {
                    System.out.println("信息如下");//
                    System.out.println("员工号 = " + foundEp.geteUserId());
                    System.out.println("姓名 = " + foundEp.geteName());
                    System.out.println("部门号 = " + foundEp.geteDeptId());
                    System.out.println("电话 = " + foundEp.geteMobile());
                    System.out.println("薪水 = " + foundEp.geteSalary());
                    System.out.println("邮箱 = " + foundEp.geteEmail());
                    System.out.println("就职时间 = " + foundEp.geteEmployDate());
                    EmployeeView.employeeManage(handler);
                }
                break;
            case 2:
                System.out.println("请输入员工名");
                String searchedUserName = ScannerUtil.scanString(true);
                foundEp = QueryUtil.queryEmployeeByUserName(searchedUserName);
                if (foundEp == null) {
                    System.out.println("查无此人");
                    EmployeeView.employeeManage(handler);
                } else {
                    System.out.println("该员工信息如下");
                    System.out.println("员工号 = " + foundEp.geteUserId());
                    System.out.println("姓名 = " + foundEp.geteName());
                    System.out.println("部门号 = " + foundEp.geteDeptId());
                    System.out.println("电话 = " + foundEp.geteMobile());
                    System.out.println("薪水 = " + foundEp.geteSalary());
                    System.out.println("邮箱 = " + foundEp.geteEmail());
                    System.out.println("就职时间 = " + foundEp.geteEmployDate());
                    //回到员工个人信息管理界面
                    EmployeeView.employeeManage(handler);
                }
                break;
            case 3:
                ArrayList<EmployeePo> eps;
                eps = EmployeeDao.queryAllEmployees();
                System.out.println("所有员工信息如下");//
                for (EmployeePo ep : eps) {
//                    System.out.println("员工号 = " + ep.geteUserId());
//                    System.out.println("姓名 = " + ep.geteName());
//                    System.out.println("部门号 = " + ep.geteDeptId());
//                    System.out.println("电话 = " + ep.geteMobile());
//                    System.out.println("薪水 = " + ep.geteSalary());
//                    System.out.println("邮箱 = " + ep.geteEmail());
//                    System.out.println("就职时间 = " + ep.geteEmployDate());
//                    System.out.println();
                    System.out.printf("员工号=%-8d 员工名=%-8s 部门号=%-8d 手机号=%-16s 薪水=%-16.2f 邮箱=%-24s 就职日期%-16s\n",
                            ep.geteUserId(), ep.geteName(), ep.geteDeptId(), ep.geteMobile(),
                            ep.geteSalary(), ep.geteEmail(), ep.geteEmployDate());
                }
                EmployeeView.employeeManage(handler);
                break;
            default:
                System.out.println("bad number");
        }
    }

    public static void updateEmployeeInfo(EmployeePo handler) {
        boolean isUpdate = false;

        System.out.println("请输入需要需改的员工号");
        int updateUserId = ScannerUtil.scanNum();

        EmployeePo foundEp = QueryUtil.queryEmployeeByUserId(updateUserId);
        if (foundEp == null) {
            System.out.println("查无此人");
            EmployeeView.employeeManage(handler);
        } else {
            System.out.println("信息如下");//
            System.out.println("员工号 = " + foundEp.geteUserId());
            System.out.println("姓名 = " + foundEp.geteName());
            System.out.println("部门号 = " + foundEp.geteDeptId());
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
                if (choice == 0) {
                    EmployeeView.employeeManage(handler);
                } else {
                    System.out.println("请输入修改后的信息:");
                    switch (choice) {
                        case 1:
                            int newDepartmentId = ScannerUtil.scanNum();
                            foundEp.seteDeptId(newDepartmentId);
                            isUpdate = EmployeeDao.updateEmployee(updateUserId, 1, foundEp);
                            break;
                        case 2:
                            String newMobile = ScannerUtil.scanString(false);
                            foundEp.seteMobile(newMobile);
                            isUpdate = EmployeeDao.updateEmployee(updateUserId, 2, foundEp);
                            break;
                        case 3:
                            double newSalary = ScannerUtil.scanSalary();
                            foundEp.seteSalary(newSalary);
                            isUpdate = EmployeeDao.updateEmployee(updateUserId, 3, foundEp);
                            break;
                        case 4:
                            String newEmail = ScannerUtil.scanEmail();
                            foundEp.seteEmail(newEmail);
                            isUpdate = EmployeeDao.updateEmployee(updateUserId, 4, foundEp);
                            break;
                        case 5:
                            java.sql.Date newEmployDate = ScannerUtil.scanSqlDate();
                            foundEp.seteEmployDate(newEmployDate);
                            isUpdate = EmployeeDao.updateEmployee(updateUserId, 5, foundEp);
                            break;
                        case 6:
                            String newPwd = ScannerUtil.scanPwd();
                            foundEp.setePassword(newPwd);
                            isUpdate = EmployeeDao.updateEmployee(updateUserId, 6, foundEp);
                            break;
                        default:
                            System.out.println("bad number");
                            break;
                    }
                }
                if (isUpdate) {
                    System.out.println("修改成功, 选择相应选项可继续修改或0退出修改:");
                } else {
                    System.out.println("修改失败, 即将退出修改");
                    EmployeeView.employeeManage(handler);
                }
            } while (true);
        }
    }

    /**
     * 添加新员工
     *
     * @param handler 操作者
     */
    public static void addEmployee(EmployeePo handler) {
        EmployeePo newEp = new EmployeePo();
        boolean isUpdated = false;
        System.out.println("请输入待添加员工的信息:");

        System.out.println("员工号(必选): ");
        int userId = ScannerUtil.scanNum();
        newEp.seteUserId(userId);

        //密码可能含有空格
        System.out.println("密码(必选): ");
        String pwd = ScannerUtil.scanPwd();
        newEp.setePassword(pwd);

        System.out.println("姓名(必选): ");
        String name = ScannerUtil.scanString(true);
        newEp.seteName(name);

        System.out.println("部门号(必选): ");
        int departmentId = ScannerUtil.scanNum();
        newEp.seteDeptId(departmentId);

        System.out.println("薪水(必选): ");
        double salary = ScannerUtil.scanSalary();
        newEp.seteSalary(salary);

        System.out.println("电话(可选): ");
        String mobile = ScannerUtil.scanString(false);
        newEp.seteMobile(mobile);

        System.out.println("邮箱(可选): ");
        String email = ScannerUtil.scanEmail();
        newEp.seteEmail(email);

        System.out.println("*就职时间(格式为xxxx-xx-xx): ");
        java.sql.Date sqlDate = ScannerUtil.scanSqlDate();
        newEp.seteEmployDate(sqlDate);

        isUpdated = EmployeeDao.addEmployee(newEp);
        if (isUpdated) {
            System.out.println("添加员工成功");
        } else {
            System.out.println("添加员工失败");
        }

        EmployeeView.employeeManage(handler);
    }

    /**
     * 删除员工
     *
     * @param handler 操作者
     */
    public static void deleteEmployee(EmployeePo handler) {
        System.out.println("请输入待删除员工的员工号:");
        int deletedUserId = ScannerUtil.scanNum();

        EmployeePo foundEp = QueryUtil.queryEmployeeByUserId(deletedUserId);
        if (foundEp == null) {
            System.out.println("查无此人");
            EmployeeView.employeeManage(handler);
        } else {
            System.out.println("待删除员工信息如下");
            System.out.println("员工号 = " + foundEp.geteUserId());
            System.out.println("姓名 = " + foundEp.geteName());
            System.out.println("部门号 = " + foundEp.geteDeptId());
            System.out.println("电话 = " + foundEp.geteMobile());
            System.out.println("薪水 = " + foundEp.geteSalary());
            System.out.println("邮箱 = " + foundEp.geteEmail());
            System.out.println("就职时间 = " + foundEp.geteEmployDate());

            do {
                System.out.println("是否删除该员工[y/n]:");
                String choice = ScannerUtil.scanString(true);
                String firstLetter = choice.substring(0, 1);

                if (firstLetter.equalsIgnoreCase("y")) {
                    boolean isDeleted = EmployeeDao.deleteEmployee(deletedUserId);
                    if (isDeleted) {
                        System.out.println("你已成功删除该员工");
                    } else {
                        System.out.println("删除员工操作失败");
                    }
                    EmployeeView.employeeManage(handler);
                } else if (firstLetter.equalsIgnoreCase("n")) {
                    EmployeeView.employeeManage(handler);
                } else {
                    System.out.println("请输入正确的选项:");
                }
            } while (true);
        }
    }

}
