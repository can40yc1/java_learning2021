package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("first", "middle", "last",
                "nick", "mister", "microsoft", "NY, Statue of Liberty", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHome();
    }

}
