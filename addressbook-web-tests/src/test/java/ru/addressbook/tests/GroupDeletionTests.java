package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groupSteps().getList().size() == 0) {
            app.groupSteps().create(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupDeletion() {

        List<GroupData> before = app.groupSteps().getList();
        int index = before.size() - 1;

        app.groupSteps().delete(index);

        List<GroupData> after = app.groupSteps().getList();

        before.remove(index);

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(after, before);
    }
}
