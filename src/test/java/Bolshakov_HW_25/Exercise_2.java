package Bolshakov_HW_25;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Exercise_2 extends BaseTestClass {

    @Test(testName = "Додавання продуктів в корзину і їх видалення")
    public void Add_items_to_cart() throws InterruptedException {

        LoginPage OurLoginPage = new LoginPage(driver);
        ProductPage OurProductPage = new ProductPage(driver);
        SoftAssert asert = new SoftAssert();

        OurLoginPage.InsertUsername(Users_data.SET1.getUsername());
        OurLoginPage.InsertUserpass(Users_data.SET1.getPassword());
        OurLoginPage.LogIn();

        asert.assertEquals(OurProductPage.checkProducts(), 6);                                        // перевірка кількості товарів на сторінці
        int randomProduct = (int) (Math.random() * (OurProductPage.checkProducts() - 1));
        asert.assertEquals(OurProductPage.addProductsToCart(randomProduct, true), 1);        // перевірка , що тільки 1 товар в кошику
        asert.assertEquals(OurProductPage.addProductsToCart(randomProduct, false), 2);      // перевірка , що додалося 2 товара

        asert.assertEquals(OurProductPage.delete_item_from_cart(), 1);                              //Видаляємо другий доданий продукт і перевіряємо кількість товарів в корзині

        OurLoginPage.LogOut();
        asert.assertAll();
    }
}
