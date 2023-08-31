package stepdefs;

import java.security.InvalidAlgorithmParameterException;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import resource.*;

import static io.restassured.RestAssured.*;

public class placeAPI extends utility{
	
	dataBuild db = new dataBuild();
	
	@Given("Add place payload")
	public void add_place_payload() {
		request = given().spec(requestSpecification()).body(db.addPlaceDataBuild());
	}
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String website) {
		request = given().spec(requestSpecification()).body(db.addPlaceDataBuild(name, language, website));
	}
	
	@Given("Get place at {string}")
	public void get_place_payload(String name) {
		String place_id = getMapValue(name);
		request = given().spec(requestSpecification()).spec(requestSpecification().queryParam("place_id",place_id));
	}
	
	@Given("Delete place at {string}")
	public void delete_place_payload(String name) {
		request = given().spec(requestSpecification()).body(db.deletePlaceDataBuild(name));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_add_place_api_with_post_http_request(String api, String httpMethod) throws InvalidAlgorithmParameterException {
		callAPI(api, httpMethod);
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code_response_code(int responseCode) {
		checkStatusCode(responseCode);
	}

	@And("{string} in reponse is {string}")
	public void status_in_reponse_is_response_status(String key, String value) {
		valueCheck(response, key, value);
	}
	
	@And("verify the place_id maps to {string}")
	public void verify_the_place_id_maps_to(String name) {
		mapPlaceIDToName(name);
	}
	
	@After
	public void afterClass() {
		printResponse(response);
	}

}
