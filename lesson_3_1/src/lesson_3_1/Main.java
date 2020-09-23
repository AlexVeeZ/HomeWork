package lesson_3_1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {



    public static void main(String[] args) throws IOException {


        char [] charArr = new char[] {'@','#','№','$','%'};

        System.out.print("Массив первоначальный вид: " + Arrays.toString(charArr));
        Main.swap(charArr, 0, 4);
        System.out.println("");
        System.out.print("Массив после перестановки элементов: " + Arrays.toString(charArr));



        Integer[] array = {1,4,7,2,3};
        ArrayList <Integer> list = Main.toArrayList(array);


        // Задание 3

        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();

        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        System.out.println("");
        System.out.println("Добавляем:\nВ первый ящик - 3 яблока," +
                "\nВо второй - 2 апельсина," +
                "\nТретий -оставляем пустым");
        Box<Apple> box1 = new Box<Apple>(apple1, apple2, apple3);
        Box<Orange> box2 = new Box<Orange>(orange1, orange2);
        Box<Orange> box3 = new Box<Orange>();

        System.out.println("Вес 1-го ящика: "+box1.getWeight());
        System.out.println("Вес 2-го ящика: "+box2.getWeight());
        System.out.println("Вес 3-го ящика: "+box3.getWeight());

        System.out.println("Перекладываем апельсины из 2-го ящика в 3-ий:");
        box2.transfer(box3);
        System.out.println("Вес 2-го ящика: "+box2.getWeight());
        System.out.println("Вес 3-го ящика: "+box3.getWeight());
        System.out.println("Докладываем 1 апельсин во 2-й ящик");
        box2.add(orange1);
        System.out.println("Вес 2-го ящика: "+box2.getWeight());
    }

    //Задание 1 (метод)
    public static void swap (char[] arr, int index1, int index2){
        Object tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = (char) tmp;
    }
    //Задание 2 (метод)
    public static < T > ArrayList < T > toArrayList(T[]arr) throws IOException {
        ArrayList<T> list = new ArrayList<T>(Arrays.asList(arr));
        return list;
    }


}
