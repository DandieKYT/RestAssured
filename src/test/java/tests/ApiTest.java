package tests;

import models.LoginModel;
import models.LombokModel;

import models.SingleUserModel;
import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import static specs.DeleteSpec.deleteRequestSpec;
import static specs.DeleteSpec.deleteResponseSpec;
import static specs.ErrorSpec.errorRequestSpec;
import static specs.ErrorSpec.errorResponseSpec;
import static specs.LoginSpec.loginRequestSpec;
import static specs.LoginSpec.loginResponseSpec;
import static specs.SecondLoginSpec.secondLoginRequestSpec;
import static specs.SecondLoginSpec.secondLoginResponseSpec;
import static specs.SingleUserSpec.singleRequestSpec;
import static specs.SingleUserSpec.singleResponseSpec;

public class ApiTest extends TestBase {
    @Test
    public void apiStatusCode() {

        LombokModel response = step("Make request and checkout status code", () ->
                given(errorRequestSpec)
                        .get()
                        .then()
                        .spec(errorResponseSpec)
                        .extract().as(LombokModel.class));
    }

    @Test
    public void postUserTest() {

        LombokModel loginModel = new LombokModel();
        loginModel.setName("morpheus");
        loginModel.setJob("leader");

        LombokModel response = step("Make request with name and job", () ->
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
          step("Make request delete and checkout status code", () ->{
                given(deleteRequestSpec)
                        .delete()
                        .then()
                        .spec(deleteResponseSpec);
    });
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
        SingleUserModel response = step("Make request", () ->
                given(singleRequestSpec)
                        .get()
                        .then()
                        .spec(singleResponseSpec)
                        .body("data.id", is(2))
                        .extract().as(SingleUserModel.class));
    }
}


