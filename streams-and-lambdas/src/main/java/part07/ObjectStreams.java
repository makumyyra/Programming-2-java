package part07;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Stream;

import person.Person;

/**
 * This class contains methods that handle streams of objects. You will learn
 * that using your own classes, such as Person, in streams is very similar to
 * using streams of built-in types, such as String and Integer.
 */
public class ObjectStreams {

    /**
     * Returns a list of the lengths of the strings in the specified stream.
     *
     * @param strings the stream of strings
     * @return a list of the lengths of the strings
     */
    public List<Integer> getLengths(Stream<String> strings) {
    	
    	List<Integer> lengths = strings
    			.map(s -> s.length())
    			.toList();
    	
    	
        return lengths; // TODO
    }

    /**
     * Returns a list of the ages of the people in the specified stream. Each
     * Person object has an 'age()' method that returns the age of that person.
     *
     * For example, if the stream contains the persons
     * [Person{name=Ada, age=20}, Person{name=Bob, age=30}, Person{name=Carol,
     * age=40}], this method returns the list [20, 30, 40].
     *
     * See the Person class for more information.
     *
     * @param persons the stream of Person objects
     * @return a list of the ages
     */
    public List<Integer> getAges(Stream<Person> persons) {
    	
    	
        // Hint: This method is very similar to the getLengths() method above!
    	List<Integer> ages = persons
    			.map((Person) -> Person.age())
    			.toList();
    	
        return ages; 
    }
    

    /**
     * Returns a list of the names of the persons in the specified stream. Each
     * Person object has a 'name()' method that returns the name of that person.
     *
     * For example, if the stream contains the persons
     * [Person{name=Ada, age=20}, Person{name=Bob, age=30}, Person{name=Carol,
     * age=40}], this method returns the list ["Ada", "Bob", "Carol"].
     *
     * @param persons the stream of Person objects
     * @return a list of the names
     */
    public List<String> getNames(Stream<Person> persons) {
    	
    	List<String> names = persons
    			.map((Person) -> Person.name())
    			.toList();
    	
        return names; 
    }

    /**
     * Returns the average age of the persons in the specified stream.
     * If the stream is empty, returns 0.
     *
     * @param persons the stream of persons
     * @return the average age of the persons or 0 if the stream is empty
     */
    public double getAverageAge(Stream<Person> persons) {
    	
    	OptionalDouble avg = persons
    			.mapToInt((Person) -> Person.age())
    			.average();        
    	
    	if(avg.isPresent()) {
    		return avg.getAsDouble();
    	} else {
    		return 0;
    	}
    	
    	
        /*
         * Hint: you can use the `getAges()` method above to get a stream of the ages.
         * You may remember `mapToInt()` and `average` from previous exercises.
         */
    }
}
