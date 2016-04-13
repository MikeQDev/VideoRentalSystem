package com.vrs.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTester {

	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl342", "mike", "fuckyou");
	}

	public static void main(String[] args) throws SQLException {
		ResultSet heh = selectSql("SELECT * FROM MOVIE");
		int a = heh.getMetaData().getColumnCount();
		System.out.println(a);
		while(heh.next()){
			for(int i=1; i<=a; i++)
				System.out.println(heh.getObject(i));
		}
		//System.out.println(updateSql("CREATE TABLE HI(ID NUMBER(5,2), NAME VARCHAR2(255))"));
	}

	static boolean updateSql(String updateString) {
		boolean success = false;
		Connection conn = null;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(updateString);
			success = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return success;
	}

	static boolean insertSql(String insertString) {
		boolean success = false;
		Connection conn = null;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(insertString);
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return success;
	}

	static ResultSet selectSql(String queryString) {
		Connection conn = null;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			
			return stmt.executeQuery(queryString);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			/*if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}*/
		}
		return null;
	}
}
