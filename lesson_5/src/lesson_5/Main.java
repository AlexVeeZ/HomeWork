package lesson_5;

public class Main {



    public static void main(String[] args) {

        //создал анкеты сотрудников
        Worker worker_1 = new Worker("Иванов Петр Семенович", 45,'М',"Курьер",32500.00,"8-916-111-56-56");
        //System.out.println(worker_1);
        Worker worker_2 = new Worker("Климова Ольга Николаевна", 35,'Ж',"Кассир",38500.00,"8-916-222-65-65");
        //System.out.println(worker_2);
        Worker worker_3 = new Worker("Зайцева Кристина Сергеевна", 32,'Ж',"Администратор",48500.50,"zayceva@mail.ru","8-926-333-78-78");
        //System.out.println(worker_3);
        Worker worker_4 = new Worker("Петров Олег Анатольевич", 52,'М',"Повар",52300.90,"goodpovar@gmail.com","8-926-444-87-87");
        //System.out.println(worker_4);
        Worker worker_5 = new Worker("Сидоров Павел Генадьевич", 55,'М',"Грузчик",30100.30,"8-903-555-90-90");
        //System.out.println(worker_5);

        Worker[] staffArray = getWorkers();
        olderForty(worker_1, worker_2, worker_3, worker_4, worker_5, staffArray);

    }

    //метод отображает анкеты сотрудников, созданные в виде массива
    private static Worker[] getWorkers() {

        int STAFF = 5;
        Worker[] staffArray = new Worker[STAFF];

        staffArray[0] = new Worker("Иванов Петр Семенович", 45,'М',"Курьер",32500.00,"8-916-111-56-56");
        staffArray[1] = new Worker("Климова Ольга Николаевна", 35,'Ж',"Кассир",38500.00,"8-916-222-65-65");
        staffArray[2] = new Worker("Зайцева Кристина Сергеевна", 32,'Ж',"Администратор",48500.50,"zayceva@mail.ru","8-926-333-78-78");
        staffArray[3] = new Worker("Петров Олег Анатольевич", 52,'М',"Повар",52300.90,"goodpovar@gmail.com","8-926-444-87-87");
        staffArray[4] = new Worker("Сидоров Павел Генадьевич", 55,'М',"Грузчик",30100.30,"8-903-555-90-90");

        for (int i = 0; i < STAFF; i++) {

            System.out.println(staffArray[i]);

        }
        return staffArray;
    }

    //метод "пробегается" по возрасту сотрудников и возвращает анкеты сотрудников старше 40 лет
    private static void olderForty(Worker worker_1, Worker worker_2, Worker worker_3, Worker worker_4, Worker worker_5, Worker[] staffArray) {

        int STAFF_AGE = 40;

        System.out.println("\n\tСОТРУДНИКИ СТАРШЕ 40 ЛЕТ:");

        String result_1 = (worker_1.age > STAFF_AGE)? "true" : "false";
        if (result_1 == "true") System.out.println(staffArray[0]);
        String result_2 = (worker_2.age > STAFF_AGE)? "true" : "false";
        if (result_2 == "true") System.out.println(staffArray[1]);
        String result_3 = (worker_3.age > STAFF_AGE)? "true" : "false";
        if (result_3 == "true") System.out.println(staffArray[2]);
        String result_4 = (worker_4.age > STAFF_AGE)? "true" : "false";
        if (result_4 == "true") System.out.println(staffArray[3]);
        String result_5 = (worker_5.age > STAFF_AGE)? "true" : "false";
        if (result_5 == "true") System.out.println(staffArray[4]);
    }

}
