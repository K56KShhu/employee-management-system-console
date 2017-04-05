package com.zkyyo.www.dao;

import com.zkyyo.www.db.DbClose;
import com.zkyyo.www.db.DbConn;
import com.zkyyo.www.po.DepartmentPo;

import java.sql.*;
import java.util.ArrayList;

public class DepartmentDao {

    public static boolean addDepartment(DepartmentPo newDept) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        boolean isAdded = false;

        try {
            String sql = "INSERT INTO department (dept_id, dept_name," +
                    "dept_population, description, built_date) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newDept.getDeptId());
            stmt.setString(2, newDept.getDeptName());
            stmt.setInt(3, newDept.getDeptPopulation());
            stmt.setString(4, newDept.getDeptDesc());
            stmt.setDate(5, newDept.getBuiltDate());

            int effects = stmt.executeUpdate();
            if (effects > 0) {
                isAdded = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt);
        }
        return isAdded;
    }

    public static ArrayList<DepartmentPo> queryAllDepts() {
        Connection conn = DbConn.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<DepartmentPo> depts = new ArrayList<DepartmentPo>();

        try {
            String sql = "SELECT * FROM department";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int deptId = rs.getInt("dept_id");
                String deptName = rs.getString("dept_name");
                int deptPopulation = rs.getInt("dept_population");
                String description = rs.getString("description");
                java.sql.Date builtDate = rs.getDate("built_date");

                DepartmentPo dept = new DepartmentPo(deptId, deptName,
                        deptPopulation, description, builtDate);
                depts.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return depts;
    }

    public static boolean deleteDept(int deleteddeptId) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM department WHERE dept_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, deleteddeptId);
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

    public static boolean updateDept(int updateDeptId, int type, DepartmentPo newDept) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        boolean isUpdated = false;

        try {
            String sql = null;
            int effects = 0;
            switch (type) {
                //部门名
                case 1:
                    sql = "UPDATE department SET dept_name=? WHERE dept_id=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, newDept.getDeptName());
                    stmt.setInt(2, updateDeptId);
                    effects = stmt.executeUpdate();
                    break;
                //部门描述
                case 2:
                    sql = "UPDATE department SET description=? WHERE dept_id=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, newDept.getDeptDesc());
                    stmt.setInt(2, updateDeptId);
                    effects = stmt.executeUpdate();
                    break;
                //建立时间
                case 3:
                    sql = "UPDATE department SET built_date=? WHERE dept_id=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setDate(1, newDept.getBuiltDate());
                    stmt.setInt(2, updateDeptId);
                    effects = stmt.executeUpdate();
                    break;
            }
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

}
