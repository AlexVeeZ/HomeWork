package lesson_3_3;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class MyReader {

    protected Scanner sc = new Scanner(System.in);
    private String book;


    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void readBook(){

        try(FileInputStream fis = new FileInputStream(getBook())){
            byte[] arr = new byte[512];
            int x;
            byte userAnswer = 0;
            do{
                System.out.println("MENU:\nRead all book - 1\nRead page - 2\nExit - 3");
                userAnswer = sc.nextByte();
                if(userAnswer == 1){
                    while ((x = fis.read(arr)) > 0){
                        System.out.println(new String(arr, 0, x,"UTF-8"));
                    }
                }else if(userAnswer == 2){
                    readPage();
                }else if(userAnswer == 3){
                    return;
                }else{
                    System.out.println("Uncorrect menu point");
                }

            }while (true);




        }catch (
                IOException e){
            e.printStackTrace();
        }









    }

    public void readPage(){

        int pageNumber;
        int numberIndexInPage = 1800;
        int startIndex = 0;
        int finishIndex = 0;
        do{
            System.out.println("\nFor exit to menu - 0\nRead page #: ");
            pageNumber = sc.nextInt();
            System.out.println("Page # "+pageNumber);
            try(RandomAccessFile raf = new RandomAccessFile(getBook(),"r")){
                if(pageNumber == 1){
                    finishIndex = numberIndexInPage;
                    for (int i = startIndex; i < finishIndex; i++) {
                        raf.seek(i);
                        System.out.print((char)raf.read());
                    }

                } else if (pageNumber > 1) {
                    finishIndex = pageNumber * numberIndexInPage;
                    startIndex = finishIndex - numberIndexInPage;

                    for (int i = startIndex; i < finishIndex; i++) {
                        raf.seek(i);
                        System.out.print((char) raf.read());

                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }while(pageNumber != 0);
    }

}
