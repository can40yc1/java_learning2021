package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

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
    public void testContactDeletion() throws InterruptedException {

        Contacts before = app.contactSteps().getAll();
        ContactData deletedContact = before.iterator().next();
        app.contactSteps().delete(deletedContact);
        Contacts after = app.contactSteps().getAll();

        Assert.assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
