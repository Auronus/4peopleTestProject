package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

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
    public final void checkElementExist(String elementName, SelenideElement element) {
        element.shouldBe(exist);
    }

    public final void checkElementText(String elementName, By locator, String expectedText) {
        checkElementText(elementName, $(locator), expectedText);
    }

    @Step("Проверка что текст элемент [{elementName}] соответствует [{expectedText}]")
    public final void checkElementText(String elementName, SelenideElement element, String expectedText) {
        element.shouldHave(exactText(expectedText)
                .because("actual: " + page.getElementText(elementName, element)));
    }

    public final void checkElementPlaceholder(String elementName, By locator, String expectedText) {
        checkElementAttribute(elementName, $(locator), expectedText, "placeholder");
    }

    public final void checkElementPlaceholder(String elementName, SelenideElement element, String expectedText) {
        checkElementAttribute(elementName, element, expectedText, "placeholder");
    }

    public final void checkElementAttribute(String elementName, By locator, String expectedText, String attribute) {
        checkElementAttribute(elementName, $(locator), expectedText, attribute);
    }

    @Step("Проверка что аттрибут [{attribute}] элемент [{elementName}] соответствует [{expectedText}]")
    public final void checkElementAttribute(String elementName, SelenideElement element, String expectedText, String attribute) {
        element.shouldHave(attribute(attribute, expectedText)
                .because("actual: " + page.getElementAttribute(elementName, element, attribute)));
    }
}
