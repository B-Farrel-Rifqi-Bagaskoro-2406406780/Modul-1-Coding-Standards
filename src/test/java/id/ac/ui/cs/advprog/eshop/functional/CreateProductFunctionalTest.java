package id.ac.ui.cs.advprog.eshop.functional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void pageHeading_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        String pageHeading = driver.findElement(By.tagName("h3")).getText();
        assertEquals("Create New Product", pageHeading);
    }

    @Test
    void nameInputField_exists(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        assertTrue(nameInput.isDisplayed());
        assertEquals("text", nameInput.getAttribute("type"));
    }

    @Test
    void quantityInputField_exists(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        assertTrue(quantityInput.isDisplayed());
        assertEquals("text", quantityInput.getAttribute("type"));
    }

    @Test
    void submitButton_exists(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        assertTrue(submitButton.isDisplayed());
        assertEquals("Submit", submitButton.getText());
    }

    @Test
    void formAction_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        WebElement form = driver.findElement(By.tagName("form"));
        String formAction = form.getAttribute("action");
        assertTrue(formAction.contains("/product/create"));
        assertEquals("post", form.getAttribute("method"));
    }

    @Test
    void createProduct_submitsAndAppearsInList(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        
        String productName = "test product";
        String productQuantity = "75";
        
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        
        nameInput.sendKeys(productName);
        quantityInput.sendKeys(productQuantity);
        submitButton.click();
        
        // tunggu page load, cek table element
        WebElement table = driver.findElement(By.className("table"));
        assertTrue(table.isDisplayed());
        
        // cek apakah product baru ada di page source
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains(productName));
        assertTrue(pageSource.contains(productQuantity));
    }


}
