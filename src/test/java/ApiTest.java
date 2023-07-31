
import io.qameta.allure.Feature;
import models.LoginModel;
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
import static specs.SecondLoginSpec.secondLoginRequestSpec;
import static specs.SecondLoginSpec.secondLoginResponseSpec;

public class ApiTest {
    @Test
    public void apiStatusCode() {
        get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    @Feature("Проверка POST")
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
        LoginModel loginModelSecond = new LoginModel();
        loginModelSecond.setEmail("eve.holt@reqres.in");
        loginModelSecond.setPassword("cityslicka");

        LoginModel response = step("Make request", () ->
        given(secondLoginRequestSpec)
                .body(loginModelSecond)
                .when()
                .post()
                .then()
                .spec(secondLoginResponseSpec)
                .extract().as(LoginModel.class));
        step("Verify response token", () ->
                assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));
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


