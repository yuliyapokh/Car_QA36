package tests;

import manager.DataProviderUser;
import manager.ListenerTNG;
import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerTNG.class)

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("Logout success");

        }

    }


    @Test(dataProvider = "loginDataUser",dataProviderClass = DataProviderUser.class)
    public void loginSuccessUser(User user){

        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("Test success");
    }

    @Test(dataProvider = "loginDataFile",dataProviderClass = DataProviderUser.class)
    public void loginSuccessFile(User user){

        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("Test success");
    }


/////////////////////////




    @Test(groups = {"smoke"})
    public void loginSuccess(){
        logger.info("Login with valid data: email 'pokh@i2.ua'  & password 'Yyp12345!'");
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm("pokh@i2.ua","Yyp12345!");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("Test success");
    }
    @Test
    public void loginSuccessModel(User user){

        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("Test success");
    }

    @Test
    public void loginWrongEmail(User user){
        logger.info("Login with invalid data: email 'pokhi2.ua'  & password 'Yyp12345!'");


        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(),"It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Wrong email!!");
    }
    @Test
    public void loginWrongPassword(User user){


        logger.info("Login with invalid data: email 'pokh@i2.ua'  & password '12345'");

        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");
        logger.info("TWrong password!!!");
    }
    @Test (enabled = false)
    public void loginUnregisterUser(){

    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeDialogContainer();
    }
}