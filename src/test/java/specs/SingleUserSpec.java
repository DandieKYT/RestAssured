package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;


public class SingleUserSpec {
    public static RequestSpecification singleRequestSpec = with()
            .baseUri("https://reqres.in/api/users/2")
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .contentType(JSON);


    public static ResponseSpecification singleResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .build();
}
