package lesson_7;

import java.util.Random;

public class Main {

    public static final int PLATE_SIZE = 3;
    public static final Random random = new Random();
    private static FoodObserver foodObserver = FoodObserver.getInstance();



    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < PLATE_SIZE; i++) {
            foodObserver.addPlate(new Plate());
        }

        Cat cat1 = new Cat("Барсик");
        Cat cat2 = new Cat("Варсик");
        Cat cat3 = new Cat("Гарсик");
        Cat cat4 = new Cat("Дарсик");
        Cat cat5 = new Cat("Жарсик");

        foodObserver.addCat(cat1);
        foodObserver.addCat(cat2);
        foodObserver.addCat(cat3);
        foodObserver.addCat(cat4);
        foodObserver.addCat(cat5);

        int foodCount;

        while (true){
            System.out.println("Доброе утро! Пора кормить котов!");
            foodCount = random.nextInt(12);
            System.out.printf("Добавили еды: ",foodCount);
            foodObserver.addFood(foodCount);

            foodObserver.catsInfo();
            foodObserver.platesInfo();

            System.out.println();
            System.out.println("Наступает ночь!");
            randomCatHungry();
            System.out.println("----\n");
            Thread.sleep(10000);
        }

    }


    public static void randomCatHungry() throws InterruptedException{
        for (Cat cat : foodObserver.cats){
            cat.randomeAppetite();
        }



    }
}
