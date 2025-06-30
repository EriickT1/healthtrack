package com.healthtrack.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UsuarioUITest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Configuración para diferentes entornos
        String os = System.getProperty("os.name").toLowerCase();
        String driverPath;
        ChromeOptions options = new ChromeOptions();
        
        if (os.contains("win")) {
            // Configuración para Windows
            driverPath = "chromedriver.exe";
        } else {
            // Configuración para Linux (GitHub Actions)
            driverPath = "/usr/bin/chromedriver";
            options.setBinary("/usr/bin/chromium-browser");
            options.addArguments("--headless=new"); // Nueva sintaxis para headless
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-debugging-port=9222");
            options.addArguments("--disable-extensions");
            options.addArguments("--window-size=1280,1024");
        }
        
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void testCargaFormulario() {
        driver.get("http://localhost:8080/");
        WebElement inputPeso = driver.findElement(By.name("peso"));
        Assertions.assertNotNull(inputPeso);
    }

    @Test
    public void testActualizarPeso() {
        driver.get("http://localhost:8080/");
        WebElement inputPeso = driver.findElement(By.name("peso"));
        inputPeso.clear();
        inputPeso.sendKeys("80.5");
        inputPeso.submit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean contieneTexto = wait.until(ExpectedConditions.textToBePresentInElementLocated(
            By.tagName("body"), "80.5"));
        
        Assertions.assertTrue(contieneTexto);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}