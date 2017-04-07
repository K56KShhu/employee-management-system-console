package com.zkyyo.www.test;

import com.zkyyo.www.db.DbClose;
import com.zkyyo.www.db.DbConn;
import com.zkyyo.www.view.MainView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UniqueTest {
    public static final int userIdDigits = 10;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(creatUserId());
        }
    }

    public static int creatUserId() {
        Connection conn = DbConn.getConn();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int largestNum = (int) Math.pow(10, userIdDigits);

        try {
            for (int i = 0; i < largestNum; i++) {
                int newUserId = (int) (largestNum * Math.random());

                if (newUserId != 0) {
                    String sql = "SELECT * FROM employee WHERE user_id=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, newUserId);

                    rs = stmt.executeQuery();
                    if (rs.next() == false) {
                        System.out.println("12");
                        return newUserId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbClose.close(conn, stmt, rs);
        }
        return 0;
    }
}
