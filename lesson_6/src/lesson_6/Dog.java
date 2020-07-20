package lesson_6;

public class Dog extends Animals {

    //константы возможности собаки
    final private int MAX_RUN_DOG = 500;
    final private double MAX_JUMP_DOG = 0.5;
    final private int MAX_SWIM_DOG = 10;

    //конструктор со ссылкой на родительский класс (на вход принимаются параметры препятствий)
    public Dog(int maxRun, double maxJump, int maxSwim) {
        super(maxRun, maxJump, maxSwim);
    }

    //далее идут методы сравнения возможности кота справиться с препятствиями

    public boolean run(){
        if (this.maxRun <= MAX_RUN_DOG){
            System.out.println("Собака пробежала дистанцию: "+maxRun+" м.");
            return true;
        }

        System.out.println("Собака не смогла пробежать дистанцию, возможности собаки "+MAX_RUN_DOG+" м." );
        return false;



    }

    public boolean jump(){
        if (this.maxRun <= MAX_JUMP_DOG){
            System.out.println("Собака перепрыгнула препятствие: "+maxJump+" м.");
            return true;
        }

        System.out.println("Собака не смогла перепрыгнуть, возможности собаки "+MAX_JUMP_DOG+" м." );
        return false;



    }

    public boolean swim(){
        if (this.maxSwim <= MAX_SWIM_DOG){
            System.out.println("Собака переплыла препятствие: "+maxSwim+" м.");
            return true;
        }

        System.out.println("Собака не смогла переплыть препятствие, возможности собаки "+MAX_SWIM_DOG+" м." );
        return false;



    }








}
