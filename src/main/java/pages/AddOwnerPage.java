package pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.io.FileReader;
import java.io.IOException;

public class AddOwnerPage {
    //variables
    WebDriver driver;
    String URL = "https://petclincqpros.herokuapp.com/";
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("Utilities\\data.json"));



    //locators
    By findOwnerBtn = By.xpath("//span[contains(.,'Find owners')]");
    By addOwnerBtn = By.xpath("//a[@class = 'btn btn-default']");
    By firstName = By.id("firstName");
    By lastName = By.id("lastName");
    By address = By.id("address");
    By city = By.id("city");
    By telephone = By.id("telephone");
    By addOwnerInfo = By.xpath("//button[@type = 'submit']");
    By validationText = By.xpath("//h2[contains(.,'Owner Information')]");
    By addPetBtn = By.xpath("//a[@class = 'btn btn-default'][contains(.,'Add')]");
   // By verifyOwnerName = By.xpath("(//span)[14]");
    By petName = By.id("name");
    By petBirthday = By.id("birthDate");
    By petDropDownMenu = By.id("type");
    By addNewPetBtn = By.xpath("//button[@type = 'submit']");



    //constructor
    public AddOwnerPage(WebDriver driver) throws IOException, ParseException {
        this.driver = driver;
    }



    //Actions
    public void navigateToOwnerPage(){
        driver.navigate().to(URL);
        driver.findElement(findOwnerBtn).click();
        driver.findElement(addOwnerBtn).click();
    }

    public void fillOwnerForm(){
        driver.findElement(firstName).sendKeys(jsonObject.get("firstname").toString());
        driver.findElement(lastName).sendKeys(jsonObject.get("lastname").toString());
        driver.findElement(address).sendKeys(jsonObject.get("address").toString());
        driver.findElement(city).sendKeys(jsonObject.get("city").toString());
        driver.findElement(telephone).sendKeys(jsonObject.get("telephone").toString());
        driver.findElement(addOwnerInfo).click();
    }

    public String validationText(){
        return driver.findElement(validationText).getText();
    }

    public void addNewPetToThisOwner(){
        driver.findElement(addPetBtn).click();
        driver.findElement(petName).sendKeys(jsonObject.get("name").toString());
        driver.findElement(petBirthday).sendKeys(jsonObject.get("BirthData").toString());

        //Select here to handle drop down menu
        WebElement mySelectElement = driver.findElement(petDropDownMenu);
        Select dropdown = new Select(mySelectElement);
        dropdown.selectByVisibleText("cat");

        driver.findElement(addNewPetBtn).click();
    }
}
