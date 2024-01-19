package exercise;

import java.util.List;

import model.Post;
import model.User;

public class Filtering {

    /**
     * Filters out deleted posts from the given list of posts. A post is considered
     * deleted if its deletedAt field is not null.
     *
     * @param posts list of posts to filter
     * @return a new list of posts that does not contain deleted posts
     */
    public static List<Post> filterOutDeletedPosts(List<Post> posts) {
    	
    	List<Post> filteredByDeleted = null;
    	
    	filteredByDeleted = 
    			posts.stream()
    			.filter(p -> (p.deletedAt() == null))
    			.toList();
    	
        return filteredByDeleted;
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
    	
    	List<Post> filteredByUser = null;
    	
    	filteredByUser = 
    			posts.stream()
    			.filter(p -> p.userId() == user.id())
    			.toList();
    	
        return filteredByUser;
    	
    	
       // return posts.subList(0, 10); // TODO: Implement manual filtering logic here.
    }
}
