package com.zkyyo.www.serve;

import com.zkyyo.www.dao.DepartmentDao;
import com.zkyyo.www.dao.EmployeeDao;
import com.zkyyo.www.po.DepartmentPo;
import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.util.CreateIdUtil;
import com.zkyyo.www.util.QueryUtil;
import com.zkyyo.www.util.ScannerUtil;
import com.zkyyo.www.view.EmployeeView;

import java.util.ArrayList;

public class EmployeeServe {
    private static volatile EmployeeServe INSTANCE = null;

    private EmployeeServe() {}

    public static EmployeeServe getInstance() {
        if (INSTANCE == null) {
            synchronized (EmployeeServe.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EmployeeServe();
                }
            }
        }
        return INSTANCE;
    }

    public void queryEmployee(int type, EmployeePo handler) {
        int possibleUserId;
        int accurateUserId;
        String possibleUserName;
        EmployeePo foundEp = null;
        EmployeeDao epd = EmployeeDao.getInstance();

        switch (type) {
            case 1:
                System.out.println("请输入精确查询的员工号:");
                accurateUserId = ScannerUtil.scanNum();
                QueryUtil.queryEmployeeByUserId(accurateUserId);
                break;
            case 2:
                System.out.println("请输入模糊查询的员工号:");
                possibleUserId = ScannerUtil.scanNum();
                foundEp = QueryUtil.fuzzyQueryEmployee(possibleUserId);
                if (foundEp != null) {
                    QueryUtil.queryEmployeeByUserId(foundEp.geteUserId());
                }
                break;
            case 3:
                System.out.println("请输入模糊查询的员工名:");
                possibleUserName = ScannerUtil.scanString(true);
                foundEp = QueryUtil.fuzzyQueryEmployee(possibleUserName);
                if (foundEp != null) {
                    QueryUtil.queryEmployeeByUserId(foundEp.geteUserId());
                }
                break;
            case 4:
                ArrayList<EmployeePo> eps;
                eps = epd.selectEmployees();
                System.out.println("所有员工信息如下");//
                for (EmployeePo ep : eps) {
                    System.out.printf("员工号:%-12d 员工名:%-8s 部门号:%-6d 手机号:%-14s 薪水:%-16.2f 邮箱:%-30s 就职日期:%-16s\n",
                            ep.geteUserId(), ep.geteName(), ep.geteDeptId(), ep.geteMobile(),
                            ep.geteSalary(), ep.geteEmail(), ep.geteEmployDate());
                }
                break;
        }
        EmployeeView.employeeManage(handler);
    }

    public void updateEmployeeInfo(EmployeePo handler) {
        boolean isUpdate = false;
        System.out.println("请输入需要需改的员工号");
        int updateUserId = ScannerUtil.scanNum();
        EmployeePo foundEp = QueryUtil.queryEmployeeByUserId(updateUserId);
        EmployeeDao epd = EmployeeDao.getInstance();

        if (foundEp != null) {
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
                            isUpdate = epd.updateEmployee(updateUserId, 1, foundEp);
                            break;
                        case 2:
                            String newMobile = ScannerUtil.scanString(false);
                            foundEp.seteMobile(newMobile);
                            isUpdate = epd.updateEmployee(updateUserId, 2, foundEp);
                            break;
                        case 3:
                            double newSalary = ScannerUtil.scanSalary();
                            foundEp.seteSalary(newSalary);
                            isUpdate = epd.updateEmployee(updateUserId, 3, foundEp);
                            break;
                        case 4:
                            String newEmail = ScannerUtil.scanEmail();
                            foundEp.seteEmail(newEmail);
                            isUpdate = epd.updateEmployee(updateUserId, 4, foundEp);
                            break;
                        case 5:
                            java.sql.Date newEmployDate = ScannerUtil.scanSqlDate();
                            foundEp.seteEmployDate(newEmployDate);
                            isUpdate = epd.updateEmployee(updateUserId, 5, foundEp);
                            break;
                        case 6:
                            String newPwd = ScannerUtil.scanPwd();
                            foundEp.setePassword(newPwd);
                            isUpdate = epd.updateEmployee(updateUserId, 6, foundEp);
                            break;
                        default:
                            System.out.println("无效选项,请重新输入:");
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

    public void addEmployee(EmployeePo handler) {
        DepartmentDao dd = DepartmentDao.getInstance();
        EmployeeDao epd = EmployeeDao.getInstance();
        EmployeePo newEp = new EmployeePo();
        boolean isUpdated = false;
        System.out.println("请输入待添加员工的信息:");

        int userId = CreateIdUtil.creatUserId();
        System.out.println("姓名(必选): ");
        String name = ScannerUtil.scanString(true);
        System.out.println("部门号(必选): ");
        int departmentId;
        do {
            departmentId = ScannerUtil.scanNum();
            DepartmentPo dept = dd.selectDepartmentByDeptId(departmentId);
            if (dept == null) {
                System.out.println("所输入的部门不存在,请重新输入:");
            } else {
                break;
            }
        } while (true);
        System.out.println("密码(必选): ");
        String pwd = ScannerUtil.scanPwd();
        System.out.println("薪水(必选): ");
        double salary = ScannerUtil.scanSalary();
        System.out.println("电话(可选): ");
        String mobile = ScannerUtil.scanString(false);
        System.out.println("邮箱(可选): ");
        String email = ScannerUtil.scanEmail();
        System.out.println("*就职时间(格式为xxxx-xx-xx, 跳过则默认为当前日期): ");
        java.sql.Date sqlDate = ScannerUtil.scanSqlDate();

        newEp.seteUserId(userId);
        newEp.setePassword(pwd);
        newEp.seteName(name);
        newEp.seteDeptId(departmentId);
        newEp.seteSalary(salary);
        newEp.seteMobile(mobile);
        newEp.seteEmail(email);
        newEp.seteEmployDate(sqlDate);

        isUpdated = epd.addEmployee(newEp);
        if (isUpdated) {
            System.out.println("添加员工成功");
            System.out.println("赶紧找张小纸条记下: " + newEp.geteName() + "的员工号为 " + newEp.geteUserId());
        } else {
            System.out.println("添加员工失败");
        }

        EmployeeView.employeeManage(handler);
    }

    public void deleteEmployee(EmployeePo handler) {
        EmployeeDao epd = EmployeeDao.getInstance();
        System.out.println("请输入待删除员工的员工号:");
        int deletedUserId = ScannerUtil.scanNum();
        EmployeePo foundEp = QueryUtil.queryEmployeeByUserId(deletedUserId);

        if (foundEp != null) {
            do {
                System.out.println("是否删除该员工[y/n]:");
                String choice = ScannerUtil.scanString(true);
                String firstLetter = choice.substring(0, 1);

                if (firstLetter.equalsIgnoreCase("y")) {
                    boolean isDeleted = epd.deleteEmployee(deletedUserId);
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

//
//    /**
//     * 单元测试
//     *
//     * @param args 外部参数
//     */
//    public static void main(String[] args) {
//        System.out.println("--------测试--------");
//        System.out.println("0. exit");
//        System.out.println("1. 增加");
//        System.out.println("2. 删除");
//        System.out.println("3. 查询");
//        System.out.println("4. 修改");
//        System.out.println("choice: ");
//
//        do {
//            int choice = ScannerUtil.scanNum();
//            switch (choice) {
//                case 0:
//                    System.exit(1);
//                    break;
//                case 1:
//                    addEmployee(new EmployeePo());
//                    break;
//                case 2:
//                    deleteEmployee(new EmployeePo());
//                    break;
//                case 3:
//                    queryEmployee(4, new EmployeePo());
//                    break;
//                case 4:
//                    updateEmployeeInfo(new EmployeePo());
//                    break;
//                default:
//                    System.exit(1);
//                    break;
//            }
//        } while (true);
//    }

}
