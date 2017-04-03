package com.zkyyo.www.view;

import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.util.ScannerUtil;

public class EvaluationView {
    public static void evaluationManage(EmployeePo handler) {
        System.out.println("********评价系统********");
        System.out.println("1. 查询评价");
        System.out.println("2. 修改评价");
        System.out.println("3. 增加评价");
        System.out.println("4. 删除评价");
        System.out.println("***********************");

        System.out.println("请输入相应选项(0返回功能选择界面):");
        int choice = ScannerUtil.scanNum();
        switch (choice) {
            case 0:
                MainView.functionsChoice(handler);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("bad number");
        }
    }
}
