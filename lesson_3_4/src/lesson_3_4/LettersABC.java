package lesson_3_4;

public class LettersABC {

    static Object mon = new Object();
    //так и не понял зачем ставить volatile, код и без него норм работает.
    static volatile char startLetter = 'A';

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (startLetter != 'A') {
                            mon.wait();
                        }
                        System.out.print("A");
                        startLetter = 'B';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (startLetter != 'B') {
                            mon.wait();
                        }
                        System.out.print("B");
                        startLetter = 'C';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        while (startLetter != 'C') {
                            mon.wait();
                        }
                        System.out.print("C");
                        startLetter = 'A';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}


