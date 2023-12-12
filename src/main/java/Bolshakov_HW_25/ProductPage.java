package Bolshakov_HW_25;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {

    private WebDriverWait wait;

    @FindBy(css = "div[class='inventory_list'] div[class='inventory_item']")
    private List<WebElement> productItem;

    @FindBy(css = "div[class='inventory_item_label'] div[class='inventory_item_name']")
    private WebElement productNames;

    @FindBy(css = "button.btn_primary.btn_inventory")
    private WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int checkProducts() {
        return productItem.size();
    }

    public void addProductsToCart(int amount) {

        if (amount == 1) {
            for (WebElement product : productItem) {
                if (product.findElement(By.cssSelector(String.valueOf(productNames))).getText().equals("Sauce Labs Bike Light")) {
                    product.findElement(By.cssSelector(String.valueOf(addToCartButton))).click();
                }
            }
        } else {

        }


    }
}
