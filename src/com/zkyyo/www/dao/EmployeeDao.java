package com.zkyyo.www.dao;

import java.sql.*;
import java.util.ArrayList;

import com.zkyyo.www.db.DbClose;
import com.zkyyo.www.db.DbConn;
import com.zkyyo.www.po.EmployeePo;
import com.zkyyo.www.serve.EmployeeServe;

public class EmployeeDao {
    private static volatile EmployeeDao INSTANCE = null;

    private EmployeeDao() {}

    public static EmployeeDao getInstance() {
        if (INSTANCE == null) {
            synchronized (EvaluationDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EmployeeDao();
                }
            }
        }
        return INSTANCE;
    }

    public EmployeePo loginCheck(int enterUserId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbConn.getConn();
            String sql = "SELECT * FROM employee WHERE user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, enterUserId);
            rs = pstmt.executeQuery();

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
            DbClose.close(conn, pstmt, rs);
        }
        return null;
    }

    public boolean addEmployee(EmployeePo newEp) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean isUpdated = false;

        try {
            conn = DbConn.getConn();
            String sql = "INSERT INTO employee (user_id, user_pwd, user_name, dept_id mobile, salary, email, employee_date)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newEp.geteUserId());
            pstmt.setString(2, newEp.getePassword());
            pstmt.setString(3, newEp.geteName());
            pstmt.setInt(4, newEp.geteDeptId());
            pstmt.setString(5, newEp.geteMobile());
            pstmt.setDouble(6, newEp.geteSalary());
            pstmt.setString(7, newEp.geteEmail());
            pstmt.setDate(8, newEp.geteEmployDate());

            int efforts = pstmt.executeUpdate();
            if (efforts > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, pstmt);
        }
        return isUpdated;
    }

    public boolean deleteEmployee(int deletedUserId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DbConn.getConn();
            String sql = "DELETE FROM employee WHERE user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deletedUserId);

            int effects = pstmt.executeUpdate();
            if (effects > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, pstmt);
        }

        return false;
    }

    public EmployeePo selectEmployeeByUserId(int searchedUserId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DbConn.getConn();
            String sql = "SELECT * FROM employee WHERE user_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, searchedUserId);
            rs = pstmt.executeQuery();

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
            DbClose.close(conn, pstmt, rs);
        }
        return null;
    }

    public ArrayList<EmployeePo> selectEmployees() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<EmployeePo> eps = new ArrayList<EmployeePo>();

        try {
            conn = DbConn.getConn();
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

    public ArrayList<EmployeePo> selectPossibleEmployeesByUserId(int userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<EmployeePo> eps = new ArrayList<EmployeePo>();

        try {
            conn = DbConn.getConn();
            String sql = "SELECT * FROM employee WHERE user_id LIKE ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + userId + "%");
            rs = pstmt.executeQuery();

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
            DbClose.close(conn, pstmt, rs);
        }
        return eps;
    }

    public ArrayList<EmployeePo> selectPossibleEmployeesByUserName(String userName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<EmployeePo> eps = new ArrayList<EmployeePo>();

        try {
            conn = DbConn.getConn();
            String sql = "SELECT * FROM employee WHERE user_name LIKE ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + userName + "%");
            rs = pstmt.executeQuery();

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
            DbClose.close(conn, pstmt, rs);
        }
        return eps;
    }

    public boolean updateEmployee(int updateUserId, int type, EmployeePo newEp) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean isUpdate = false;

        try {
            conn = DbConn.getConn();
            String sql = null;
            int effects = 0;
            switch (type) {
                case 1:
                    sql = "UPDATE employee SET dept_id=? WHERE user_id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, newEp.geteDeptId());
                    pstmt.setInt(2, updateUserId);
                    effects = pstmt.executeUpdate();
                    break;
                case 2:
                    sql = "UPDATE employee SET mobile=? WHERE user_id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, newEp.geteMobile());
                    pstmt.setInt(2, updateUserId);
                    effects = pstmt.executeUpdate();
                    break;
                case 3:
                    sql = "UPDATE employee SET salary=? WHERE user_id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setDouble(1, newEp.geteSalary());
                    pstmt.setInt(2, updateUserId);
                    effects = pstmt.executeUpdate();
                    break;
                case 4:
                    sql = "UPDATE employee SET email=? WHERE user_id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, newEp.geteEmail());
                    pstmt.setInt(2, updateUserId);
                    effects = pstmt.executeUpdate();
                    break;
                case 5:
                    sql = "UPDATE employee SET employee_date=? WHERE user_id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setDate(1, newEp.geteEmployDate());
                    pstmt.setInt(2, updateUserId);
                    effects = pstmt.executeUpdate();
                    break;
                case 6:
                    sql = "UPDATE employee SET user_pwd=? WHERE user_id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, newEp.getePassword());
                    pstmt.setInt(2, updateUserId);
                    effects = pstmt.executeUpdate();
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
            DbClose.close(conn, pstmt);
        }
        return isUpdate;
    }
}
