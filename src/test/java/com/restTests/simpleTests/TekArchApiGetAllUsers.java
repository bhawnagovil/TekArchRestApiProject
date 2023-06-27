package com.restTests.simpleTests;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class TekArchApiGetAllUsers {

	TekArchLoginAPITest TekliginApi;
	@BeforeClass
	public void init() {
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
	}
	public void getUsers(String token) {
		TekliginApi= new TekArchLoginAPITest();
	String extractedToken= TekliginApi.loginToApi();
	Header ob = new Header("token", extractedToken);
	Response res = RestAssured.given().header(ob).when()
			.get("getdata");

	res.then().statusCode(200).contentType(ContentType.JSON);

	// .time(Matchers.lessThan(10000L));

	res.prettyPrint();
}
	
}
