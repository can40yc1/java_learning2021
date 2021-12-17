package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhone() {
        app.goTo().homePage();
        ContactData contact = app.contactSteps().getAll().iterator().next();
        ContactData contactInfoFromEditForm = app.contactSteps().infoFromEditForm(contact);
    }
}
