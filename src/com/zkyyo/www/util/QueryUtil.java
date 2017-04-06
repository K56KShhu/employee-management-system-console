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


}
