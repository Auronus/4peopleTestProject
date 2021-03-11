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
     *
      * @param event
     * @param live
     */
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

        checkUrl(expectedUrl);
    }

}
