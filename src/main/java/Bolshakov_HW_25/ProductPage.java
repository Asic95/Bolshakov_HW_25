package Bolshakov_HW_25;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {

    private WebDriverWait wait;

    @FindBy(css = "div[class='inventory_list'] div[class='inventory_item']")
    private List<WebElement> productItem;

    @FindBy(css = "button.btn_primary.btn_inventory")
    private List<WebElement> addToCartButton;

    @FindBy(css = "span.fa-layers-counter.shopping_cart_badge")
    private WebElement amountOfProductsInCart;

    @FindBy(xpath = ".//a[@class='shopping_cart_link fa-layers fa-fw']")
    private WebElement goToCart;

    @FindBy(xpath = ".//div[@class='cart_item']")
    private List<WebElement> cartItems;

    @FindBy(xpath = ".//a[@class='btn_secondary' and contains(text(),'Continue Shopping')]")
    public WebElement backFromCartButton;

    @FindBy(css = "#cart_contents_container > div > div.cart_list > div.cart_item > div.cart_item_label > div.item_pricebar > button")
    public List<WebElement> removeButton;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int checkProducts() {
        return productItem.size();
    }

    public int addProductsToCart(int amount) throws InterruptedException {
        int randomProduct, randomProduct2;
        int isCartOkay = 0;
        randomProduct = (int) (Math.random() * (productItem.size()-1));         // рандомно вибираємо будь-яке значення для додавання в корзину
        randomProduct2 = (int) (Math.random() * (productItem.size()-1));         // рандомно вибираємо будь-яке значення для додавання в корзину
        if (amount == 1) {
            for (int k = 0; k < productItem.size(); k++) {
                if (k == randomProduct) {
                    addToCartButton.get(k).click();
                    isCartOkay = checkItemsInCart(amount);
                    backFromCartButton.click();
                }
            }
        } else {
            if (randomProduct2 == randomProduct) {
                while (randomProduct2 == randomProduct) {
                    randomProduct2 = (int) (0 + (Math.random() * (productItem.size())));    // перевизначаємо значення ID другого продукту якщо воно збігається з першим
                }
            }
            for (int k = 0; k < productItem.size(); k++) {
                if (k == randomProduct2) {
                    addToCartButton.get(k).click();
                    isCartOkay = checkItemsInCart(amount);
                    backFromCartButton.click();
                }
            }
        }
        if ((Integer.valueOf(amountOfProductsInCart.getText()).equals(amount) && (isCartOkay == amount))) {     //перевіряємо товари в кошику і цифру на icon "Cart"
            return amount;
        } else
            return 0;
    }

    public int checkItemsInCart(int amount) throws InterruptedException {
        goToCart.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        Thread.sleep(1500);                                             //пауза для візуального сприйняття
        return cartItems.size();
    }

    public int delete_item_from_cart() throws InterruptedException {
        goToCart.click();
        removeButton.get(0).click();
        Thread.sleep(1500);                                             //пауза для візуального сприйняття
        return cartItems.size();
    }
}
