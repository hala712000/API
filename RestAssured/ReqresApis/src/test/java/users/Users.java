package users;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertNotNull;

public class Users {
    @Test
    public void CreateUser(){

        HashMap <String , String> body = new HashMap<>();
        body.put("name" , "morpheus");
        body.put("job" , "leader");

        given().baseUri("https://reqres.in")
                .header("Content-Type" , "application/json")
                .body(body)
                .when().post("/api/users")
                .then().log().all()
                .assertThat().statusCode(201)
                .body("name" , notNullValue())
                .body("job" , notNullValue())
                .body("id" , notNullValue())
                .body("createdAt" , notNullValue());

    }

    @Test
    public void GetAllUsers(){
        given().baseUri("https://reqres.in")
                .when().get("/api/users?page=2")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("page" , notNullValue())
                .body("per_page" , notNullValue())
                .body("total" , notNullValue())
                .body("total_pages" , notNullValue())
                .body("data" , notNullValue())
                .body("data" , hasSize(6))
                .body("support" , notNullValue());
    }

    @Test
    public void GetSingleUser(){
        given().baseUri("https://reqres.in")
                .when().get("/api/users/2")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("data" , notNullValue())
                .body("data.id" , notNullValue())
                .body("data.email" , notNullValue())
                .body("data.first_name" , notNullValue())
                .body("data.last_name" , notNullValue())
                .body("data.avatar" , notNullValue())
                .body("support" , notNullValue())
                .body("support.url" , notNullValue())
                .body("support.text" , notNullValue());



    }

    @Test
    public void GetSingleUserNotFound() {
        given().baseUri("https://reqres.in")
                .when().get("/api/users/23")
                .then().log().all()
                .assertThat().statusCode(404);


    }

    @Test
    public void UpdateUserJob() {
        HashMap <String , String> body = new HashMap<>();
        body.put("name" , "morpheus");
        body.put("job" , "zion resident");

        given().baseUri("https://reqres.in")
                .header("Content-Type" , "application/json")
                .body(body)
                .when().put("/api/users/2")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("name" , notNullValue())
                .body("job" , notNullValue())
                .body("updatedAt" , notNullValue());
    }

    @Test
    public void PatchUserJob() {
        HashMap <String , String> Body = new HashMap<>();
        Body.put("name" , "morpheus");
        Body.put("job" , "zion resident");

        given().baseUri("https://reqres.in")
                .header("Content-Type" , "application/json")
                .body(Body)
                .when().patch("/api/users/2")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("name" , notNullValue())
                .body("job" , notNullValue())
                .body("updatedAt" , notNullValue());
    }

    @Test
    public void DelayedResponse(){
        given().baseUri("https://reqres.in")
                .param("delay" , 3)
                .when().get("/api/users?delay=3")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("page" , notNullValue())
                .body("per_page" , notNullValue())
                .body("total" , notNullValue())
                .body("total_pages" , notNullValue())
                .body("data" , notNullValue())
                .body("data" , hasSize(6))
                .body("support" , notNullValue());
    }

    @Test
    public void DeleteUser(){
        given().baseUri("https://reqres.in")
                .when().delete("/api/users/2")
                .then().log().all()
                .assertThat().statusCode(204);
    }
}


