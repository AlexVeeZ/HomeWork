import org.junit.Assert;
import org.junit.Test;


public class TestMethods {

    @Test
    public void test1ArraySort_2(){
        int [] in = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int [] out = new int[]{5, 6, 7, 8, 9};
        Assert.assertArrayEquals(out,MethodsForTest.arraySort_2(in));
    }

    @Test(expected = RuntimeException.class)
    public void test2ArraySort_2(){
        int [] in = new int[]{1, 2, 3, 5, 6, 7, 8, 9};
        MethodsForTest.arraySort_2(in);
    }

    @Test
    public void test1ArraySort_1(){
        int [] in = new int[]{0, 2, 3, 0, 5, 6, 7, 8, 9};
        Assert.assertFalse(MethodsForTest.arraySort_1(in));
    }

    @Test
    public void test2ArraySort_1(){
        int [] in = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assert.assertTrue(MethodsForTest.arraySort_1(in));
    }


}
