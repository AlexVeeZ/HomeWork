package lesson_6;

public abstract class Animals {
    //отображаем аргументы препятствий
    protected int maxRun;
    protected double maxJump;
    protected int maxSwim;


    //создаем шаблон, входными данными будут задаваться характеристики препятствий
    public Animals(int maxRun, double maxJump, int maxSwim) {
        this.maxRun = maxRun;
        this.maxJump = maxJump;
        this.maxSwim = maxSwim;

    }
    //методы,отвечающие за сравнение возможности кота/собаки справиться с препятствиями
    public abstract boolean run();
    public abstract boolean jump();
    public abstract boolean swim();

    //вывод на экран информации
    public void printInfo(){
        System.out.println("\n\tПАРАМЕТРЫ ПРЕПЯТСТВИЙ:");
        System.out.println("Дистанция для бега (м.): "+ maxRun
                + "\nВысота препятствия (м.): " + maxJump
                + "\nДистанция для плавания (м.): " + maxSwim);

    }







}
