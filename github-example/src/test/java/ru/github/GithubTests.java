package ru.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("can40yc1", "java_learning2021")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
