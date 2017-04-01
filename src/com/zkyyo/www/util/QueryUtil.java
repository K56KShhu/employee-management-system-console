package com.zkyyo.www.util;

import com.zkyyo.www.dao.ConnDao;
import com.zkyyo.www.po.EmployeePo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryUtil {

    /**
     * 根据员工号查询员工
     * @param searchNum 需要查找的员工号
     */
    public static EmployeePo queryEmployeeByNumber(int searchNum) {
        Connection conn = ConnDao.getConn();
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT * FROM employee WHERE number = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, searchNum);
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
        }
        return null;
    }

    public static EmployeePo queryEmployeeByName(String searchName) {
        Connection conn = ConnDao.getConn();
        PreparedStatement stmt = null;

        try {
            String sql = "SELECT * FROM employee WHERE name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, searchName);
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
        }
        return null;
    }
}
