package utility;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v132.network.Network;
import org.openqa.selenium.devtools.v132.network.model.ResponseReceived;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static ui.driver.config.Config.baseUrl;

public class NetworkMonitor {
    private static DevTools devTools;
    private static CompletableFuture<Integer> responseFuture;
    private static String targetUrl;

    public static void startMonitoring() {

        ChromeDriver driver = (ChromeDriver) WebDriverRunner.getWebDriver();

        if (devTools != null) {
            devTools.close();  // Закрываем предыдущий DevTools, если он был
        }

        devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.responseReceived(), NetworkMonitor::handleResponse);
        System.out.println("[NetworkMonitor] Мониторинг сети запущен.");
    }


    public static void stopMonitoring() {
        if (devTools != null) {
            devTools.send(Network.disable());
            devTools.close();
            devTools = null;
            responseFuture = null;
            targetUrl = null;
            System.out.println("[NetworkMonitor] Мониторинг сети остановлен.");
        }
    }

    public static void waitForAllResponses(String url, int expectedStatus, int expectedCount) {
        targetUrl = baseUrl + url;
        responseFuture = new CompletableFuture<>();

        AtomicInteger receivedCount = new AtomicInteger(0);

        System.out.println("[NetworkMonitor] Ожидание " + expectedCount + " ответов от: " + targetUrl);

        devTools.addListener(Network.responseReceived(), response -> {
            String responseUrl = response.getResponse().getUrl();
            int statusCode = response.getResponse().getStatus();

            if (responseUrl.contains(targetUrl) && statusCode == expectedStatus) {
                int count = receivedCount.incrementAndGet();
                System.out.println("[NetworkMonitor] Получен " + count + "-й ответ 200 для " + responseUrl);

                if (count == expectedCount) {
                    responseFuture.complete(count);
                }
            }
        });

        try {
            responseFuture.get(60, TimeUnit.SECONDS); // Ждём все запросы, максимум 60 секунд
            System.out.println("[NetworkMonitor] Все " + expectedCount + " запросов завершены.");
        } catch (Exception e) {
            System.err.println("[NetworkMonitor] Ошибка ожидания всех ответов: " + e.getMessage());
            throw new RuntimeException("Ошибка при ожидании всех ответов", e);
        }
    }

    public static int waitForResponse(String url, int expectedStatus) {
        targetUrl = baseUrl + url;

        // Сбрасываем старый future перед созданием нового
        if (responseFuture != null) {
            responseFuture.completeExceptionally(new RuntimeException("Старый future сброшен"));
        }
        responseFuture = new CompletableFuture<>();

        System.out.println("[NetworkMonitor] Ожидание ответа от: " + targetUrl);

        try {
            int statusCode = responseFuture.get(50, TimeUnit.SECONDS);
            if (statusCode == expectedStatus) {
                System.out.println("[NetworkMonitor] Получен ожидаемый статус: " + statusCode);
            } else {
                System.out.println("[NetworkMonitor] Ожидали " + expectedStatus + ", но получили " + statusCode);
            }
            return statusCode;
        } catch (Exception e) {
            System.err.println("[NetworkMonitor] Ошибка ожидания ответа: " + e.getMessage());
            throw new RuntimeException("Ошибка при ожидании ответа", e);
        }
    }


    private static void handleResponse(ResponseReceived response) {
        String responseUrl = response.getResponse().getUrl();
        int statusCode = response.getResponse().getStatus();

        System.out.println("[NetworkMonitor] Перехваченный ответ: " + responseUrl + " | Статус: " + statusCode);

//        if (responseUrl != null && responseUrl.contains(targetUrl)) {
        if (responseUrl != null && responseUrl.startsWith(targetUrl.split("\\?")[0])) {
            System.out.println("[NetworkMonitor] Найден ответ для " + targetUrl);
            responseFuture.complete(statusCode);
        }
    }
}
