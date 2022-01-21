package ru.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static com.google.gson.JsonParser.parseString;
import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

    @BeforeClass
    public void checkBlockingIssue() {
        skipIfNotFixed(1641);
    }

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("test test").withDescription("my test");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);

    }

    private Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json")).returnContent().asString();
        JsonElement parsed = parseString(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    private int createIssue(Issue issue) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json").bodyForm(
                        new BasicNameValuePair("subject", issue.getSubject()),
                        new BasicNameValuePair("description", issue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = parseString(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }
}
