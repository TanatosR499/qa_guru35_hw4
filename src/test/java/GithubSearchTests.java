import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GithubSearchTests {
    @BeforeAll
    static void setUp(){
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://github.com";
    }
    @Test
    void fillFormSuccessTest(){
//        Откройте страницу Selenide в Github
//        Перейдите в раздел Wiki проекта
//        Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
//        Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
    }
}
