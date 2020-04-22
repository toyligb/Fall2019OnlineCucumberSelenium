package com.vytrack.pages;

import com.vytrack.utilities.BrowserUtilities;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPageBase {

    @FindBy(id = "prependedInput")
    private WebElement username;

    @FindBy(id = "prependedInput2")
    private WebElement password;

    @FindBy(id = "_submit")
    private WebElement login;

    @FindBy(linkText = "Forgot your password?")
    private WebElement forgotPassword;

    @FindBy(css = "[class='alert alert-error']")
    private WebElement warningMessage;

//    public LoginPage() {
//        //constructor which connects our WebDriver, page class and page factory
//        //PageFactory - used to use @FindBy annotations
//        //PageFactory - helps to find elements easier
//        PageFactory.initElements(Driver.getDriver(), this);
//    }

    public String getWarningMessageTest() {
        return warningMessage.getText();
    }

    /**
     * Method to login, version #1
     * Login as a specific user
     *
     * @param usernameValue
     * @param passwordValue
     */
    public void login(String usernameValue, String passwordValue) {
        username.sendKeys(usernameValue);
        password.sendKeys(passwordValue, Keys.ENTER);
        BrowserUtilities.waitForPageToLoad(10);
        BrowserUtilities.wait(3);
    }

    /**
     * Method to login, version #2
     * Login as a default user
     * Credentials will be retrieved from configuration.properties file
     */
    public void login() {
        username.sendKeys(ConfigurationReader.getProperty("store_manager"));
        password.sendKeys(ConfigurationReader.getProperty("password"), Keys.ENTER);
        BrowserUtilities.waitForPageToLoad(10);
        BrowserUtilities.wait(3);
    }

    /**
     * Method to login, version #3
     * This method stands for login based on user type
     * For example: if parameter is equals to driver, user will login as a driver
     * If role parameter is not correct, method will throw exception
     * @param role - driver, sales manager, store manager
     */
    public void login(String role) {
        String userName = "";
        if (role.equalsIgnoreCase("Driver")) {
            userName = "user15";
        } else if (role.equalsIgnoreCase("sales manager")) {
            userName = "salesmanager110";
        } else if (role.equalsIgnoreCase("store manager")) {
            userName = "storemanager85";
        } else {
            throw new RuntimeException("Invalid role!");
        }

        System.out.println("Login as: " + role);
        login(userName, "UserUser123");
    }

}

/*
VYTrack consist on bunch of pages, open app is one page, login is one page.. every page consist of elements,
every element can be taken by selenium webDriver, represent as webElement than we can do whatever we want.
It is not acceptable to continuously repeat to finding same webElement
We must have minimum code duplication
In terms of web elements ; up until now we did not have concept to store and manage locators efficiently
Every test class we repeat same web elements like login
We will come up with design approach that will allow us to create classes => based on pages
We will store locators that represent web elements
login page class => login page locators  => PAGE OBJECT MODEL
we are not gonna keep finding locators again and again!
POM => we will create page classes for every page that will be tested
POM => store locators
Configuration Properties file => store credentials - connection url's - info about servers
keep elements inside class not in properties
if you put them in properties file ; you have to always keep checking what is the keyname to use that locator, this isn't convenient
"so when you create a page class are you storing all the WebElement locators as variables,
and then have basic navigation methods built in as well that you can call upon? " -yes (Jordan)
Before page object we create page class
create class => give corresponding name (login page => LoginPage class)
instantiate that class
pages package => is collection of page classes  (com.automation.pages)
under pages package => we create LoginPage (corresponding name to the page that we will test)
finding web element in POM : selenium has sth to improve this process : Page Factory
helps to find element easier, syntax is shorter, more organized
@FindBy
@FindBys
@FindAll
we use this annotations and put the locator in parenthesis
to be able to use annotations :
first initialize page factory that comes from selenium
create constructor
    public LoginPage(){
            //to connect our webDriver, page class and page factory
            //PageFactory - used to use @FindBy annotations
            //PageFactory - helps to find elements easier
            PageFactory.initElements(Driver.getDriver(), this);
    }
and write locators - annotations on the top of page :
    //use these on top of instance variables
    @FindBy(id = "prependedInput")
    public WebElement username;
    @FindBy(id = "prependedInput2")
    public WebElement password;
    @FindBy(id = "_submit")
    public WebElement login;
*/
