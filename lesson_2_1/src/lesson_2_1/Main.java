package lesson_2_1;

public class Main {

    /*1. Попытался объединить Wall и RunningTrack под одним интерфейсом,
    понял что идея плохая, когда пришлось переносить поля и методы стены в беговую дорожку и обратно,
    таким образом окончательно отказался от этой идеи , когда создав массив препятствий, участники бегали по стене,
    как по беговой дорожке и пытались перепрыгнуть дистанцию дорожки.*/

    /*2. Не понял ,как прервать участие спортсмена ,в случае его неудачи с преодолением препятствия
    (пробовал прервать цикл через оператор break - не сработало. Пробовал через булево значение true/ false - еще больше запутался)*/

    public static void main(String[] args) throws InterruptedException {

        //Ниже реализация ДЗ пункт 1, 2:

//        Cat cat = new Cat("Барсик",400,2);
//        Human human = new Human("Василий",1200,1);
//        Robot robot = new Robot("Робокоп",5000,3);
//
//        RunningTrack runningTrack = new RunningTrack(850);
//        cat.run();
//        human.run();
//        robot.run();
//
//        cat.runDistance(runningTrack);
//        human.runDistance(runningTrack);
//        robot.runDistance(runningTrack);
//
//        Wall wall = new Wall(2);
//        cat.jump();
//        human.jump();
//        robot.jump();
//
//        cat.jumpTheWall(wall);
//        human.jumpTheWall(wall);
//        robot.jumpTheWall(wall);

        //Ниже реализация ДЗ пункт 3, 4:

        Competitors [] competitors = {
                new Human("Василий",1200,1),
                new Cat("Барсик",400,2),
                new Robot("Робокоп",5000,3),
                new Human("Степан",1000,2),
                new Cat("Мурзик",200, 3),
                new Robot("Валли",2000,0),

        };

        Wall [] walls = {
                new Wall(1),
                new Wall(2),
                new Wall(3),
        };

        RunningTrack [] runningTracks = {
                new RunningTrack(200),
                new RunningTrack(1500),


        };

        for (Wall w : walls) {
            for (Competitors comp : competitors) {
                comp.jumpTheWall(w);
                Thread.sleep(5000);
            }
        }

        for (RunningTrack runTr : runningTracks) {
            for (Competitors comp : competitors) {
                comp.runDistance(runTr);
                Thread.sleep(5000);
            }
        }








    }
}
