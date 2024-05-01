package resource;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertNotNull;
public class Resource {
    @Test
    public void GetAllResource() {

        given().baseUri("https://reqres.in")
                .when().get("/api/unknown")
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
    public void GetSingleResource(){
        given().baseUri("https://reqres.in")
                .when().get("/api/unknown/2")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("data" , notNullValue())
                .body("data.id" , notNullValue())
                .body("data.name" , notNullValue())
                .body("data.year" , notNullValue())
                .body("data.color" , notNullValue())
                .body("data.pantone_value" , notNullValue())
                .body("support" , notNullValue())
                .body("support.url" , notNullValue())
                .body("support.text" , notNullValue());

    }


    @Test
    public void GetSingleResourceNotFound(){
        given().baseUri("https://reqres.in")
                .when().get("/api/unknown/23")
                .then().log().all()
                .assertThat().statusCode(404);

    }
}
