package resources;

import pojo.AddPlaceApi;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {

    public AddPlaceApi addPlacePayload(String name, String address, String language){
        AddPlaceApi payload = new AddPlaceApi();

        payload.setAddress(address);
        payload.setAccuracy(50);
        payload.setLanguage(language);
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        payload.setLocation(location);
        payload.setName(name);
        payload.setPhone_number("312-222-3333");
        List<String> types = new ArrayList<String>();
        types.add("typeOne");
        types.add("typeTwo");
        payload.setTypes(types);
        payload.setWebsite("https://www.my-emmi.com/startemmi2/");
        return payload;
    }

    public String deletePlacePayload(String placeId){
        return "{\r\n   \"place_id\":\""+placeId+"\"\r\n}";
    }
}
