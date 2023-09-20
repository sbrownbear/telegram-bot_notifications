package registration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "100.0";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub"; // Запус в Selenoid, wd - WebDriver. Использует браузер установленный в другом месте

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true, // Окно в окне
                "enableVideo", true // Запись видео
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
