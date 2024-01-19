package part02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Your task is to implement the methods in this class. Use the JUnit test
 * provided to verify that your implementation works as expected. You can also
 * write a main method to test your implementations.
 *
 * Do not change the signatures of the methods already provided. However, you
 * are free to add new methods.
 */
public class CollectionsBasics {

    /**
     * Finds the maximum value in a list of integers. You can assume that the list
     * is not empty.
     *
     * @param numbers The list of integers.
     * @return The maximum value in the list.
     */
    public int maximum(List<Integer> numbers) {

        return Collections.max(numbers);
    }

    /**
     * Calculates the sum of all integers in a list.
     *
     * @param numbers The list of integers.
     * @return The sum of all integers in the list.
     */
    public int sum(List<Integer> numbers) {
        var sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum; 
    }

    /**
     * Concatenates (or joins) a list of strings into a single string.
     * For example, if the list contains the strings "foo", "bar" and "baz",
     * the result is "foobarbaz". You can assume that the list is not empty.
     *
     * @param strings The list of strings.
     * @return The concatenated string.
     */
    public String concatenateStrings(List<String> strings) {
        String newString = "";

        for (String string : strings) {
            newString += string;
        }

        return newString; 
    }

    /**
     * Returns the lengths of the strings in the input list.
     * For example, if the input list contains the strings "Java", "Python" and
     * "TypeScript", the result is a list containing the numbers 4, 6 and 10.
     *
     * @param strings The list of strings.
     * @return A list containing the lengths of the strings in the input list.
     */
    public List<Integer> getLengths(List<String> strings) {
        List<Integer> lengths = new ArrayList<Integer>();

        for (String string : strings) {
            lengths.add(string.length());
        }

        return lengths; 
    }

    public static void main(String[] args) {

        CollectionsBasics ohjelma = new CollectionsBasics();

        ArrayList<String> list=new ArrayList<String>();   
        list.add("Mango");    
        list.add("Applekmfoiermddkdkkkkkkkk");    
        list.add("Banana");    
        list.add("Grapes");    

        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(7);
        arr.add(4);
        arr.add(5);

        System.out.println(ohjelma.maximum(arr));
        System.out.println(ohjelma.sum(arr));
        System.out.println(ohjelma.concatenateStrings(list));
        System.out.println(ohjelma.getLengths(list).toString());


    }
}
