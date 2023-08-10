package stepdefs;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.*;
import pojo.*;
import resource.dataBuild;
import util.utility;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.*;
import io.restassured.builder.*;

public class placeAPI {
	RequestSpecification request;
	Response response;
	
	utility util = new utility();
	dataBuild db = new dataBuild();
	
	RequestSpecification req = new RequestSpecBuilder()
			.setBaseUri("https://rahulshettyacademy.com/maps/api/place/")
			.setContentType(ContentType.JSON)
			.build();
	
	ResponseSpecification res = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.build();

	@Given("Add place payload")
	public void add_place_payload() {
		request = given().spec(req).body(db.addPlaceDataBuild());
	}

	@When("user calls {string} with post http request")
	public void user_calls_add_place_api_with_post_http_request(String api) throws InvalidAlgorithmParameterException {
		if(api.equals("AddPlaceAPI")) {
			response = request.when().post("add/json/")
				.then().spec(res).extract().response();
		}
		else if(api.equals("DeletePlaceAPI")) {
			response = request.when().post("delete/json/")
					.then().spec(res).extract().response();
		}
		else if(api.equals("UpdatePlaceAPI")) {
			response = request.when().post("update/json/")
					.then().spec(res).extract().response();
		}
		else if(api.equals("GetPlaceAPI")) {
			response = request.when().post("get/json/")
					.then().spec(res).extract().response();
		}
		else {
			throw new InvalidAlgorithmParameterException();
		}
	}

	@Then("the API calls is success with status code {int}")
	public void the_api_calls_is_success_with_status_code_response_code(int responseCode) {
		Assert.assertEquals(responseCode, response.getStatusCode());
	}

	@And("{string} in reponse is {string}")
	public void status_in_reponse_is_response_status(String key, String value) {
		util.valueCheck(response, key, value);
	}

}
