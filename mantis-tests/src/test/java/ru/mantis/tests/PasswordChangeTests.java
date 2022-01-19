package ru.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.mantis.model.MailMessage;
import ru.mantis.model.UserData;
import ru.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordChange() throws IOException, MessagingException {

        Users users = app.db().users();
        UserData user = users.stream().iterator().next();
        while (user.getUserName().equals(app.getProperty("web.adminLogin"))) {
            users.remove(user);
            user = users.stream().iterator().next();
        }
        String username = user.getUserName();
        long now = System.currentTimeMillis();
        String newPassword = String.valueOf(now);

        app.passwordChange().start(username);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String passwordChangeLink = findPasswordChangeLink(mailMessages, user.getEmail());
        app.passwordChange().finish(passwordChangeLink, newPassword);
        assertTrue(app.newSession().login(username, newPassword));
    }

    private String findPasswordChangeLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
