package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() throws InterruptedException {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("first", "middle", "last",
                    "nick", "mister", "microsoft", "NY, Statue of Liberty", "test1"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().confirmContactDeleting();
    }
}
