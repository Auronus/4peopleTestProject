package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import helpers.ParametersProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Базовый класс отвечающий за создание драйвера
 */
public abstract class BaseTest {

    @BeforeMethod
    protected void config() {
        Configuration.browser = ParametersProvider.getPropertyByName("defaultBrowserName");
        Configuration.timeout = Long.parseLong(ParametersProvider.getPropertyByName("implicitTimeout"));

        Selenide.open();
    }

    @AfterMethod
    public final void closeBrowser() {
        Selenide.closeWindow();
    }
}
