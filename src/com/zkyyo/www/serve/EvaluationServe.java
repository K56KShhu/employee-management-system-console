package com.zkyyo.www.serve;

import com.zkyyo.www.dao.DepartmentDao;
import com.zkyyo.www.dao.EmployeeDao;
import com.zkyyo.www.dao.EvaluationDao;
import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.po.EvaluationPo;
import com.zkyyo.www.util.ScannerUtil;
import com.zkyyo.www.view.EvaluationView;

import java.util.Map;

public class EvaluationServe {
    private static volatile EvaluationServe INSTANCE = null;

    private EvaluationServe() {
    }

    public static EvaluationServe getInstance() {
        if (INSTANCE == null) {
            synchronized (EvaluationServe.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EvaluationServe();
                }
            }
        }
        return INSTANCE;
    }

    public void addEvaluation(EmployeePo handler) {
        EmployeeDao epd = EmployeeDao.getInstance();
        EvaluationDao evald = EvaluationDao.getInstance();
        System.out.println("请输入被评价员工的员工号:");
        int beEvaluatedId = ScannerUtil.scanNum();
        EmployeePo foundEp = epd.selectEmployeeByUserId(beEvaluatedId);
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
                    System.out.println("评价等级超出范围,请重新输入:");
                }
            } while (starLevel > 10);
            System.out.println("请输入评价内容(可选):");
            String comment = ScannerUtil.scanString(false);
            EvaluationPo newEval = new EvaluationPo(beEvaluatedId, handler.geteUserId(), starLevel, comment);
            isAdded = evald.addEvaluation(newEval);

            if (isAdded) {
                System.out.println("评价成功");
            } else {
                System.out.println("评价失败");
            }
        }

        EvaluationView.evaluationManage(handler);
    }

    public void querySendedEvaluation(EmployeePo handler) {
        EmployeeDao epd = EmployeeDao.getInstance();
        EvaluationDao evald = EvaluationDao.getInstance();
        System.out.println("请输入待查询员工的员工号:");
        int searchedId = ScannerUtil.scanNum();
        EmployeePo foundEp = epd.selectEmployeeByUserId(searchedId);

        if (foundEp == null) {
            System.out.println("查无此员工");
        } else {
            Map<Integer, EvaluationPo> evals = evald.selectEvaluationMapByEvaluatorId(searchedId);
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

    public void queryReceivedEvaluation(EmployeePo handler) {
        EmployeeDao epd = EmployeeDao.getInstance();
        EvaluationDao evald = EvaluationDao.getInstance();
        System.out.println("请输入待查询员工的员工号:");
        int searchedId = ScannerUtil.scanNum();
        EmployeePo foundEp = epd.selectEmployeeByUserId(searchedId);

        if (foundEp == null) {
            System.out.println("查无此员工");
        } else {
            Map<Integer, EvaluationPo> evals = evald.selectEvaluationMapByBeEvaluatedId(searchedId);
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

    public void queryEvaluations(EmployeePo handler) {
        EvaluationDao evald = EvaluationDao.getInstance();
        Map<Integer, EvaluationPo> evals = evald.selectEvaluationMap();
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

    public void updateSendedEvaluation(EmployeePo handler) {
        EmployeeDao epd = EmployeeDao.getInstance();
        EvaluationDao evald = EvaluationDao.getInstance();
        System.out.println("请输入发送评价的员工:");
        int userId = ScannerUtil.scanNum();
        EmployeePo foundEp = epd.selectEmployeeByUserId(userId);

        if (foundEp == null) {
            System.out.println("查无此员工");
        } else {
            Map<Integer, EvaluationPo> evals = evald.selectEvaluationMapByBeEvaluatedId(userId);
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
                                System.out.println("评价等级超出范围,请重新输入");
                            }
                        } while (starLevel > 10);
                        System.out.println("请输入修改后的评价内容(可选):");
                        String comment = ScannerUtil.scanString(false);
                        updatedEval.setStarLevel(starLevel);
                        updatedEval.setComment(comment);

                        boolean isUpdated = evald.updateEvaluation(updatedEval);
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

    public void updateReceivedEvaluation(EmployeePo handler) {
        EmployeeDao epd = EmployeeDao.getInstance();
        EvaluationDao evald = EvaluationDao.getInstance();
        System.out.println("请输入收到评价的员工:");
        int userId = ScannerUtil.scanNum();
        EmployeePo foundEp = epd.selectEmployeeByUserId(userId);

        if (foundEp == null) {
            System.out.println("查无此员工");
        } else {
            Map<Integer, EvaluationPo> evals = evald.selectEvaluationMapByEvaluatorId(userId);
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
                                System.out.println("评价等级超出范围,请重新输入");
                            }
                        } while (starLevel > 10);
                        System.out.println("请输入修改后的评价内容(可选):");
                        String comment = ScannerUtil.scanString(false);
                        updatedEval.setStarLevel(starLevel);
                        updatedEval.setComment(comment);

                        boolean isUpdated = evald.updateEvaluation(updatedEval);
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

    public void deleteSendedEvaluation(EmployeePo handler) {
        EmployeeDao epd = EmployeeDao.getInstance();
        EvaluationDao evald = EvaluationDao.getInstance();
        System.out.println("请输入发送评价的员工:");
        int userId = ScannerUtil.scanNum();
        EmployeePo foundEp = epd.selectEmployeeByUserId(userId);

        if (foundEp == null) {
            System.out.println("查无此员工");
        } else {
            Map<Integer, EvaluationPo> evals = evald.selectEvaluationMapByBeEvaluatedId(userId);
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
                        boolean isDeleted = evald.deleteEvaluation(delEvalId);
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

    public void deleteReceievdEvaluation(EmployeePo handler) {
        EmployeeDao epd = EmployeeDao.getInstance();
        EvaluationDao evald = EvaluationDao.getInstance();
        System.out.println("请输入收到评价的员工:");
        int userId = ScannerUtil.scanNum();
        EmployeePo foundEp = epd.selectEmployeeByUserId(userId);

        if (foundEp == null) {
            System.out.println("查无此员工");
        } else {
            Map<Integer, EvaluationPo> evals = evald.selectEvaluationMapByEvaluatorId(userId);
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
                        boolean isDeleted = evald.deleteEvaluation(delEvalId);
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
