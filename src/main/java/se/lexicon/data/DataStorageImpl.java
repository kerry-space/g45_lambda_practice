package se.lexicon.data;


import se.lexicon.model.Person;
import se.lexicon.util.PersonGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Create implementations for all methods. I have already provided an implementation for the first method *
 */
public class DataStorageImpl implements DataStorage {

    private static final DataStorage INSTANCE;

    static {
        INSTANCE = new DataStorageImpl();
    }

    private final List<Person> personList;

    private DataStorageImpl(){
        personList = PersonGenerator.getInstance().generate(1000);
    }

    static DataStorage getInstance(){
        return INSTANCE;
    }


    @Override
    public List<Person> findMany(Predicate<Person> filter) {
        List<Person> result = new ArrayList<>();
        for(Person person : personList){
            if(filter.test(person)){
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Person findOne(Predicate<Person> filter) {

        for (Person person: personList){
            if(filter.test(person)){
                return person;
            }
        }
        return null;
    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString){

        Person personFind =  findOne(filter);
        String stringOfPerson =  personToString.apply(personFind);
        return stringOfPerson;
    }

    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter, Function<Person, String> personToString){

       List<String> stringOfPersons = new ArrayList<>();
       for(Person person: personList){
           if(filter.test(person)){
               stringOfPersons.add(personToString.apply(person));
           }
       }
        return stringOfPersons;
    }

    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer){

        consumer.equals(findOne(filter));
    }

    @Override
    public List<Person> findAndSort(Comparator<Person> comparator){
       return null;
    }

    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator){

       List<Person> list = new ArrayList<>();
       list.add(findOne(filter));
       list.sort(comparator);

       return list;
    }
}
