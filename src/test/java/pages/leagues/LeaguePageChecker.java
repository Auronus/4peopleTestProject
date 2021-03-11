package pages.leagues;

import helpers.ParametersProvider;
import helpers.api.models.ValueItem;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import pages.BasePageChecker;

/**
 * Класс проверок главного экрана
 */
public class LeaguePageChecker extends BasePageChecker<LeaguePage> {

    /**
     * Конструктор.
     *
     * @param page объект класса EventPageChecker
     */
    public LeaguePageChecker(LeaguePage page) {
        super(page);
    }

    /**
     * @param league
     * @param live
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
