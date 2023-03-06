package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager {
    WebDriver wd;
    HelperUser helperUser;
    HelperCar helperCar;
    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    public void init() {
            if (browser.equals(Browser.CHROME.browserName()))
            {
                wd = new ChromeDriver();
                logger.info("All tests start in Chrome Browser");
            }else if(browser.equals(Browser.FIREFOX.browserName())){
                wd=new FirefoxDriver();
                logger.info("All tests start in Firefox Browser");
            }else if(browser.equals(Browser.IE.browserName())){
                wd=new InternetExplorerDriver();
            }


        WebDriverListener listener = new ListenerWD();
        wd=new EventFiringDecorator<>(listener).decorate(wd);

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wd.navigate().to("https://ilcarro.web.app");
        helperUser= new HelperUser(wd);
        helperCar=new HelperCar(wd);


    }

    public void stop() {
        wd.quit();
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperCar getHelperCar() {
        return helperCar;
    }
}