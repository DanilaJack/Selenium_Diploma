package ui.driver.config;

public class Config {

    public static String baseUrl;
    public static String stand;
    public static String browser;
    public static boolean headless;
    public static boolean maximized;
    public static int implicitWaitTimeout;
    public static String browserSize;
    public static String cachePath;
    public static String yandexDriverPath;

    static { //--> Set default configuration
        Config.browser = "chrome";
        Config.browserSize = "1600x1000";
        Config.cachePath = "src/test/resources/.cache/selenium";
        Config.yandexDriverPath = "src/test/resources/yandexdriver.exe";
    }
}
