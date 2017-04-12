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
        System.out.println("0. 返回");
        System.out.println("**************************");
        DepartmentServe ds = DepartmentServe.getInstance();

        do {
            System.out.print(handler.geteName() + "@" + "department-manage:~$ ");
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    MainView.functionsChoice(handler);
                    break;
                case 1:
                    departmentQueryWays(handler);
                    break;
                case 2:
                    ds.updateDept(handler);
                    break;
                case 3:
                    ds.addDept(handler);
                    break;
                case 4:
                    ds.deleteDept(handler);
                    break;
                default:
                    System.out.println(choice + ": command not found");
                    break;
            }
        } while (true);
    }

    public static void departmentQueryWays(EmployeePo handler) {
        System.out.println("1. 精确查询 > 通过部门号");
        System.out.println("-  --------------------");
        System.out.println("2. 模糊查询 > 通过部门号");
        System.out.println("3. 模糊查询 > 通过部门名字");
        System.out.println("4. 模糊查询 > 通过员工号");
        System.out.println("5. 模糊查询 > 铜鼓员工名字");
        System.out.println("-  --------------------");
        System.out.println("6. 显示所有部门");
        System.out.println("0. 返回");
        System.out.println("**************************");
        DepartmentServe ds = DepartmentServe.getInstance();

        do {
            System.out.print(handler.geteName() + "@" + "department-manage:~$ ");
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    departmentManage(handler);
                    break;
                case 1:
                    ds.queryDepartment(1, handler);
                    break;
                case 2:
                    ds.queryDepartment(2, handler);
                    break;
                case 3:
                    ds.queryDepartment(3, handler);
                    break;
                case 4:
                    ds.queryDepartment(4, handler);
                    break;
                case 5:
                    ds.queryDepartment(5, handler);
                    break;
                case 6:
                    ds.queryDepartment(6, handler);
                    break;
                default:
                    System.out.println(choice + ": command not found");
                    break;
            }
        } while (true);
    }
}
