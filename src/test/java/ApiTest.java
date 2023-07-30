
import models.LombokModel;

import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import static specs.LoginSpec.loginRequestSpec;
import static specs.LoginSpec.loginResponseSpec;

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

        LombokModel response = step("Make request", () ->
         given(loginRequestSpec)
                .body(loginModel)
                .when()
                .post()
                .then()
                .spec(loginResponseSpec)
                .extract().as(LombokModel.class));

        step("Verify response name", () ->
                assertThat(response.getName()).isEqualTo("morpheus"));
        step("Verify response job", () ->
                assertThat(response.getJob()).isEqualTo("leader"));
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


