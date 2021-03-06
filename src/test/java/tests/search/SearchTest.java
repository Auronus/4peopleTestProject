package tests.search;

import helpers.ParametersProvider;
import org.testng.annotations.Test;
import pages.main.MainPage;
import tests.BaseTest;

import static com.codeborne.selenide.Selenide.open;

/**
 * Класс для проверки работы поиска
 */
public class SearchTest extends BaseTest {

    @Test(description = "Проверка элементов поля поиска на главной")
    public final void checkSearchFieldElements() {
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .checkPage(checker -> checker.checkSearchField());
    }
}
