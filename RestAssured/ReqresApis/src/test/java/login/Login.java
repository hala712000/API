package login;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertNotNull;

public class Login {



    @Test
    public void ShouldBeAbleToLogin(){

        HashMap<String , String> responseBody = new HashMap<>();
        responseBody.put("email" , "eve.holt@reqres.in");
        responseBody.put("password" , "cityslicka");

        given().baseUri("https://reqres.in")
                .header("Content-Type" , "application/json")
                .body(responseBody)
                .when().post("/api/login")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("token" , notNullValue());

    }

    @Test
    public void ShouldNotBeAbleToLogin(){

        HashMap<String , String> respoBody = new HashMap<>();
        respoBody.put("email" , "peter@klaven");

        given().baseUri("https://reqres.in")
                .header("Content-Type" , "application/json")
                .body(respoBody)
                .when().post("/api/login")
                .then().log().all()
                .assertThat().statusCode(400)
                .body(containsString("Missing password"));


    }
}
