package com.zkyyo.www.dao;

import com.zkyyo.www.db.DbClose;
import com.zkyyo.www.db.DbConn;
import com.zkyyo.www.po.EvaluationPo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class EvaluationDao {
    private static volatile EvaluationDao INSTANCE = null;

    private EvaluationDao() {
    }

    public static EvaluationDao getInstance() {
        if (INSTANCE == null) {
            synchronized (EvaluationDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EvaluationDao();
                }
            }
        }
        return INSTANCE;
    }

    public boolean addEvaluation(EvaluationPo newEval) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        boolean isAdded = false;

        try {
            String sql = "INSERT INTO evaluation (be_evaluated_id, evaluator_id, star_level, comment) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newEval.getBeEvaluatedId());
            stmt.setInt(2, newEval.getEvaluatorId());
            stmt.setInt(3, newEval.getStarLevel());
            stmt.setString(4, newEval.getComment());
            int effects = stmt.executeUpdate();
            if (effects > 0)
                isAdded = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt);
        }
        return isAdded;
    }

    public Map<Integer, EvaluationPo> selectEvaluationMapByEvaluatorId(int evaluatorId) {
        Map<Integer, EvaluationPo> evals = new TreeMap<>();
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM evaluation WHERE evaluator_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, evaluatorId);
            rs = stmt.executeQuery();

            int index = 1;
            while (rs.next()) {
                int evaluationId = rs.getInt("test_evaluation_id");
                int beEvaluatedId = rs.getInt("be_evaluated_id");
                int starLevel = rs.getInt("star_level");
                String comment = rs.getString("comment");

                EvaluationPo eval = new EvaluationPo(evaluationId, beEvaluatedId, evaluatorId, starLevel, comment);
                evals.put(index, eval);
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return evals;
    }

    public Map<Integer, EvaluationPo> selectEvaluationMapByBeEvaluatedId(int beEvaluatedId) {
        Map<Integer, EvaluationPo> evals = new TreeMap<>();
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM evaluation WHERE be_evaluated_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, beEvaluatedId);
            rs = stmt.executeQuery();

            int index = 1;
            while (rs.next()) {
                int evaluationId = rs.getInt("test_evaluation_id");
                int evaluatorId = rs.getInt("evaluator_id");
                int starLevel = rs.getInt("star_level");
                String comment = rs.getString("comment");

                EvaluationPo eval = new EvaluationPo(evaluationId, beEvaluatedId, evaluatorId, starLevel, comment);
                evals.put(index, eval);
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return evals;
    }

    public Map<Integer, EvaluationPo> selectEvaluationMap() {
        Map<Integer, EvaluationPo> evals = new TreeMap<>();
        Connection conn = DbConn.getConn();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM evaluation";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            int index = 1;
            while (rs.next()) {
                int evaluationId = rs.getInt("test_evaluation_id");
                int beEvaluatedId = rs.getInt("be_evaluated_id");
                int evaluatorId = rs.getInt("evaluator_id");
                int starLevel = rs.getInt("star_level");
                String comment = rs.getString("comment");

                EvaluationPo eval = new EvaluationPo(evaluationId, beEvaluatedId, evaluatorId, starLevel, comment);
                evals.put(index, eval);
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return evals;
    }

    public boolean updateEvaluation(EvaluationPo newEvaluation) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        boolean isUpdated = false;

        try {
            String sql = "UPDATE evaluation SET star_level=?, comment=? WHERE test_evaluation_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newEvaluation.getStarLevel());
            stmt.setString(2, newEvaluation.getComment());
            stmt.setInt(3, newEvaluation.getEvaluationId());
            int effects = stmt.executeUpdate();

            if (effects > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt);
        }

        return isUpdated;
    }

    public boolean deleteEvaluation(int evaluationId) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM evaluation WHERE test_evaluation_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, evaluationId);
            int effects = stmt.executeUpdate();
            if (effects > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt);
        }

        return isDeleted;
    }
}
