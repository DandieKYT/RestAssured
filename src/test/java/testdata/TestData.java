package testdata;

import com.github.javafaker.Faker;

public class TestData {
    static Faker faker = new Faker();
    public final static String
            testCaseName = faker.rockBand().name(),
            nameStepTestCaseOne = faker.funnyName().name(),
            nameStepTestCaseTwo = faker.artist().name(),
            commentProject = "Все супер";
    public final static String
    jsonStringCreateTestCaseRequest = String.format("{\"steps\":[{\"name\":\"%s\",\"spacing\":\"\"}, " +
                 "{\"name\":\"%s\",\"spacing\":\"\"}] }",nameStepTestCaseOne,nameStepTestCaseTwo);
    public final static String
    jsonStringEditRequest = String.format("{\"steps\":[{\"name\":\"%s\",\"attachments\":[],\"steps\":[]," +
            "\"leaf\":true,\"stepsCount\":0,\"hasContent\":false,\"spacing\":\"\"}," + "{\"name\":\"%s\",\"attachments\":[]," +
            "\"steps\":[],\"leaf\":true,\"stepsCount\":0," +
            "\"hasContent\":false,\"spacing\":\"\"}],\"workPath\":[1]}", nameStepTestCaseTwo, nameStepTestCaseOne);

}

