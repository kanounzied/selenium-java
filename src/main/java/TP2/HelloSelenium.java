package TP2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelloSelenium {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String url = "https://www.tunisianet.com.tn/connexion?back=my-account";
        String email = "*";
        String password = "*";

        driver.get(url);
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);

        WebElement submitButton = driver.findElement(By.id("submit-login"));
        submitButton.click();

        WebDriverWait waitVar = new WebDriverWait(driver, 10);

        Thread.sleep(1500);
        WebElement searchBar = waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_query_top")));
        searchBar.sendKeys("PC portable MacBook M1 13.3");

        Thread.sleep(10000);
        WebElement firstResult = waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".item:nth-child(1) img")));

        Thread.sleep(1500);
        List<WebElement> productsTitle = driver.findElements(By.className("product-title"));
        productsTitle.get(0).click();

        Thread.sleep(1500);
        WebElement addToCartButton = driver.findElement(By.className("add-to-cart"));
        addToCartButton.click();

        // Click to order
        Thread.sleep(1500);
        WebElement orderButton = driver.findElement(By.cssSelector("a.btn-block"));
        orderButton.click();

    }
}
