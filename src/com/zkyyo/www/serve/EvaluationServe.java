package com.zkyyo.www.serve;

import com.zkyyo.www.dao.EvaluationDao;
import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.po.EvaluationPo;
import com.zkyyo.www.util.QueryUtil;
import com.zkyyo.www.util.ScannerUtil;
import com.zkyyo.www.view.EvaluationView;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.UnknownFormatConversionException;

public class EvaluationServe {

//    public static void addEvaluation(EmployeePo handler) {
//        System.out.println("请输入被评价员工的员工号:");
//        int beEvaluatedId = ScannerUtil.scanNum();
//        EmployeePo foundEp = QueryUtil.queryEmployeeByUserId(beEvaluatedId);
//        boolean isAdded = false;
//
//        if (foundEp == null)
//            System.out.println("查无此员工");
//        else {
//            System.out.println("请输入对 " + foundEp.geteName() + " 的评价(可选):");
//            String content = ScannerUtil.scanString(false);
//            EvaluationPo newEval = new EvaluationPo(beEvaluatedId, handler.geteUserId(), content);
//            isAdded = EvaluationDao.addEvaluation(newEval);
//
//            if (isAdded)
//                System.out.println("评价成功");
//            else
//                System.out.println("评价失败");
//        }
//
//        EvaluationView.evaluationManage(handler);
//    }

    public static void queryEvaluation(EmployeePo handler) {
        System.out.println("1. 我发出的所有评价");
        System.out.println("2. 我获得的所有评价");
        System.out.println("3. 其他员工发出的所有评价");
        System.out.println("4. 其他员工获得的所有评价");
        System.out.println("5. 所有评价");
        System.out.println("请选择查询的内容(0返回评价系统)");

//        ArrayList<EvaluationPo> evals = new ArrayList<EvaluationPo>();
        Map<Integer, EvaluationPo> evals = new TreeMap<>();
        int choice = ScannerUtil.scanNum();
        switch (choice) {
            case 0:
                EvaluationView.evaluationManage(handler);
                break;
            //我发出的评价
            case 1:
                evals = EvaluationDao.selectEvaluationMapByEvaluatorId(handler.geteUserId());
                break;
            //我收到的评价
            case 2:
//                evals = EvaluationDao.selectEvaluationListByBeEvaluatedId(handler.geteUserId());
                break;
            case 3:
            case 4:
                System.out.println("请输入该员工的员工号:");
                int searchedId = ScannerUtil.scanNum();
                EmployeePo foundEp = QueryUtil.queryEmployeeByUserId(searchedId);
                if (foundEp == null) {
                    System.out.println("查无此员工");
                    EvaluationView.evaluationManage(handler);
                } else {
                    //其他员工发出的评价
                    if (choice == 3) {
//                        evals = EvaluationDao.selectEvaluationListByEvaluatorId(searchedId);
                    }
                    //其他员工收到的评价
                    else if (choice == 4) {
//                        evals = EvaluationDao.selectEvaluationListByBeEvaluatedId(searchedId);
                    }
                }
                break;
            //所有评价
            case 5:
//                evals = EvaluationDao.selectEvaluationList();
                break;
        }
        if (evals.isEmpty()) {
            System.out.println("暂无评价");
        } else {
            System.out.println("索引 \t 被评价者id \t 评价者id \t 星级 \t 内容");
            for (Map.Entry<Integer, EvaluationPo> entry : evals.entrySet()) {
                int index = entry.getKey();
                EvaluationPo eval = entry.getValue();
                System.out.printf("%-8d %-11d %-11d %-7d %s\n", index, eval.getBeEvaluatedId(),
                        eval.getEvaluatorId(), eval.getStarLevel(), eval.getComment());
            }
        }
        EvaluationView.evaluationManage(handler);
    }

//    public static void deleteEvaluation(EmployeePo handler) {
//        ArrayList<EvaluationPo> evals = EvaluationDao.selectEvaluationListByEvaluatorId(handler.geteUserId());
//        if (evals.size() == 0) {
//            System.out.println("暂无评价");
//        }
//        else {
//            EvaluationPo eval = null;
//            System.out.println("索引 \t 被评价者id \t 评价者id \t 内容");
//            for (int i = 0; i < evals.size(); i++) {
//                eval = evals.get(i);
//                System.out.printf("%-3d %-12d %-11d %s\n", i + 1, eval.getBeEvaluatedId(), eval.getEvaluatorId(), eval.getComment());
//
//                System.out.println("请输入待删除评价的索引:");
//                int choice = ScannerUtil.scanNum();
//
//            }
//        }
//}

    public static void deleteEvaluation(EmployeePo handler) {
        int evaluatorId = handler.geteUserId();
        Map<Integer, EvaluationPo> evals = EvaluationDao.selectEvaluationMapByEvaluatorId(evaluatorId);

        if (evals.isEmpty()) {
            System.out.println("暂无评价");
        } else {
            System.out.println("索引 \t 被评价者id \t 评价者id \t 星级 \t 内容");
            for (Map.Entry<Integer, EvaluationPo> entry : evals.entrySet()) {
                int index = entry.getKey();
                EvaluationPo eval = entry.getValue();
                System.out.printf("%-8d %-11d %-11d %-7d %s\n", index, eval.getBeEvaluatedId(),
                        eval.getEvaluatorId(), eval.getStarLevel(), eval.getComment());
            }

            System.out.println("请输入待删除评价的索引:");
            do {
                int deleteIndex = ScannerUtil.scanNum();
                if (evals.get(deleteIndex) == null) {
                    System.out.println("找不到该索引对应的评价,请重新输入");
                } else {
                    int delEvalId = evals.get(deleteIndex).getEvaluationId();
                    boolean isDeleted = EvaluationDao.deleteEvaluation(delEvalId);
                    if (isDeleted) {
                        System.out.println("删除评价成功");
                    } else {
                        System.out.println("删除评价失败");
                    }
                    EvaluationView.evaluationManage(handler);
                }
            } while (true);
        }
    }
}
