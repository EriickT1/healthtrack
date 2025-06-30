package com.healthtrack.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class UsuarioUITest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe"); 
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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

        // Assertions.assertTrue(driver.getPageSource().contains("Usuario")); 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
        By.tagName("body"), "80.5"));
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
