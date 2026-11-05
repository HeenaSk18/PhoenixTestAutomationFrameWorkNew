package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
//	public static void main(String[] args) {
		
	
	@Test
	public void loginAPITest() throws IOException {
//		Rest Assured Code --- RA is Utlity cls
//		ConfigManager configManager= new ConfigManager(); -- for access bbut not to create object -- restriction 
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");//POJO object
		
//		Read the property value that is going to be passed from terminal
		System.out.println("------------------->>>>> "+System.getProperty("env"));
		
		given() //RequestSpeficiation
			
//			.baseUri("http://64.227.160.186:9000/v1")
//			.baseUri(configManager.getProperty("BASE_URI"))
//			.baseUri(ConfigManager.getProperty("BASE_URI"))
//			.baseUri(getProperty("BASE_URI"))
			.baseUri(getProperty("BASE_URI")) //explicitly configmanager2

			.and()
			.contentType(ContentType.JSON)
			.and()
			.body(userCredentials)
			.accept(ContentType.JSON)
			.log().uri()
			.log().method()
			.log().headers()
			.log().body()
			.when()
			.post("login")
			.then()
			.log().all()
			.statusCode(200)
			.time(lessThan(1000L))
			.and()
			.body("message", equalTo("Success"))
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
			
	}
	
}
