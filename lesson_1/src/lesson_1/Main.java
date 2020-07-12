package lesson_1;

import com.sun.org.apache.xpath.internal.res.XPATHErrorResources_sv;

public class Main {

    public static void main(String[] args) {

        char letter = 'M';
        String title = "Тихо!";
        int year = 1993;
        float numberP = 3.14f;
        boolean result = true;
        byte memory = 127;
        short numberShort = 32767;
        long numberLong = 999999999;
        double numberPoint = 36.6;


        //использовал все типы переменных и вывел данные

        System.out.println("Буква " + letter + " на барабане!");
        System.out.println("Идет экзамен " + title);
        System.out.println("Мой год рождения: " + year);
        System.out.println("Число пи равно " + numberP);
        System.out.println("Верно на английском звучит " + result);
        System.out.println("Средний рост 10 летнего ребенка: " + memory);
        System.out.println("Максимальное число для переменной short: " + numberShort);
        System.out.println("Зарплата Билла Гейтса в минуту " + numberLong);
        System.out.println("Температура тела " + numberPoint);




        System.out.println("Результат примера: " + getDecision(10,5,10,2.5));

        getInterval(13.5,7);

        getPlusMinus(10);

        getNegative(-10);

        greetingUser("Александр");

        getLeapYear(2025);
    }
    // метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат
    public static double getDecision(double a,double b,double c,double d){

        return a * (b + (c / d));

    }
    //метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах от 10 до 20(включительно)
    public static void getInterval(double a, double b){

        double c = a + b;

        if (c >= 10 && c <= 20){
            System.out.println(true);
        } else {
            System.out.println(false);
        }

    }
    //метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль положительное ли число передали, или отрицательное
    public static void getPlusMinus(double a){

        if(a >= 0){
            System.out.println("Заданное Вами число - положительное");
        } else {
            System.out.println("Заданное Вами число - отрицательное");
        }


    }
    //метод, которому в качестве параметра передается целое число, метод должен вернуть true, если число отрицательное
    public static void getNegative(double a){
        if (a < 0){
            System.out.println("true");
        }

    }
    //метод, которому в качестве параметра передается строка, обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»
    public static void greetingUser(String a){

        System.out.println("Привет, " + a + "!");

    }
    //метод, который определяет является ли год високосным, и выводит сообщение в консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный
    public static void getLeapYear (int a){

        if (a % 4 == 0 && (a % 100 != 0 || a % 400 == 0)){
            System.out.println("Указанный вами год: " + a + " високосный");
        } else {
            System.out.println("Указанный вами год: " + a + " не високосный");
        }

    }
}
