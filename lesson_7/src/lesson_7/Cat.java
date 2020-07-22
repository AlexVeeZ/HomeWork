package lesson_7;

import java.util.Random;

public class Cat {

    private final String name;
    private int satiety;

    private final int maxSatiety;
    private boolean hunger = true;

    public Cat(String name, int maxSatiety) {
        this.name = name;
        this.maxSatiety = maxSatiety;
    }

    public void randomeAppetite(){
        satiety -= new Random().nextInt(5)+1;
        hunger = true;
        if(satiety < 0) satiety = 0;
        System.out.printf("Кот %s проголодался! Его сытость равна %s из %s%n", name,satiety,maxSatiety);


    }
    public Cat(String name){
        this(name, new Random().nextInt(3)+2);
    }
    public int getMaxSatiety(){return maxSatiety;}

    public void setHunger(boolean hunger){
        setHunger(hunger, 0);

    }

    public void setHunger (boolean hunger, int catAppetite){
        this.hunger = hunger;
        if(!this.hunger){
            this.satiety = maxSatiety;
        }else{
            this.satiety = maxSatiety - catAppetite;
        }
    }

    public String getName(){return name;}

    public int getSatiety(){return satiety;}

    public boolean getHunger(){return hunger;}

    public void eat() {
        boolean isOurCat = FoodObserver.getInstance().isOurCat(this);
        if (isOurCat)
            doEat();
        else
            System.out.printf("Кот %s засланец! А ну брысь! %n", name);

    }

    private void doEat(){FoodObserver.getInstance().decreaseFood(maxSatiety - satiety,this);}

}
