package ui.driver.factory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import static ui.driver.options.Options.*;

public class DriverManager {

    public static RemoteWebDriver driver;

    private static final Supplier<? extends RemoteWebDriver> chromeDriver = () -> new ChromeDriver(getChromeOptions());

    private static final Supplier<? extends RemoteWebDriver> firefoxDriver = () -> new FirefoxDriver(getFireFoxOptions());

    private static final Supplier<? extends RemoteWebDriver> edgeDriver = () -> new EdgeDriver(getEdgeOptions());

    private static final Supplier<? extends RemoteWebDriver> yandexDriver = () -> new ChromeDriver(
            new ChromeDriverService
                    .Builder()
                    .usingDriverExecutable(new File(ui.driver.config.Config.yandexDriverPath))
                    .build(),
            getChromeOptions()
    );

    private static final Supplier<RemoteWebDriver> remoteChromeDriver = () -> {
        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), getChromeOptions());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL", e);
        }
    };

    private static final Map<String, Supplier<? extends RemoteWebDriver>> map = new HashMap<>();

    static {
        map.put("chrome", chromeDriver);
        map.put("firefox", firefoxDriver);
        map.put("edge", edgeDriver);
        map.put("yandex", yandexDriver);
        map.put("remote-chrome", remoteChromeDriver);
    }

    public static RemoteWebDriver getDriver(String browser) {
        return (driver = map.get(browser).get());
    }
}
