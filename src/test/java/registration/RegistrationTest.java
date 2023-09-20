package registration;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class RegistrationTest extends TestBase {

    @Test
    @Tag("smoke")
    void successfulRegistrationTest() {
        String firstName = "Sergey",
                lastName = "Konoplev",
                userEmail = "sergeyKonoplev@gmail.com",
                gender = "Male",
                userNumber = "9875036934",
                fullDateOfBirth = "08 May,1996",
                dayOfBirth = "08",
                monthOfBirth = "May",
                yearOfBirth = "1996",
                subjects = "Maths",
                hobbies = "Sports",
                picture = "img/1.png",
                verifyPicture = "1.png",
                currentAddress = "Russia",
                state = "NCR",
                city = "Delhi",
                stateAndCity = state + ' ' + city;

        step("Открыть форму", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            Selenide.executeJavaScript("$('#fixedban').remove()");
            Selenide.executeJavaScript("$('footer').remove()");
        });

        step("Заполнить форму", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(userEmail);
            $("#genterWrapper").$(byText(gender)).click();
            $("#userNumber").setValue(userNumber);

            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(monthOfBirth);
            $(".react-datepicker__year-select").selectOption(yearOfBirth);
            $(".react-datepicker__day--0" + dayOfBirth + ":not(.react-datepicker__day--outside-month)").click();

            $("#subjectsInput").setValue(subjects).pressEnter();
            $("#hobbiesWrapper").$(byText(hobbies)).click();
            $("#uploadPicture").uploadFromClasspath(picture);
            $("#currentAddress").setValue(currentAddress);
            $("#state").click();
            $("#stateCity-wrapper").$(byText("NCR")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Delhi")).click();
            $("#submit").click();
        });

        step("Проверить результат", () -> {
            $(".modal-content").should(appear);
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(
                    text(firstName), text(lastName), text(userEmail), text(gender),
                    text(userNumber), text(fullDateOfBirth), text(subjects), text(hobbies),
                    text(verifyPicture), text(currentAddress), text(stateAndCity));
        });
    }
}
