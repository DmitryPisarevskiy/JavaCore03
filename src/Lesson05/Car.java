package Lesson05;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    public static final String ANSI_RESET="\u001B[0m";
    public static final String ANSI_GREEN="\u001B[32m";
    private static int CARS_COUNT;
    private CyclicBarrier cb;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            race.getCdlStart().countDown();
            race.getCdlStart().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        race.setNumOfFinisher(race.getNumOfFinisher()+1);
        switch (race.getNumOfFinisher()) {
            case 1:
                System.out.printf(ANSI_GREEN+"%s - ПОБЕДИТЕЛЬ ГОНКИ!!!\n"+ANSI_RESET, name);
                break;
            case 2:
                System.out.printf(ANSI_GREEN+"%s - ВТОРОЕ МЕСТО!!\n"+ANSI_RESET, name);
                break;
            case 3:
                System.out.printf(ANSI_GREEN+"%s - ТРЕТЬЕ МЕСТО!\n"+ANSI_RESET, name);
                break;
        }
        if (race.getNumOfFinisher()==CARS_COUNT) {
            System.out.printf(ANSI_GREEN+"%s - последнее место...\n"+ANSI_RESET, name);
        }
        race.getCdlFinish().countDown();
        try {
            race.getCdlFinish().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
