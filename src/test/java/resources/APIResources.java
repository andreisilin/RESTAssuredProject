package resources;

//enum is a class in Java which has collection of constants and methods
public enum APIResources {

    AddPlaceResource("/maps/api/place/add/json"),
    GetPlaceRecourse("/maps/api/place/get/json"),
    UpdatePlaceResources("/maps/api/place/put/json"),
    DeletePlaceResources("/maps/api/place/delete/json");
    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }
}
