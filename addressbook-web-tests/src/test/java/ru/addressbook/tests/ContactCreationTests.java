package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("first", "middle", "last",
                "nick", "mister", "microsoft", "NY, Statue of Liberty", "test1");

        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();

        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.add(contact.setId(after.stream().max(byId).get().getId()));

        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(after, before);
    }
}
