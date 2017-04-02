package com.zkyyo.www.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zkyyo.www.dao.ConnDao;
import com.zkyyo.www.po.EmployeePo;

public class QueryUtil {

    /**
     * 根据员工号查询员工
     *
     * @param searchedUserId 需要查找的员工号
     */
    public static EmployeePo queryEmployeeByUserId(int searchedUserId) {
        Connection conn = ConnDao.getConn();
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT * FROM employee WHERE user_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, searchedUserId);
            ResultSet rs = stmt.executeQuery();

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
        }
        return null;
    }

    public static EmployeePo queryEmployeeByUserName(String searchedUserName) {
        Connection conn = ConnDao.getConn();
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT * FROM employee WHERE user_name=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, searchedUserName);
            ResultSet rs = stmt.executeQuery();

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
        }
        return null;
    }
}
