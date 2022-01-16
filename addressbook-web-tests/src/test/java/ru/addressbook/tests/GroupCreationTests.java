package ru.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.addressbook.model.GroupData;
import ru.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[] {new GroupData().withName(split[0]).withFooter(split[1]).withHeader(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.groupSteps().getAll();
        app.groupSteps().create(group);
        assertThat(app.groupSteps().count(), equalTo(before.size() + 1));

        Groups after = app.groupSteps().getAll();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }


    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.groupSteps().getAll();
        GroupData group = new GroupData().withName("test1'");
        app.groupSteps().create(group);

        assertThat(app.groupSteps().count(), equalTo(before.size()));

        Groups after = app.groupSteps().getAll();
        assertThat(after, equalTo(before));
    }

}
