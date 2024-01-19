package part06;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A predicate is a function that returns a boolean value. Predicates are often
 * used to check if the stream itself or the elements in the stream pass a
 * certain check. For example, you can check if all the elements in a stream are
 * positive numbers.
 */
public class PredicatesWithStreams {

    /**
     * Returns true if all the numbers in the specified stream are positive. Zero is
     * not considered positive in this method.
     *
     * @param numbers the stream of numbers to check
     * @return true if all the numbers in the stream are over zero
     */
    public boolean allPositive(IntStream numbers) {
    	
    	return numbers.allMatch(number -> (number > 0) );
    	
        // hint: see the `allMatch()` method
        
    }

    /**
     * Returns the first positive number in the specified stream. Zero is not
     * considered positive in this method. If the stream does not contain any
     * positive numbers, returns 0.
     *
     * @param numbers the stream of numbers to check
     * @return the first positive number in the stream or 0 if there are no positive
     */
    public int getFirstPositive(IntStream numbers) {
    	
    	int firstPositive = numbers
    			.filter(number -> (number > 0))
    			.findFirst()
    			.orElse(0);
        /*
         * hint: you can chain the `filter()`, `findFirst()` and `isPresent()` methods.
         */
        return firstPositive; 
    }
    /**
     * Returns true if the specified stream contains no null values.
     *
     * @param strings the stream of strings to check
     * @return true if the stream contains no null values
     */
    public boolean containsNoNulls(Stream<String> strings) {
    	
    	boolean noNulls = strings
    			.noneMatch(s -> (s == null));
    	
        // hint: see the `noneMatch()` method
        return noNulls; // TODO
    }
}
