package com.zkyyo.www.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zkyyo.www.db.DbClose;
import com.zkyyo.www.db.DbConn;
import com.zkyyo.www.po.DepartmentPo;
import com.zkyyo.www.po.EmployeePo;

public class QueryUtil {

    /**
     * 根据员工号查询员工
     *
     * @param searchedUserId 需要查找的员工号
     */
    public static EmployeePo queryEmployeeByUserId(int searchedUserId) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM employee WHERE user_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, searchedUserId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int eUserId = rs.getInt("user_id");
                String ePassword = rs.getString("user_pwd");
                String eName = rs.getString("user_name");
                int eDeptId = rs.getInt("dept_id");
                String eMobile = rs.getString("mobile");
                double eSalary = rs.getDouble("salary");
                String eEmail = rs.getString("email");
                java.sql.Date eEmployDate = rs.getDate("employee_date");

                return new EmployeePo(eUserId, ePassword, eName, eDeptId, eMobile, eSalary, eEmail, eEmployDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return null;
    }

    public static EmployeePo queryEmployeeByUserName(String searchedUserName) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM employee WHERE user_name=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, searchedUserName);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int eUserId = rs.getInt("user_id");
                String ePassword = rs.getString("user_pwd");
                String eName = rs.getString("user_name");
                int eDertId = rs.getInt("dept_id");
                String eMobile = rs.getString("mobile");
                double eSalary = rs.getDouble("salary");
                String eEmail = rs.getString("email");
                java.sql.Date eEmployDate = rs.getDate("employee_date");

                return new EmployeePo(eUserId, ePassword, eName, eDertId,
                        eMobile, eSalary, eEmail, eEmployDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return null;
    }

    /*
    public static ArrayList<EmployeePo> newMachine(String clue) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs;

        String regex = "^\\d+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(clue);
        boolean isUserId = m.matches();

        if (isUserId) {
            try {
                String sql = "SELECT * FROM employee WHERE user_id=?";
                stmt = conn.prepareStatement(sql);
                int searchedUserId = Integer.valueOf(clue);
                stmt.setInt(1, searchedUserId);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    int eUserId = rs.getInt("user_id");
                    String ePassword = rs.getString("user_pwd");
                    String eName = rs.getString("user_name");
                    int eDeptId = rs.getInt("dept_id");
                    String eMobile = rs.getString("mobile");
                    double eSalary = rs.getDouble("salary");
                    String eEmail = rs.getString("email");
                    java.sql.Date eEmployDate = rs.getDate("employee_date");

                    EmployeePo ep = new EmployeePo(eUserId, ePassword, eName, eDeptId, eMobile, eSalary, eEmail, eEmployDate);
                    return ep;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                new DbClose(conn, stmt, rs);
            }
        }
        else {
            try {
                String sql = "SELECT * FROM employee WHERE user_name=?";
                stmt = conn.prepareStatement(sql);
                String searchedUserName = clue;
                stmt.setString(1, searchedUserName);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    int eUserId = rs.getInt("user_id");
                    String ePassword = rs.getString("user_pwd");
                    String eName = rs.getString("user_name");
                    int eDertId = rs.getInt("dept_id");
                    String eMobile = rs.getString("mobile");
                    double eSalary = rs.getDouble("salary");
                    String eEmail = rs.getString("email");
                    java.sql.Date eEmployDate = rs.getDate("employee_date");

                    EmployeePo ep = new EmployeePo(eUserId, ePassword, eName, eDertId, eMobile, eSalary, eEmail, eEmployDate);
                    return ep;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                new DbClose(conn, stmt, rs);
            }
        }
    }
    */

    public static DepartmentPo queryDeptByDeptId(int searchedDeptId) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM department WHERE dept_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, searchedDeptId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int deptId = rs.getInt("dept_id");
                String deptName = rs.getString("dept_name");
                int deptPopulation = rs.getInt("dept_population");
                String description = rs.getString("description");
                java.sql.Date builtDate = rs.getDate("built_date");

                return new DepartmentPo(deptId, deptName,
                        deptPopulation, description, builtDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return null;
    }


    public static DepartmentPo queryDeptByDeptName(String searchedDeptName) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM department WHERE dept_name=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, searchedDeptName);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int deptId = rs.getInt("dept_id");
                String deptName = rs.getString("dept_name");
                int deptPopulation = rs.getInt("dept_population");
                String description = rs.getString("description");
                java.sql.Date builtDate = rs.getDate("built_date");

                return new DepartmentPo(deptId, deptName,
                        deptPopulation, description, builtDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return null;
    }
}
