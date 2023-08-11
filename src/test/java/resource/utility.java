package resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.security.InvalidAlgorithmParameterException;

import org.junit.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class utility {
	
	RequestSpecification req;
	ResponseSpecification res;
	String logFileLocation = "./logs.log";
	File file;
	
	protected RequestSpecification request;
	protected Response response;
	
	public RequestSpecification requestSpecification() {
		PrintStream log = null;
		try {
			log = new PrintStream(logFileLocation);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			file = new File(logFileLocation);
		}
		req = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com/maps/api/place/")
				.setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.addQueryParam("key", "qaclick123")
				.build();
		
		return req;
	}
	
	public ResponseSpecification responseSpecification() {
		res = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.build();
		
		return res;
	}
	
	public void callAPI(String api) throws InvalidAlgorithmParameterException {
		if(api.equals("AddPlaceAPI")) {
			response = request.when().post("add/json/")
				.then().spec(responseSpecification()).extract().response();
		}
		else if(api.equals("DeletePlaceAPI")) {
			response = request.when().post("delete/json/")
					.then().spec(responseSpecification()).extract().response();
		}
		else if(api.equals("UpdatePlaceAPI")) {
			response = request.when().post("update/json/")
					.then().spec(responseSpecification()).extract().response();
		}
		else if(api.equals("GetPlaceAPI")) {
			response = request.when().post("get/json/")
					.then().spec(responseSpecification()).extract().response();
		}
		else {
			throw new InvalidAlgorithmParameterException();
		}
	}
	
	public void valueCheck(Response response, String key, String value) {
		JsonPath js = new JsonPath(response.asString());
		Assert.assertEquals(js.get(key), value);
	}
	
	public void printResponse(Response response) {
		System.out.println(response.asPrettyString());
	}
	
	public void checkStatusCode(int responseCode) {
		Assert.assertEquals(responseCode, response.getStatusCode());
	}
}
