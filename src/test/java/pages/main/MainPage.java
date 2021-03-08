package pages.main;

import lombok.Getter;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.searchwindow.SearchModalWindow;

import static com.codeborne.selenide.Selenide.$;

/**
 * Главный экран
 */
@Getter
public class MainPage  extends BasePage<MainPage, MainPageChecker> {

    //region Локаторы

    /**
     * LIVE BETS search field
     */
    private By liveBetsSearchField = By.xpath("(//*[@class='b-filters__input searchInput'])[1]");

    /**
     * Кнопка поиска на главной
     */
    private By liveBetsSearchButton =  By.xpath("(//*[@class='b-filters__searchBut'])[1]");

    /**
     * Модальное окно
     */
    By searchModalWindow = By.cssSelector("div.search-popup.v-modal-search");
    //endregion

    /**
     * Метод для использование чекера.
     *
     * @return объект класса MainPageChecker
     */
    public MainPageChecker getChecker() {
        return new MainPageChecker(this);
    }

    public final MainPage scrollToSearch() {
        $(liveBetsSearchField).scrollIntoView(false);
        return this;
    }

    public final SearchModalWindow clickSearchButton() {
        clickElements("Кнопка поиска LIVE BETS", liveBetsSearchButton);
        return new SearchModalWindow();
    }

    public final SearchModalWindow search(String text) {
        fillInputField("Поле поиска", liveBetsSearchField, text);
        clickElements("Кнопка поиска LIVE BETS", liveBetsSearchButton);
        return new SearchModalWindow();
    }
}
