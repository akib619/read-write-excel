package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class GooglePage {
    private WebDriver driver;
    
    private By searchInputLocator = By.xpath("//textarea[@id='APjFqb']");
    private By searchElementsLocator = By.xpath("//ul[@role='listbox']//li/descendant::div[@class='eIPGRd']");
    
    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public WebElement getSearchInput() {
        return driver.findElement(searchInputLocator);
    }
    
    public List<WebElement> getSearchElements() {
        return driver.findElements(searchElementsLocator);
    }
}

