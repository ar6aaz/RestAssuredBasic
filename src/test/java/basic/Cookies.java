package basic;

import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Cookies {
//        1. Send cookie using header
    @Test
    public void sendCookie(){
        given()
                .baseUri("https://restcountries.com/v2/")
                .queryParam("fullText",true)
                .cookie("user","abc").
        when()
                .get("/name/india").
        then()
                .log().all()
                .statusCode(200);
    }

//        2. Send cookie using Cookie builder
    @Test
    public void sendCookieusingBuilder(){
        Cookie cookie = new Cookie.Builder("usertype","abc").setSecured(true).setComment("Test comment").build();

        given()
                .baseUri("https://restcountries.com/v2/")
                .queryParam("fullText",true)
                .cookie(cookie).
        when()
                .get("/name/india").
        then()
                .log().all()
                .statusCode(200);
    }

//        3. Extract Response cookies
    @Test
    public void extractResponseCookies(){
        Map<String, String> cookies =
        given()
                .baseUri("https://data.fixer.io/api")
                .queryParam("access_key","").
        when()
                .get("/latest").
        then()
                .log().all()
                .statusCode(200)
                .extract().cookies();

        System.out.println("Cloudflare Cookie: "+cookies.get("__cfduid"));
    }


}
