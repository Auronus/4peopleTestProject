package pages.event;

import com.codeborne.selenide.Condition;
import lombok.Getter;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.main.MainPageChecker;
import pages.searchwindow.SearchModalWindow;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

/**
 * Главный экран
 */
@Getter
public class EventPage extends BasePage<EventPage, EventPageChecker> {

    private final By pageTitle = By.cssSelector("div.pages__title_main");

    public EventPage() {
        switchTo().window(1);
        $(pageTitle).waitUntil(Condition.exist, 30000);
    }

    /**
     * Метод для использование чекера.
     *
     * @return объект класса EventPageChecker
     */
    public EventPageChecker getChecker() {
        return new EventPageChecker(this);
    }

}
