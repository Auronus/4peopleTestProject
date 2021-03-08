package helpers.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.util.Strings;

public class ExecutorAPI {

    /**
     * POST-запрос.
     * @param specification спецификация
     * @param body тело запроса
     * @param url url запроса
     * @param code ожидаемый код статуса
     * @return response
     */
    public static Response post(final RequestSpecification specification,
                                final Object body,
                                final String url,
                                final int code) {
        Response response = RestAssured.given()
                .spec(specification)
                .body(body).post(url);

        checkMessageFromResponse(response, code);
        return response;

    }

    /**
     * GET-запрос.
     * @param specification спецификая
     * @param url url запроса
     * @param code ожидаемый код статуса
     * @return response
     */
    public static Response get(final RequestSpecification specification,
                               final String url,
                               final int code) {
        Response response = RestAssured.given()
                .spec(specification)
                .get(url);
        checkMessageFromResponse(response, code);
        return response;
    }

    /**
     * PUT-запрос
     * @param specification спецификация
     * @param body тело запроса
     * @param code ожидаемый код статуса
     * @param url url запроса
     * @return response
     */
    public static Response put(final RequestSpecification specification,
                               final Object body,
                               final String url,
                               final int code) {
        Response response = RestAssured.given()
                .spec(specification)
                .body(body)
                .put(url);
        checkMessageFromResponse(response, code);
        return response;
    }

    /**
     * DELETE-запрос.
     * @param specification спецификация
     * @param url url запроса
     * @param code ожидаемый код статуса
     * @return response
     */
    public static Response delete(final RequestSpecification specification,
                                  final String url,
                                  final int code) {
        Response response = RestAssured.given()
                .spec(specification)
                .delete(url);
        checkMessageFromResponse(response, code);
        return response;
    }

    private static void checkMessageFromResponse(Response response, int code) {
        if (response.statusCode() != code) {
            if (Strings.isNotNullAndNotEmpty(response.getBody().asString())) {
                String errorCode = response.jsonPath().getList("code", String.class).get(0);
                String message = response.jsonPath().getList("message", String.class).get(0);
                Assert.fail(response.statusCode() + ": " + message + ", " + errorCode);
            } else {
                Assert.fail(response.statusCode() + ": что-то пошло не так (нет сообщения об ошибке от сервера)");
            }
        }
    }

    /**
     * Пустой конструктор.
     */
    private ExecutorAPI() {
    }
}
