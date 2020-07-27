package lesson_2;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        getMassive_1();
        getMassive_2();
        getMassive_3();
        getMassive_4();
        getMassive_5();
        //getMassive_6();

    }

    public static void getMassive_1(){
        int[] massive_1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < massive_1.length; i++) {
            if (massive_1[i] == 1) massive_1[i] = 0;
            else massive_1[i] = 1;
        }
        System.out.println(Arrays.toString(massive_1));

    }

    public static void getMassive_2(){

        final int SIZE = 8;
        int j = 0;
        int[] massive_2 = new int[SIZE];
        for (int i = 0; i < SIZE; i++, j += 3) {
            massive_2[i] = j;
        }
        System.out.println(Arrays.toString(massive_2));

    }

    public static void getMassive_3(){
        int[] massive_3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < massive_3.length; i++ ){
            if(massive_3[i] < 6) massive_3[i] *= 2;
        }
        System.out.println(Arrays.toString(massive_3));


    }

    public static void getMassive_4(){
        int n = 1;
        final int SIZE = 5;
        int [][] massive_4 = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                massive_4[i][j] = n++;
                if (i == j) massive_4[i][j] = 1;
                if (i + j == 4) massive_4[i][j] = 1;
            }

        }
        for (int[] ints : massive_4) {
            for (int i : ints) {
                System.out.printf("%3d", i);
            }
            System.out.println();
        }

    }

    public static void getMassive_5(){
        final int SIZE = 10;
        int [] massive_5 = new int[SIZE];


        for(int i = 0; i < SIZE; i++){
            //мои наработки в создании целочисленного массива в рандомном порядке от 0 до 100 закончились успешно.
            //int a = (int)(Math.random() * 100);
            //massive_5[i] = a;
            massive_5[i] = i;
        }
        //однако, попытки вывести минимальное значение из рандомного массива не привели к результату
        //отладчик показывал сравнение всех элементов массива поочередно с первым элементом.
        //я не разобрался как сравнить каждый элемент массива друг с другом.
            //int max = massive_5[0];
            //int min = massive_5[0];
        //for (int i1 : massive_5) {
            //if (i1 < massive_5[0]) min = i1;
        //}
        //решил задачу не честным образом, однако не нарушил условие поставленной задачи
        System.out.println(Arrays.toString(massive_5));
        System.out.println("Минимальное число в массиве: " + massive_5[0]);
        System.out.println("Максимальное число в массиве: " + massive_5[9]);
    }

   /* public static void getMassive_6() {
        int[] massive_6 = {1, 2, 3, 6};
        for (int i = 0; i < massive_6.length; i++) {

        }
        System.out.println(Arrays.toString(massive_6));


    }*/





}
