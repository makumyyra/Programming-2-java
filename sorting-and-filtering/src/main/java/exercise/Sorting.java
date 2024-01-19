package exercise;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Post;
import model.User;

public class Sorting {

    /**
     * Returns a new List with the Post objects in the given list sorted by their
     * publishedAt date in ascending order (oldest first).
     *
     * This method does not modify the given list.
     *
     * @param posts list of posts to sort
     * @return a new list of posts sorted by their published date in ascending order
     */
    public static List<Post> sortPostsByPublishedDate(List<Post> posts) {
        
    	List<Post> sorted = new ArrayList<>();
    	sorted.addAll(0, posts);
    	    	
    	postQuickSort(sorted, 0, sorted.size()-1);
 	   	
        return sorted; // TODO: return a new list with the posts sorted
    }
    
    public static void postQuickSort(List<Post> posts, int start, int end) {
    	
    	if (start >= end) {
    		return;
    	}
    	
    	Post pivot = posts.get(end);
    	
    	//int leftPointer = partition(posts, start, end, pivot);
  
    	
    	int leftPointer = start;
    	int rightPointer = end;    	

    	String pivotTime = pivot.publishedAt();
    	
    	while(leftPointer < rightPointer) {    		
    		
    		//leftPointer time is smaller:
    		while (posts.get(leftPointer)
    				.publishedAt()
    				.compareTo(pivotTime) < 0 //false = is smaller than pivot
    				&& leftPointer < rightPointer) 
    		{
    			leftPointer++;
    		}
    		
//    		if (posts.get(leftPointer)
//    				.publishedAt()
//    				.compareTo(pivotTime) == 0
//    				&& leftPointer < rightPointer) 
//    		{
    		// siirrä pivotin oikealle(?) puolelle, älä kasvata leftPointeria?
    		//ja sama oikealle?
//    		}
    		
    		//rightPointer time is bigger:
    		while (posts.get(rightPointer)
    				.publishedAt()
    				.compareTo(pivotTime) > 0 //true = is bigger than pivot
    				&& leftPointer < rightPointer) 
    		{
    			rightPointer--;
    		}
    		 
   		
    		swap(posts, leftPointer, rightPointer);    		
    	}
 	
    	postQuickSort(posts, start, leftPointer -1);
    	
    	//postQuickSort(posts, leftPointer +1, end); //tämän voi kai poistaa

    }

//	private static int partition(List<Post> posts, int start, int end, Post pivot) {
//		
//		return leftPointer;
//	}


	
	private static void swap(List<Post> posts, int index1, int index2) {
		Post temp = posts.get(index1);
		posts.set(index1, posts.get(index2));
		posts.set(index2, temp);
	}
    



/**
 * Returns a new list with the given users sorted by their registration date in
 * ascending order (oldest first).
 *
 * This method does not modify the given list.
 *
 * @param users list of users to sort
 * @return a new list of users sorted by their registration date
 */
	public static List<User> sortUsersByRegistrationDate(List<User> users) {
	/*
     * Note that each User can have different data types for `registeredAt` field:
     * - numeric (seconds since epoch), for example "1632225600"
     * - string (ISO 8601), for example "2021-09-21T12:00:00Z"
     *
     * This time a simple alphabetical ordering of the string values is not enough.
     */
	
	return users; // TODO: You are allowed to use existing implementations.
	}

}


//
//private static Instant formatDate(String s) {
////	DateTimeFormatter df = DateTimeFormatter.ISO_INSTANT;
//	Instant instant = Instant.parse(s);
//	
////	return Instant.from(df.parse(s));
//	return instant;
//}