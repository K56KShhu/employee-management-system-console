package com.zkyyo.www.serve;

import com.zkyyo.www.dao.EmployeeDao;
import com.zkyyo.www.dao.EvaluationDao;
import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.po.EvaluationPo;
import com.zkyyo.www.util.ScannerUtil;
import com.zkyyo.www.view.EvaluationView;

import java.util.Map;

public class EvaluationServe {

    public static void addEvaluation(EmployeePo handler) {
        System.out.println("请输入被评价员工的员工号:");
        int beEvaluatedId = ScannerUtil.scanNum();
        EmployeePo foundEp = EmployeeDao.queryEmployeeByUserId(beEvaluatedId);
        boolean isAdded = false;

        if (foundEp == null)
            System.out.println("查无此员工");
        else {
            System.out.println("正在输入对 " + foundEp.geteName() + " 的评价:");
            System.out.println("请输入评价等级(0-10的整数):");
            int starLevel;
            do {
                starLevel = ScannerUtil.scanNum();
                if (starLevel > 10) {
                    System.err.println("评价等级超出范围,请重新输入:");
                }
            } while (starLevel > 10);
            System.out.println("请输入评价内容(可选):");
            String comment = ScannerUtil.scanString(false);
            EvaluationPo newEval = new EvaluationPo(beEvaluatedId, handler.geteUserId(), starLevel, comment);
            isAdded = EvaluationDao.addEvaluation(newEval);

            if (isAdded) {
                System.out.println("评价成功");
            } else {
                System.out.println("评价失败");
            }
        }

        EvaluationView.evaluationManage(handler);
    }

    public static void querySendedEvaluation(EmployeePo handler) {
        System.out.println("请输入待查询员工的员工号:");
        int searchedId = ScannerUtil.scanNum();
        EmployeePo foundEp = EmployeeDao.queryEmployeeByUserId(searchedId);

        if (foundEp == null) {
            System.out.println("查无此员工");
        } else {
            Map<Integer, EvaluationPo> evals = EvaluationDao.selectEvaluationMapByEvaluatorId(searchedId);
            if (evals.isEmpty()) {
                System.out.println("该员工未发送任何评价");
            } else {
                System.out.println(foundEp.geteName() + "(" + foundEp.geteUserId() + ")" + "发出的评价如下:");
                System.out.println("索引 \t 被评价者id \t 星级 \t 内容");
                for (Map.Entry<Integer, EvaluationPo> entry : evals.entrySet()) {
                    int index = entry.getKey();
                    EvaluationPo eval = entry.getValue();
                    System.out.printf("%-8d %-11d %-7d %s\n", index, eval.getBeEvaluatedId(),
                            eval.getStarLevel(), eval.getComment());
                }
            }
        }

        EvaluationView.evaluationManage(handler);
    }

    public static void queryReceivedEvaluation(EmployeePo handler) {
        System.out.println("请输入待查询员工的员工号:");
        int searchedId = ScannerUtil.scanNum();
        EmployeePo foundEp = EmployeeDao.queryEmployeeByUserId(searchedId);

        if (foundEp == null) {
            System.out.println("查无此员工");
        } else {
            Map<Integer, EvaluationPo> evals = EvaluationDao.selectEvaluationMapByBeEvaluatedId(searchedId);
            if (evals.isEmpty()) {
                System.out.println("该员工未收到任何评价");
            } else {
                System.out.println(foundEp.geteName() + "(" + foundEp.geteUserId() + ")" + "收到的评价如下:");
                System.out.println("索引 \t 评价者id \t 星级 \t 内容");
                for (Map.Entry<Integer, EvaluationPo> entry : evals.entrySet()) {
                    int index = entry.getKey();
                    EvaluationPo eval = entry.getValue();
                    System.out.printf("%-8d %-11d %-7d %s\n", index, eval.getEvaluatorId(),
                            eval.getStarLevel(), eval.getComment());
                }
            }
        }

        EvaluationView.evaluationManage(handler);
    }

