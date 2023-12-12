package Bolshakov_HW_25;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriverWait wait;

    static final String ERROR = "Epic sadface: Sorry, this user has been locked out.";

    @FindBy(css = "#user-name")
    private WebElement UsernameInput;

    @FindBy(css = "#password")
    private WebElement UserpassInput;

    @FindBy(css = "#login-button")
    public WebElement LoginButton;

    @FindBy(css = "#inventory_filter_container > div")
    public WebElement PageTitle;

    @FindBy(css = "div[class='bm-burger-button']")
    public WebElement PageMenuButton;

    @FindBy(css = "#logout_sidebar_link")
    public WebElement LogoutButton;

    @FindBy(css = "#login_button_container > div > form > h3")
    public WebElement ErrorMessage;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void InsertUsername(String Username) {
        UsernameInput.clear();                          // У випадку якщо тест з валідними даними запускається другим
        UsernameInput.sendKeys(Username);
    }

    public void InsertUserpass(String Userpass) {
        UserpassInput.clear();                          // У випадку якщо тест з валідними даними запускається другим
        UserpassInput.sendKeys(Userpass);
    }

    public void LogIn() {
        LoginButton.click();
    }

    public boolean checkLogIn() {
        wait.until(ExpectedConditions.visibilityOf(PageTitle));
        return PageTitle.isDisplayed();
    }

    public void LogOut() {
        PageMenuButton.click();
        wait.until(ExpectedConditions.visibilityOf(LogoutButton));
        LogoutButton.click();
    }

    public boolean CheckValidationMessage() {
        return ErrorMessage.getText().equals(ERROR);
    }

}
