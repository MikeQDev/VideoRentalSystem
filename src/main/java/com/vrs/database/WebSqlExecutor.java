package com.vrs.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class WebSqlExecutor {
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl342", "mike", "fuckyou");
	}

	/*public static void main(String[] args) throws SQLException {
		ResultSet heh = selectSql("SELECT * FROM MOVIE");
		int a = heh.getMetaData().getColumnCount();
		System.out.println(a);
		
		
		while (heh.next()) {
				System.out.println(":"+targetFormat.format(heh.getDate(4)));
		}
		// System.out.println(updateSql("CREATE TABLE HI(ID NUMBER(5,2), NAME VARCHAR2(255))"));
	}*/
	
	public static boolean updateViews(int vidId){
		try {
			ResultSet rS = selectSql("SELECT * FROM MOVIE WHERE MOVIEID="+vidId);
			rS.next();
			int curViews = rS.getInt(5);
			updateSql("UPDATE MOVIE SET VIEWCOUNT="+(++curViews)+" WHERE MOVIEID="+vidId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean updateSql(String updateString) throws Exception {
		boolean success = false;
		Connection conn = null;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(updateString);
			success = true;
		} finally {
			if (conn != null)
				conn.close();
		}
		return success;
	}

	public static boolean insertSql(String insertString) throws Exception {
		Connection conn = null;
		boolean success = false;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(insertString);
			success = true;
		} finally {
			if (conn != null)
				conn.close();
		}
		return success;
	}

	public static ResultSet selectSql(String queryString) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		conn = getConnection();
		stmt = conn.createStatement();

		return stmt.executeQuery(queryString);
	}
}
