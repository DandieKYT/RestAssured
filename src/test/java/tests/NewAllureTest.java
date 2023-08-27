package tests;

import authentication.Authentication;
import static java.lang.String.format;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testcase.CreateAndDeleteTestCase;
import static config.OpenBrowserConfig.openBaseUrlBrowser;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.Specification.requestSpec;
import static specs.Specification.responseSpec;
import static testdata.TestData.*;

public class NewAllureTest extends TestBase {
    private final CreateAndDeleteTestCase createTestCaseTest = new CreateAndDeleteTestCase();
    private String testCaseId;
    @BeforeEach
    void createTestCase(){
        Authentication.getInstance().authenticate();
        createTestCaseTest.createNewTestCases();
        this.testCaseId = CreateAndDeleteTestCase.getTestCaseID();
    }

    @AfterEach
    void tearDown(){
        createTestCaseTest.deleteTestCase();
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Добавление шагов в тест кейс и редактирование")
    void testAddEditSteps(){
        step("Добавление шагов в тест кейс", () ->
                given(requestSpec)
                        .body(jsonStringCreateTestCaseRequest)
                        .when()
                        .post(format("/testcase/%s/scenario", testCaseId))
                        .then()
                        .spec(responseSpec)
                        .statusCode(200));

        step("Открытие", () ->
                openBaseUrlBrowser());

        step("Измение порядка шагов в тест кейсе", () ->
                given(requestSpec)
                        .body(jsonStringEditRequest)
                        .when()
                        .post(format("/testcase/%s/scenario", testCaseId))
                        .then()
                        .spec(responseSpec)
                        .statusCode(200));

        step("Открытие", () ->
                openBaseUrlBrowser());
   }

   @Test
   @DisplayName("Добавление предулосвий в тест-кейс")
    void precondition(){
       step("Добавление предусловия в тест кейс", () ->
               given(requestSpec)
                       .body(jsonStringCreatePrecondition)
                       .when()
                       .patch(format("/testcase/%s", testCaseId))
                       .then()
                       .spec(responseSpec)
                       .statusCode(200));
   }
    @Test
    @DisplayName("Добавление коментария в тест-кейс")
    void comment(){
        step("Добавление предусловия в тест кейс", () ->
                given(requestSpec)
                        .body(jsonStringCreateCommentProject)
                        .when()
                        .post("/comment")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200));
    }

}
