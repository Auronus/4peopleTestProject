package helpers.api;

/**
 * Константы для работы с запросами
 */
public interface Constants {

    /**
     * Запрос поиска чекбокса Live
     */
    String SEARCH_LIVE_ULR = "/LiveFeed/Web_SearchZip?text=%s&limit=50&lng=en&mode=4&partner=169";

    /**
     * Запрос поиска чекбокса Sports
     */
    String SEARCH_SPORTS_URL =   "/LineFeed/Web_SearchZip?text=%s&limit=50&lng=en&mode=4&partner=169";
}
