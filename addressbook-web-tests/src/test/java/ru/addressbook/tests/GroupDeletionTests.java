package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().goToGroupPage();

        List<GroupData> before = app.getGroupHelper().getGroupList();

        if (before.size() == 0) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
            before = app.getGroupHelper().getGroupList();
        }
        int index = before.size() - 1;

        app.getGroupHelper().selectGroup(index);
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();

        before.remove(index);

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(after, before);
    }

}
