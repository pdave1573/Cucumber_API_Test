package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.security.InvalidAlgorithmParameterException;
import java.util.Properties;

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

	static RequestSpecification req;
	static ResponseSpecification res;
	String logFileLocation;
	String key;
	String baseURI;
	File file;

	protected RequestSpecification request;
	protected Response response;

	public RequestSpecification requestSpecification() {
		if(req==null) {
			PrintStream log = null;
			globalProperties();
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
					.setBaseUri(baseURI)
					.setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.addQueryParam("key", key)
					.build();

			return req;
		}
		else
			return req;
	}

	public ResponseSpecification responseSpecification() {
		if(res==null) {
			res = new ResponseSpecBuilder()
					.expectContentType(ContentType.JSON)
					.expectStatusCode(200)
					.build();

			return res;
		}
		else
			return res;
	}

	public void globalProperties() {
		Properties prop = new Properties();
		FileInputStream propFile;
		try {
			propFile = new FileInputStream("src/test/java/resource/global.properties");
			prop.load(propFile);
			key = prop.getProperty("key");
			baseURI = prop.getProperty("BaseUri");
			logFileLocation = prop.getProperty("logFileLocation");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void callAPI(String api, String httpMethod){
		apiEndPointResources resource = apiEndPointResources.valueOf(api);
		
		if(httpMethod.equalsIgnoreCase("post")) {
		response = request.when().post(resource.getResource())
				.then().spec(responseSpecification()).extract().response();
		}
		else if(httpMethod.equalsIgnoreCase("get")) {
			response = request.when().get(resource.getResource())
					.then().spec(responseSpecification()).extract().response();
		}
		else if(httpMethod.equalsIgnoreCase("put")) {
			response = request.when().put(resource.getResource())
					.then().spec(responseSpecification()).extract().response();
		}
		else if(httpMethod.equalsIgnoreCase("delete")) {
			response = request.when().delete(resource.getResource())
					.then().spec(responseSpecification()).extract().response();
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
