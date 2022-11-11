package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PetClincHomePage {

    //Variables
    WebDriver driver;
    String URL = "https://petclincqpros.herokuapp.com/";


    //Locators
    By ImageOnHomePage =By.className("img-responsive");


    //Default constructor
    public PetClincHomePage(WebDriver driver){
        this.driver = driver;
    }


    //Actions
    public void navigateToHomePage(){
        driver.navigate().to(URL);
    }

    public boolean imageDisplayed(){
        return driver.findElement(ImageOnHomePage).isDisplayed();
    }
}
