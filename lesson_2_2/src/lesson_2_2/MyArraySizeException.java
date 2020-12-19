package lesson_2_2;

public class MyArraySizeException extends ArrayIndexOutOfBoundsException{

    private int number;

    public MyArraySizeException(int number) {
        this.number = number;
    }

    public MyArraySizeException(String s,int number) {
        super(s);
        this.number = number;
    }


    public int getNumber() {
        return number;
    }






}
