package basic;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class SimplePostPutDeleteRequest {

//    1. POST Request using File

    @Test
    public void verifyPostRequestUsingFile(){

        File file = new File(System.getProperty("user.dir")+"/resources/create_employee.json");

        int id = given()
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .contentType(ContentType.JSON)
                .body(file).
        when()
                .post("/create").
        then()
                .statusCode(200)
                .body("data.name",equalTo("Mark"))
                .extract().path("data.id");

        System.out.println("ID is: "+id);
    }

//    1. POST Request using JSON Object
    @Test
    public void verifyPostRequestUsingJSONObject(){

        JSONObject body = new JSONObject();
        body.put("name","Mark");
        body.put("salary","123");
        body.put("age","23");

        int id = given()
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .contentType(ContentType.JSON)
                .body(body.toString()).
                        when()
                .post("/create").
                        then()
                .statusCode(200)
                .body("data.name",equalTo("Mark"))
                .extract().path("data.id");

        System.out.println("ID is: "+id);
    }

//    3. PUT request
    @Test
    public void verifyPutRequestUsingJsonObject(){

        JSONObject body = new JSONObject();
        body.put("name","ar6");
        body.put("salary","123");
        body.put("age","23");

        given()
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .contentType(ContentType.JSON)
                .body(body.toString()).
        when()
                .put("/update/21").
        then()
                .statusCode(200);
    }

//    4. DELETE request
    @Test
    public void verifyDeleteRequest(){

    String msg =
        given()
                .baseUri("https://dummy.restapiexample.com/api/v1").
        when()
                .delete("/delete/1295").
        then()
                .statusCode(200)
                .extract().path("message");

        System.out.println("msg: "+msg);
    }


}
