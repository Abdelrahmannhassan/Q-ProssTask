package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OwnerPage {
    //variables
    WebDriver driver;
    String URL = "https://petclincqpros.herokuapp.com/";


    //Locators
    By ownerBTN = By.xpath("//a[@title = 'find owners']");
    By findOwnerBTN = By.xpath("//button[@type = 'submit']");


    //Default Constructor
    public OwnerPage(WebDriver driver){
        this.driver = driver;
    }


    //Actions
    public void navigateToOwnerPage(){
        driver.navigate().to(URL);
        driver.findElement(ownerBTN).click();
        driver.findElement(findOwnerBTN).click();
    }

}
