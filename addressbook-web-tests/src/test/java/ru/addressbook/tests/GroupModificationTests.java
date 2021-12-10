package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModofocation() {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        if (before.size() == 0) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
            before = app.getGroupHelper().getGroupList();
        }
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test10", "test20", "test30"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
    }
}
