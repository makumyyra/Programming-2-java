package part03;

import java.util.ArrayList;
import java.util.List;

import part02.CollectionsBasics;

/**
 * First, implement the missing parts in Person class. Then, implement the
 * methods in this class.
 *
 * You can use the JUnit test provided to verify that your implementation works
 * as expected. You can also write a main method to test your implementations.
 */
public class ObjectExercise {

    /**
     * Generates a formatted string from a list of Person objects, representing
     * their names. This method takes a list of Person objects and generates a
     * string that represents the names of the people in the list.
     *
     * The formatting of the resulting string depends on the number of people in the
     * list:
     *
     * - If the list is empty, an empty string is returned.
     * - If the list contains only one person, their name is returned ("Rachel").
     * - If the list contains two people, their names are joined with "and" in
     * between ("Rachel and Monica").
     * - If the list contains three people, all names are included and a comma is
     * used between first two names ("Rachel, Monica and Ross")
     * - If the list contains more than three people, the names of the first two
     * people are listed, followed by "and X others", where X is the number of
     * remaining people ("Rachel, Monica and 4 others").
     *
     * @param people A list of Person objects to generate the names string from.
     * @return A formatted string representing the names of people in the list.
     */
    public String generateNamesString(List<Person> people) {

        String result = "";
        
        if (people.size() != 0) {

            if (people.size() == 1) {
                return people.get(0).getName();
            } 
            else if (people.size() == 2) {
                result += people.get(0).getName() + " and " + people.get(1).getName(); 
            }
            else if (people.size() == 3) {
                result += people.get(0).getName() + ", " + people.get(1).getName() + " and " + people.get(2).getName(); 
            } else {
                result += people.get(0).getName() + ", " + people.get(1).getName() + " and " + (people.size() - 2) + " others";
            }

        }
        return result;
    }


    public static void main(String[] args) {

        ObjectExercise ohjelma = new ObjectExercise();
        ArrayList<Person> tyypit =new ArrayList<Person>();

        Person person1 = new Person("Suvi", 20);
        tyypit.add(person1);
        Person person2 = new Person("Heikki", 20);
        tyypit.add(person2);
        Person person3 = new Person("Miia", 20);
        tyypit.add(person3);
        Person person4 = new Person("Kiira", 20);
        tyypit.add(person4);
        Person person5 = new Person("Sepi", 20);
        tyypit.add(person5);
          
    
        String nimet = ohjelma.generateNamesString(tyypit);
        System.out.println(nimet);


    }
}

        