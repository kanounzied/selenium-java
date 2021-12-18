package TP3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Tp3_junit {

    public static String URL = "https://todomvc.com/";
    private WebDriver driver;
    JavascriptExecutor js;

    @Test
    public void todoTest() throws InterruptedException{
        driver.get(URL);
        clickTechnologyLink("Backbone.js");
        addTodo("Read a book");
        addTodo("Cook dinner");
        addTodo("write code");
        removeTodo();
        Thread.sleep(1000);

        assertLeft(2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Backbone.js",
            "AngularJS",
            "Dojo",
            "React"})
    public void todosTestCase(String platform) {
        driver.get(URL);
        clickTechnologyLink(platform);
        addTodo("Read a book");
        addTodo("Cook dinner");
        addTodo("write code");
        removeTodo();

        assertLeft(2);

    }

    @BeforeAll
    static void setupDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {

        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;

    }

    public void clickTechnologyLink(String linkText) {
        WebElement backboneLink = driver.findElement(By.linkText(linkText));
        backboneLink.click();
    }

    public void addTodo(String todo) {
        WebElement todoElement = driver.findElement(By.cssSelector(".new-todo"));
        todoElement.sendKeys(todo);
        todoElement.sendKeys(Keys.ENTER);
    }

    private void removeTodo() {
        WebElement element = driver.findElement(By.cssSelector("li:nth-child(2) .toggle"));
        element.click();
    }

    private void assertLeft(int expectedLeft) {
        WebElement element = driver.findElement(By.xpath("//footer/*/span | //footer/span"));
        if (expectedLeft == 1) {
            String expectedTest = String.format("$d item left", expectedLeft);
            validateInnerText(element, expectedTest);
        } else {
            String expectedTest = String.format("$d items left", expectedLeft);
            validateInnerText(element, expectedTest);
        }
    }

    private void validateInnerText(WebElement element, String expectedTest) {
        ExpectedConditions.textToBePresentInElement(element, expectedTest);
    }

    @AfterEach
    public void quitDriver() throws InterruptedException {
        driver.quit();
    }

//    public class ActivTest {
//        WebDriver driver;
//        JavascriptExecutor js;
//
//
//        @Test
//        public void todoTestCase() throws InterruptedException {
//            driver.get("https://todomvc.com");
//            choosePlatform("Backbone.js");
//            addTodo("Read a book");
//            addTodo("Cook dinner");
//            addTodo("write code");
//            removeTodo();
//            Thread.sleep(1000);
//
//            assertLeft(2);
//
//        }
//
//        @ParameterizedTest
//        @ValueSource(strings = {"Backbone.js",
//                "AngularJS",
//                "Dojo",
//                "React"})
//        public void todosTestCase(String platform) {
//            driver.get("https://todomvc.com");
//            choosePlatform(platform);
//            addTodo("Read a book");
//            addTodo("Cook dinner");
//            addTodo("write code");
//            removeTodo();
//
//            assertLeft(2);
//
//        }
//
//        private void choosePlatform(String platform) {
//            WebElement element = driver.findElement(By.linkText(platform));
//            element.click();
//        }
//
//        private void addTodo(String todo) {
//            WebElement element = driver.findElement(By.className("new-todo"));
//            element.sendKeys(todo);
//            element.sendKeys(Keys.RETURN);
//        }
//
//        private void removeTodo() {
//            WebElement element = driver.findElement(By.cssSelector("li:nth-child(2) .toggle"));
//            element.click();
//        }
//
//        private void assertLeft(int expectedLeft) {
//            WebElement element = driver.findElement(By.xpath("//footer/*/span | //footer/span"));
//            if (expectedLeft == 1) {
//                String expectedTest = String.format("$d item left", expectedLeft);
//                validateInnerText(element, expectedTest);
//            } else {
//                String expectedTest = String.format("$d items left", expectedLeft);
//                validateInnerText(element, expectedTest);
//            }
//        }
//
//        private void validateInnerText(WebElement element, String expectedTest) {
//            ExpectedConditions.textToBePresentInElement(element, expectedTest);
//        }
//
//        @AfterEach
//        public void quitDriver() throws InterruptedException {
//            driver.quit();
//        }
//    }


}
