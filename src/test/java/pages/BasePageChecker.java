package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Базовый класс для проверки страниц
 */
public class BasePageChecker<PAGE extends BasePage> {

    protected WebDriver driver;
    public PAGE page;

    public BasePageChecker(PAGE page) {
        this.page = page;
        this.driver = WebDriverRunner.getWebDriver();
    }

    /**
     * Получение экземпляра страницы, переданного в checker
     *
     * @return экземпляр страницы переданного в checker
     */
    public PAGE getPage() {
        return page;
    }

    public final void checkElementExist(String elementName, By locator) {
        checkElementExist(elementName, $(locator));
    }

    @Step("Проверка что элемент [{elementName}] представлен на экране")
    protected final void checkElementExist(String elementName, SelenideElement element) {
        element.shouldBe(exist);
    }

    protected final void checkElementExistNegative(String elementName, By locator) {
        checkElementExistNegative(elementName, $(locator));
    }

    @Step("Проверка что элемент [{elementName}] НЕ представлен на экране")
    protected final void checkElementExistNegative(String elementName, SelenideElement element) {
        element.shouldNotBe(exist);
    }



    protected final void checkElementText(String elementName, By locator, String expectedText) {
        checkElementText(elementName, $(locator), expectedText);
    }

    @Step("Проверка что текст элемент [{elementName}] соответствует [{expectedText}]")
    protected final void checkElementText(String elementName, SelenideElement element, String expectedText) {
        element.shouldHave(exactText(expectedText)
                .because("actual: " + element.getText()));
    }



    protected final void checkElementValue(String elementName, By locator, String expectedText) {
        checkElementValue(elementName, $(locator), expectedText);
    }

    @Step("Проверка что value элемент [{elementName}] соответствует [{expectedText}]")
    protected final void checkElementValue(String elementName, SelenideElement element, String expectedText) {
        element.shouldHave(value(expectedText)
                .because("actual: " + element.getValue()));
    }



    protected final void checkElementPlaceholder(String elementName, By locator, String expectedText) {
        checkElementAttribute(elementName, $(locator), expectedText, "placeholder");
    }

    protected final void checkElementPlaceholder(String elementName, SelenideElement element, String expectedText) {
        checkElementAttribute(elementName, element, expectedText, "placeholder");
    }



    protected final void checkElementAttribute(String elementName, By locator, String expectedText, String attribute) {
        checkElementAttribute(elementName, $(locator), expectedText, attribute);
    }

    @Step("Проверка что аттрибут [{attribute}] элемент [{elementName}] соответствует [{expectedText}]")
    protected final void checkElementAttribute(String elementName, SelenideElement element, String expectedText, String attribute) {
        element.shouldHave(attribute(attribute, expectedText)
                .because("actual: " + page.getElementAttribute(elementName, element, attribute)));
    }



    protected final void checkCheckboxChecked(String elementName, By locator) {
        checkCheckboxChecked(elementName, $(locator));
    }

    @Step("Проверка что чекбокс элемента [{elementName}] включен")
    protected final void checkCheckboxChecked(String elementName, SelenideElement element) {
        element.shouldBe(checked);
    }



    /**
     * Проверка размера списка элементов, который должен быть равен указанному числу.
     *
     * @param elementName название элементов для аннотации @Step
     * @param locator     список элементов
     * @param neededSize  число, которому должен быть равен список
     */
    @Step("Проверка размера списка - [{0}], что он равен {2}")
    protected final void checkListSize(final String elementName,
                                       final By locator,
                                       final int neededSize) {
        $$(locator).shouldHaveSize(neededSize);
    }
}
