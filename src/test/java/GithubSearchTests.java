import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
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
        $("#wiki-body").$(byText("Chapters")).parent().sibling(0).$("li a[href*='Soft']")
                .shouldHave(href("https://github.com/selenide/selenide/wiki/SoftAssertions")).click();
//        Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $("#wiki-body").$("[id*='using-junit5']").parent().sibling(0)
                .shouldHave(attributeMatching("class","^.*source-java.*$"));
    }
}
