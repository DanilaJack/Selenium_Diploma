package ui.driver.options;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import ui.driver.config.Config;
import utility.ProjectSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Options {
    private static final List<String> args = new ArrayList<>();
    static Map<String, Object> prefs = new HashMap<>();

    static {
        //--> System property
        System.setProperty("SE_CACHE_PATH", Config.cachePath);
        //--> Отказ от сбора данных (Selenium Manager отправляет данные в Plausible один раз в день)
        System.setProperty("SE_AVOID_STATS", "true");
        //--> headless on || off
        if(Config.headless) args.add("--headless");
        //--> Starts the browser maximized, regardless of any previous settings
        if(Config.maximized) args.add("--start-maximized");

        args.add("--remote-allow-origins=*");
        args.add("--auto-select-desktop-capture-source=your-source"); // Автоматически разрешает доступ к источнику
        args.add("--allow-file-access-from-files");
        //-->  Flags: https://peter.sh/experiments/chromium-command-line-switches/
        prefs.put("download.default_directory", ProjectSettings.DOWNLOADS_PATH);
        prefs.put("profile.default_content_setting_values.clipboard", 1);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        //--> Устанавливаем размер окна
        //args.add("--window-size=1920,1080");

    }

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (!args.isEmpty()) options.addArguments(args);
        if (!prefs.isEmpty()) options.setExperimentalOption("prefs", prefs); // Добавляем prefs
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-yandex-disk-viewer");
        return options;
    }

    public static FirefoxOptions getFireFoxOptions() {
        return !args.isEmpty() ? new FirefoxOptions().addArguments(args) : new FirefoxOptions();
    }

    public static EdgeOptions getEdgeOptions() {
        return !args.isEmpty() ? new EdgeOptions().addArguments(args) : new EdgeOptions();
    }

}

