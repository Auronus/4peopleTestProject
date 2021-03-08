package tests.search;

import helpers.ParametersProvider;
import helpers.api.ApiMethods;
import helpers.api.models.SearchResultModel;
import helpers.api.models.ValueItem;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.main.MainPage;
import tests.BaseTest;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;

/**
 * Класс для проверки работы поиска
 */
@Epic("Поиск")
public class SearchTest extends BaseTest {

    @Test(description = "Проверка элементов поля поиска на главной")
    public final void checkSearchFieldElements() {
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .checkPage(checker -> checker.checkSearchField());
    }

    @Test(description = "Проверка элементов модального окна поиска на главной (Пустой поиск)")
    public final void checkEmptySearchModalElements() {
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .clickSearchButton()
                .checkPage(checker -> checker.checkEmptySearchWindowElements());
    }

    @Test(description = "Проверка закрытия модального окна")
    public final void checkCloseSearchWindow() {
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .clickSearchButton()
                .clickCloseButton()
                .checkPage(checker -> checker.checkSearchWindowNotPresented());
    }

    @Test(description = "Проверка работы поиска через главную (Вкладка Matches)")
    public final void checkSearchMatches() {
        String searchString = "Rocket League";
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .checkPage(checker -> checker.checkSearchResults(searchString)
                );
    }

    @Test(description = "Проверка работы поиска через главную (Вкладка Matches) - Чекбокс Live")
    public final void checkSearchMatchesLives() {
        String searchString = "Rocket League";

        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .clickSportsCheckbox()
                .clickSearchButton()
                .checkPage(checker -> checker.checkSearchResultsLive(searchString)
                );
    }

    @Test(description = "Проверка работы поиска через главную (Вкладка Matches) - Чекбокс Sports")
    public final void checkSearchMatchesSports() {
        String searchString = "Rocket League";

        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .clickLiveCheckbox()
                .clickSearchButton()
                .checkPage(checker -> checker.checkSearchResultsSports(searchString)
                );
    }

    @Test(description = "Проверка работы поиска через главную (Вкладка Leagues)")
    public final void checkSearchLeagues() {
        String searchString = "Rocket League";
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .clickLeaguesTab()
                .checkPage(checker -> checker.checkSearchResults(searchString)
                );
    }

    @Test(description = "Проверка работы поиска через главную (Вкладка Leagues) - Чекбокс Live")
    public final void checkSearchLeaguesLives() {
        String searchString = "Rocket League";

        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .clickLeaguesTab()
                .clickSportsCheckbox()
                .clickSearchButton()
                .checkPage(checker -> checker.checkSearchResultsLive(searchString)
                );
    }

    @Test(description = "Проверка работы поиска через главную (Вкладка Leagues) - Чекбокс Sports")
    public final void checkSearchLeaguesSports() {
        String searchString = "Rocket League";

        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .clickLeaguesTab()
                .clickLiveCheckbox()
                .clickSearchButton()
                .checkPage(checker -> checker.checkSearchResultsSports(searchString)
                );
    }

    @Test(description = "Проверка очищения поля поиска")
    public final void checkClearSearchField() {
        String searchString = "Rocket League";

        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .checkPage(checker -> checker.checkSearchFieldText(searchString))
                .clickClearButton()
                .checkPage(checker -> checker.checkSearchFieldText(""));
    }

    @Test(description = "Проверка перехода с экрана поиска по элементу из списка")
    public final void checkOpenEventLink() {
        String searchString = "Rocket League";
        ValueItem event;
        boolean live;
        List<ValueItem> liveSearchResults = new ApiMethods().getLiveSearchResults(searchString).getValue();
        List<ValueItem> notLiveSearchResults = new ApiMethods().getNotLiveSearchResults(searchString).getValue();
        if (liveSearchResults.size() > 0) {
            event = liveSearchResults.get(0);
            live = true;
        } else if (notLiveSearchResults.size() > 0) {
            event = notLiveSearchResults.get(0);
            live = false;
        } else {
            throw new AssertionError(String.format("По поискому запросу %s не найдено событий", searchString));
        }
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .openEvent(event)
                .checkPage(checker -> checker.checkUrl(event, live));
    }
}
