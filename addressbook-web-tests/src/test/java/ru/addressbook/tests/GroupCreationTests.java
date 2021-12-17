package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();

        Set<GroupData> before = app.groupSteps().getAll();
        GroupData group = new GroupData().setName("test1");

        app.groupSteps().initGroupCreation();
        app.groupSteps().fillGroupForm(group);
        app.groupSteps().submitGroupCreation();
        app.groupSteps().returnToGroupPage();

        Set<GroupData> after = app.groupSteps().getAll();

        before.add(group.setId(after.stream().mapToInt(GroupData::getId).max().getAsInt()));

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(after, before);
    }

}
