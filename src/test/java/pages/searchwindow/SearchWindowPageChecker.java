package pages.searchwindow;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import helpers.DateHelper;
import helpers.api.ApiMethods;
import helpers.api.models.ValueItem;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import pages.BasePageChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Класс проверок модального окна поиска
 */
public class SearchWindowPageChecker extends BasePageChecker<SearchModalWindow> implements SearchData {

    /**
     * Конструктор.
     *
     * @param page объект класса SearchWindowPageChecker
     */
    public SearchWindowPageChecker(SearchModalWindow page) {
        super(page);
    }

    public final void checkSearchFieldText(String expectedText) {
        checkElementValue("Поле поиска", page.getSearchField(), expectedText);
    }

    @Step("Проверка элементов модального окна поиска")
    public final void checkEmptySearchWindowElements() {
        checkElementExist("Модальное окно поиска", page.getSearchModalWindow());

        checkElementText(
                "Заголовок EVENTS FOUND",
                page.getSearchModalHeader(),
                String.format(EVENTS_FOUND_HEADER_TEXT, 0)
        );

        checkElementExist("Чекбокс Live", page.getLiveCheckbox());
        checkCheckboxChecked("Чекбокс Live", page.getLiveCheckbox());
        checkElementText("Текст чекбоса Live", page.getLiveLabel(), LIVE_CHECKBOX_LABEL);

        checkElementExist("Чекбокс Sports", page.getSportsCheckbox());
        checkCheckboxChecked("Чекбокс Sports", page.getSportsCheckbox());
        checkElementText("Текст чекбоса Sports", page.getSportsLabel(), SPORTS_CHECKBOX_LABEL);

        checkElementExist("Поле поиска", page.getSearchField());
        checkElementValue("Поле поиска", page.getSearchField(), "");
        checkElementExist("Иконка микрофона", page.getSearchFieldMicButton());
        checkElementExist("Кнопка очистки поля поиска", page.getSearchFieldCleanButton());
        checkElementExist("Кнопка поиска", page.getSearchButton());
        checkElementExist("Кнопка лупы в кнопке поиска", page.getSearchButtonIcon());
        checkElementText("Кнопка поиска", page.getSearchButton(), SEARCH_BUTTON_TEXT);

        checkElementText("Заголовок MATCHES", page.getMatchesButton(), MATCHES_HEADER);
        checkElementText("Заголовок LEAGUES", page.getLeaguesButton(), LEAGUES_HEADER);
        checkElementText("Текст об отсутствии результатов", page.getNoResultsLabel(), NO_RESULTS_LABEL);
    }


    @Step(value = "Проверка результатов поиска (Включены оба чекбокса)")
    public final void checkSearchResults(List<ValueItem> liveSearchResults, List<ValueItem> sportsSearchResults) {
        List<ValueItem> newLiveSearchResults = (liveSearchResults != null ? liveSearchResults : new ArrayList<>());
        List<ValueItem> newSportsSearchResults = (sportsSearchResults != null ? sportsSearchResults : new ArrayList<>());

        checkElementText(
                "Заголовок EVENTS FOUND",
                page.getSearchModalHeader(),
                String.format(EVENTS_FOUND_HEADER_TEXT, newLiveSearchResults.size() + newSportsSearchResults.size())
        );

        checkElementExist("Результат в списке", page.getItemCell());
        List<SelenideElement> items = $$(page.getItemCell());
        checkListSize(
                "Элементы в списке",
                page.getItemCell(),
                newLiveSearchResults.size() + newSportsSearchResults.size()
        );

        int countForCheck = Math.min(items.size(), 10);
        for (int i = 0; i < countForCheck; i++) {
            if (i < newLiveSearchResults.size()) {
                checkItemCell(i, newLiveSearchResults.get(i), items.get(i), true);
            } else {
                checkItemCell(i, newSportsSearchResults.get(i - newLiveSearchResults.size()), items.get(i), false);
            }
        }
    }

