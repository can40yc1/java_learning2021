package ru.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groupSteps().getAll().size() == 0) {
            app.groupSteps().create(new GroupData().setName("test1"));
        }
    }

    @Test
    public void testGroupModofocation() {
        Set<GroupData> before = app.groupSteps().getAll();

        GroupData modifiedGroup = before.iterator().next();

        GroupData group = new GroupData()
                .setName("test10")
                .setFooter("test20")
                .setFooter("test30")
                .setId(modifiedGroup.getId());

        app.groupSteps().modify(group);

        Set<GroupData> after = app.groupSteps().getAll();

        before.remove(modifiedGroup);
        before.add(group);

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(after, before);
    }
}
