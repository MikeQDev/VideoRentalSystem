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
	/**
	 * 
	 * @return connection to Oracle 11g database
	 * @throws SQLException
	 */
	private static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl342", "mike", "redacted");
	}

	/**
	 * Increase video views
	 * @param vidId
	 * @return true if video views successfully updated, otherwise false
	 */
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

	/**
	 * SQL update statement
	 * @param updateString
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * SQL insert statement
	 * @param insertString
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * SQL select statement
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public static ResultSet selectSql(String queryString) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		conn = getConnection();
		stmt = conn.createStatement();

		return stmt.executeQuery(queryString);
	}
}
