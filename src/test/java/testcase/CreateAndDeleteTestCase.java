package testcase;

import config.AuthConfig;
import config.OpenBrowserConfig;
import models.CreateTestCaseBody;
import models.CreateTestCaseResponse;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import testdata.TestData;


import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Specification.requestSpec;
import static specs.Specification.responseSpec;

public class CreateAndDeleteTestCase {
    private static String testCaseID;
    private final AuthConfig authConfig = ConfigFactory.create(AuthConfig.class);
    private final CreateTestCaseBody testCaseBody = new CreateTestCaseBody();

    @DisplayName("Создание нового тест кейса")
    public void createNewTestCases() {
        testCaseBody.setName(TestData.testCaseName);
        CreateTestCaseResponse testCaseResponse = step("Отправляем запрос на создание тест кейса", () -> given(requestSpec)
                .body(testCaseBody)
                .queryParam("projectId", authConfig.projectId())
                .when()
                .post("/testcasetree/leaf")
                .then()
                .spec(responseSpec)
                .statusCode(200).extract().as(CreateTestCaseResponse.class));
        step("Проверяем имя тест кейса, что оно соответсвует заданному", () ->
                assertThat(testCaseResponse.getName()).isEqualTo(TestData.testCaseName));
        step("Проверяем созданный тест не авторизован", () ->
                assertThat(testCaseResponse.getAutomated()).isEqualTo("false"));
        step("Проверяем статус код тест кейса", () ->
                assertThat(testCaseResponse.getStatusName()).isEqualTo("Draft"));

        testCaseID = testCaseResponse.getId();
    }

    public static String getTestCaseID() {
        return testCaseID;
    }

    @DisplayName("Удаление созданного тест кейса")
    public void deleteTestCase() {
        String jsonStringDeleteTestCaseRequest = String.format("{\"selection\":{\"inverted\":false,\"groupsInclude\":[]," +
                "    \"groupsExclude\":[],\"leafsInclude\":[%s],\"leafsExclude\":[],\"kind\":\"TreeSelectionDto\"," +
                "\"projectId\":%s,\"path\":[]}}", getTestCaseID(), authConfig.projectId());
        step("Удаляем тест кейс", () ->
                given(requestSpec)
                        .body(jsonStringDeleteTestCaseRequest)
                        .when()
                        .post("/testcase/bulk/remove")
                        .then()
                        .spec(responseSpec)
                        .statusCode(204));
        step("Проверка отсутствия тест кейса", OpenBrowserConfig::openBaseUrlBrowser);
        String messageDelete = $x("//div[text()='Test case was deleted']").innerText();
        step("Проверка сообщения что тест кейс удален", () ->
                assertThat(messageDelete).isEqualTo("Test case was deleted")
        );
    }
}
