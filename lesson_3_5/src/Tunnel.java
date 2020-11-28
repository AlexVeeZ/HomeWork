import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private Semaphore snp;


    public Tunnel() {
        snp = new Semaphore(MainClass.CARS_COUNT / 2);
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            if(!snp.tryAcquire()){
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                snp.acquire();
            }

            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println(c.getName() + " закончил этап: " + description);
            snp.release();
        }
    }
}
