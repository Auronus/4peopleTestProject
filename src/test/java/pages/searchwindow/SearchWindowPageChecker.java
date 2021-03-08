package pages.searchwindow;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import helpers.DateHelper;
import helpers.api.ApiMethods;
import helpers.api.models.ValueItem;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import pages.BasePageChecker;

import java.util.List;

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
    public final void checkSearchResults(String searchString) {
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> liveSearchResults = apiMethods.getLiveSearchResults(searchString).getValue();
        List<ValueItem> notLiveSearchResults = apiMethods.getNotLiveSearchResults(searchString).getValue();

        checkElementText(
                "Заголовок EVENTS FOUND",
                page.getSearchModalHeader(),
                String.format(EVENTS_FOUND_HEADER_TEXT, liveSearchResults.size() + notLiveSearchResults.size())
        );

        checkElementExist("Результат в списке", page.getItemCell());
        List<SelenideElement> items = $$(page.getItemCell());
        checkListSize("Элементы в списке", page.getItemCell(), liveSearchResults.size() + notLiveSearchResults.size());

        int countForCheck = Math.min(items.size(), 10);
        for (int i = 0; i < countForCheck; i++) {
            if (i < liveSearchResults.size()) {
                checkItemCell(i, liveSearchResults.get(i), items.get(i), true);
            } else {
                checkItemCell(i, notLiveSearchResults.get(i - liveSearchResults.size()), items.get(i), false);
            }
        }
    }

    @Step(value = "Проверка результатов поиска (Включены оба чекбокса)")
    public final void checkSearchResultsLive(String searchString) {
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> liveSearchResults = apiMethods.getLiveSearchResults(searchString).getValue();

        checkElementText(
                "Заголовок EVENTS FOUND",
                page.getSearchModalHeader(),
                String.format(EVENTS_FOUND_HEADER_TEXT, liveSearchResults.size())
        );

        checkElementExist("Результат в списке", page.getItemCell());
        List<SelenideElement> items = $$(page.getItemCell());

        int countForCheck = Math.min(items.size(), 10);
        for (int i = 0; i < countForCheck; i++) {
            checkItemCell(i, liveSearchResults.get(i), items.get(i), true);
        }
    }

    @Step(value = "Проверка результатов поиска (Включены оба чекбокса)")
    public final void checkSearchResultsSports(String searchString) {
        ApiMethods apiMethods = new ApiMethods();
        List<ValueItem> notLiveSearchResults = apiMethods.getNotLiveSearchResults(searchString).getValue();

        checkElementText(
                "Заголовок EVENTS FOUND",
                page.getSearchModalHeader(),
                String.format(EVENTS_FOUND_HEADER_TEXT, notLiveSearchResults.size())
        );

        checkElementExist("Результат в списке", page.getItemCell());
        List<SelenideElement> items = $$(page.getItemCell());
        checkListSize("Элементы в списке", page.getItemCell(), notLiveSearchResults.size());

        int countForCheck = Math.min(items.size(), 10);
        for (int i = 0; i < countForCheck; i++) {
            checkItemCell(i, notLiveSearchResults.get(i), items.get(i), false);
        }
    }

    @Step(value = "Проверка [{i}]-ого элемента в списке ")
    private void checkItemCell(int i, ValueItem item, SelenideElement cell, boolean live) {
        //Хедер N + . + S (timestamp)
        //Тело SN + . + L или LE
        //Команды O1 + - + O2

        //Первая ставка E(0).C
        //Вторая ставка E(2).C
        //Третья ставка E(1).C
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

        //Если количество коэффициентов четное, то их 2
        if (item.getE().size() >= 2) {
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
        }
    }
}
