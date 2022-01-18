package ru.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistraionHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistraionHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    }
}
