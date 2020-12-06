package lesson_2_2;

import java.io.OutputStream;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {


        try {
            myArray(5);

        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }
        myArray(4);

        try{
            myArray_2();
        }catch (MyArrayDataException e) {
            e.printStackTrace();
        }

    }

    public static void myArray(int size) throws MyArraySizeException {

        if (size != 4) {
            throw new MyArraySizeException("Array size must be 4, incorrect size: " + size, size);
        }

        int n = 0;

        int[][] myArray = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                myArray[i][j] = n++;
                System.out.printf(" " + myArray[i][j]);
            }
            System.out.println();

        }

    }

    public static void myArray_2() throws MyArrayDataException{

        String [] myArrayString = new String[]{"1","2","3","4","five","6","7"};

        int n = 0;

        for (int j = 0; j < myArrayString.length; j++) {
            if (myArrayString[j] == "five") {
                continue;
            }
            n = n + Integer.parseInt(myArrayString[j]);
        }
        System.out.println("\nSum of indexes: "+n);

        for (int i = 0; i < myArrayString.length; i++) {
            if (myArrayString[i] == "five") {
                throw new MyArrayDataException("Index incorrect: "+myArrayString[4]);
            }
        }



    }
}