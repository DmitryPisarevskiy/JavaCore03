package Lesson05;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static final String ANSI_RESET="\u001B[0m";
    public static final String ANSI_GREEN="\u001B[32m";
    public static final int CARS_COUNT = 4;
    public static CountDownLatch cdl = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args) {
        System.out.println(ANSI_GREEN+"ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!"+ANSI_RESET);
        Race race = new Race(CARS_COUNT, new Road(60), new Tunnel(CARS_COUNT/2), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (Car car : cars) {
            new Thread(car).start();
        }
        try {
            race.getCdlStart().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ANSI_GREEN+"ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"+ANSI_RESET);
        try {
            race.getCdlFinish().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ANSI_GREEN+"ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!"+ANSI_RESET);
    }
}



