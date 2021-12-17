package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groupSteps().getAll().size() == 0) {
            app.groupSteps().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.groupSteps().getAll();
        GroupData deletedGroup = before.iterator().next();
        app.groupSteps().delete(deletedGroup);

        assertThat(app.groupSteps().count(), equalTo(before.size() - 1));

        Groups after = app.groupSteps().getAll();
        assertThat(after, equalTo(before.without(deletedGroup)));
    }
}
