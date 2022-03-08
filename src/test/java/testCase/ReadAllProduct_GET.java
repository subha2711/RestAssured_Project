package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ReadAllProduct_GET {

	
	@Test
	public void  read_All_Products() {
		//https://techfios.com/api-prod/api/product
		// uri https://techfios.com/api-prod/api/product
		// /read.php
		
		Response response = 
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type","application/json")
				.when()
					.get(" /read.php")
				.then()
					.extract().response();
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code : " + statusCode);
		Assert.assertEquals(statusCode, 200);
		String responseBody = response.getBody().print();
		System.out.println("rb--"+ responseBody);
	}
}
