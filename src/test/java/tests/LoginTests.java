package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getUser().isLogged()){
            app.getUser().logOut();
        }
    }

    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .name("Galina")
                .lastName("Smith")
                .email("galina@gmail.com")
                .password("Gg123456$")
                .build();

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginButton();
        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @Test
    public void loginNegativeTestWrongEmail(){
        User user = User.builder()
                .name("Galina")
                .lastName("Smith")
                .email("galinagmail.com")
                .password("Gg123456$")
                .build();

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginButton();
        Assert.assertTrue(app.getUser().isLoggedFailed());
    }

    @AfterMethod
    public void postCondition(){
        app.getUser().clickOkButton();
    }
}
