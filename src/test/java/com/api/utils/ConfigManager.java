package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	
	private static Properties prop = new Properties(); 
	private static String path ="config/config.properties";
	private ConfigManager() {
//		private constructor
	}
	
	static {
	InputStream input	= Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	if(input ==null) {
		throw new RuntimeException("Cannot Find the File at the path "+path);
	}
	try {
			prop.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getProperty(String key) throws IOException {
		return prop.getProperty(key);

	}
}
