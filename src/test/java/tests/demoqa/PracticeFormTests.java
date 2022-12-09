package tests.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class PracticeFormTests extends TestBase {

    @BeforeAll

    static void setUp() {

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {

        step("Open registrations form", () -> {

            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");

        });
        step("Fill form", () -> {

            $("#firstName").setValue("Petr");
            $("#lastName").setValue("Kukushkin");
            $("#userEmail").setValue("kuku@gmail.com");
            $("#genterWrapper").$(byText("Male")).click();
            $("#userNumber").setValue("8999333224");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("July");
            $(".react-datepicker__year-select").selectOption("1992");
            $(".react-datepicker__day--031:not(.react-datepicker__day--outside-month)").click();
            $("#subjectsInput").setValue("Chemistry").pressEnter();
            $("#hobbiesWrapper").$(byText("Reading")).click();
            $("#uploadPicture").uploadFromClasspath("esponja.png");
            $("#currentAddress").setValue("Neva 40");
            $("#state").click();
            $("#stateCity-wrapper").$(byText("Haryana")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Karnal")).click();
            $("#submit").click();
        });
        step("Check form results", () -> {
            $(".table-responsive").shouldHave(text("Petr Kukushkin"));
            $(".table-responsive").shouldHave(text("kuku@gmail.com"));
            $(".table-responsive").shouldHave(text("Male"));
            $(".table-responsive").shouldHave(text("8999333224"));
            $(".table-responsive").shouldHave(text("31 July,1992"));
            $(".table-responsive").shouldHave(text("Chemistry"));
            $(".table-responsive").shouldHave(text("Reading"));
            $(".table-responsive").shouldHave(text("esponja.png"));
            $(".table-responsive").shouldHave(text("Neva 40"));
            $(".table-responsive").shouldHave(text("Haryana Karnal"));
        });
    }
}