package utils;

/**
 * This record class represents a commit in the git history. There is no logic
 * here, just data.
 *
 * In addition to storing each piece of data, the original commit text is also
 * stored. This is useful for printing the commits in the last part of the
 * exercise.
 */
public record Commit(String id, String contributor, String parentId, String message, String original) {
}
part00_CommitParser.java
package utils;

import java.util.List;
import java.util.stream.Stream;

/**
 * This class takes care of parsing the git history from a string into Commit
 * objects. The rest of the application does not need to know how the parsing
 * is done, which simplifies the rest of the application significantly.
 * Other parts of the application can just call the parse methods and get
 * plain old Java objects as a result.
 */
public class CommitParser {

    /**
     * Parses a single commit from the git history and returns a Commit object.
     * The returned object contains the commit id, the contributor, the parent id,
     * the commit message, and the original commit text.
     */
    public static Commit parseOne(String commitText) {
        int separatorIndex = commitText.indexOf("\n");

        // the first line is the commit id, the contributor, and the parent id
        String firstLine = commitText.substring(0, separatorIndex);

        // the message is everything after the first line
        String message = commitText.substring(separatorIndex + 1);

        /*
         * The words in the first line are indexed as follows:
         * Commit 1d9g4z by NewbieNate (Parent: e6c5b2)
         * __0___ ___1__ 2_ ____3_____ ___4____ ___5___
         */
        String[] chunks = firstLine.split(" ");

        String id = chunks[1];
        String contributor = chunks[3];
        String parentId = chunks[5].replaceAll("[^a-zA-Z0-9]", ""); // remove non-alphanumeric characters

        return new Commit(id, contributor, parentId, message, commitText);
    }

    /**
     * Parses many commits from the git history and returns a list of Commit
     * objects.
     *
     * @param commitHistory the git history as described in the assignment
     * @return a list of Commit objects
     */
    public static List<Commit> parseMany(String commitHistory) {
        if (commitHistory.isEmpty()) {
            return List.of();
        }

        // each commit is separated by an empty line
        String[] commits = commitHistory.split("\n\n");
        return Stream.of(commits).map(String::strip).map(CommitParser::parseOne).toList();
    }
}
part01_CommitIds.java
package part01;

import java.util.Collection;
import java.util.List;

import utils.Commit;
import utils.CommitParser;

public class CommitIds {

    /**
     * Returns the commit ids from the given commit log.
     *
     * @param commitLog a multi-line string containing the log of commits
     * @return a collection of commit ids
     */
    public Collection<String> getCommitIds(String commitLog) {
        List<Commit> commits = CommitParser.parseMany(commitLog);

        return commits.stream().map(Commit::id).toList();
    }
}
part02_Contributors.java
package part02;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Commit;
import utils.CommitParser;

public class Contributors {

    /**
     * Returns the contributors from the given commit log. See the previous exercise
     * or the readme file for more information about the format of the commit log.
     *
     * For example, for the commit log in the readme file, this method should return
     * the following usernames in any order:
     *
     * ["EagerElla", "LoopyLou", "NewbieNate", "ProgPete"]
     *
     * @param commitLog a multi-line string containing the log of commits
     * @return a collection of contributor usernames from the commit log
     */
    public Collection<String> getContributors(String commitLog) {
        List<Commit> commits = CommitParser.parseMany(commitLog);

        return commits.stream().map(Commit::contributor).distinct().toList();
    }

    /**
     * This method groups the commit ids by contributor. The format of the input is
     * the same as in the previous exercises. The output of this method is a map
     * where the keys are the usernames of the contributors in the log and the keys
     * are collections of commit ids that belong to the specific contributor.
     *
     * @param commitLog a multi-line string containing the log of commits
     * @return a map containing the commit ids grouped by contributor
     */
    public Map<String, Collection<String>> groupCommitIdsByContributors(String commitLog) {
        List<Commit> commits = CommitParser.parseMany(commitLog);
        Map<String, Collection<String>> grouped = new HashMap<>();

        this.getContributors(commitLog).forEach(contributor -> {
            List<String> idsForCurrent = commits.stream()
                    .filter(commit -> commit.contributor().equals(contributor))
                    .map(Commit::id)
                    .toList();

            grouped.put(contributor, idsForCurrent);
        });

        return grouped;
    }
}
part03_Main.java
package part03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Commit;
import utils.CommitParser;

public class Main {

    public static void main(String[] args) throws IOException {
        Path logFile = Path.of("pizza-commits.txt");
        String fileContents = Files.readString(logFile, StandardCharsets.UTF_8);

        List<Commit> commits = CommitParser.parseMany(fileContents);

        // This map holds the parent ids as keys and the corresponding commits as values
        Map<String, Commit> parentMap = new HashMap<>();
        commits.forEach(commit -> parentMap.put(commit.parentId(), commit));

        // The first commit has no parent, and is marked with the parent id "None"
        Commit current = parentMap.get("None");

        // We want to iterate through the commits proceeding from the first commit
        // to the next one, that has the current commit as its parent.
        while (current != null) {
            // We have stored the original text of the commit in the Commit object, so we
            // can just print it out and add a newline
            System.out.println(current.original() + "\n");

            // next we want to print the commit, that has the current commit as its parent
            current = parentMap.get(current.id());
        }
    }
}