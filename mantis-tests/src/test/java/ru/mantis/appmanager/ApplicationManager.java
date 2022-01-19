package ru.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;
    private String browser;
    private RegistraionHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private PasswordChangeHelper passwordChangeHelper;
    private DbHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {

        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        dbHelper = new DbHelper();
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistraionHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistraionHelper(this);
        }
        return registrationHelper;
    }

    public PasswordChangeHelper passwordChange() {
        if (passwordChangeHelper == null) {
            passwordChangeHelper = new PasswordChangeHelper(this);
        }
        return passwordChangeHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(Browser.CHROME.browserName())) {
                System.setProperty("webdriver.chrome.driver", "chromedriver");
                wd = new ChromeDriver();
            } else if (browser.equals(Browser.FIREFOX.browserName())) {
                System.setProperty("webdriver.firefox.driver", "geckodriver");
                wd = new FirefoxDriver();
            }
            wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }
}
