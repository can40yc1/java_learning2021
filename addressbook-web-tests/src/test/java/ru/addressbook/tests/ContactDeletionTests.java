package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws InterruptedException {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("first", "middle", "last",
                    "nick", "mister", "microsoft", "NY, Statue of Liberty", "test1"));
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;

        app.getContactHelper().selectContact(index);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().confirmContactDeleting();
        app.getContactHelper().returnToHome();

        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(before.get(index));

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(after, before);
    }
}
