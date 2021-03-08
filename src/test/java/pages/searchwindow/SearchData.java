package pages.searchwindow;

import pages.BaseData;

/**
 * Данные экрана поиска
 */
public interface SearchData extends BaseData {

    String SEARCH_FIELD_PLACEHOLDER = "";

    String EVENTS_FOUND_HEADER_TEXT = "EVENTS FOUND: %s";
    String LIVE_CHECKBOX_LABEL = "Live";
    String SPORTS_CHECKBOX_LABEL = "Sports";
    String MATCHES_HEADER = "MATCHES";
    String LEAGUES_HEADER = "LEAGUES";
    String SEARCH_BUTTON_TEXT = "Search";

    String NO_RESULTS_LABEL = "No results";

    String FIRST_COEF_NAME = "1";
    String SECOND_COEF_NAME = "2";
    String DRAW_COEF_NAME = "X";

}
