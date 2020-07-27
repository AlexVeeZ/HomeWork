package lesson_3;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        guessNumber();
        guessWords();

    }

    public static void guessNumber(){
        //объявляем два класса random и scanner
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        //ввожу переменную ,отвечающую за возврат/выход из цикла
        int oneMore;
        //запускаю цикл
        do {
            int rdm = random.nextInt(9);//компьютер загадывает число от 0 до 9
            System.out.println("Компьютер загадал число в диапазоне от 0 до 9.\nПользователю предлагается отгадать его за 3 попытки, \nиспользуя ввод цифрами и подсказки компьютера.");
            //ввожу цикл for для ограничения кол-ва попыток
            for (int i = 0; i < 3; i++) {
                int userAnswer = scanner.nextInt();//запрашиваю пользовательский вариант ответа
                //далее в цикле происходит сравнение правильного и пользовательского ответа
                if (userAnswer > rdm) {
                    System.out.println("Вы указали число, больше загаданного");
                } else if (userAnswer < rdm) {
                    System.out.println("Вы указали число, меньше загаданного");
                } else{
                    System.out.println("Вы угадали!");
                }

            }
            //по истечении 3-х попыток либо по факту угаданного числа, пользователю предлагают начать заново/выйти
            System.out.println("Желаете начать игру заново? \n1 - да \n0 - нет");
            oneMore = scanner.nextInt();
            //при ответе пользователя "1" цикл do while повторяется
        }while (oneMore == 1);

    }

    public static void guessWords(){
        //объявляем два класса random и scanner
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        //создаем массив из примера
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        //перемешиваем массив слов (получаем порядковый индекс загаданного слова)
        int rdm = random.nextInt(words.length);
        //приводим порядковый индекс к соответствующему строковому значению (получаем слово)
        String computerWord = words[rdm];
        //объявляем задание и инструкцию для пользователя, далее предлагаем пользователю ввести слово
        System.out.println("Укажите предполагаемое слово на английском языке: " + "\n" + (Arrays.toString(words)) + "\n*Указанное слово должно быть в нижнем регистре");
        String userAnswer = scanner.nextLine();
        //запускаю цикл с счетчиком в 15 символов
        for (int i = 0; i < 15; i++) {
            if (userAnswer.equals(computerWord)) {
                System.out.println("Вы отгадали слово!");
                return;
            } else if (userAnswer != computerWord){
                System.out.println(computerWord.charAt(i));//вывожу символы загаданного слова по порядку
                userAnswer = scanner.nextLine();
            }
        }//К сожалению, не хватило опыта для осуществления этой задачи в точности по тех. заданию.
    }
}
