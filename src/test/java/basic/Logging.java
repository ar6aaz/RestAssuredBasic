package basic;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Logging {

//    1. Log Everything - same as log all
    @Test
    public void logAllDetails(){
        given()
                .baseUri("https://restcountries.com/v2/").
        when()
                .get("/alpha/USA").
        then()
                .log().everything();
//                .log().all();
    }

//    2. Log Only Body or only headers
    @Test
    public void logBodyOrHeader(){
        given()
                .baseUri("https://restcountries.com/v2/").
        when()
                .get("/alpha/USA").
        then()
//                .log().body();
                .log().headers();
    }

//    3. Log cookies and status
    @Test
    public void logCookies(){
        given()
                .baseUri("https://restcountries.com/v2/").
        when()
                .get("/alpha/USA").
        then()
//                .log().cookies();
        .log().status();
    }

//        4. Log if there are errors
    @Test
    public void logIfError(){
        given()
                .baseUri("https://restcountries.com/v2/").
        when()
                .get("/alpha/XYZ").
        then()
                .log().ifError()
                .log().ifStatusCodeIsEqualTo(404);
    }

//      5. Log if validation fails
    @Test
    public void logIfValidationFails(){
        given()
                .baseUri("https://restcountries.com/v2/").
        when()
                .get("/alpha/XYZ").
        then()
                .log().ifValidationFails()
                .statusCode(200);
    }
}