    @Step(value = "Проверка результатов поиска (League)")
    public final void checkSearchLeagueResults(List<ValueItem> liveSearchResults, List<ValueItem> notLiveSearchResults) {
        List<ValueItem> liveLeagueItems = (liveSearchResults != null)
                ? liveSearchResults
                .stream()
                .filter(event -> event.getLI() != 0)
                .distinct()
                .collect(Collectors.toList())
                : new ArrayList<>();

        List<ValueItem> sportsLeagueItems = (notLiveSearchResults != null)
                ? notLiveSearchResults
                .stream()
                .filter(event -> event.getLI() != 0)
                .distinct()
                .collect(Collectors.toList())
                : new ArrayList<>();

        checkElementText(
                "Заголовок EVENTS FOUND",
                page.getSearchModalHeader(),
                String.format(EVENTS_FOUND_HEADER_TEXT, liveLeagueItems.size() + sportsLeagueItems.size())
        );
        if (liveLeagueItems.size() == 0 && sportsLeagueItems.size() == 0) {
            checkElementText("Текст об отсутствии результатов", page.getNoResultsLabel(), NO_RESULTS_LABEL);
            return;
        }

        checkElementExist("Результат в списке", page.getItemCell());
        List<SelenideElement> items = $$(page.getItemCell());
        checkListSize(
                "Элементы в списке",
                page.getItemCell(),
                liveLeagueItems.size() + sportsLeagueItems.size()
        );

        int countForCheck = Math.min(items.size(), 10);
        for (int i = 0; i < countForCheck; i++) {
            Selenide.sleep(200);
            if (i < liveLeagueItems.size()) {
                checkLeagueItemCell(i, liveLeagueItems.get(i), items.get(i), true);
            } else {
                checkLeagueItemCell(i, sportsLeagueItems.get(i - liveLeagueItems.size()), items.get(i), false);
            }
        }
    }

    @Step(value = "Проверка [{i}]-ого элемента в списке ")
    private void checkItemCell(int i, ValueItem item, SelenideElement cell, boolean live) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", cell);
        Selenide.sleep(100);

        checkElementText(
                "Заголовок %s-ого элемента в списке",
                cell.$(page.getEventHeader()),
                String.format("%s. %s%s",
                        item.getN(),
                        DateHelper.transformDateToSearchResultMask(item.getS()),
                        (live ? " LIVE" : "")
                )
        );

        checkElementText(
                "Название дисциплины %s-ого элемента в списке",
                cell.$(page.getEventLeague()),
                String.format("%s. %s.", item.getSN(), item.getL())
        );

        if (item.getO1() != null && item.getO2() != null) {
            checkElementText(
                    "Команды %s-ого элемента в списке",
                    cell.$(page.getEventTeams()),
                    String.format("%s - %s%s",
                            item.getO1(),
                            item.getO2(),
                            ((item.getDI() != null && !item.getDI().equals("")) ? (". " + item.getDI()) : "")
                    )
            );
        } else {
            checkElementText(
                    "Команды %s-ого элемента в списке",
                    cell.$(page.getEventTeams()),
                    item.getO1() +
                            (item.getDI() != null ? ". " + item.getDI() : "")
            );
        }

        List<SelenideElement> coefNames = cell.$$(page.getCoefName());
        List<SelenideElement> coefValues = cell.$$(page.getCoefValue());

        //Неясна логика отображения коэффициентов, нужна документация
        //Первая ставка E(0).C
        //Вторая ставка E(2).C
        //Третья ставка E(1).C
       /* if (item.getE().size() >= 2) {
            checkElementText(
                    "Имя первого коэффициента",
                    coefNames.get(0),
                    FIRST_COEF_NAME
            );
            checkElementText(
                    "Коэффициент первой команды",
                    coefValues.get(0),
                    String.valueOf(item.getE().get(0).getC())
            );

            if (item.getE().size() % 2 == 0) {
                checkElementText(
                        "Имя второго коэффициента",
                        coefNames.get(coefNames.size() - 1),
                        SECOND_COEF_NAME
                );
                checkElementText(
                        "Коэффициент второй команды",
                        coefValues.get(coefValues.size() - 1),
                        String.valueOf(item.getE().get(1).getC())
                );
            }
            if (item.getE().size() % 3 == 0) {
                checkElementText(
                        "Коэффициент второй команды",
                        coefValues.get(coefValues.size() - 1),
                        String.valueOf(item.getE().get(2).getC())
                );

                checkElementText(
                        "Название коэффициента при ничье(?)",
                        coefNames.get(coefNames.size() - 1),
                        DRAW_COEF_NAME
                );
                checkElementText(
                        "Коэффициент ничьи",
                        coefValues.get(1),
                        String.valueOf(item.getE().get(1))
                );
            }
        }*/
    }

    @Step(value = "Проверка [{i}]-ого элемента в списке ")
    private void checkLeagueItemCell(int i, ValueItem item, SelenideElement cell, boolean live) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", cell);
        Selenide.sleep(100);

        checkElementText(
                "Заголовок %s-ого элемента в списке",
                cell.$(page.getEventHeader()),
                String.format("%s. %s%s",
                        item.getN(),
                        DateHelper.transformDateToSearchResultMask(item.getS()),
                        (live ? " LIVE" : "")
                )
        );
        checkElementText(
                "Название дисциплины %s-ого элемента в списке",
                cell.$(page.getEventLeague()),
                String.format("%s. %s.", item.getSN(), item.getL())
        );
    }

}
