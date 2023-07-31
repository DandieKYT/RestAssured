package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import helpers.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.util.Map;

public class TestBase {

Attachment attachment = new Attachment();

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserVersion = "100.0";
        Configuration.remote = "https://user1:1234@" + System.getProperty("selenoid_url", "selenoid.autotests.cloud/wd/hub");
        Configuration.baseUrl="https://reqres.in/";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of("enableVNC", true, "enableVideo", true

        ));

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void attachments() {
        attachment.browserLogs();
        attachment.attachScreenshot();
        attachment.pageSource();
        attachment.addVideo();
    }
}
