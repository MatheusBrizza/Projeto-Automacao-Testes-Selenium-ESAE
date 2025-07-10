package org.example;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    private void setUpDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--remote-allow-origins=*");

    System.setProperty("webdriver.chrome.driver", "C:\\Users\\mathe\\OneDrive\\Área de Trabalho\\chromedriver.exe");
    driver = new ChromeDriver(chromeOptions);
    driver.get("https://ead.pge.rs.gov.br");
    }

//    @AfterEach
//    private void close() {
//        driver.quit();
//    }

    @Test
    public void shouldOpenHomePage() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://ead.pge.rs.gov.br/"));
    }
    @Test
    public void shouldLoginWithCorrectCredentials() {
        WebElement inputEmail = driver.findElement(By.id("inputName"));
        inputEmail.sendKeys("e-matheus-jacob");
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.sendKeys("Resident evil 7");
        WebElement buttonEnter = driver.findElement(By.id("submit"));
        buttonEnter.click();
        WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement userMenu = espera.until(ExpectedConditions.presenceOfElementLocated(By.id("user-menu-toggle")));
        assertTrue(userMenu.isDisplayed());
    }

    @Test
    public void shouldGetErrorMessageWhenLoginWithWrongCredentials() {
        WebElement inputEmail = driver.findElement(By.id("inputName"));
        inputEmail.sendKeys("e-matheus-jacob");
        WebElement inputPassword = driver.findElement(By.id("inputPassword"));
        inputPassword.sendKeys("abc123");
        WebElement buttonEnter = driver.findElement(By.id("submit"));
        buttonEnter.click();
        WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement errorMessage = espera.until(ExpectedConditions.presenceOfElementLocated(By.className("alert")));
        String expectedErrorMessage = "Nome de usuário ou senha errados. Por favor tente outra vez.";
        String actualErrorMessage = errorMessage.getText();
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void shouldGetErrorMessageWhenLoginWithBlankInputs() {
        WebElement buttonEnter = driver.findElement(By.id("submit"));
        buttonEnter.click();
        WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement errorMessage = espera.until(ExpectedConditions.presenceOfElementLocated(By.className("alert")));
        String expectedErrorMessage = "Nome de usuário ou senha errados. Por favor tente outra vez.";
        String actualErrorMessage = errorMessage.getText();
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
