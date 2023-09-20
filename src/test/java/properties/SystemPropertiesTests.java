package properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

// Временное хранилище ключей и значений в Java через SystemProperties
public class SystemPropertiesTests {

    @Test
    void simplePropertyTest() {
        // По умочанию стоит firefox, но выдаст opera
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName);
    }

    @Test
    @Tag("first_property")
    void simpleProperty2Test() {
        String browserName = System.getProperty("browser","firefox");
        System.out.println(browserName);
        // Команда в терминале gradle clean first_property_test выдаст firefox
        // Команда в терминале2 gradle clean first_property_test -Dbrowser=safari выдаст safari
    }
}
