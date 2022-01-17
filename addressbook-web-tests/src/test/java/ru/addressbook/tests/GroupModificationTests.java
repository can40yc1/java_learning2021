package ru.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.groupSteps().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModofocation() {
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withName("test10")
                .withHeader("test20")
                .withFooter("test30")
                .withId(modifiedGroup.getId());
        app.goTo().groupPage();
        app.groupSteps().modify(group);

        assertThat(app.groupSteps().count(), equalTo(before.size()));

        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }
}
