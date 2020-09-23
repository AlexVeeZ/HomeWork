package lesson_2_3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PhoneBook {

    protected HashMap<String, Set<String>> book = new HashMap<>();

    protected void add(String name, String number){
        Set<String> note = book.getOrDefault(name, new HashSet<>());
        note.add(number);
        book.put(name, note);
    }

    protected void add(String name, String... number){
        Set<String> note = book.getOrDefault(name, new HashSet<>());
        note.addAll(Arrays.asList(number));
        book.put(name, note);
    }

    protected Set<String> get(String name){return book.get(name);}

    protected void printInfo(){
        System.out.println(book);
    }



}
