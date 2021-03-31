package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

import java.io.IOException;

public class AddPlaceDefinition extends Utils {

    RequestSpecification request;
    RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    Response response;
    TestDataBuilder dataBuilder = new TestDataBuilder();
    static String placeId;

    @Given("addPlace payload with {string} {string} {string}")
    public void addPlace_payload_with(String name, String address, String language) throws IOException {
        request = given().spec(requestSpecification()).
                body(dataBuilder.addPlacePayload(name, address, language));
    }

    @When("user calls {string} API with {string} request")
    public void user_calls_API_with_POST_request(String resource, String httpMethod) {
        APIResources apiResources = APIResources.valueOf(resource);
        resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
        if(httpMethod.equalsIgnoreCase("POST")){
            response = request.when().post(apiResources.getResource());
        }else if(httpMethod.equalsIgnoreCase("GET")){
            response = request.when().get(apiResources.getResource());
        }else if(httpMethod.equalsIgnoreCase("UPDATE")){
            response = request.when().put(apiResources.getResource());
        }else{
            response = request.when().delete(apiResources.getResource());
        }
    }

    @Then("response is successful with status code {int}")
    public void response_is_successful_with_status_code(Integer code) {
        assertEquals(200,response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {
        assertEquals(getJsonPath(response,key),value);
    }

    @Then("verify place_id is created maps to {string} using {string} API")
    public void verify_place_id_is_created_maps_to_using_api(String expectedName, String resource) throws IOException {
        placeId = getJsonPath(response,"place_id");
        request = given().spec(requestSpecification()).queryParam("place_id",placeId);
        user_calls_API_with_POST_request(resource,"GET");
        String actualName = getJsonPath(response,"name");
        assertEquals(expectedName,actualName);
    }

    @Given("deletePlace payload")
    public void deletePlace_payload() throws IOException {
        request = given().spec(requestSpecification()).body(dataBuilder.deletePlacePayload(placeId));
    }
}
