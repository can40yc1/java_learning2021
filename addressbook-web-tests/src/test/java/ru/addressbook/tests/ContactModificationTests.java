package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("first1", "middle1", "last1",
                "nick1", "mister1", "microsoft1", "NY, Statue of Liberty1"));
        app.getContactHelper().submitContactpModification();
        app.getContactHelper().returnToHome();
    }
}
