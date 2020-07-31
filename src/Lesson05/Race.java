package Lesson05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Race {
    private ArrayList<Stage> stages;
    private CountDownLatch cdlStart;
    private CountDownLatch cdlFinish;
    private int numOfFinisher=0;

    public void setNumOfFinisher(int numOfFinisher) {
        this.numOfFinisher = numOfFinisher;
    }

    public int getNumOfFinisher() {
        return numOfFinisher;
    }

    public CountDownLatch getCdlFinish() {
        return cdlFinish;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public CountDownLatch getCdlStart() {
        return cdlStart;
    }

    public Race(int carCount, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        cdlStart = new CountDownLatch(carCount);
        cdlFinish = new CountDownLatch(carCount);
    }
}