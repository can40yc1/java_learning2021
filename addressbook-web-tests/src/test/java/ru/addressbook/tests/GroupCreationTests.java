package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();

        List<GroupData> before = app.groupSteps().getList();
        GroupData group = new GroupData().setName("test1");

        app.groupSteps().initGroupCreation();
        app.groupSteps().fillGroupForm(group);
        app.groupSteps().submitGroupCreation();
        app.groupSteps().returnToGroupPage();

        List<GroupData> after = app.groupSteps().getList();

        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.add(group.setId(after.stream().max(byId).get().getId()));
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(after, before);
    }

}
