package tests;

import manager.DataProviderUser;
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


    @Test (dataProvider = "registrationDataUser", dataProviderClass = DataProviderUser.class)
    public void registrationUser(User user){
        logger.info("Registration with valid data");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
        logger.info("Test success");
    }

    @Test(dataProvider ="registrationDataFile",dataProviderClass = DataProviderUser.class)
    public void registrationFile(User user){
        logger.info("Registration with valid data");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
        logger.info("Test success");
    }
    

    ///////////////////////

    @Test
    public void registrationSuccess(User user){
        logger.info("Registration with valid data");
       // Random random = new Random();
       // int i = random.nextInt(1000);
       // User user = new User().withName("Yuli").withLastName("Pokh").withEmail("yuli"+i+"@mail.com").withPassword("Juli12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
        logger.info("Test success");
    }
    @Test
    public void registrationWrongEmail(User user){

        logger.info("Registration with invalid data");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isErrorMessageContains("Wrong email format"));
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("wrong email");
    }
    @Test
    public void registrationWrongPassword(User user){
        logger.info("Registration with invalid data");


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