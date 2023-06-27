package com.restTests.simpleTests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restTests.POJOs.LoginDataPOJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginTekApiWithPojo {

	@BeforeClass
	public void init() {
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
	}

	@Test
	public String loginToApi() {

		LoginDataPOJO loginData = new LoginDataPOJO();
		loginData.setUsername("bhawna.govil@gmail.com");
		loginData.setPassword("Tekarch@123");
		Response res = RestAssured.given().contentType(ContentType.JSON).body(loginData).when().post("login");
		res.then().statusCode(201).contentType(ContentType.JSON).time(Matchers.lessThan(5000L));
		String extractedToken = res.body().jsonPath().getString("[0].token");
		System.out.println("token=" + extractedToken);
		res.prettyPrint();
		return extractedToken;
	}
}
