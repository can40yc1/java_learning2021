package ru.addressbook.tests;

import jdk.dynalink.linker.LinkerServices;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("first", "middle", "last",
                    "nick", "mister", "microsoft", "NY, Statue of Liberty", "test1"));
        }

        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "first1", "middle1", "last1",
                "nick1", "mister1", "microsoft1", "NY, Statue of Liberty1", null);

        app.getContactHelper().initContactModification(index);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactpModification();
        app.getContactHelper().returnToHome();

        List<ContactData> after = app.getContactHelper().getContactList();

        before.remove(index);
        before.add(contact);
        before.sort(Comparator.comparingInt(ContactData::getId));
        after.sort(Comparator.comparingInt(ContactData::getId));

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(after, before);
    }
}
