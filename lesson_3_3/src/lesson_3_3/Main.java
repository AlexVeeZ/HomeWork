package lesson_3_3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args){


        /*File file = new File("txt6.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*try(FileInputStream fis = new FileInputStream("txt1.txt")){
            byte[] arr = new byte[512];
            int x;
            while ((x = fis.read(arr)) > 0){
                System.out.println(new String(arr, 0,x,"UTF-8"));
            }
        }catch (IOException e){
            e.printStackTrace();
        }*/

        /*long start = System.currentTimeMillis();
        ArrayList<FileInputStream> someFiles = new ArrayList<>();
        try {
            someFiles.add(new FileInputStream("txt1.txt"));
            someFiles.add(new FileInputStream("txt2.txt"));
            someFiles.add(new FileInputStream("txt3.txt"));
            someFiles.add(new FileInputStream("txt4.txt"));
            someFiles.add(new FileInputStream("txt5.txt"));
            SequenceInputStream in = new SequenceInputStream(Collections.enumeration(someFiles));

            byte[] arr = new byte[512];
            int x;
            while ((x = in.read(arr)) > 0){
                System.out.println(new String(arr, 0,x,"UTF-8"));
            }
            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        System.out.println(finish - start);*/

        MyReader myRead = new MyReader();
        myRead.setBook("txt6.txt");
        myRead.readBook();




    }






}
