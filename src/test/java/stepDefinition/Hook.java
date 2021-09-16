package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hook {
	@Before("@DeletePlace")
	public void getPlaceId() throws IOException {
		 // get place id
		// this code should run ONLY if PLaceid is null
		AddPlaceStep step = new AddPlaceStep();
		if (step.placeId == null) {
			step.user_has_api_endpoint("mike", "efik", "4 round street", "www.sarl.com");
			step.user_sends_http_request("AddPlaceAPI", "post");
		}
	}
	
}