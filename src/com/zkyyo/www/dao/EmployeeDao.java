package com.zkyyo.www.dao;

import java.sql.*;
import java.util.ArrayList;

import com.zkyyo.www.db.DbClose;
import com.zkyyo.www.db.DbConn;
import com.zkyyo.www.po.EmployeePo;

public class EmployeeDao {

    public static EmployeePo loginCheck(int enterUserId) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM employee WHERE user_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enterUserId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int eUserId = rs.getInt("user_id");
                String ePassword = rs.getString("user_pwd");
                String eName = rs.getString("user_name");
                int eDeptId = rs.getInt("dept_id");
                String eMobile = rs.getString("mobile");
                double eSalary = rs.getDouble("salary");
                String eEmail = rs.getString("email");
                java.sql.Date eEmployDate = rs.getDate("employee_date");

                EmployeePo ep = new EmployeePo(eUserId, ePassword, eName,
                        eDeptId, eMobile, eSalary, eEmail, eEmployDate);
                return ep;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return null;
    }

    public static boolean addEmployee(EmployeePo newEp) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        boolean isUpdated = false;

        try {
            String sql = "INSERT INTO employee (user_id, user_pwd, user_name, dept_id," +
                    " mobile, salary, email, employee_date)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newEp.geteUserId());
            stmt.setString(2, newEp.getePassword());
            stmt.setString(3, newEp.geteName());
            stmt.setInt(4, newEp.geteDeptId());
            stmt.setString(5, newEp.geteMobile());
            stmt.setDouble(6, newEp.geteSalary());
            stmt.setString(7, newEp.geteEmail());
            stmt.setDate(8, newEp.geteEmployDate());

            int efforts = stmt.executeUpdate();
            if (efforts > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt);
        }
        return isUpdated;
    }

    public static boolean deleteEmployee(int deletedUserId) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;

        try {
            String sql = "DELETE FROM employee WHERE user_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, deletedUserId);

            int effects = stmt.executeUpdate();
            if (effects > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt);
        }

        return false;
    }

    public static EmployeePo selectEmployeeByUserId(int searchedUserId) {
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

    public static ArrayList<EmployeePo> selectEmployees() {
        Connection conn = DbConn.getConn();
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<EmployeePo> eps = new ArrayList<EmployeePo>();

        try {
            String sql = "SELECT * FROM employee";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int eUserId = rs.getInt("user_id");
                String ePassword = rs.getString("user_pwd");
                String eName = rs.getString("user_name");
                int eDeptId = rs.getInt("dept_id");
                String eMobile = rs.getString("mobile");
                double eSalary = rs.getDouble("salary");
                String eEmail = rs.getString("email");
                java.sql.Date eEmployDate = rs.getDate("employee_date");

                eps.add(new EmployeePo(eUserId, ePassword, eName, eDeptId,
                        eMobile, eSalary, eEmail, eEmployDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return eps;
    }

    public static ArrayList<EmployeePo> selectPossibleEmployeesByUserId(int userId) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<EmployeePo> eps = new ArrayList<EmployeePo>();

        try {
            String sql = "SELECT * FROM employee WHERE user_id LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + userId + "%");
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

                eps.add(new EmployeePo(eUserId, ePassword, eName, eDeptId,
                        eMobile, eSalary, eEmail, eEmployDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return eps;
    }

    public static ArrayList<EmployeePo> selectPossibleEmployeesByUserName(String userName) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<EmployeePo> eps = new ArrayList<EmployeePo>();

        try {
            String sql = "SELECT * FROM employee WHERE user_name LIKE ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + userName + "%");
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

                eps.add(new EmployeePo(eUserId, ePassword, eName, eDeptId,
                        eMobile, eSalary, eEmail, eEmployDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return eps;
    }

    public static boolean updateEmployee(int updateUserId, int type, EmployeePo newEp) {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        boolean isUpdate = false;

        try {
            String sql = null;
            int effects = 0;
            switch (type) {
                case 1:
                    sql = "UPDATE employee SET dept_id=? WHERE user_id=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, newEp.geteDeptId());
                    stmt.setInt(2, updateUserId);
                    effects = stmt.executeUpdate();
                    break;
                case 2:
                    sql = "UPDATE employee SET mobile=? WHERE user_id=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, newEp.geteMobile());
                    stmt.setInt(2, updateUserId);
                    effects = stmt.executeUpdate();
                    break;
                case 3:
                    sql = "UPDATE employee SET salary=? WHERE user_id=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setDouble(1, newEp.geteSalary());
                    stmt.setInt(2, updateUserId);
                    effects = stmt.executeUpdate();
                    break;
                case 4:
                    sql = "UPDATE employee SET email=? WHERE user_id=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, newEp.geteEmail());
                    stmt.setInt(2, updateUserId);
                    effects = stmt.executeUpdate();
                    break;
                case 5:
                    sql = "UPDATE employee SET employee_date=? WHERE user_id=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setDate(1, newEp.geteEmployDate());
                    stmt.setInt(2, updateUserId);
                    effects = stmt.executeUpdate();
                    break;
                case 6:
                    sql = "UPDATE employee SET user_pwd=? WHERE user_id=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, newEp.getePassword());
                    stmt.setInt(2, updateUserId);
                    effects = stmt.executeUpdate();
                    break;
                default:
                    break;
            }
            if (effects > 0) {
                isUpdate = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt);
        }
        return isUpdate;
    }
}
