package com.zkyyo.www.dao;

import java.sql.*;
import java.util.ArrayList;

import com.zkyyo.www.po.EmployeePo;
// ???访问权限???


public class EmployeeDao {

    /**
     * 用于验证登录
     * @param enterNumber 请求登录的员工号
     * @return 返回请求员工对象
     */
    public static EmployeePo loginCheck(int enterNumber) {
        Connection conn = ConnDao.getConn();
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT * FROM employee WHERE number = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, enterNumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int eNumber = rs.getInt("number");
                String ePassword = rs.getString("password");
                String eName = rs.getString("name");
                int eDepartmentId = rs.getInt("department_id");
                String eMobile = rs.getString("mobile");
                double eSalary = rs.getDouble("salary");
                String eEmail = rs.getString("email");
                String eEmployDate = rs.getString("employee_date");

                EmployeePo ep = new EmployeePo(eNumber, ePassword, eName, eDepartmentId, eMobile, eSalary, eEmail, eEmployDate);
                return ep;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new ClearDao(conn, stmt).close();
        }
        return null;
    }

    /**
     * 删除选中的员工
     * @param deletedNumber 待删除员工的员工号
     * @return 删除成功返回true, 否则返回false
     */
    public static boolean deleteEmployee(int deletedNumber) {
        Connection conn = ConnDao.getConn();
        PreparedStatement stmt = null;

        try {
            String sql = "DELETE FROM employee WHERE number = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, deletedNumber);

            int effects = stmt.executeUpdate();
            if (effects > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new ClearDao(conn, stmt).close();
        }

        return false;
    }

    /**
     * 查询数据库中的所有员工
     * @return 返回一个包含所有员工对象的数组
     */
    public static ArrayList<EmployeePo> queryAllEmployees() {
        Connection conn = ConnDao.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<EmployeePo> eps = new ArrayList<EmployeePo>();

        try {
            String sql = "SELECT * FROM employee";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int eNumber = rs.getInt("number");
                String ePassword = rs.getString("password");
                String eName = rs.getString("name");
                int eDepartmentId = rs.getInt("department_id");
                String eMobile = rs.getString("mobile");
                double eSalary = rs.getDouble("salary");
                String eEmail = rs.getString("email");
                String eEmployDate = rs.getString("employee_date");

                EmployeePo ep = new EmployeePo(eNumber, ePassword, eName, eDepartmentId,
                        eMobile, eSalary, eEmail, eEmployDate);
                eps.add(ep);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new ClearDao(conn, stmt, rs);
        }
        return eps;
    }

    /**
     * 向数据库中添加新员工
     * @param newEp 操作者
     * @return 添加成功返回true, 否则返回false
     */
    public static boolean addEmployee(EmployeePo newEp) {
        Connection conn = ConnDao.getConn();
        PreparedStatement stmt = null;
        boolean isUpdated = false;

        try {
            String sql = "INSERT INTO employee (number, password, name, department_id, mobile, salary, email, employee_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newEp.geteNumber());
            stmt.setString(2, newEp.getePassword());
            stmt.setString(3, newEp.geteName());
            stmt.setInt(4, newEp.geteDepartmentId());
            stmt.setString(5, newEp.geteMobile());
            stmt.setDouble(6, newEp.geteSalary());
            stmt.setString(7, newEp.geteEmail());
            stmt.setString(8, newEp.geteEmployDate());

            int efforts = stmt.executeUpdate();
            if (efforts > 0)
                isUpdated = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new ClearDao(conn, stmt).close();
        }
        return isUpdated;
    }

    /**
     * 修改员工数据
     * @param number 待修改员工的员工号
     * @param type 待修改的数据类型
     * @param newEp 修改后的对象
     */
    public static boolean updateEmployee(int number, int type, EmployeePo newEp) {
        Connection conn = ConnDao.getConn();
        PreparedStatement stmt = null;
        boolean isUpdate = false;

        try {
            String sql = null;
            int effects = 0;
            switch (type) {
                case 1:
                    sql = "UPDATE employee SET department_id = ? WHERE number = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, newEp.geteDepartmentId());
                    stmt.setInt(2, number);
                    effects = stmt.executeUpdate();
                    break;
                case 2:
                    sql = "UPDATE employee SET mobile = ? WHERE number = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, newEp.geteMobile());
                    stmt.setInt(2, number);
                    effects = stmt.executeUpdate();
                    break;
                case 3:
                    sql = "UPDATE employee SET salary = ? WHERE number = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setDouble(1, newEp.geteSalary());
                    stmt.setInt(2, number);
                    effects = stmt.executeUpdate();
                    break;
                case 4:
                    sql = "UPDATE employee SET email = ? WHERE number = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, newEp.geteEmail());
                    stmt.setInt(2, number);
                    effects = stmt.executeUpdate();
                    break;
                case 5:
                    sql = "UPDATE employee SET employee_date = ? WHERE number = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, newEp.geteEmployDate());
                    stmt.setInt(2, number);
                    effects = stmt.executeUpdate();
                    break;
                case 6:
                    sql = "UPDATE employee SET password = ? WHERE number = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, newEp.getePassword());
                    stmt.setInt(2, number);
                    effects = stmt.executeUpdate();
                    break;
            }
            if (effects > 0)
                isUpdate = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new ClearDao(conn, stmt).close();
        }
        return isUpdate;
    }
}
