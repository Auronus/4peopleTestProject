package pages.leagues;

import com.codeborne.selenide.Condition;
import lombok.Getter;
import org.openqa.selenium.By;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

/**
 * Экран лиги
 */
@Getter
public class LeaguePage extends BasePage<LeaguePage, LeaguePageChecker> {

    private final By pageTitle = By.cssSelector("div.pages__title_main");

    public LeaguePage() {
        switchTo().window(1);
        $(pageTitle).waitUntil(Condition.exist, 30000);
    }

    /**
     * Метод для использование чекера.
     *
     * @return объект класса LeaguePageChecker
     */
    public LeaguePageChecker getChecker() {
        return new LeaguePageChecker(this);
    }

}
