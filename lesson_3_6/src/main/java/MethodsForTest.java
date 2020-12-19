import java.util.Arrays;
import java.util.Random;

public class MethodsForTest {

    public static void main(String[] args) {



    }

    public static boolean arraySort_1(int [] array){

        boolean flag = false;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1 || array[i] == 4){
                flag = true;
            }
        }
        return flag;
    }

    public static int[] arraySort_2(int [] array){

        for (int i = array.length - 1; i >= 0; i--) {
            if(array[i] == 4){
                array = Arrays.copyOfRange(array,i++, array.length);
            }

        }
        throw new RuntimeException("В массиве нет числа 4");
    }

}
