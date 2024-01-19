package part02;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import part01.Commit;

public class Contributors {

    /**
     * Returns the contributors from the given commit log. See the previous exercise
     * or the readme file for more information about the format of the commit log.
     *
     * The returned collection must not contain duplicates. The order of the
     * contributors in the returned collection is not important. If the given commit
     * log is empty, the method should return an empty collection.
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
    	
    	String[] commits = commitLog.split("\n\n");
    	Set<String> contributors = new HashSet<>();
    	
    	if (commits.length != 0) {
	    	for (String commit : commits) {
	    		
	    		if(commit.length()!= 0) { //tässä löytyy oikeat kolme
	    			
	    			Commit c = new Commit(commit);
		    		String contributor = c.getContributor();
		    		contributors.add(contributor);
	    		}
	    	}
    	}
    	// Like in the previous exercise, you need to return an instance of some class
        // that implements the Collection interface. The choice is yours.
        return contributors;
    }

    /**
     * This method groups the commit ids by contributor. The format of the input is
     * the same as in the previous exercises. The output of this method is a map
     * where the keys are the usernames of the contributors in the log and the keys
     * are collections of commit ids that belong to the specific contributor.
     * The order of the usernames and ids in the returned map is not important.
     *
     * For example, for the commit log in the readme file, this method should return
     * a map containing the following usernames and ids in any order:
     *
     * {
     * "EagerElla": ["4f2a1d", "8h5k2y"],
     * "LoopyLou": ["e6c5b2", "j7i2k9"],
     * "NewbieNate": ["7b9f1e", "1d9g4z", "o1z6x9"],
     * "ProgPete": ["a3d8e7", "m3n5p8"]
     * }
     *
     * @param commitLog a multi-line string containing the log of commits
     * @return a map containing the commit ids grouped by contributor
     */
    public Map<String, Collection<String>> groupCommitIdsByContributors(String commitLog) {
    	
    	Map<String, Collection<String>> contributorCommits = new HashMap<>();
    	Collection<String> commitsByContributor; 
    	
    	String[] commits = commitLog.split("\n\n");
    	
    	for (String commit : commits) {
    		if(commit.length()!= 0) {     			
    			Commit c = new Commit(commit);
	    		String contributor = c.getContributor();
	    		String commitId = c.getCommitId();
	    		contributorCommits.computeIfAbsent(contributor, k -> new HashSet<String>()).add(commitId);
	    					
	    					/*Or to implement a multi-value map, Map<K,Collection<V>>,supporting multiple values per key: 
	    						 map.computeIfAbsent(key, k -> new HashSet<V>()).add(v);

	    						The mapping function should not modify this map during computation.
	    						Parameters:key key with which the specified value is to be associatedmappingFunction the mapping function to compute a valueReturns:the current (existing or computed) value associated withthe specified key, or null if the computed value is nullThrows:NullPointerException - if the specified key is null andthis map does not support null keys, or the mappingFunctionis nullUnsupportedOperationException - if the put operationis not supported by this map(optional)ClassCastException - if the class of the specified key or valueprevents it from being stored in this map(optional)IllegalArgumentException - if some property of the specified keyor value prevents it from being stored in this map(optional)Since:1.8Impl Spec:The default implementation is equivalent to the following steps for this map, then returning the current value or null if nowabsent:  if (map.get(key) == null) {
	    						     V newValue = mappingFunction.apply(key);
	    						     if (newValue != null)
	    						         map.put(key, newValue); */
	    	}
	     }
    		
        return contributorCommits;
    }
}
