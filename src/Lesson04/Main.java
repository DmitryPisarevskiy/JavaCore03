package Lesson04;

public class Main {
    private static final Object mon = new Object();
    private static volatile char currentLetter = 'A';
    private static final int TIMES_TO_PRINT = 5;

    public static void main(String[] args) {
        Thread t1 = new Thread(Main::printA);
        Thread t2 = new Thread(Main::printB);
        Thread t3 = new Thread(Main::printC);
        t1.start();
        t2.start();
        t3.start();
    }

    public static void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < TIMES_TO_PRINT; i++) {
                    while (currentLetter != 'A') {
                        mon.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < TIMES_TO_PRINT; i++) {
                    while (currentLetter != 'B') {
                        mon.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < TIMES_TO_PRINT; i++) {
                    while (currentLetter != 'C') {
                        mon.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

