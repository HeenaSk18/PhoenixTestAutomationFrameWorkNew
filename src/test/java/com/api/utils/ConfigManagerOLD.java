package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {
//WAP to read the Properties file from src/test/resouces/config.properties
//	public static void main(String[] args) throws IOException {
//		Special Class: Properties
	
	private static Properties prop = new Properties(); // to get the property
	
//	static block --- Operation of loading the properties file in the memory
//	bcz it is static block it will executed Once During_Class Loading Time_____
	static {
		File configFile = new File(
				System.getProperty("user.dir") + File.separator + "src"+ File.separator + "test"+File.separator +"resources"+File.separator +"config"+File.separator +"config.properties");
//		file.spearator ---/mac and linux \window --- platform independent
		FileReader fileReader=null;
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
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
// static for acces of instent of object help 
//	Properties prop = new Properties(); // Create the object of Properties Class
//		 Load the Properties file using the load() -- reader and inputstream

//		 File configFile = new File("C:\\Users\\Heena\\eclipse-workspace\\PhoenixTestAutomationFrameWorkNew\\src\\test\\resources\\config\\config.properties");
//		 hard coding --- path -- compatible only windows os -- it should use all OS/users all respect all mechine
//		 easly get with system.getproperty("user.dir) --- C:\\Users\\Heena\\eclipse-workspace\\PhoenixTestAutomationFrameWorkNew -- path/directory for the project is present in machine

//		
//		File configFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config\\config.properties");
//		
//		FileReader fileReader = new FileReader(configFile);
//
//		prop.load(fileReader);

//		System.out.println(prop.getProperty("BASE_URI"));
		return prop.getProperty(key);

	}
}
