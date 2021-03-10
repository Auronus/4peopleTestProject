package tests.search;

import helpers.ParametersProvider;
import helpers.api.ApiMethods;
import helpers.api.models.ValueItem;
import io.qameta.allure.Epic;
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
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> liveSearchResults = apiMethods.getLiveSearchResults(searchString).getValue();
        List<ValueItem> sportsSearchResults = apiMethods.getNotLiveSearchResults(searchString).getValue();
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .checkPage(checker -> checker.checkSearchResults(liveSearchResults, sportsSearchResults));
    }

    @Test(description = "Проверка работы поиска через главную (Вкладка Matches) - Чекбокс Live")
    public final void checkSearchMatchesLives() {
        String searchString = "Rocket League";
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> liveSearchResults = apiMethods.getLiveSearchResults(searchString).getValue();
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .clickSportsCheckbox()
                .clickSearchButton()
                .checkPage(checker -> checker.checkSearchResults(liveSearchResults, null));
    }

    @Test(description = "Проверка работы поиска через главную (Вкладка Matches) - Чекбокс Sports")
    public final void checkSearchMatchesSports() {
        String searchString = "Rocket League";
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> sportsSearchResults = apiMethods.getNotLiveSearchResults(searchString).getValue();
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .clickLiveCheckbox()
                .clickSearchButton()
                .checkPage(checker -> checker.checkSearchResults(null, sportsSearchResults));
    }

    @Test(description = "Проверка работы поиска через модальное окно (Вкладка Matches)")
    public final void checkSearchMatchesViaModal() {
        String searchString = "Rocket League";
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> liveSearchResults = apiMethods.getLiveSearchResults(searchString).getValue();
        List<ValueItem> sportsSearchResults = apiMethods.getNotLiveSearchResults(searchString).getValue();
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .clickSearchButton()
                .search(searchString)
                .checkPage(checker -> checker.checkSearchResults(liveSearchResults, sportsSearchResults));
    }

    @Test(description = "Проверка работы поиска через модальное окно (Вкладка Matches) - Чекбокс Live")
    public final void checkSearchMatchesLivesViaModal() {
        String searchString = "Rocket League";
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> liveSearchResults = apiMethods.getLiveSearchResults(searchString).getValue();
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .clickSearchButton()
                .search(searchString)
                .clickSportsCheckbox()
                .clickSearchButton()
                .checkPage(checker -> checker.checkSearchResults(liveSearchResults, null));
    }

    @Test(description = "Проверка работы поиска через модальное окно (Вкладка Matches) - Чекбокс Sports")
    public final void checkSearchMatchesSportsViaModal() {
        String searchString = "Rocket League";
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> sportsSearchResults = apiMethods.getNotLiveSearchResults(searchString).getValue();
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .clickSearchButton()
                .search(searchString)
                .clickLiveCheckbox()
                .clickSearchButton()
                .checkPage(checker -> checker.checkSearchResults(null, sportsSearchResults));
    }

    @Test(description = "Проверка работы поиска через главную (Вкладка Leagues)")
    public final void checkSearchLeagues() {
        String searchString = "Rocket League";
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> liveSearchResults = apiMethods.getLiveSearchResults(searchString).getValue();
        List<ValueItem> notLiveSearchResults = apiMethods.getNotLiveSearchResults(searchString).getValue();
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .clickLeaguesTab()
                .checkPage(checker -> checker.checkSearchLeagueResults(liveSearchResults, notLiveSearchResults));
    }

    @Test(description = "Проверка работы поиска через главную (Вкладка Leagues) - Чекбокс Live")
    public final void checkSearchLeaguesLives() {
        String searchString = "Rocket League";
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> liveSearchResults = apiMethods.getLiveSearchResults(searchString).getValue();
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .clickLeaguesTab()
                .clickSportsCheckbox()
                .clickSearchButton()
                .checkPage(checker -> checker.checkSearchLeagueResults(liveSearchResults, null));
    }

    @Test(description = "Проверка работы поиска через главную (Вкладка Leagues) - Чекбокс Sports")
    public final void checkSearchLeaguesSports() {
        String searchString = "Rocket League";
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> notLiveSearchResults = apiMethods.getNotLiveSearchResults(searchString).getValue();
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .search(searchString)
                .clickLeaguesTab()
                .clickLiveCheckbox()
                .clickSearchButton()
                .checkPage(checker -> checker.checkSearchLeagueResults(null, notLiveSearchResults));
    }

    @Test(description = "Проверка работы поиска через модальное окно (Вкладка Leagues)")
    public final void checkSearchLeaguesViaModal() {
        String searchString = "Rocket League";
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> liveSearchResults = apiMethods.getLiveSearchResults(searchString).getValue();
        List<ValueItem> notLiveSearchResults = apiMethods.getNotLiveSearchResults(searchString).getValue();
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .clickSearchButton()
                .clickLeaguesTab()
                .search(searchString)
                .checkPage(checker -> checker.checkSearchLeagueResults(liveSearchResults, notLiveSearchResults));
    }

    @Test(description = "Проверка работы поиска через модальное окно (Вкладка Leagues) - Чекбокс Live")
    public final void checkSearchLeaguesLivesViaModal() {
        String searchString = "Rocket League";
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> liveSearchResults = apiMethods.getLiveSearchResults(searchString).getValue();
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .clickSearchButton()
                .clickLeaguesTab()
                .search(searchString)
                .clickSportsCheckbox()
                .checkPage(checker -> checker.checkSearchLeagueResults(liveSearchResults, null));
    }

    @Test(description = "Проверка работы поиска через модальное окно (Вкладка Leagues) - Чекбокс Sports")
    public final void checkSearchLeaguesSportsViaModal() {
        String searchString = "Rocket League";
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> notLiveSearchResults = apiMethods.getNotLiveSearchResults(searchString).getValue();
        open(ParametersProvider.getPropertyByName("url"));
        new MainPage()
                .scrollToSearch()
                .clickSearchButton()
                .clickLeaguesTab()
                .search(searchString)
                .clickLiveCheckbox()
                .checkPage(checker -> checker.checkSearchLeagueResults(null, notLiveSearchResults));
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
