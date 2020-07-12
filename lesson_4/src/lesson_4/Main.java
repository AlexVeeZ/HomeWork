package lesson_4;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;

    public static final char DOT_EMPTY = '•';
    public static final char DOT_HUMAN = 'X';
    public static final char DOT_AI = 'O';
    public static final String EMPTY = " ";
    public static final String FIRST_EMPTY_CHAR = "  ";

    public static char[][] map = new char[SIZE][SIZE];
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new  Random();

    public static void main(String[] args){

        turnGame();


    }

    public static void turnGame() {
        initMap();

        printMap();

        playGame();
    }

    public static void playGame() {
        while (true) {
            humanTurn();
            printMap();
            if(checkEnd(DOT_HUMAN, "Вы выиграли!")){
                System.exit(0);
            }

            aiTurn();
            printMap();
            if(checkEnd(DOT_AI, "Вы проиграли!")){
                System.exit(0);
            }


        }
    }



    public static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        printMapHeader();

        printMapRows();
    }

    public static void printMapHeader() {
        System.out.print(FIRST_EMPTY_CHAR);
        for (int i = 0; i < SIZE; i++) {
            printNumber(i);
        }
        System.out.println();
    }

    public static void printNumber(int i) {
        System.out.print(i + 1 + EMPTY);
    }


    public static void printMapRows() {
        for (int i = 0; i < SIZE; i++) {
            printNumber(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + EMPTY);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void humanTurn() {
        int rowNumber, colNumber;
        do {
            System.out.println("Ход пользователя!\nВведите номер строки и столбца");
            System.out.print("Строка: ");
            rowNumber = scanner.nextInt();
            System.out.print("Столбец: ");
            colNumber = scanner.nextInt();

        }while (!isCellValid(rowNumber, colNumber, DOT_HUMAN));

        map[rowNumber - 1][colNumber - 1] = DOT_HUMAN;


    }

    private static boolean isCellValid(int rowNumber, int colNumber, char symbol) {

        boolean isHuman = symbol == DOT_HUMAN;

        if((rowNumber < 1 || rowNumber > SIZE) || (colNumber < 1 || colNumber > SIZE)){

            if(isHuman)
            System.out.println("\nНекорректно введены значения строки/столбца");
            return false;
        }
        if(map[rowNumber - 1][colNumber - 1] != DOT_EMPTY){

            if(isHuman)
            System.out.println(("\nВы выбрали занятую ячейку!"));
            return false;
        }

        return true;
    }

    private static void aiTurn() {
        int rowNumber, colNumber;

        do {
            rowNumber = random.nextInt(SIZE)+ 1;
            colNumber = random.nextInt(SIZE)+ 1;

        }while (!isCellValid(rowNumber, colNumber, DOT_AI));

        map[rowNumber - 1][colNumber - 1] = DOT_AI;

    }

    private static boolean checkEnd(char symbol, String winMessage) {

        if(checkWin(symbol)){
            System.out.println(winMessage);
            return true;
        }
        if(isMapFull()){
            System.out.println("Ничья!");
            return true;
        }

        return false;
    }


    private static boolean isMapFull() {

        for (char[] chars : map) {
            for (char aChar : chars) {
                if (aChar == DOT_EMPTY){
                    return false;
                }
            }
        }
        return Boolean.TRUE;
    }

    private static boolean checkWin(char symbol) {
        if (map[0][0] == symbol && map[0][1] == symbol && map[0][2] == symbol && map[0][3] == symbol) return true;
        if (map[0][1] == symbol && map[0][2] == symbol && map[0][3] == symbol && map[0][4] == symbol) return true;
        if (map[1][0] == symbol && map[1][1] == symbol && map[1][2] == symbol && map[1][3] == symbol) return true;
        if (map[1][1] == symbol && map[1][2] == symbol && map[1][3] == symbol && map[1][4] == symbol) return true;
        if (map[2][0] == symbol && map[2][1] == symbol && map[2][2] == symbol && map[2][3] == symbol) return true;
        if (map[2][1] == symbol && map[2][2] == symbol && map[2][3] == symbol && map[2][4] == symbol) return true;
        if (map[3][0] == symbol && map[3][1] == symbol && map[3][2] == symbol && map[3][3] == symbol) return true;
        if (map[3][1] == symbol && map[3][2] == symbol && map[3][3] == symbol && map[3][4] == symbol) return true;
        if (map[4][0] == symbol && map[4][1] == symbol && map[4][2] == symbol && map[4][3] == symbol) return true;
        if (map[4][1] == symbol && map[4][2] == symbol && map[4][3] == symbol && map[4][4] == symbol) return true;

        if (map[0][0] == symbol && map[1][0] == symbol && map[2][0] == symbol && map[3][0] == symbol) return true;
        if (map[1][0] == symbol && map[2][0] == symbol && map[3][0] == symbol && map[4][0] == symbol) return true;
        if (map[0][1] == symbol && map[1][1] == symbol && map[2][1] == symbol && map[3][1] == symbol) return true;
        if (map[1][1] == symbol && map[2][1] == symbol && map[3][1] == symbol && map[4][1] == symbol) return true;
        if (map[0][2] == symbol && map[1][2] == symbol && map[2][2] == symbol && map[3][2] == symbol) return true;
        if (map[1][2] == symbol && map[2][2] == symbol && map[3][2] == symbol && map[4][2] == symbol) return true;
        if (map[0][3] == symbol && map[1][3] == symbol && map[2][3] == symbol && map[3][3] == symbol) return true;
        if (map[1][3] == symbol && map[2][3] == symbol && map[3][3] == symbol && map[4][3] == symbol) return true;
        if (map[0][4] == symbol && map[1][4] == symbol && map[2][4] == symbol && map[3][4] == symbol) return true;
        if (map[1][4] == symbol && map[2][4] == symbol && map[3][4] == symbol && map[4][4] == symbol) return true;

        if (map[3][0] == symbol && map[2][1] == symbol && map[1][2] == symbol && map[0][3] == symbol) return true;
        if (map[4][1] == symbol && map[3][2] == symbol && map[2][3] == symbol && map[1][4] == symbol) return true;
        if (map[4][0] == symbol && map[3][1] == symbol && map[2][2] == symbol && map[1][3] == symbol) return true;
        if (map[0][4] == symbol && map[1][3] == symbol && map[2][2] == symbol && map[3][1] == symbol) return true;
        if (map[0][1] == symbol && map[1][2] == symbol && map[2][3] == symbol && map[3][4] == symbol) return true;
        if (map[1][0] == symbol && map[2][1] == symbol && map[3][2] == symbol && map[4][3] == symbol) return true;
        if (map[0][0] == symbol && map[1][1] == symbol && map[2][2] == symbol && map[3][3] == symbol) return true;
        if (map[1][1] == symbol && map[2][2] == symbol && map[3][3] == symbol && map[4][4] == symbol) return true;


        return false;
    }
}
