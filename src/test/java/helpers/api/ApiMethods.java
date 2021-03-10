package helpers.api;

import helpers.ParametersProvider;
import helpers.api.models.SearchResultModel;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

/**
 * Класс для работы с запросами
 */
public class ApiMethods {

    /**
     * Базовый url.
     */
    protected String url;

    /**
     * Спецификации запросов
     */
    protected RequestSpecification requestSpecification;


    /**
     * Конструктор
     */
    public ApiMethods() {
        setUp();
    }

    /**
     * Выставление данных для создания запросов
     */
    protected void setUp() {
        this.url = ParametersProvider.getPropertyByName("url");
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    /**
     * Получить результаты поиска .
     * api: /LiveFeed/Web_SearchZip?text=%s&limit=50&lng=en&mode=4&partner=169
     *
     * @return SearchResultModel
     */
    @Step("Получить результаты поиска по [{searchString}].")
    public SearchResultModel getLiveSearchResults(String searchString) {
        Response response = ExecutorAPI.get(requestSpecification,
                String.format(Constants.SEARCH_LIVE_ULR, searchString),
                HttpStatus.SC_OK);
        return response.as(SearchResultModel.class);
    }

    /**
     * Получить результаты поиска.
     * api: /LineFeed/Web_SearchZip?text=%s&limit=50&lng=en&mode=4&partner=169
     *
     * @return SearchResultModel
     */
    @Step("Получить результаты поиска по [{searchString}].")
    public SearchResultModel getNotLiveSearchResults(String searchString) {
        Response response = ExecutorAPI.get(requestSpecification,
                String.format(Constants.SEARCH_SPORTS_URL, searchString),
                HttpStatus.SC_OK);
        return response.as(SearchResultModel.class);
    }

}

