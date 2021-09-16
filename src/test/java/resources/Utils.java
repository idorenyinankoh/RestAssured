package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class Utils extends BodyResouce {
	public static RequestSpecification req;
	
	public RequestSpecification requestSpec() throws IOException {
		
		if (req==null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL"))
					.setContentType(ContentType.JSON)
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return req;				
		}
		return req; // the if statement is added to maintain the log. so that it wont erase values after evry run
	}
	public ResponseSpecification responseSpec() {
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return res;
	}
	public String getGlobalValue(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream filepath = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\globalinputData.properties");
		properties.load(filepath);
		String bString = properties.getProperty(key);
		return bString;
	}
	public String getJsonPath(Response response, String key) {
		String responseString = response.asString();
		JsonPath json = new JsonPath(responseString);
		return json.getString(key);
		}


}