    public static void queryEvaluations(EmployeePo handler) {
        Map<Integer, EvaluationPo> evals = EvaluationDao.selectEvaluationMap();
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

    public static void updateSendedEvaluation(EmployeePo handler) {
        System.out.println("请输入发送评价的员工:");
        int userId = ScannerUtil.scanNum();
        EmployeePo foundEp = EmployeeDao.queryEmployeeByUserId(userId);

        if (foundEp == null) {
            System.out.println("查无此员工");
        } else {
            Map<Integer, EvaluationPo> evals = EvaluationDao.selectEvaluationMapByBeEvaluatedId(userId);
            if (evals.isEmpty()) {
                System.out.println("该员工未收到任何评价");
            } else {
                System.out.println(foundEp.geteName() + "(" + foundEp.geteUserId() + ")" + "收到的评价如下:");
                System.out.println("索引 \t 评价者id \t 星级 \t 内容");
                for (Map.Entry<Integer, EvaluationPo> entry : evals.entrySet()) {
                    int index = entry.getKey();
                    EvaluationPo eval = entry.getValue();
                    System.out.printf("%-8d %-11d %-7d %s\n", index, eval.getEvaluatorId(),
                            eval.getStarLevel(), eval.getComment());
                }

                System.out.println("请输入待修改的评价索引:");
                do {
                    int updateIndex = ScannerUtil.scanNum();
                    if (evals.get(updateIndex) == null) {
                        System.out.println("找不到该索引对应的评价,请重新输入");
                    } else {
                        EvaluationPo updatedEval = evals.get(updateIndex);

                        int starLevel;
                        do {
                            System.out.println("请输入修改后的评价星级:");
                            starLevel = ScannerUtil.scanNum();
                            if (starLevel > 10) {
                                System.err.println("评价等级超出范围,请重新输入");
                            }
                        } while (starLevel > 10);
                        System.out.println("请输入修改后的评价内容(可选):");
                        String comment = ScannerUtil.scanString(false);
                        updatedEval.setStarLevel(starLevel);
                        updatedEval.setComment(comment);

                        boolean isUpdated = EvaluationDao.updateEvaluation(updatedEval);
                        if (isUpdated) {
                            System.out.println("评价修改成功");
                        } else {
                            System.out.println("评价修改失败");
                        }
                        EvaluationView.evaluationManage(handler);
                    }
                } while (true);
            }
        }
    }

    public static void updateReceivedEvaluation(EmployeePo handler) {
        System.out.println("请输入收到评价的员工:");
        int userId = ScannerUtil.scanNum();
        EmployeePo foundEp = EmployeeDao.queryEmployeeByUserId(userId);

        if (foundEp == null) {
            System.out.println("查无此员工");
        } else {
            Map<Integer, EvaluationPo> evals = EvaluationDao.selectEvaluationMapByEvaluatorId(userId);
            if (evals.isEmpty()) {
                System.out.println("该员工未发送任何评价");
            } else {
                System.out.println(foundEp.geteName() + "(" + foundEp.geteUserId() + ")" + "发出的评价如下:");
                System.out.println("索引 \t 被评价者id \t 星级 \t 内容");
                for (Map.Entry<Integer, EvaluationPo> entry : evals.entrySet()) {
                    int index = entry.getKey();
                    EvaluationPo eval = entry.getValue();
                    System.out.printf("%-8d %-11d %-7d %s\n", index, eval.getBeEvaluatedId(),
                            eval.getStarLevel(), eval.getComment());
                }

                System.out.println("请输入待修改的评价索引:");
                do {
                    int updateIndex = ScannerUtil.scanNum();
                    if (evals.get(updateIndex) == null) {
                        System.out.println("找不到该索引对应的评价,请重新输入");
                    } else {
                        EvaluationPo updatedEval = evals.get(updateIndex);

                        int starLevel;
                        do {
                            System.out.println("请输入修改后的评价星级:");
                            starLevel = ScannerUtil.scanNum();
                            if (starLevel > 10) {
                                System.err.println("评价等级超出范围,请重新输入");
                            }
                        } while (starLevel > 10);
                        System.out.println("请输入修改后的评价内容(可选):");
                        String comment = ScannerUtil.scanString(false);
                        updatedEval.setStarLevel(starLevel);
                        updatedEval.setComment(comment);

                        boolean isUpdated = EvaluationDao.updateEvaluation(updatedEval);
                        if (isUpdated) {
                            System.out.println("评价修改成功");
                        } else {
                            System.out.println("评价修改失败");
                        }
                        EvaluationView.evaluationManage(handler);
                    }
                } while (true);
            }
        }
    }

    public static void deleteSendedEvaluation(EmployeePo handler) {
        System.out.println("请输入发送评价的员工:");
        int userId = ScannerUtil.scanNum();
        EmployeePo foundEp = EmployeeDao.queryEmployeeByUserId(userId);

        if (foundEp == null) {
            System.out.println("查无此员工");
        } else {
            Map<Integer, EvaluationPo> evals = EvaluationDao.selectEvaluationMapByBeEvaluatedId(userId);
            if (evals.isEmpty()) {
                System.out.println("该员工未收到任何评价");
            } else {
                System.out.println(foundEp.geteName() + "(" + foundEp.geteUserId() + ")" + "收到的评价如下:");
                System.out.println("索引 \t 评价者id \t 星级 \t 内容");
                for (Map.Entry<Integer, EvaluationPo> entry : evals.entrySet()) {
                    int index = entry.getKey();
                    EvaluationPo eval = entry.getValue();
                    System.out.printf("%-8d %-11d %-7d %s\n", index, eval.getEvaluatorId(),
                            eval.getStarLevel(), eval.getComment());
                }

                System.out.println("请输入待删除的评价索引:");
                do {
                    int deletedIndex = ScannerUtil.scanNum();
                    if (evals.get(deletedIndex) == null) {
                        System.out.println("找不到该索引对应的评价,请重新输入");
                    } else {
                        int delEvalId = evals.get(deletedIndex).getEvaluationId();
                        boolean isDeleted = EvaluationDao.deleteEvaluation(delEvalId);
                        if (isDeleted) {
                            System.out.println("删除评价成功");
                        } else {
                            System.out.println("删除评价失败");
                        }
                    }
                } while (true);
            }
        }
    }

    public static void deleteReceievdEvaluation(EmployeePo handler) {
        System.out.println("请输入收到评价的员工:");
        int userId = ScannerUtil.scanNum();
        EmployeePo foundEp = EmployeeDao.queryEmployeeByUserId(userId);

        if (foundEp == null) {
            System.out.println("查无此员工");
        } else {
            Map<Integer, EvaluationPo> evals = EvaluationDao.selectEvaluationMapByEvaluatorId(userId);
            if (evals.isEmpty()) {
                System.out.println("该员工未发送任何评价");
            } else {
                System.out.println(foundEp.geteName() + "(" + foundEp.geteUserId() + ")" + "发出的评价如下:");
                System.out.println("索引 \t 被评价者id \t 星级 \t 内容");
                for (Map.Entry<Integer, EvaluationPo> entry : evals.entrySet()) {
                    int index = entry.getKey();
                    EvaluationPo eval = entry.getValue();
                    System.out.printf("%-8d %-11d %-7d %s\n", index, eval.getBeEvaluatedId(),
                            eval.getStarLevel(), eval.getComment());
                }

                System.out.println("请输入待删除的评价索引:");
                do {
                    int deletedIndex = ScannerUtil.scanNum();
                    if (evals.get(deletedIndex) == null) {
                        System.out.println("找不到该索引对应的评价,请重新输入");
                    } else {
                        int delEvalId = evals.get(deletedIndex).getEvaluationId();
                        boolean isDeleted = EvaluationDao.deleteEvaluation(delEvalId);
                        if (isDeleted) {
                            System.out.println("删除评价成功");
                        } else {
                            System.out.println("删除评价失败");
                        }
                    }
                } while (true);
            }
        }
    }
}
