package com.zkyyo.www.util;

import com.zkyyo.www.dao.DepartmentDao;
import com.zkyyo.www.dao.EmployeeDao;
import com.zkyyo.www.po.DepartmentPo;
import com.zkyyo.www.po.EmployeePo;

import java.util.ArrayList;
import java.util.List;

public class QueryUtil {

    public static EmployeePo queryEmployeeByUserId(int userId) {
        EmployeeDao epd = EmployeeDao.getInstance();
        EmployeePo foundEp = epd.selectEmployeeByUserId(userId);
        if (foundEp == null) {
            System.out.println("查无此人");
        } else {
            System.out.println("信息如下");//
            System.out.printf("员工号:%-12d 员工名:%-8s 部门号:%-6d 手机号:%-14s 薪水:%-16.2f 邮箱:%-30s 就职日期:%-16s\n",
                    foundEp.geteUserId(), foundEp.geteName(), foundEp.geteDeptId(), foundEp.geteMobile(),
                    foundEp.geteSalary(), foundEp.geteEmail(), foundEp.geteEmployDate());
        }
        return foundEp;
    }



    public static <T> EmployeePo fuzzyQueryEmployee(T userInfo) {
        EmployeeDao epd = EmployeeDao.getInstance();
        List<EmployeePo> list = new ArrayList<>();
        EmployeePo accurateEp = null;

        if (userInfo instanceof Integer) {
            int userId = (Integer) userInfo;
            list = epd.selectPossibleEmployeesByUserId(userId);
        } else if (userInfo instanceof String) {
            String userName = (String) userInfo;
            list =epd.selectPossibleEmployeesByUserName(userName);
        }

        if (list.size() == 0) {
            System.out.println("找不到匹配的员工号");
        } else if (list.size() == 1) {
            accurateEp = list.get(0);
        } else {
            System.out.println("匹配结果如下");
            for (int i = 0; i < list.size(); i++) {
                EmployeePo e = list.get(i);
                System.out.printf("索引:%-4d 员工号:%-12d 员工名:%-8s 部门号:%-6d\n",
                        i + 1, e.geteUserId(), e.geteName(), e.geteDeptId());
            }
            System.out.println("请输入相应的索引查询详细信息(或0退出):");
            do {
                int index = ScannerUtil.scanNum();
                if (index == 0) {
                    break;
                } else if (index > list.size()) {
                    System.out.println("索引无效,请重新输入");
                } else {
                    accurateEp = list.get(index - 1);
                    break;
                }
            } while (true);
        }
        return accurateEp;
    }

    public static DepartmentPo queryDepartmentByDeptrId(int deptId) {
        DepartmentDao dd = DepartmentDao.getInstance();
        DepartmentPo foundDept = dd.selectDepartmentByDeptId(deptId);
        if (foundDept == null) {
            System.out.println("查无此部门");
        } else {
            System.out.println("信息如下");
            System.out.printf("部门号:%-6d 部门名:%-12s 人数:%-6d 建立时间:%-16s 部门描述:%s\n",
                    foundDept.getDeptId(), foundDept.getDeptName(), foundDept.getDeptPopulation(),
                    foundDept.getBuiltDate(), foundDept.getDeptDesc());
        }
        return foundDept;
    }

    public static <T> DepartmentPo fuzzyQueryDepartment(T deptInfo) {
        DepartmentDao dd = DepartmentDao.getInstance();
        List<DepartmentPo> list = new ArrayList<>();
        DepartmentPo accurateDept = null;

        if (deptInfo instanceof Integer) {
            int deptId = (Integer) deptInfo;
            list = dd.selectPossibleDepartmentsByDeptId(deptId);
        } else if (deptInfo instanceof String) {
            String deptName = (String) deptInfo;
            list = dd.selectPossibleDepartmentByDeptName(deptName);
        }

        if (list.size() == 0) {
            System.out.println("找不到匹配的部门");
        } else if (list.size() == 1) {
            accurateDept = list.get(0);
        } else {
            System.out.println("匹配结果如下");
            for (int i = 0; i < list.size(); i++) {
                DepartmentPo dept = list.get(i);
                System.out.printf("部门号:%-6d 部门名:%-12s 人数:%-6d 建立时间:%-12s 部门描述:%s",
                        dept.getDeptId(), dept.getDeptName(), dept.getDeptPopulation(),
                        dept.getBuiltDate(), dept.getDeptDesc());

            }
            System.out.println("请输入相应的索引查询详细信息(或0退出):");
            do {
                int index = ScannerUtil.scanNum();
                if (index == 0) {
                    break;
                } else if (index > list.size()) {
                    System.out.println("索引无效,请重新输入");
                } else {
                    accurateDept= list.get(index - 1);
                    break;
                }
            } while (true);
        }
        return accurateDept;
    }
}
