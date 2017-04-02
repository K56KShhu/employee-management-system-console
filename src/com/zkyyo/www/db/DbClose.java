package com.zkyyo.www.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 关闭与数据库的连接
 */
public class DbClose {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public DbClose(Connection conn, PreparedStatement stmt) {
        this.conn = conn;
        this.stmt = stmt;
    }

    public DbClose(Connection conn, PreparedStatement stmt, ResultSet rs) {
        this(conn, stmt);
        this.rs = rs;
    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public PreparedStatement getStmt() {
        return stmt;
    }

    public void setStmt(PreparedStatement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
}
