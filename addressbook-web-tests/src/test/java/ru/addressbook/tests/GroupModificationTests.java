package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groupSteps().getList().size() == 0) {
            app.groupSteps().create(new GroupData("test1", null, null));
        }
    }

    @Test
    public void testGroupModofocation() {
        List<GroupData> before = app.groupSteps().getList();
        int index = before.size() - 1;
        GroupData group = new GroupData("test10", "test20", "test30", before.get(index).getId());

        app.groupSteps().modify(index, group);

        List<GroupData> after = app.groupSteps().getList();

        before.remove(index);
        before.add(group);

        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(after, before);
    }
}
