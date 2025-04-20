import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class GithubSearchTests {
    @BeforeAll
    static void setUp(){
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://github.com";
    }
    @Test
    void searchSelenideSoftAssertTest(){
//        Откройте страницу Selenide в Github
        open("/");
        $("button[placeholder = 'Search or jump to...']").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        $("[data-testid='results-list']").$$("div").first().$("a")
                .shouldHave(attributeMatching("href","^.*\\/selenide\\/selenide$")).click();
//        Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();
//        Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-body").$("li a[href*='Soft']")
                .shouldHave(href("https://github.com/selenide/selenide/wiki/SoftAssertions")).click();
//        Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $("#wiki-body").$("[id*='using-junit5']").parent().sibling(0)
                .shouldHave(attributeMatching("class","^.*source-java.*$"));

        $("#wiki-body").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }"""));
    }

    //5 урок. Selenide #2
    // На главной странице GitHub выберите: Меню -> Solutions -> Enterprize (с помощью команды hover для Solutions).
    // Убедитесь, что загрузилась нуная страница (например, что заголовок: "The AI-powered developer platform.").

    @Test
    void searchSolutionsEnterpriseTest(){
        open("/");
        $(byClassName("HeaderMenu-nav")).$(byText("Solutions")).hover().sibling(0)
                .$(byTagAndText("a","Enterprises")).click();
        $("#hero-section-brand-heading").should(appear).shouldHave(text("The AI-powered developer platform"));
    }
}
