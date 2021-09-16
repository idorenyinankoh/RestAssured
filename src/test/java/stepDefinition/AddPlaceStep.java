package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import io.cucumber.core.gherkin.messages.internal.gherkin.internal.com.eclipsesource.json.Json;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.serialization;
import resources.ApiResources;
import resources.BodyResouce;
import resources.Utils;

public class AddPlaceStep extends Utils {
	ResponseSpecification res;
	RequestSpecification request;
	Response response;
	BodyResouce td;
	static String placeId;
	String responseName;

	@Given("User has API endpoint {string} {string} {string} {string}")
	public void user_has_api_endpoint(String name, String language, String address, String website) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		request = given().spec(requestSpec()).body(addLocation(name, language, address, website));
	}

	@When("user sends {string} http {string} request")
	public void user_sends_http_request(String resource, String httpMethod) {
		ApiResources apiresources = ApiResources.valueOf(resource);
		System.out.println("This is the " + apiresources.getResource());// USE .getResource TO GET THE PATH VALUE
		// assertEquals(response.getStatusCode(), 200);
		if (httpMethod.equalsIgnoreCase("POST")) {
			response = request.when().post(apiresources.getResource());
		} else if (httpMethod.equalsIgnoreCase("GET")) {
			response = request.when().get(apiresources.getResource());
		} else if (httpMethod.equalsIgnoreCase("DELETE")) {
			response = request.when().delete(apiresources.getResource());
		}

	}

	@Then("{string} contains in it response {string}")
	public void contains_in_it_response(String actual, String expected) throws IOException {

		String status = getJsonPath(response, actual);
		System.out.println(status);
		placeId = getJsonPath(response, "place_id");
//		String test = getGlobalValue("baseURL");
//		System.out.println(test);
//		String testing = getGlobalValue("testd");
//		System.out.println(testing);

		assertEquals(getJsonPath(response, actual), expected);

	}

	@Then("zensure that the result of the place_id, the {string}  is equal to the name from {string}")
	public void ensure_that_the_result_of_the_place_id_the_is_equal_to_the_name_from(String name, String path)
			throws IOException {

		request = given().spec(requestSpec()).queryParam("place_id", placeId);
		user_sends_http_request(path, "get"); // running the this method to run the get.
		String responseName = getJsonPath(response, "name");
		System.out.println(responseName + " is the compared against " + name);
		assertEquals(responseName, name);
	}

	@Given("User has API endpoint")
	public void user_has_api_endpoint() throws IOException {
		request = given().spec(requestSpec()).body(getDeleteBody(placeId));

	}

	@Then("validate that status code is ok")
	public void validate_that_status_code_is_ok() {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} is {string}")
	public void is(String expected, String actual) {
		assertEquals(getJsonPath(response, expected), actual);
		
	}

}
