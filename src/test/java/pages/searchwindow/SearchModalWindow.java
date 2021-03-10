package pages.searchwindow;

import com.codeborne.selenide.Condition;
import helpers.api.models.ValueItem;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import pages.BasePage;
import pages.event.EventPage;
import pages.main.MainPage;

import static com.codeborne.selenide.Selenide.$;

/**
 * Модальное окно поиска
 */
@Getter
public class SearchModalWindow extends BasePage<SearchModalWindow, SearchWindowPageChecker> implements SearchData {

    //region Локаторы

    /**
     * Модальное окно
     */
    By searchModalWindow = By.cssSelector("div.search-popup.v-modal-search");

    /**
     * Заголовок модального окна
     */
    By searchModalHeader = By.className("search-popup__title");
    /**
     * Количество найденных элементов
     */
    By searchModalCount = By.className("search-popup__text search-popup__text--accent");

    /**
     * Кнопка закрытия модального окна
     */
    By closeModalWindowButton = By.className("search-popup__close");

    /**
     * Чекбокс Live
     */
    By liveCheckbox = By.xpath("//*[@class='search-popup__setting']//*[@id='checkbox_1']");
    /**
     * Лейбл Live
     */
    By liveLabel = By.xpath("(//*[@class='search-popup__setting']/label)[1]");

    /**
     * Чекбокс Sports
     */
    By sportsCheckbox = By.xpath("//*[@class='search-popup__setting']//*[@id='checkbox_2']");
    /**
     * Лейбл Sports
     */
    By sportsLabel = By.xpath("(//*[@class='search-popup__setting']/label)[2]");


    /**
     * Поле поиска
     */
    By searchField = By.id("search-in-popup");
    /**
     * Кнопка очистки поля поиска
     */
    By searchFieldCleanButton = By.className("search-popup__clear");
    /**
     * Кнопка микрофона в поле поиска
     */
    By searchFieldMicButton = By.cssSelector("div.voice-mic.search-popup__mic.fa.fa-microphone");
    /**
     * Кнопка поиска
     */
    By searchButton = By.className("search-popup__button");

    /**
     * Иконка лупы в поле поиска
     */
    By searchButtonIcon = By.cssSelector("i.fa.fa-search");


    /**
     * Заголовок Matches
     */
    By matchesButton = By.xpath("//*[@class='search-popup-tabs__header']/button[1]");
    /**
     * Заголовок League
     */
    By leaguesButton = By.xpath("//*[@class='search-popup-tabs__header']/button[2]");
    /**
     * Сообщение об отсутствии результатов поиска
     */
    By noResultsLabel = By.className("search-popup__nothing-find");

    /**
     * Элемент в таблице
     */
    By itemCell = By.className("search-popup-events__item");
    /**
     * Заголовок элемента в таблице
     */
    By eventHeader = By.className("search-popup-event__header");
    /**
     * Лига элемента в таблице
     */
    By eventLeague = By.className("search-popup-event__league");
    /**
     * Команды элемента в таблице
     */
    By eventTeams = By.className("search-popup-event__teams");

    By coefName = By.className("search-popup-coef__name");
    By coefValue = By.className("search-popup-coef__value");

    public SearchModalWindow() {
        $(searchModalWindow).should(Condition.exist);
    }
    //endregion

    /**
     * Метод для использование чекера.
     *
     * @return объект класса SearchWindowPageChecker
     */
    public SearchWindowPageChecker getChecker() {
        return new SearchWindowPageChecker(this);
    }


    public final MainPage clickCloseButton() {
        clickElements("Кнопка закрытия модального окна", closeModalWindowButton);
        return new MainPage();
    }

    public final SearchModalWindow clickSportsCheckbox() {
        clickElements("Чекбокс Sports", sportsCheckbox);
        return this;
    }

    public final SearchModalWindow clickLiveCheckbox() {
        clickElements("Чекбокс Live", liveCheckbox);
        return this;
    }

    public final SearchModalWindow clickSearchButton() {
        clickElements("Кнопка поиска", searchButton);
        return this;
    }

    public final SearchModalWindow clickLeaguesTab() {
        clickElements("Таб Leagues", leaguesButton);
        return this;
    }

    public final SearchModalWindow clickClearButton() {
        clickElements("Кнопка очистки поля поиска", searchFieldCleanButton);
        return this;
    }

    @Step("Открытие события с N [{event.N}]")
    public final EventPage openEvent(ValueItem event) {
        By eventLocator = By.xpath(String.format("//*[contains(text(),'%s')]", event.getN()));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", $(eventLocator));
        clickElements("Event с номером " + event.getN(), eventLocator);
        return new EventPage();
    }

    @Step("Поиск по запросу [{searchString}]")
    public final SearchModalWindow search(String searchString) {
        fillInputField("Поле поиска", searchField, searchString);
        clickElements("Кнопка поиска", searchButton);
        return this;
    }
}
