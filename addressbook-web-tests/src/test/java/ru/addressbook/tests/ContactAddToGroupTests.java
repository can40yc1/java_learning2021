package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.ContactData;
import ru.addressbook.model.Contacts;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contactSteps().create(new ContactData()
                    .withFirstname("first")
                    .withMiddlename("middle")
                    .withLastname("last")
                    .withNickname("nick")
                    .withTitle("mister")
                    .withCompany("microsoft")
                    .withAddress("NY, Statue of Liberty"));
        }
        if (!app.contactSteps().isThereAContactNotInGroup()) {
            app.goTo().groupPage();
            app.groupSteps().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testContactAddToGroup() {

        Contacts before = app.db().contacts();
        ContactData modifiedContact = app.contactSteps().getContactNotInGroup();
        Groups groups = app.db().groups();

        List<GroupData> availableGroups = new ArrayList<>();
        for (GroupData group : groups) {
            if (!modifiedContact.getGroups().contains(group)) {
                availableGroups.add(group);
            }
        }
        GroupData addGroup = availableGroups.iterator().next();

        app.goTo().homePage();
        app.contactSteps().addContactToGroup(modifiedContact, addGroup);

        Contacts beforeWithoutOldContact = before.without(modifiedContact);
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(beforeWithoutOldContact.withAdded(modifiedContact.inGroup(addGroup))));
    }


}
