import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver","C:\\Tools\\Selenium\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("http://localhost:8000");
        //Thread.sleep(500);

        WebElement WebElem = driver.findElement(By.id("username-input")); //changing focus to the addition input 1

        //sending keystrokes to that input element
        WebElem.sendKeys("Jim Pickens");
        //Thread.sleep(500);

        WebElem = driver.findElement(By.id("password-input")); //changing focus to the addition input 2

        //sending keystrokes to that input element
        WebElem.sendKeys("CheeseLover3");
        //Thread.sleep(500);

        WebElement btn = driver.findElement(By.id("loginbtn")); //changing focus to the add button

        btn.click(); //pressing the button



        //Thread.sleep(12000);

        try{
            wait.until(ExpectedConditions.urlContains("Employee"));
        }catch (Exception e){}

        btn = driver.findElement(By.id("NewTicketBtn"));

        btn.click();

        //Thread.sleep(4000);

        try{
            wait.until(ExpectedConditions.urlContains("New-Ticket"));
        }catch (Exception e){}

        WebElem = driver.findElement(By.id("amount"));

        WebElem.sendKeys("69420");

        //Thread.sleep(500);

        WebElem = driver.findElement(By.id("description"));

        WebElem.sendKeys("Automated Test");

        //Thread.sleep(500);

        btn = driver.findElement(By.id("dropdownMenuButton1"));

        btn.click();

        //Thread.sleep(500);

        btn = driver.findElement(By.id("other"));

        btn.click();

        //Thread.sleep(1000);

        btn = driver.findElement(By.id("type"));

        btn.click();

        //Thread.sleep(6000);

        //System.out.println(driver.getCurrentUrl());

        driver.quit(); //closes the driver

    }
}