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
        System.out.println("0. 返回");
        System.out.println("***********************");

        do {
            System.out.print(handler.geteName() + "@" + "evaluation-manage:~$ ");
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    MainView.functionsChoice(handler);
                    break;
                case 1:
                    queryEvaluationSorts(handler);
                    break;
                case 2:
                    updateEvaluationSorts(handler);
                    break;
                case 3:
                    EvaluationServe.addEvaluation(handler);
                    break;
                case 4:
                    deleteEvaluationSorts(handler);
                    break;
                default:
                    System.out.println(choice + ": command not found");
                    break;
            }
        } while (true);
    }

    public static void queryEvaluationSorts(EmployeePo handler) {
        System.out.println("1. 查询发出的评价");
        System.out.println("2. 查询获得的评价");
        System.out.println("3. 查询所有评价");
        System.out.println("0. 返回");
        System.out.println("***********************");

        do {
            System.out.print(handler.geteName() + "@" + "evaluation-manage:~$ ");
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    evaluationManage(handler);
                    break;
                case 1:
                    EvaluationServe.querySendedEvaluation(handler);
                    break;
                case 2:
                    EvaluationServe.queryReceivedEvaluation(handler);
                    break;
                case 3:
                    EvaluationServe.queryEvaluations(handler);
                default:
                    System.out.println(choice + ": command not found");
                    break;
            }
        } while (true);
    }

    public static void updateEvaluationSorts(EmployeePo handler) {
        System.out.println("1. 修改发出的评价");
        System.out.println("2. 修改获得的评价");
        System.out.println("0. 返回");
        System.out.println("***********************");

        do {
            System.out.print(handler.geteName() + "@" + "evaluation-manage:~$ ");
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    evaluationManage(handler);
                    break;
                case 1:
                    EvaluationServe.updateSendedEvaluation(handler);
                    break;
                case 2:
                    EvaluationServe.updateReceivedEvaluation(handler);
                    break;
                default:
                    System.out.println(choice + ": command not found");
                    break;
            }
        } while (true);
    }

    public static void deleteEvaluationSorts(EmployeePo handler) {
        System.out.println("1. 删除发出的评价");
        System.out.println("2. 删除获得的评价");
        System.out.println("0. 返回");
        System.out.println("***********************");

        do {
            System.out.print(handler.geteName() + "@" + "evaluation-manage:~$ ");
            int choice = ScannerUtil.scanNum();
            switch (choice) {
                case 0:
                    evaluationManage(handler);
                    break;
                case 1:
                    EvaluationServe.deleteSendedEvaluation(handler);
                    break;
                case 2:
                    EvaluationServe.deleteReceievdEvaluation(handler);
                    break;
                default:
                    System.out.println(choice + ": command not found");
                    break;
            }
        } while (true);
    }
}














