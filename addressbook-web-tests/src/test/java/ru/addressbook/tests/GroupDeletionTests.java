package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groupSteps().getAll().size() == 0) {
            app.groupSteps().create(new GroupData().setName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {

        Set<GroupData> before = app.groupSteps().getAll();
        int index = before.size() - 1;
        GroupData deletedGroup = before.iterator().next();

        app.groupSteps().delete(deletedGroup);

        Set<GroupData> after = app.groupSteps().getAll();

        before.remove(deletedGroup);

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(after, before);
    }
}
