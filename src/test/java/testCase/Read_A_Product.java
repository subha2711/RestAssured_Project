package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class Read_A_Product {

	
	@Test
	public void  readAProducts() {
		//https://techfios.com/api-prod/api/product/read_one.php?id=3506
		//https://techfios.com/api-prod/api/product
		// uri https://techfios.com/api-prod/api/product
		// /read.php
	//	HashMap<String,String> queryParams = new HashMap<String,String>();
		Response response = 
				given()
					.baseUri("https://techfios.com/api-prod/api/product")
					.header("Content-Type","application/json")
					.queryParam("id", "3506")
				//	.queryParams(queryParams)
				.when()
					.get(" /read_one.php")
				.then()
					.extract().response();
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code : " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		
		if (actualResponseTime <=2000) {
			System.out.println("Response is within Time Range");
		}else {
			System.out.println("Response body is not in rangr");
		}
		String responseBody = response.getBody().asString();
		System.out.println("rb--"+ responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		String productName = jp.getString("name");
		System.out.println("product name is "+ productName);
		Assert.assertEquals(productName, "Pillow 2.0");
		
		String productDescription = jp.getString("description");
		System.out.println("product description is "+ productDescription);
		Assert.assertEquals(productDescription, "Amazing pillow.");
		
		String productPrice = jp.getString("price");
		System.out.println("product price is "+ productPrice);
		Assert.assertEquals(productPrice, "19");
	
		String productCategory_id = jp.getString("category_id");
		System.out.println("product Category ID  is "+ productCategory_id);
		Assert.assertEquals(productCategory_id, "2");
		
		String productCategory_name= jp.getString("category_name");
		System.out.println("product Category name  is "+ productCategory_name);
		Assert.assertEquals(productCategory_name, "Electronics");
		
	}
}
