import io.qameta.allure.Feature;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

import static org.hamcrest.Matchers.*;

public class ApiTest {
    @Test
    public void apiStatusCode() {
        get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    public void postUserTest() {
        String body = "{\"name\": \"morpheus\", \"job\": \"leader\"" +
                " } ";
        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    public void deleteTest() {
        delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    void loginSuccesfull() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";
        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
    @Test
    @Feature("Проблема с проверкой JSON")
    public void singleUser() {
        get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(2));
    }

}


