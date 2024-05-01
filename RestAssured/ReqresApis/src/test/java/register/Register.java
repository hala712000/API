package register;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.testng.Assert.assertNotNull;

public class Register {
    @Test
    public void ShouldBeAbleToRegister() {
        RestAssured.baseURI = "https://reqres.in";

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password", "pistol");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/register")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        String id = response.jsonPath().getString("id");
        String token = response.jsonPath().getString("token");

        assertNotNull(id, "id is present in the response");
        assertNotNull(token, "token is present in the response");
    }

    @Test
    public void ShouldNotBeAbleToRegister(){

        HashMap<String , String> response = new HashMap<>();
        response.put("email" , "sydney@fife");

        given().baseUri("https://reqres.in")
                .header("Content-Type" , "application/json")
                .body(response)
                .when()
                .post("/api/register")
                .then().log().all()
                .assertThat().statusCode(400)
                .body(containsString("Missing password"));




    }
}


