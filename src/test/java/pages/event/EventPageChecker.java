package pages.event;

import com.codeborne.selenide.WebDriverRunner;
import helpers.ParametersProvider;
import helpers.api.models.ValueItem;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import pages.BasePageChecker;

/**
 * Класс проверок главного экрана
 */
public class EventPageChecker extends BasePageChecker<EventPage> {

    /**
     * Конструктор.
     *
     * @param page объект класса EventPageChecker
     */
    public EventPageChecker(EventPage page) {
        super(page);
    }

    @Step("Проверка элементов поля поиска блока Live Bets")
    public final void checkUrl(ValueItem event, boolean live) {
        String leagueUrl;
        String teamsUrl;
        String[] leaguesUrlElements = event.getLE().replace(".", "").split(" ");
        String[] firstTeamUrlElements = event.getO1().replace(".", "").replace("(", "").replace(")", "").split(" ");
        String[] secondTeamUrlElements = event.getO2().replace(".", "").replace("(", "").replace(")", "").split(" ");

        leagueUrl = StringUtils.join(leaguesUrlElements, "-");
        teamsUrl = StringUtils.join(firstTeamUrlElements, "-") + "-" + StringUtils.join(secondTeamUrlElements, "-");


        String expectedUrl = String.format(
                "%s%s/%s/%s/%s/",
                ParametersProvider.getPropertyByName("url"),
                live ? "live" : "line",
                event.getSN().replace(" ", "-"),
                (event.getLI() != 0 ? event.getLI() : event.getI()) + "-" + leagueUrl,
                event.getI() + "-" + teamsUrl
        );

        Assert.assertEquals(
                WebDriverRunner.url(),
                expectedUrl,
                String.format(
                        "URL октрытой страницы [%s] не сооветствует ожидаемому [%s]",
                        WebDriverRunner.url(),
                        expectedUrl
                )
        );
    }

}
