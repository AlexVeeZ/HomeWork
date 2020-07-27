package lesson_7;

import java.util.Random;


public class Plate {

    private int food;
    private int volume;

    public Plate(int volume) { this.volume = volume; }

    public Plate(){this (new Random().nextInt(4)+3);}

    public int getVolume() {
        return volume;
    }

    public int getFood() {
        return food;
    }

    public int addFoodOnPlate(int food){

        if (volume - this.food>= food){
            this.food += food;
            return 0;
        }

        food -= (volume - this.food);
        this.food = volume;
        return food;

    }

    public int decreaseFoodOnPlate(int appetiteCount){
        if (appetiteCount > food){
            appetiteCount -= food;
            food = 0;
            return appetiteCount;
        }
        food -= appetiteCount;
        return 0;
    }



}
