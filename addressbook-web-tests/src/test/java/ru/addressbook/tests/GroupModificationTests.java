package ru.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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
        Groups before = app.groupSteps().getAll();

        GroupData modifiedGroup = before.iterator().next();

        GroupData group = new GroupData()
                .setName("test10")
                .setFooter("test20")
                .setFooter("test30")
                .setId(modifiedGroup.getId());

        app.groupSteps().modify(group);

        Groups after = app.groupSteps().getAll();

        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }
}
