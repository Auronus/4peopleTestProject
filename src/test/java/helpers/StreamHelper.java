package helpers;

import helpers.api.models.Value;
import helpers.api.models.ValueItem;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

public class StreamHelper {

    @Step("Получить результаты поиска для вкладки Matches")
    public static final List<ValueItem> getMatchesSearchResults(List<ValueItem> items) {
        List<ValueItem> newItems = new ArrayList<>();

        return newItems;
    }
}
