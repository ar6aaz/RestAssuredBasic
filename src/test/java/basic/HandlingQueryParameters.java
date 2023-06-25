package basic;

import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class HandlingQueryParameters {

//    1. Single Query parameters in URL
    @Test
    public void singleQueryParam(){
        given()
                .baseUri("https://restcountries.com/v2/").
//                param("fullText",true).
                queryParam("fullText",true).
        when()
                .get("/name/india").
        then()
                .log().all()
                .statusCode(200);
    }

    //    2. Multiple Query parameters in URL
    @Test
    public void multipleQueryParams(){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("noofRecords", "10");
        params.put("idStarts", "1001");

        given()
                .baseUri("https://hub.dummyapis.com/")
                .queryParams(params).
        when()
                .get("/employee").
        then()
                .log().all();

    }

    //    3. Multiple value in same parameter
    @Test
    public void multipleValueInSameParam(){
       given()
                .baseUri("https://restcountries.com/v2")
                .queryParam("codes","no,ee,in").
        when()
                .get("/alpha").
        then()
                .log().all()
                .statusCode(200);

    }

//    //    4. Multiple value as path parameter
    @Test
    public void multipleValueAsPathParam(){
        given()
                .baseUri("https://restcountries.com/v2")
                .pathParam("currency","USD").
        when()
                .get("/currency/{currency}").
        then()
                .log().all()
                .statusCode(200);

    }

    //    5. Handling form data
    @Test
    public void sendFormData(){
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("First Name","Thomas")
                .formParam("Last Name","Shelby").
        when()
                .post("/post").
        then()
                .log().all()
                .statusCode(200);

    }

//    6. Sending Request Headers
    @Test
    public void sendRequestHeaders(){
        given()
                .baseUri("https://postman-echo.com")
                .header("Content-Type","application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("First Name","Thomas")
                .formParam("Last Name","Shelby").
        when()
                .post("/post").
        then()
                .log().all()
                .statusCode(200);
    }

//    7. Sending Request Headers as object
    @Test
    public void sendRequestHeadersAsObject(){
        HashMap<String, Object> headers = new HashMap<String, Object>();
        headers.put("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");

        given()
                .baseUri("https://postman-echo.com")
                .headers(headers)
                .formParam("First Name","Thomas")
                .formParam("Last Name","Shelby").
        when()
                .post("/post").
        then()
                .log().all()
                .statusCode(200);
    }

//    8. Validate Response Headers

    @Test
    public void validateResponseHeaders() {
        given()
                .baseUri("https://restcountries.com/v2/")
                .queryParam("fullText", true).
        when()
                .get("/name/india").
        then()
                .log().all()
                .statusCode(200)
                .header("Server", "Apache/2.4.38 (Debian)");
    }

//    9. Extract Response Headers

    @Test
    public void extractResponseHeaders() {
    Headers headers =
        given()
                .baseUri("https://restcountries.com/v2/")
                .queryParam("fullText", true).
        when()
                .get("/name/india").
        then()
                .log().all()
                .statusCode(200)
                .extract().headers();

        System.out.println("Cache-Control header value: "+ headers.getValue("Cache-Control"));
    }

}
