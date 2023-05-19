package com.qa.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;





public class GetCallBDD {
	@Test(priority=1)
	public void test_petstore() {

		//	given().
		//	when().
		//	then().
		//	assert()

		given().
		when().
		get("https://petstore.swagger.io/v2/swagger.json").
		then().
		assertThat().
		statusCode(200);

	}



	@Test(priority=2)
	public void test_add() {

		HashMap data=new HashMap();	
		data.put("id", "1");
		data.put("petId","15");
		data.put("quantity", "10");
		data.put("shipDate", "2023-04-20T08:07:43.224Z");
		data.put("status", "placed");
		data.put("complete", "true");


		Response res=
				given()
				.contentType("application/json")
				.body(data)
				.when()
				.post("https://petstore.swagger.io/v2/store/order")
				.then()
				.statusCode(200)
				.log().body()
				.extract().response();
		String jsonString= res.asString();
		Assert.assertEquals(jsonString.contains("successful operation"), true);


	}
	@Test(priority=3)
	public void test_getPetDetails() {

		given()
		.when()
		.get("https://petstore.swagger.io/v2/store/order/1")
		.then()
		.statusCode(200)
		.log().body();



	}


}
