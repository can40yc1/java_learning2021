package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groupSteps().getAll().size() == 0) {
            app.groupSteps().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModofocation() {
        Groups before = app.groupSteps().getAll();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withName("test10")
                .withHeader("test20")
                .withFooter("test30")
                .withId(modifiedGroup.getId());
        app.groupSteps().modify(group);

        assertThat(app.groupSteps().count(), equalTo(before.size()));

        Groups after = app.groupSteps().getAll();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }
}
