package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static final Properties prop = new Properties();
	private static String path;
	private static String env;

	private ConfigManager() {
		// private constructor
	}

	static {
		env = System.getProperty("env", "qa").toLowerCase().trim();
		System.out.println("Running Test in Env " + env);

		switch (env) {
			case "dev" -> path = "config/config.dev.properties";
			case "qa"  -> path = "config/config.qa.properties";
			case "uat" -> path = "config/config.uat.properties";
			default    -> path = "config/config.qa.properties";
		}

		try (InputStream input = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream(path)) {

			if (input == null) {
				throw new RuntimeException("Cannot find the file at the path " + path);
			}
			prop.load(input);

		} catch (IOException e) {
			throw new RuntimeException("Failed to load config file: " + path, e);
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}