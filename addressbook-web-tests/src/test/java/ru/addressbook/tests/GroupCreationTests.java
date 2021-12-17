package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();

        Groups before = app.groupSteps().getAll();
        GroupData group = new GroupData().setName("test1");

        app.groupSteps().initGroupCreation();
        app.groupSteps().fillGroupForm(group);
        app.groupSteps().submitGroupCreation();
        app.groupSteps().returnToGroupPage();

        Groups after = app.groupSteps().getAll();

        Assert.assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(
                before.withAdded(group.setId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }

}
