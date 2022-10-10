package AlertClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserUtils;

import java.time.Duration;

public class AlertIntro {

    //Alerts= pop-up

    @Test
    public void alertAcceptAndGetText() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        WebElement jsAlert = driver.findElement(By.xpath("//button[contains(@onclick,'jsAlert()')]"));
        jsAlert.click();

        Alert alert = driver.switchTo().alert();
        String actualText = alert.getText();
        String expectedText = "I am a JS Alert";
        alert.accept();
        Assert.assertEquals(actualText, expectedText);
        WebElement header = driver.findElement(By.tagName("h3"));
        System.out.println(BrowserUtils.getText(header));
        WebElement message = driver.findElement(By.id("result"));
        Assert.assertEquals(BrowserUtils.getText(message), "You successfully clicked an alert");
    }
        @Test
         public void dismiss(){
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://the-internet.herokuapp.com/javascript_alerts");

            WebElement jsConfirm= driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
            jsConfirm.click();

            Alert alert=driver.switchTo().alert();
            String actualText= alert.getText();
            String expectedText="I am a JS Confirm";
            Assert.assertEquals(actualText,expectedText);
            alert.dismiss();
            WebElement result= driver.findElement(By.id("result"));
            Assert.assertEquals(BrowserUtils.getText(result),"You clicked: Cancel");

    }

    /*
    Hello World !
     */

    /*
    ccacaca
     */

    @Test
    public void alertSendKeys(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        WebElement jsPrompt= driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]"));
        jsPrompt.click();

        Alert alert=driver.switchTo().alert();
        alert.sendKeys("techtorial");
        alert.accept();
        WebElement message= driver.findElement(By.id("result"));
        Assert.assertEquals(BrowserUtils.getText(message),"You entered: techtorial");

    }
}

