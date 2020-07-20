package lesson_6;



public class Main {


        public static void main(String[] args) {
            //задаем новые параметры препятствий для кота
            Cat cat = new Cat(500, 10,0);
            //публикуем информацию о препятствии и анализ выполнения задачи котом
            cat.printInfo();
            cat.run();
            cat.jump();
            cat.swim();

            //задаем новые параметры препятствий для собаки
            Dog dog = new Dog(300,5,5);
            //публикуем информацию о препятствии и анализ выполнения задачи собакой
            dog.printInfo();
            dog.run();
            dog.jump();
            dog.swim();
        }




}
