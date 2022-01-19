package ru.mantis.appmanager;

import org.openqa.selenium.By;

import java.io.IOException;

public class PasswordChangeHelper extends HelperBase {

    public PasswordChangeHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username) throws IOException {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[value='Войти']"));
        click(By.linkText("управление"));
        click(By.linkText("Управление пользователями"));
        click(By.linkText(username));
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    public void finish(String passwordChangeLink, String password) {
        wd.get(passwordChangeLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Изменить учетную запись']"));
    }
}
