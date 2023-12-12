package Bolshakov_HW_25;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Exercise_2 extends BaseTestClass {

    @Test(testName = "Перевірка продуктів на сторнці")
    public void Login_with_not_valid_data() {

        LoginPage OurLoginPage = new LoginPage(driver);
        ProductPage OurProductPage = new ProductPage(driver);
        SoftAssert asert = new SoftAssert();

        OurLoginPage.InsertUsername(Users_data.SET1.getUsername());
        OurLoginPage.InsertUserpass(Users_data.SET1.getPassword());
        OurLoginPage.LogIn();

        //OurProductPage.checkProducts();
        asert.assertEquals(OurProductPage.checkProducts(), 6);

        asert.assertAll();
    }
}
