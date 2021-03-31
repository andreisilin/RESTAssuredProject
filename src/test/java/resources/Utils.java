package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    public static RequestSpecification reqSpec;

    public RequestSpecification requestSpecification() throws IOException {
        if(reqSpec==null){
            PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
            System.out.println("======Sending request==========");
            reqSpec = new RequestSpecBuilder().setBaseUri(getProperty("baseUrl")).addQueryParam("key",
                    "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return reqSpec;
        }
        return reqSpec;
    }

    public static String getProperty(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream =new FileInputStream("C:\\Users\\andrei.silin\\IdeaProjects\\RESTAssuredProject\\src\\test\\java\\resources\\global.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);

    }

    public String getJsonPath(Response response, String key){
        String stringResponse = response.asString();
        JsonPath jsonPath = new JsonPath(stringResponse);
        return jsonPath.getString(key);
    }
}
