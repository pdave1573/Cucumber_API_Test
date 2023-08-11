package stepdefs;

import java.security.InvalidAlgorithmParameterException;

import io.cucumber.java.en.*;
import resource.*;

import static io.restassured.RestAssured.*;

public class placeAPI extends utility{
	
	dataBuild db = new dataBuild();
	
	
	@Given("Add place payload")
	public void add_place_payload() {
		request = given().spec(requestSpecification()).body(db.addPlaceDataBuild());
	}

	@When("user calls {string} with post http request")
	public void user_calls_add_place_api_with_post_http_request(String api) throws InvalidAlgorithmParameterException {
		callAPI(api);
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code_response_code(int responseCode) {
		checkStatusCode(responseCode);
	}

	@And("{string} in reponse is {string}")
	public void status_in_reponse_is_response_status(String key, String value) {
		valueCheck(response, key, value);
		printResponse(response);
	}

}
