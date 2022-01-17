package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.contactSteps().isThereAContactInAnyGroup()) {
            app.contactSteps().create(new ContactData()
                    .withFirstname("first")
                    .withMiddlename("middle")
                    .withLastname("last")
                    .withNickname("nick")
                    .withTitle("mister")
                    .withCompany("microsoft")
                    .withAddress("NY, Statue of Liberty"));
        }
    }

    @Test
    public void testContactDeleteFromGroup() {

        Contacts before = app.db().contacts();
        ContactData modifiedContact = app.contactSteps().getContactInGroup();
        GroupData group = modifiedContact.getGroups().stream().iterator().next();

        app.goTo().homePage();
        app.contactSteps().deleteContactFromGroup(modifiedContact, group);

        Contacts beforeWithoutOldContact = before.without(modifiedContact);
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(beforeWithoutOldContact.withAdded(modifiedContact.notInGroup(group))));
    }


}
