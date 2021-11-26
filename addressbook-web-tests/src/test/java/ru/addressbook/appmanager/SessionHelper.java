package ru.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password, String url) {
        wd.get(url);
        type(By.name("user"), username);
        type(By.name("pass"), password);
        submit(By.id("LoginForm"));
    }
}
