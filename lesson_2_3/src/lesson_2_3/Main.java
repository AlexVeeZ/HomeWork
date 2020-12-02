package lesson_2_3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {


        String [] words = new String[]{
                "cherry","apple","pineapple","orange","lemon","banana","strawberry","mango","kiwi","coconut","apple","lemon","mango"
        };

        HashMap<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word,0) +1);
            
        }
        System.out.println(map);

        PhoneBook list = new PhoneBook();
        list.add("Филипп Киркоров","8-916-342-85-90");
        list.add("Николай Басков", "8-916-342-58-09","8-926-342-09-85");


        System.out.println(list.get("Николай Басков"));











        /*List<String> myArrayList = new LinkedList<>();

        myArrayList.add("cherry");,"pineapple","orange","lemon","banana","strawberry","mango","kiwi","coconut","apple","lemon","mango")
        myArrayList.add("apple");*/


    }
}
