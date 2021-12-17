package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contactSteps().getAll().size() == 0) {
            app.contactSteps().create(new ContactData()
                    .withFirstname("first")
                    .withMiddlename("middle")
                    .withLastname("last")
                    .withNickname("nick")
                    .withTitle("mister")
                    .withCompany("microsoft")
                    .withAddress("NY, Statue of Liberty")
                    .withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contactSteps().getAll();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withFirstname("first1")
                .withMiddlename("middle1")
                .withLastname("last1")
                .withNickname("nick1")
                .withTitle("mister1")
                .withCompany("microsoft1")
                .withAddress("NY, Statue of Liberty1")
                .withId(modifiedContact.getId());

        app.contactSteps().modify(contact);
        Contacts after = app.contactSteps().getAll();

        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }


}
