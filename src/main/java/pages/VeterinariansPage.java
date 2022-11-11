package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VeterinariansPage {

    //Variables
    WebDriver driver;
    String URL = "https://petclincqpros.herokuapp.com/";


    //Locators
    By VeterinariansBtn = By.xpath("//a[@title = 'veterinarians']");


    //Default constructor
    public VeterinariansPage(WebDriver driver) {
        this.driver = driver;
    }


    //Actions
    public void navigateToURL() {
        driver.navigate().to(URL);
        driver.findElement(VeterinariansBtn).click();
    }

}

