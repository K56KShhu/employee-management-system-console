package com.zkyyo.www.view;

import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.serve.EvaluationServe;
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
                EvaluationServe.queryEvaluation(handler);
                break;
            case 2:

                break;
            case 3:
                EvaluationServe.addEvaluation(handler);
                break;
            case 4:

                break;
            default:
                System.out.println("bad number");
        }
    }

//    public static void queryEvaluationWays(EmployeePo handler) {
//        System.out.println("1. 我获得的评价");
//        System.out.println("2. 我对其他人的评价");
//        System.out.println("3. 别人获得的评价");
//        System.out.println("请选择查询的内容(0返回评价系统)");
//
//        int choice = ScannerUtil.scanNum();
//        switch (choice) {
//            case 0:
//                evaluationManage(handler);
//                break;
//            case 1:
//                EvaluationServe.queryMyEvaluation(handler);
//                break;
//            case 2:
//                EvaluationServe.
//                break;
//            case 3:
//                EvaluationServe.queryOthersEvaluation(handler)
//                break;
//            default:
//                System.out.println("bad number");
//        }
//    }
}














