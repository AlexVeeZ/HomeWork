package lesson_2_1;

public class Cat implements RunAndJump, Competitors {

    protected String name;
    protected int maxDistance;
    protected int maxHeight;
    //protected boolean result = true;

    public Cat(String name, int maxDistance, int maxHeight) {
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }


    @Override
    public void run() {
        System.out.println(name +" бежит!");
    }

    @Override
    public void jump() {
        System.out.println(name +" прыгает!");
    }

    public void runDistance(RunningTrack runningTrack){
            System.out.println("Впереди препятствие: Беговая дорожка - " + runningTrack.distance + " м.");
            run();
            if (maxDistance >= runningTrack.getDistance()) {
                System.out.println(name + " справился с беговой дорожкой!");
                //result = true;
            } else {
                System.out.println(name + " не справился с беговой дорожкой!");
                //result = false;
            }

    }

    public void jumpTheWall(Wall wall){
            System.out.println("Впереди препятствие: Стенка высотой - " + wall.height + " м.");
            jump();
            if (maxHeight >= wall.getHeight()) {
                System.out.println(name + " перепрыгнул стену!");
                //result = true;
            } else {
                System.out.println(name + " не перепрыгнул стену!");
                //result = false;
            }
    }



}
