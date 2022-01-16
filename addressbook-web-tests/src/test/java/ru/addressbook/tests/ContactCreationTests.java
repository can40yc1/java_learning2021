package ru.addressbook.tests;

import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @Test()
    public void testContactCreation() {

        File photo = new File("src/test/resources/photo1.png");
        Contacts before = app.contactSteps().getAll();
        ContactData contact = new ContactData()
                .withFirstname("first")
                .withMiddlename("middle")
                .withLastname("last")
                .withNickname("nick")
                .withTitle("mister")
                .withCompany("microsoft")
                .withAddress("NY, Statue of Liberty")
                .withGroup("test1")
                .withPhoto(photo);

        app.contactSteps().create(contact);
        Contacts after = app.contactSteps().getAll();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}
