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

    /**
     * Проверка URL текущей страницы ивента
     * @param league объект открытого элемента
     * @param live выбора live или line
     */
    public final void checkUrl(ValueItem league, boolean live) {
        String leagueUrl;
        String[] leaguesUrlElements = league.getLE().replace(".", "").split(" ");

        leagueUrl = StringUtils.join(leaguesUrlElements, "-");


        String expectedUrl = String.format(
                "%s%s/%s/%s/",
                ParametersProvider.getPropertyByName("url"),
                live ? "live" : "line",
                league.getSN().replace(" ", "-"),
                (league.getLI() != 0 ? league.getLI() : league.getI()) + "-" + leagueUrl
        );

        checkUrl(expectedUrl);
    }

}
