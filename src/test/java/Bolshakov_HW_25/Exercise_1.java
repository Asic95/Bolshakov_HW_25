package Bolshakov_HW_25;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Exercise_1 extends BaseTestClass {

    @Test(testName = "Перевірка валідних даних юзерів")
    public void Login_with_valid_data() throws InterruptedException {

        LoginPage OurLoginPage = new LoginPage(driver);
        SoftAssert asert = new SoftAssert();

        for (Users_data userData : Users_data.values()) {
            if (userData.getUsername().equals("locked_out_user")) {
                continue;
            } else {
                OurLoginPage.InsertUsername(userData.getUsername());
                OurLoginPage.InsertUserpass(userData.getPassword());
                OurLoginPage.LogIn();
                Thread.sleep(1500);                             //затримка для візуального сприйняття роботи тесту
                asert.assertTrue(OurLoginPage.checkLogIn());
                OurLoginPage.LogOut();
            }

        }
        asert.assertAll();
    }

    @Test(testName = "Перевірка невалідних даних юзерів")
    public void Login_with_not_valid_data() {

        LoginPage OurLoginPage = new LoginPage(driver);

        OurLoginPage.InsertUsername(Users_data.SET2.getUsername());
        OurLoginPage.InsertUserpass(Users_data.SET2.getPassword());
        OurLoginPage.LogIn();
        Assert.assertTrue(OurLoginPage.CheckValidationMessage());
    }
}
