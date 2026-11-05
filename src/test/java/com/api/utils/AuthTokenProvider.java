package com.api.utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import static com.api.constant.Role.*;

import com.api.constant.Role;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private AuthTokenProvider() {
		
	}
	public static String getToken(Role roles) throws IOException {

//	public static String getToken(String roles) throws IOException {
//		want to make the request for the login api and we to extract the token and 
//		print it on the console.
		
		UserCredentials userCredentials=null;
		
		if(roles==FD) {
			userCredentials = new UserCredentials("iamfd", "password");
		}
		else if(roles==SUP) {
			userCredentials = new UserCredentials("iamsup", "password");
		}
		else if(roles==ENG) {
			userCredentials = new UserCredentials("iameng", "password");
		}
		else if(roles==QC) {
			userCredentials = new UserCredentials("iamqc", "password");
		}
		
		
//		if(role.equalsIgnoreCase("FD")) {
//			userCredentials = new UserCredentials("iamfd", "password");
//		}
//		else if(role.equalsIgnoreCase("SUP")) {
//			userCredentials = new UserCredentials("iamsup", "password");
//		}
//		else if(role.equalsIgnoreCase("ENG")) {
//			userCredentials = new UserCredentials("iameng", "password");
//		}
//		else if(role.equalsIgnoreCase("QC")) {
//			userCredentials = new UserCredentials("iamqc", "password");
//		}
	String token=	given()
		 .baseUri(ConfigManager.getProperty("BASE_URI"))
		 .contentType(ContentType.JSON)
//		 .body(new UserCredentials("iamfd", "password"))//hardcode 
		 .body(userCredentials) 
		 .when()
		 .post("login")
		 .then()
		 .log().ifValidationFails()
		 .statusCode(200)
		 .body("message", equalTo("Success"))
		 .extract()
		 .body()
		 .jsonPath()
		 .getString("data.token");
		
//	System.out.println("-------------------------------------------------------");
//	System.out.println(token);
	return token;
		
	}

}
