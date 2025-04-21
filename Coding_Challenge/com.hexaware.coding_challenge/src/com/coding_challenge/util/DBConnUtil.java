package com.coding_challenge.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnUtil {
	public static Connection getConn() {
		Connection conn = null;
		try {
			Properties props = new Properties();

			InputStream input = DBConnUtil.class.getClassLoader().getResourceAsStream("db.properties");

			if (input == null) {
				throw new RuntimeException("File not found in classpath: db.properties");
			}

			props.load(input);

			String url = props.getProperty("InsuranceDB.url");
			String username = props.getProperty("InsuranceDB.username");
			String password = props.getProperty("InsuranceDB.password");

			if (url == null || username == null || password == null) {
				throw new RuntimeException("One or more database properties are missing.");
			}

			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unexpected error occurred: " + e.getMessage());
		}
		return conn;
	}
}
