package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/test.properties"
})
public interface AuthConfig extends Config {

    @Key("token")
    @DefaultValue("5546280c-be21-48b5-9d9d-7722eaed55a1")
    String token();

    @Key("username")
    @DefaultValue("allure8")
    String username();

    @Key("password")
    @DefaultValue("allure8")
    String password();

    @Key("xsrfToken")
    @DefaultValue("12345")
    String xsrfToken();

    @Key("projectId")
    @DefaultValue("3488")
    String projectId();




}
