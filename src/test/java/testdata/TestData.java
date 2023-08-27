package testdata;

import com.github.javafaker.Faker;
import testcase.CreateAndDeleteTestCase;
import tests.NewAllureTest;

public class TestData {
    static Faker faker = new Faker();
    public final static String
            testCaseName = faker.rockBand().name(),
            nameStepTestCaseOne = faker.funnyName().name(),
            nameStepTestCaseTwo = faker.artist().name(),
            nameStepTestCaseThree = faker.artist().name(),
            preconditionName = faker.funnyName().name(),
            getTestCaseId = CreateAndDeleteTestCase.getTestCaseID(),
            commentProject = "Все супер";
    public final static String
            jsonStringCreateTestCaseRequest = String.format("{\"steps\":[{\"name\":\"%s\",\"spacing\":\"\"}, " +
            "{\"name\":\"%s\",\"spacing\":\"\"}, {\"name\":\"%s\",\"spacing\":\"\"} ] }", nameStepTestCaseOne, nameStepTestCaseTwo,nameStepTestCaseThree );
    public final static String
            jsonStringEditRequest = String.format("{\"steps\":[{\"name\":\"%s\",\"attachments\":[],\"steps\":[]," +
            "\"leaf\":true,\"stepsCount\":0,\"hasContent\":false,\"spacing\":\"\"}," + "{\"name\":\"%s\",\"attachments\":[]," +
            "\"steps\":[],\"leaf\":true,\"stepsCount\":0," +
            "\"hasContent\":false,\"spacing\":\"\"}],\"workPath\":[1]}", nameStepTestCaseTwo, nameStepTestCaseOne);
    public final static String
            jsonStringCreatePrecondition = String.format("{\"id\":%s,\"precondition\":\"%s\",\"preconditionHtml\": null}", getTestCaseId, preconditionName);
    public final static String
            jsonStringCreateCommentProject = String.format("{\"testCaseId\":%s,\"body\":\"%s\"}", getTestCaseId, commentProject);

}

