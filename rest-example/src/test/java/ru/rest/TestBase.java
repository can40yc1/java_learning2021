package ru.rest;

import com.google.gson.JsonElement;
import io.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import static com.google.gson.JsonParser.parseString;

public class TestBase {

    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }


    public boolean isIssueOpen(int issueId) {
        try {
            String json = RestAssured.get("https://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
            JsonElement parsed = parseString(json);
            JsonElement issue = parsed.getAsJsonObject().get("issues");
            String stateName = issue.getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
            if (stateName.equals("Resolved") || stateName.equals("Closed")) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
