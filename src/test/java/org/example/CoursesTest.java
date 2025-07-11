package org.example;

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

public class CoursesTest {
    private WebDriver driver;
    @BeforeEach
    private void setUpDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mathe\\OneDrive\\Área de Trabalho\\chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://ead.pge.rs.gov.br");
    }

    @Test
    public void shouldNotAccessAnyCourseIfNotLogged() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropDownCoursesEvents = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(//TODO: PASSAR XPATH!!!)));;
        dropDownCoursesEvents.click();
        WebDriverWait dropDownWait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement dropDownOptionSignUp = dropDownWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[3]/div[2]/header[2]/nav/div[1]/nav/ul/li[3]/div/a[1]")));
        dropDownOptionSignUp.click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://ead.pge.rs.gov.br/course/view.php?id=701", currentUrl);
    }
}
