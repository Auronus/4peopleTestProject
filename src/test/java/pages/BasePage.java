package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Consumer;

import static com.codeborne.selenide.Selenide.$;

/**
 * Базовый класс для страниц
 */
public class BasePage<PAGE extends BasePage, CHECKER extends BasePageChecker> {

    protected WebDriver driver;

    public BasePage() {
        this.driver = WebDriverRunner.getWebDriver();
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

    public final PAGE clickElements(String elementName, By locator) {
        return clickElements(elementName, $(locator));
    }

    @Step("Клик по элементу [{elementName}]")
    public final PAGE clickElements(String elementName, SelenideElement element) {
        element.click();
        return (PAGE) this;
    }

    public final PAGE fillInputField(String elementName, By locator, String text) {
        return fillInputField(elementName, $(locator), text);
    }

    @Step("Ввод в элемент [{elementName}] текст [{text}]")
    public final PAGE fillInputField(String elementName, SelenideElement element, String text) {
        element.setValue(text);
        return (PAGE) this;
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
