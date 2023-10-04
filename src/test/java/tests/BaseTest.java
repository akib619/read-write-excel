package tests;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {

    protected WebDriver driver;

   @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
        driver=new ChromeDriver(chromeOptions);
    }

    @BeforeMethod
    public void goHome(){
        driver.manage().window().maximize();
        driver.get("https://www.google.com/en");
        //homePage = new HomePage(driver);
    }

    // @AfterClass
    // public void tearDown(){
    //     driver.quit();
    // }

}
