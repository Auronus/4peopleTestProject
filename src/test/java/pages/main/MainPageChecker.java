package pages.main;

import io.qameta.allure.Step;
import pages.BasePageChecker;

/**
 * Класс проверок главного экрана
 */
public class MainPageChecker extends BasePageChecker<MainPage> implements MainPageData {

    /**
     * Конструктор.
     *
     * @param page объект класса MainPageChecker
     */
    public MainPageChecker(MainPage page) {
        super(page);
    }

    @Step("Проверка элементов поля поиска блока Live Bets")
    public final void checkSearchField() {
        checkElementExist("Поле поиска", page.getLiveBetsSearchField());
        checkElementExist("Кнопка поиска", page.getLiveBetsSearchButton());

        checkElementPlaceholder("Поле поиска", page.getLiveBetsSearchField(), SEARCH_FIELD_PLACEHOLDER);
    }

    public final void checkSearchWindowNotPresented() {
        checkElementExistNegative("Модальное окно", page.getSearchModalWindow());
    }

}
