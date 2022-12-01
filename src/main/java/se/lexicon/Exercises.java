package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message){
        System.out.println(message);
        //Write your code here


      List<Person> personList = storage.findMany((person -> person.getFirstName().equalsIgnoreCase("Erik")));
        personList.forEach((person -> System.out.println(person)));
        System.out.println("----------------------");
    }

    /*
        2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message){
        System.out.println(message);
        //Write your code here

        List<Person> personListAllFemales = storage.findMany((person -> person.getGender() == Gender.FEMALE));
        personListAllFemales.forEach((female) -> System.out.println(female));
        System.out.println("----------------------");
    }

    /*
        3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message){
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = (person -> person.getBirthDate().isAfter(LocalDate.parse("2000-01-01")) && person.getBirthDate().equals(LocalDate.parse("2000-01-01")) );
        List<Person> personBornAfter = storage.findMany(filter);
        personBornAfter.forEach((person) -> System.out.println(personBornAfter) );
        System.out.println("----------------------");
    }

    /*
        4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message){
        System.out.println(message);
        //Write your code here

       Person personFindById = storage.findOne((person -> person.getId() == 123));
        System.out.println(personFindById);

        System.out.println("----------------------");

    }

    /*
        5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message){
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = (person) -> person.getId() == 456;
        Function<Person, String> personUpdate = (person) -> {
            person.setFirstName("Nisse");
            person.setLastName("Bilsson");
            person.setBirthDate(LocalDate.parse("1999-09-09"));
            return person.toString();
        };

        String personToString = storage.findOneAndMapToString(filter,personUpdate);
        System.out.println(personUpdate);
        System.out.println("----------------------");
    }

    /*
        6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message){
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = (person) -> person.getGender() == Gender.MALE && person.getFirstName().toUpperCase().startsWith("E");
        Function<Person, String> personToString = (person) -> person.toString();

        List<String> foundMaleWithE = storage.findManyAndMapEachToString(filter,personToString);
        foundMaleWithE.forEach((male) -> System.out.println(male));
        System.out.println("----------------------");
    }

    /*
        7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message){
        System.out.println(message);
        //Write your code here

        Predicate<Person>  filter = (person) -> person.getBirthDate().getYear() < 10;
        Function<Person, String> toString = (person) -> person.toString();

       List<String> personsBelow10Year = storage.findManyAndMapEachToString(filter, toString);
       personsBelow10Year.forEach((person) -> System.out.println(person));
        System.out.println("----------------------");
    }

    /*
        8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message){
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = person -> person.getFirstName().toUpperCase().contains("ULF");
        Consumer<Person> printPerson = (person) -> System.out.println(person);
        storage.findAndDo(filter, printPerson);

        System.out.println("----------------------");
    }

    /*
        9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message){
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = (person) -> person.getFirstName().equalsIgnoreCase(person.getLastName());
        Consumer<Person> printPerson = (person) -> System.out.println(person);
        storage.findAndDo(filter, printPerson);

        System.out.println("----------------------");
    }

    /*
        10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static boolean isPalindrome(String name){
        String rev = "";
        for (int i=name.length()-1; i >=  0; i--){
            rev += name.charAt(i);
        }

        if(name.equalsIgnoreCase(rev)){
            return true;
        }else {
            return false;
        }
    }
    public static void exercise10(String message){
        System.out.println(message);
        //Write your code here


        Predicate<Person> filter = person -> isPalindrome(person.getFirstName());
        Consumer<Person> printPerson = (person) -> System.out.println(person);
        storage.findAndDo(filter, printPerson);

        System.out.println("----------------------");
    }

    /*
        11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message){
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = (person) -> person.getFirstName().toUpperCase().startsWith("A");
        Comparator<Person>  sortedByBirthdate = Comparator.comparing((person -> person.getBirthDate()));

         List<Person> personFindByA =  storage.findAndSort(filter,sortedByBirthdate);
         personFindByA.forEach((person -> System.out.println(person)));
        System.out.println("----------------------");
    }

    /*
        12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message){
        System.out.println(message);
        //Write your code here

        Predicate<Person> filter = (person) -> person.getBirthDate().getYear() < 1950;
        Comparator<Person> sortedByLastToEarly = Comparator.comparing( person -> person.getBirthDate());
        storage.findAndSort(filter, sortedByLastToEarly.reversed());
        System.out.println("----------------------");
    }

    /*
        13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message){
        System.out.println(message);
        //Write your code here



        Comparator<Person> compareFirstname = (Person s1, Person s2) -> s1.getFirstName().compareTo(s2.getFirstName());
        Comparator<Person> compareLastname = (Person s1, Person s2) -> s1.getLastName().compareTo(s2.getLastName());
        Comparator<Person> compareBirth = (Person s1, Person s2) -> s1.getBirthDate().compareTo(s2.getBirthDate());
        Comparator<Person> all = compareLastname.thenComparing(compareFirstname).thenComparing(compareBirth);

       List<Person> personLists = storage.findAndSort(all);
       personLists.forEach(person -> System.out.println(person));


        System.out.println("----------------------");
    }
}
