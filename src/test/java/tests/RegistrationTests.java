package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Logout success");

        }

    }
    @Test
    public void registrationSuccess(){
        logger.info("Registration with valid data");
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().withName("Yuli").withLastName("Pokh").withEmail("yuli"+i+"@mail.com").withPassword("Juli12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
        logger.info("Test success");
    }
    @Test
    public void registrationWrongEmail(){

        logger.info("Registration with invalid data: name 'Yuli', lastname 'Pokh,  email'yulimail.com', pass 'Juli12345$'");

        User user = new User().withName("Yuli").withLastName("Pokh").withEmail("yulimail.com").withPassword("Juli12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isErrorMessageContains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("wrong email");
    }
    @Test
    public void registrationWrongPassword(){
        logger.info("Registration with invalid data: name 'Yuli', lastname 'Pokh,  email'yuli@mail.com', pass 'Ju'");

        User user = new User().withName("Yuli").withLastName("Pokh").withEmail("yuli@mail.com").withPassword("Ju");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isErrorMessageContains("Password must contain"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("wrong password");


    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeDialogContainer();
    }
}