import io.qameta.allure.restassured.AllureRestAssured;
import models.LombokModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class ApiTest {
    @Test
    public void apiStatusCode() {
        get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    public void postUserTest() {

        LombokModel loginModel = new LombokModel();
        loginModel.setName("morpheus");
        loginModel.setJob("leader");
        given()
                .filter(new AllureRestAssured())
                .log().uri()
                .contentType(JSON)
                .body(loginModel)
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
    public void loginSuccessfull() {
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
    public void singleUser() {
        String email = "janet.weaver@reqres.in";
        get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body(containsString(email))
                .body("data.id", is(2));
    }

}


