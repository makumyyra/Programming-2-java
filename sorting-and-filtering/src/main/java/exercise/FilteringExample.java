exercise_Filtering.java
package exercise;

import java.util.List;

import model.Post;
import model.User;

public class FilteringExample {

    /**
     * Filters out deleted posts from the given list of posts. A post is considered
     * deleted if its deletedAt field is not null.
     *
     * @param posts list of posts to filter
     * @return a new list of posts that does not contain deleted posts
     */
    public static List<Post> filterOutDeletedPosts(List<Post> posts) {
        return posts.stream().filter(p -> p.deletedAt() == null).toList();

        // Alternative solution, by using the new isActive() method in Post class:
        // return posts.stream().filter(Post::isActive).toList();
    }

    /**
     * Filters out posts that are not written by the given user. A post is
     * considered written by the user if its userId field matches the user's id.
     *
     * @param user  the user to filter posts by
     * @param posts list of posts to filter
     * @return a new list of posts that only contains posts written by the user
     */
    public static List<Post> filterPostsByUser(User user, List<Post> posts) {
        return posts.stream().filter(p -> p.userId() == user.id()).toList();
    }
}
exercise_Sorting.java
package exercise;

import java.util.ArrayList;
import java.util.List;

import model.Post;
import model.User;

public class Sorting {

    /**
     * Sorts the given list of posts by their publishedAt date in ascending order
     * (oldest first).
     *
     * @param posts list of posts to sort
     * @return a new list of posts sorted by their published date in ascending order
     */
    public static List<Post> sortPostsByPublishedDate(List<Post> posts) {
        return quickSortPosts(posts);
    }

    /**
     * Sorts the given list of posts by their publishedAt date in ascending order
     * (oldest first). This is a recursive implementation of the quick sort
     * algorithm, which means that it will solve a part of the problem and then
     * "recursively" call itself to solve the rest of the problem.
     *
     * This is not the most efficient implementation of the quick sort algorithm,
     * but it's easy to understand and it's good enough for this exercise. Other
     * implementations may utilize a single list instead of continuously creating
     * new lists, which reduces the memory usage significantly. You can read an
     * explanation of the approach in this method at, for example,
     * https://stackoverflow.com/a/41211360.
     */
    private static List<Post> quickSortPosts(List<Post> posts) {
        if (posts.size() <= 1) {
            return posts; // already sorted!
        }

        /*
         * We take the first post (pivot) and split the rest of the posts into two
         * lists. The "left" list contains posts that are older than the pivot post and
         * the "right" list contains posts that are newer than the pivot post. Both
         * "sublists" are still in random order, so we need to make new calls to
         * continue sorting them recursively.
         */
        Post pivot = posts.get(0);
        List<Post> rest = posts.subList(1, posts.size());

        List<Post> left = new ArrayList<>();
        List<Post> right = new ArrayList<>();

        for (Post current : rest) {
            // The timestamps are in ISO format in the same timezone, so we can compare
            // them in alphabetical order:
            if (current.publishedAt().compareTo(pivot.publishedAt()) < 0) {
                left.add(current);
            } else {
                right.add(current);
            }
        }

        // Recursively sort both sublist:
        left = sortPostsByPublishedDate(left);
        right = sortPostsByPublishedDate(right);

        // Combine the sorted sublists and the pivot post:
        List<Post> sorted = new ArrayList<Post>(left);
        sorted.add(pivot);
        sorted.addAll(right);
        return sorted;
    }

    /**
     * Sorts the given list of users by their registration date in ascending order
     * (oldest first).
     *
     * Note that each User can have different data types for `registeredAt` field:
     * - numeric (seconds since epoch), for example "1632225600"
     * - string (ISO 8601), for example "2021-09-21T12:00:00Z"
     *
     * @param users list of users to sort
     * @return a new list of users sorted by their registration date
     */
    public static List<User> sortUsersByRegistrationDate(List<User> users) {
        // See the new normalizeRegisteredAt method in the User class. We can use it
        // here so that we don't need to worry about the different data types.
        // Instead, we just stream the users and sort them by the normalized date:
        return users.stream()
                .sorted((u1, u2) -> u1.normalizeRegisteredAt().compareTo(u2.normalizeRegisteredAt()))
                .toList();

        /*
         * Alternatively, we could use the Comparator.comparing() method in the
         * java.util.Comparator class and pass in a method reference to our method,
         * which the `comparing` method would call for each user to get the values to
         * compare:
         *
         * return users.stream()
         * .sorted(Comparator.comparing(User::normalizeRegisteredAt))
         * .toList();
         */
    }
}
model_Post.java
package model;

/**
 * This class represents Posts.
 *
 * The class is implemented as a Record, which is a new feature in Java 16.
 * The Record is a compact class that is used to declare classes that are used
 * mainly for storing data.
 *
 * You don't need to understand the details of the Record class, it's enough
 * that you know that it's a class that has a set of fields and
 * that it has a constructor that takes the values for thos fields as
 * parameters.
 *
 * The attributes can be accessed using the automatically generated "getter"
 * methods such as id() and title(). The Record class also has automatically
 * generated toString(), equals() and hashCode() methods.
 *
 * See more at https://dev.java/learn/records/
 */
public record Post(
        long id,
        String title,
        String body,
        long userId,

        /** Time of publishing in ISO format, for example "2023-04-10T09:45:00Z" */
        String publishedAt,

        /**
         * Optional time of deletion in ISO format, for example "2023-04-10T09:45:00Z"
         */
        String deletedAt) {

    /**
     * Returns true if the post is active, that is, it has not been deleted.
     */
    public boolean isActive() {
        return this.deletedAt == null;
    }
}
model_User.java
package exercise;

import java.time.Instant;

/**
 * This class represents Users.
 *
 * The class is implemented as a Record, which is a new feature in Java 16.
 * The Record is a compact class that is used to declare classes that are used
 * mainly for storing data.
 *
 * You don't need to understand the details of the Record class, it's enough
 * that you know that it's a class that has a set of fields and
 * that it has a constructor that takes the values for thos fields as
 * parameters.
 *
 * The attributes can be accessed using the automatically generated "getter"
 * methods such as id() and firstName(). The Record class also has automatically
 * generated toString(), equals() and hashCode() methods.
 *
 * See more at https://dev.java/learn/records/
 */
public record User(
        long id,
        String firstName,
        String lastName,
        String username,
        String registeredAt) {

    /**
     * This utility method returns a "normalized" value from the registeredAt
     * field so that it can be used without worrying about the different data types.
     *
     * "Users who registered through our mobile app have a numeric value
     * representing the epoch timestamp (in seconds), while users who registered
     * through the web app have a date string in ISO format"
     */
    public Instant normalizeRegisteredAt() {
        try {
            // Try parsing the registeredAt field as a number (epoch timestamp in seconds):
            long seconds = Long.parseLong(registeredAt);
            return Instant.ofEpochSecond(seconds);

        } catch (NumberFormatException e) {
            // Parsing as a number failed, parse it as an ISO timestamp instead:
            return Instant.parse(registeredAt);
        }
    }
}