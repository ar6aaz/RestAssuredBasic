package basic;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SimpleGETRequest {

//    1. Verify status code
    @Test
    public void verifyStatusCode(){
        given().
                baseUri("https://restcountries.com/v2/").
        when().
                get("/all").
        then().
                statusCode(200);
    }

//    2. Verify specific JSON attribute from response
    @Test
    public void verifyJSONResponse(){
        given()
                .baseUri("https://restcountries.com/v2/").
        when()
                .get("/alpha/USA").
        then()
                .statusCode(200)
                .body(
                "name",equalTo("United States of America"),
                "topLevelDomain",hasItem(".us"),
                "nativeName",equalTo("United States"),
                "languages[0].name",equalTo("English")
        );
    }

//    3. Verify certain XML attribute from response
    @Test
    public void verifyXMLResponse(){
        given()
                .baseUri("https://api.openweathermap.org/data/2.5")
                .queryParam("q","London,uk")
                .queryParam("APPID","API_KEY")
                .queryParam("mode","XML").
        when()
                .get("/weather").
        then()
                .statusCode(200)
                .body(
                    "current.city.@name",equalTo("London"),
                    "current.city.country",equalTo("GB")
                );
    }

//    4. Extract response data

    @Test
    public void extractResponseData(){
        Response resp = given()
                .baseUri("https://restcountries.com/v2/").
        when()
                .get("/alpha/USA").
        then()
                .extract().response();
        System.out.println("Response: "+resp.asString());

    }

//    5. Extract specific part of the response
@Test
public void extractSpecificResponseData(){
    String resp = given()
            .baseUri("https://restcountries.com/v2/").
                    when()
            .get("/alpha/USA").
                    then()
            .extract().path("languages[0].name");
    System.out.println("Response: "+resp);

}

    //    5. Extract specific part of the response
    @Test
    public void extractStatusLine(){
            given()
                .baseUri("https://api.printful.com").
            when()
                .get("/variant/1").
            then()
                .statusLine("HTTP/1.1 404 Not Found");

    }

}
