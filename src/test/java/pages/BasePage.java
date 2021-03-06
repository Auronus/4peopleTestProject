package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Consumer;

import static com.codeborne.selenide.Selenide.$;

/**
 * Базовый класс для страниц
 */
public class BasePage<PAGE extends BasePage, CHECKER extends BasePageChecker> {

    public BasePage() {

    }

    /**
     * Вызов блока проверок для страницы. В качестве класса с проверками используется проинициализированный
     * по-умолчанию класс в Page
     *
     * @param consumers - содержит методы проверок, которые нужно вызвать
     * @return текущая страница
     */
    @SafeVarargs
    public final PAGE checkPage(Consumer<CHECKER>... consumers) {
        CHECKER checker = getChecker();
        for (Consumer<CHECKER> consumer : consumers) {
            consumer.accept(checker);
        }
        return (PAGE) this;
    }

    protected CHECKER getChecker() {
        throw new NotImplementedException();
    }


    public final String getElementText(String elementName, By locator) {
        return getElementText(elementName, $(locator));
    }

    @Step("Получить текст элемента [{elementName}]")
    public final String getElementText(String elementName, SelenideElement element) {
        return element.getText();
    }

    @Step("Получить [{attribute}] элемента [{elementName}]")
    public final String getElementAttribute(String elementName, SelenideElement element, String attribute) {
        return element.getAttribute(attribute);
    }
}
