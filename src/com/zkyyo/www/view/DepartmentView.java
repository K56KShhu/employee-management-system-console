package com.zkyyo.www.view;

import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.util.ScannerUtil;

public class DepartmentView {
    public static void departmentManage(EmployeePo handler) {
        System.out.println("1. 查询部门");
        System.out.println("2. 修改部门");
        System.out.println("3. 增加部门");
        System.out.println("4. 删除部门");

        int choice = ScannerUtil.scanNum();
    }
}
