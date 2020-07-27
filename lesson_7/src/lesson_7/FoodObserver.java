package lesson_7;

import java.util.ArrayList;

public final class FoodObserver {

    protected ArrayList<Cat> cats = new ArrayList<>();
    private ArrayList<Plate> plates = new ArrayList<>();

    private FoodObserver(){

    }

    private static FoodObserver instance;

    public static FoodObserver getInstance(){
        if(instance == null) instance = new FoodObserver();
        return instance;
    }

    public void addCat(Cat cat){
        cats.add(cat);
    }

    public void addPlate(Plate plate){plates.add(plate);}

    public void addFood(int food){
        if(food == 0) return;
        for(Plate plate : plates){
            food = plate.addFoodOnPlate(food);
            if (food == 0){
                notifyCats();
                return;
            }
        }
        notifyCats();
    }

    public void addFood(int food, int plateNumber){
        if (plates.size() < plateNumber) addFood(food);
        else{
            food -= plates.get(plateNumber-1).addFoodOnPlate(food);
            if(food != 0)
            {
            for (Plate plate : plates){
                food -= plate.addFoodOnPlate(food);
                if (food == 0) break;

            }}
            notifyCats();
        }

    }

    private void notifyCats(){
        System.out.println("Засыпали корм! Кис-кис-кис...!");
        for (Cat cat : cats) {
            if (cat.getHunger()) cat.eat();
        }
    }

    public void decreaseFood(int catAppetite, Cat cat){
        for (Plate plate: plates){
            catAppetite = plate.decreaseFoodOnPlate(catAppetite);
           if(catAppetite == 0) {
               cat.setHunger(false);
               return;
           }
           if (catAppetite != 0){
               cat.setHunger(true, catAppetite);
           }
        }
    }

    public int totalFood(){
        int food = 0;
        for(Plate plate: plates){
            food += plate.getFood();
        }
        return food;

    }

    public void platesInfo(){
        for (int i = 1; i < plates.size(); i++) {
            System.out.printf("В миске #%s - %s/%s еды: ", i, plates.get(i-1).getFood(),
            plates.get(i-1).getVolume());
        }
    }

    public void catsInfo(){
        for (Cat cat : cats){

            if(!cat.getHunger())
                System.out.printf("Кот %s доволен, сытость: %s/%s;%n", cat.getName(),cat.getSatiety(), cat.getMaxSatiety());
            else
                System.out.printf("Кот %s голоден!, сытость: %s/%s;%n", cat.getName(),cat.getSatiety(), cat.getMaxSatiety());




        }
        System.out.println();


    }
    public boolean isOurCat(Cat cat){
        return cats.contains(cat);
    }

}
