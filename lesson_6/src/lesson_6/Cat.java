package lesson_6;

public class Cat extends Animals{

    //константы возможности кота
    final private int MAX_RUN_CAT = 200;
    final private int MAX_JUMP_CAT = 2;

    //конструктор со ссылкой на родительский класс (на вход принимаются параметры препятствий)
    public Cat(int maxRun, double maxJump, int maxSwim) {
        super(maxRun, maxJump, maxSwim);
    }

    //далее идут методы сравнения возможности кота справиться с препятствиями

    public boolean run(){
        if (this.maxRun <= MAX_RUN_CAT){
            System.out.println("Кот пробежал дистанцию: "+maxRun+" м.");
            return true;
        }

        System.out.println("Кот не смог пробежать дистанцию, возможности кота "+MAX_RUN_CAT+" м." );
        return false;



    }

    public boolean jump(){
        if (this.maxJump <= MAX_JUMP_CAT){
            System.out.println("Кот перепрыгнул препятствие: "+ maxJump + " м.");
            return true;
        }

        System.out.println("Кот не перепрыгнул препятствие, возможности кота "+MAX_JUMP_CAT+" м.");
        return false;



    }

    public boolean swim(){
        System.out.printf("Кот не умеет плавать!");
        return  false;
    }











}