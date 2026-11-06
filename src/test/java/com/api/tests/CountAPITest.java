package com.api.tests;

import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import  org.testng.annotations.Test;

import com.api.constant.Role;

public class CountAPITest {
		
		@Test
		public void verifyCountAPIResponse(){
			given()
				.baseUri(getProperty("BASE_URI"))
				.and().
				header("Authorization",getToken(Role.FD))
				.log().uri()
				.log().method()
				.log().headers()
				.when()
				.get("/dashboard/count")
				.then()
				.log().all()
				.statusCode(200)
				.body("message",equalTo("Success"))
				.time(lessThan(1000L))
				.body("data",notNullValue())
				.body("data.size()",equalTo(3))
				.body("data.count",everyItem(greaterThanOrEqualTo(0)))
				.body("data.label",everyItem(not(blankOrNullString())))
				.body("data.key", containsInAnyOrder("pending_fst_assignment", "pending_for_delivery", "created_today"))
				.body(matchesJsonSchemaInClasspath("response-schema/CountAPIresponseSchema-FD.json"));
		}
	
	@Test
	public void countAPITest_MissingAuthToken() {
		given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.log().uri()
			.log().method()
			.log().headers()
			.when()
			.get("/dashboard/count")
			.then()
			.log().all()
			.statusCode(401);
	}
}
