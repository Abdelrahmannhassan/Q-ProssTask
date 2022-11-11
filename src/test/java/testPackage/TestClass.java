package testPackage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddOwnerPage;
import pages.OwnerPage;
import pages.PetClincHomePage;
import pages.VeterinariansPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class TestClass {
    // declaration
    WebDriver driver;
    PetClincHomePage PCP;
    VeterinariansPage VP;
    OwnerPage OP;
    AddOwnerPage AOP;


    ////////////////////////////////////////////////////
    ////////// Test Cases /////////////////////////////
    //////////////////////////////////////////////////

    @Test (groups = "chrome")
    public void imageDisplayedInHomePage() {
        PCP.navigateToHomePage();
        Assert.assertTrue(PCP.imageDisplayed(), "Image not displayed");
        System.out.println("Image appears successfully!");
    }

    @Test (groups = "FireFox")
    //This part if you want to get the Veterinarians as PDF
    public void printVeterinariansPage() throws IOException {
        Path printedPage = Paths.get("src/test/Documents/VeterinariansPage.pdf");
        Pdf pdf = ((RemoteWebDriver) driver).print(new PrintOptions());
        Files.write(printedPage, OutputType.BYTES.convertFromBase64Png(pdf.getContent()));
        System.out.println("Veterinarians Page Printed Successfully");

    }

    @Test(groups = "FireFox")
    public void getAllOwners() throws IOException {
        //This part if you want to get the owners as PDF
        Path printedOwnerPage = Paths.get("src/test/Documents/AllOwnerPage.pdf");
        Pdf pdf = ((RemoteWebDriver) driver).print(new PrintOptions());
        Files.write(printedOwnerPage, OutputType.BYTES.convertFromBase64Png(pdf.getContent()));
        System.out.println("Owners Page Printed Successfully");

        //This part if you want to get all owners as png
        Screenshot s =new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(s.getImage(),"PNG",new File("src/test/Documents/OwnerScreenshot"));
        System.out.println("Screenshot captured successfully");

    }

    @Test (groups = "chrome")
    public void addNewOwner() {
        AOP.navigateToOwnerPage();
        System.out.println("Owner Page opened successfully");
        AOP.fillOwnerForm();
        System.out.println("Data filled successfully");
        Assert.assertEquals(AOP.validationText(), "Owner Information");
        System.out.println("Owner Added successfully");
        AOP.addNewPetToThisOwner();
        System.out.println("Pet added successfully");

    }




    ////////////////////////////////////////////////////////
    /////////////////Before and After Methods//////////////
    //////////////////////////////////////////////////////

    //Set up the browser
    @BeforeMethod (onlyForGroups = "chrome")
    public void setUpEnv() throws IOException, ParseException {
        // setting up chromedriver
        WebDriverManager.chromedriver().setup();

        // assignation
        driver = new ChromeDriver();

        //calling
        PCP = new PetClincHomePage(driver);
        AOP = new AddOwnerPage(driver);
        driver.manage().window().maximize();

    }

    //Set up the browser
    @BeforeMethod(onlyForGroups = "FireFox")
    public void fireFoxEnv() {
        // setting up chromedriver
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(firefoxOptions);

        // calling
        VP = new VeterinariansPage(driver);
        VP.navigateToURL();
        OP = new OwnerPage(driver);
        OP.navigateToOwnerPage();

    }

    //Close the browser
    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }

}
