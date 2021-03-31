package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.bs.A;

import java.io.IOException;

public class Hooks {

    //Hooks are being used to run some code before/after we run actual scenario.
    //In this case we run below code before we run Scenario tagged as @DeletePlace.
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //this code will be executed before we run Scenario tagged with @DeletePlace
        AddPlaceDefinition addPlaceDefinition = new AddPlaceDefinition();
        if(AddPlaceDefinition.placeId == null){
            addPlaceDefinition.addPlace_payload_with("Spektr LLC", "11, Viliams St", "Belarusian");
            addPlaceDefinition.user_calls_API_with_POST_request("AddPlaceResource", "POST");
            addPlaceDefinition.verify_place_id_is_created_maps_to_using_api("Spektr LLC", "GetPlaceRecourse");
        }
    }
}
