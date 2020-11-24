package lesson_2_5;

public class Test_2 extends Thread{

    static final int size = 10000000;
    static final int h = size / 2;

    public Test_2(String name){
        super(name);
    }


    public void run(){
        //создаем два счетчика (засекаем время)
        long a1 = System.currentTimeMillis();
        long a2 = System.currentTimeMillis();
        //создание массива
        float[] arr = new float[size];
        //делим массив на 2
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        //осуществляем цикл
        for (int i = 0; i < size; i++) {

            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        }
        //склеиваем массив
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        //отображаем затраченное время
        System.out.println(getName()+": "+(System.currentTimeMillis() - a1));
        System.out.println(getName()+": "+(System.currentTimeMillis() - a2));


    }



}
