package util;

import org.junit.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class utility {
	
	public void valueCheck(Response response, String key, String value) {
		JsonPath js = new JsonPath(response.asString());
		Assert.assertEquals(js.get(key), value);
	}
}
