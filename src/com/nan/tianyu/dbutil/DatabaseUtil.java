package com.nan.tianyu.dbutil;

import com.nan.tianyu.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseUtil {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/survey";
	static final String USER = "root";
	static final String PASS = "921222";

	private static Connection connection = null;

	public static Connection getConnection() {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return connection;
	}


	public static void main(String[] args) {
		System.out.println("This is a db utility test");

		Connection conn = null;
		Statement stmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");


			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);


			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			String sql;
			sql = "Select MAX(userid) as USERID from user;";

			ResultSet rs = stmt.executeQuery(sql);
			int id = 0;
			while (rs.next()){
				id = rs.getInt("USERID");
			}
			System.out.println(id);
			/*
			int id = 0;
			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				id = rs.getInt("UserID");
				System.out.print("USERID::::::::::::::::::::::::::::::::::::::::::::::: " + id);
			}*/
			int new_id = id + 1;

			String insert_sql;
			String email = "ser4@gmail.com";
			insert_sql = "INSERT INTO User (UserID, UserName, Password, Email, Firstname, LastName, Status, CreateDate) Values (" +  new_id + ",'" + email + "',  '123', 'ser4@gmail.com', 'user4', 'user4',  1, null)";
			stmt.executeUpdate(insert_sql);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}

	public static ArrayList<User> getUserList(){
		ArrayList<User> userList = new ArrayList<User>();

		Connection conn = DatabaseUtil.getConnection();
		Statement stmt = null;

		try {
			stmt = conn.createStatement();

			String sql;
			sql = "SELECT * FROM User";
			ResultSet rs = null;
			User user = null;
			rs = stmt.executeQuery(sql);

			int userID = -1;
			String userName = null;
			String password = null;
			String email = null;
			String firstName = null;
			String lastName = null;
			char status;
			Date createDate;
			while (rs.next()) {
				user = new User();

				// Retrieve by column name
				userID = rs.getInt("UserID");
				userName = rs.getString("Username");
				password = rs.getString("Password");
				email = rs.getString("Email");
				firstName = rs.getString("Firstname");
				lastName = rs.getString("Lastname");
				createDate = rs.getDate("CreateDate");
				user.setUserID(userID);
				user.setUserName(userName);
				user.setPassword(password);
				user.setEmail(email);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setCreateDate(createDate);

				userList.add(user);

				//System.out.print("USERID::::::::::::::::::::::::::::::::::::::::::::::: " + id);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();

		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		}
		return userList;
	}

	public static void insertUser(User user){
		Connection conn = DatabaseUtil.getConnection();
		Statement stmt = null;

		try {
			stmt = conn.createStatement();

			String sql;
			sql = "select MAX(userid) as USERID from user;";
			ResultSet rs = null;

			rs = stmt.executeQuery(sql);
			int id = 0;

			while (rs.next()){
				id = rs.getInt("USERID");
			}

			int new_id = id + 1;
			String insert_sql;
			insert_sql = "INSERT INTO User (UserID, UserName, Password, Email, Firstname, LastName, Status, CreateDate) Values (" +  new_id + ",'" + user.getUserName() + "',  '" +user.getPassword()+ "', '"+user.getEmail()+"', '" + user.getFirstName() + "', '" + user.getLastName()+ "','" + user.getStatus() + "', null)";
			stmt.executeUpdate(insert_sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
