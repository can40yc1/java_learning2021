package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {

        Contacts before = app.contactSteps().getAll();
        ContactData contact = new ContactData()
                .withFirstname("first")
                .withMiddlename("middle")
                .withLastname("last")
                .withNickname("nick")
                .withTitle("mister")
                .withCompany("microsoft")
                .withAddress("NY, Statue of Liberty")
                .withGroup("test1");

        app.contactSteps().create(contact);
        Contacts after = app.contactSteps().getAll();

        Assert.assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}
