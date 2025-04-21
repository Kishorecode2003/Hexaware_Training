package com.coding_challenge.util;

import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {
	public static String getPropertyString(String filename, String key) {
		Properties props = new Properties();

		try (InputStream is = DBPropertyUtil.class.getClassLoader().getResourceAsStream(filename)) {
			if (is == null) {
				System.err.println("File not found in classpath: " + filename);
				return null;
			}

			props.load(is);
			return props.getProperty(key);
		} catch (Exception e) {
			System.err.println("Error reading file: " + filename);
			e.printStackTrace();
			return null;
		}
	}
}
