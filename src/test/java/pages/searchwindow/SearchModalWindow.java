package pages.searchwindow;

import org.openqa.selenium.By;
import pages.BasePage;

/**
 * Модальное окно поиска
 */
public class SearchModalWindow extends BasePage<SearchModalWindow, SearchWindowPageChecker> {

    //region Локаторы

    /**
     * Модальное окно
     */
    By searchModalWindow = By.className("search-popup v-modal-search");

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
    By searchField = By.className("search-in-popup");
    /**
     * Кнопка очистки поля поиска
     */
    By searchFieldCleanButton = By.className("search-popup__clear");
    /**
     * Кнопка микрофона в поле поиска
     */
    By searchFieldMicButton = By.className("voice-mic search-popup__mic fa fa-microphone");
    /**
     * Кнопка поиска
     */
    By searchButton = By.className("search-popup__button");


    /**
     * Заголовок Matches
     */
    By matchesButton = By.xpath("//*[@class='search-popup-tabs__header']/button[1]");
    /**
     * Заголовок League
     */
    By leaguesButton = By.xpath("//*[@class='search-popup-tabs__header']/button[2]");

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
    //endregion

    /**
     * Метод для использование чекера.
     *
     * @return объект класса SearchWindowPageChecker
     */
    public SearchWindowPageChecker getChecker() {
        return new SearchWindowPageChecker(this);
    }
}
