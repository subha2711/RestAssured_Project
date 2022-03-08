package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class PUT_Update_A_Product {

	
	
	@Test
	public void  deleteAProducts() {
		//https://techfios.com/api-prod/api/product/read_one.php?id=3506
		//https://techfios.com/api-prod/api/product
		// uri https://techfios.com/api-prod/api/product
		// /read.php
		// https://techfios.com/api-prod/api/product    base
		// /create.php
		
		/*
		 * "name" : "Pillow 2.0", "price" : "9", "description" :
		 * "The best pillow for amazing programmers.", "category_id" : 2, "created" :
		 * "2018-06-01 00:35:07"
		 */
		
		String payloadPath = "src/main/java/data/updatePayload.json";
//		HashMap<String,String> payload = new HashMap<String, String>();
//		payload.put("name", "RestAssured Book");
//		payload.put("price", "5");
//		payload.put("description", "buy the book");
//		payload.put("category_id", "6");
		
		Response response = 
				given()
					.log().all()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type","application/json")
					.body(new File (payloadPath))
				
				.when()
					.put("delete.php")
				.then().log().all()
					.extract().response();
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code : " + statusCode);
		Assert.assertEquals(statusCode, 200);
//		
		long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		
		if (actualResponseTime <=2000) {
			System.out.println("Response is within Time Range");
		}else {
			System.out.println("Response body is not in rangr");
		}
		String responseBody = response.getBody().asString();
		System.out.println("rb--"+ responseBody);
//		
		JsonPath jp = new JsonPath(responseBody);
		String msg = jp.getString("message");
		System.out.println("message  is "+ msg);
		Assert.assertEquals(msg, "Product was updated.");
		
	
		
		
	}
}
