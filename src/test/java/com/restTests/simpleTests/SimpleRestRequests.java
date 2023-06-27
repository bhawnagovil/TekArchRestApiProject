package com.restTests.simpleTests;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimpleRestRequests {

	@Test
	public void TestFakeResrApi() {
	RequestSpecification req= RestAssured.given();
	Response res= req.when().get("http://fakerestapi.azurewebsites.net/api/v1/Authors");
	res.then().statusCode(200);
	res.then().contentType(ContentType.JSON);
	res.then().time(Matchers.lessThan(4000L));
	res.prettyPrint();
	res.contentType();
	int statuscode= res.statusCode();
	System.out.println("status code :"+ statuscode);
	String contentType= res.contentType();
	System.out.println(contentType);
	//rest assured internally supports jsonpath from gson
	//jayway jsonpath
	String title = res.body().jsonPath().getString("[1].title");
	System.out.println("title= "+ title);
	 res.then().body("[1].lastName", Matchers.is("Last Name 2"));
	String FirstName=res.body().jsonPath().getString("[1].firstName");
	System.out.println("FirstName="+ FirstName);
	int size=	res.body().jsonPath().get("size()");
	System.out.println("number of recors="+size);
	
	ArrayList<Integer> listID=res.body().jsonPath().get("id");
	for(int i:listID) {
		System.out.println(i);
	}
	
	System.out.println(res.body().jsonPath().get("id.min()"));
	System.out.println(res.body().jsonPath().get("id.max()"));
	}
	
	
}
