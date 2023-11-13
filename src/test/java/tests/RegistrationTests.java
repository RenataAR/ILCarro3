package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getUser().isLogged()){
            app.getUser().logOut();
        }
    }

    @Test
    public void registrationPositiveTest(){
        int i = (int)((System.currentTimeMillis()/1000)%3600);
        User user = User.builder()
                .name("Galina")
                .lastName("Smith")
                .email("galina" + i + "@gmail.com")
                .password("Gg123456$")
                .build();

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().clickCheckbox();
        app.getUser().submitRegistrationButton();
        Assert.assertTrue(app.getUser().isRegisteredSuccess());
    }

    @Test
    public void registrationNegativeTestWrongEmail(){
        int i = (int)((System.currentTimeMillis()/1000)%3600);
        User user = User.builder()
                .name("Galina")
                .lastName("Smith")
                .email("galina" + i + "gmail.com")
                .password("Gg123456$")
                .build();

        app.getUser().openRegistrationForm();
        app.getUser().fillRegistrationForm(user);
        app.getUser().clickCheckbox();
        app.getUser().submitRegistrationButton();
        Assert.assertTrue(app.getUser().isRegistrationFailed());
    }

    @AfterMethod
    public void postCondition(){
        app.getUser().clickOkButton();
    }
}
