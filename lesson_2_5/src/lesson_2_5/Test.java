package lesson_2_5;

public class Test extends Thread{

    static final int size = 10000000;
    static final int h = size / 2;

    public Test(String name){
        super(name);
    }


    public void run(){

        long a = System.currentTimeMillis();

        float[] arr = new float[size];

        for (int i = 0; i < size; i++) {

            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        }
        System.out.println(getName()+": "+(System.currentTimeMillis() - a));


    }


}