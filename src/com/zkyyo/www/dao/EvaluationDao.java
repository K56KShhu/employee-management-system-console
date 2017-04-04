package com.zkyyo.www.dao;

import com.zkyyo.www.db.DbClose;
import com.zkyyo.www.db.DbConn;
import com.zkyyo.www.po.EvaluationPo;

import java.sql.*;
import java.util.ArrayList;

public class EvaluationDao {

    public static boolean addEvaluation(EvaluationPo newEval) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        boolean isAdded = false;

        try {
            String sql = "INSERT INTO evaluation (be_evaluated_id, evaluator_id, content) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newEval.getBeEvaluatedId());
            stmt.setInt(2, newEval.getEvaluatorId());
            stmt.setString(3, newEval.getContent());
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

    public static ArrayList<EvaluationPo> selectEvaluationListByBeEvaluatedId(int beEvaluatedId) {
        ArrayList<EvaluationPo> evals = new ArrayList<EvaluationPo>();
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM evaluation WHERE be_evaluated_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, beEvaluatedId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int evaluatorId = rs.getInt("evaluator_id");
                String content = rs.getString("content");
                EvaluationPo eval = new EvaluationPo(beEvaluatedId, evaluatorId, content);
                evals.add(eval);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }

        return evals;
    }

    public static ArrayList<EvaluationPo> selectEvaluationListByEvaluatorId(int evaluatorId) {
        ArrayList<EvaluationPo> evals = new ArrayList<EvaluationPo>();
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM evaluation WHERE evaluator_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, evaluatorId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int beEvaluatedId = rs.getInt("be_evaluated_id");
                String content = rs.getString("content");
                EvaluationPo eval = new EvaluationPo(beEvaluatedId, evaluatorId, content);
                evals.add(eval);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }

        return evals;
    }

    public static ArrayList<EvaluationPo> selectEvaluationListByBothId(int beEvaluatedId, int evaluatorId) {
        ArrayList<EvaluationPo> evals = new ArrayList<EvaluationPo>();
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM evaluation WHERE be_evaluated_id=? AND evaluator_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, beEvaluatedId);
            stmt.setInt(2, evaluatorId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String content = rs.getString("content");
                EvaluationPo eval = new EvaluationPo(beEvaluatedId, evaluatorId, content);
                evals.add(eval);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }

        return evals;
    }

    public static ArrayList<EvaluationPo> selectEvaluationList() {
        ArrayList<EvaluationPo> evals = new ArrayList<EvaluationPo>();
        Connection conn = DbConn.getConn();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM evaluation";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int beEvaluatedId = rs.getInt("be_evaluated_id");
                int evaluatorId = rs.getInt("evaluator_id");
                String content = rs.getString("content");
                EvaluationPo eval = new EvaluationPo(beEvaluatedId, evaluatorId, content);
                evals.add(eval);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }

        return evals;
    }

}
