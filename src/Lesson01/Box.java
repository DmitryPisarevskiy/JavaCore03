package Lesson01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Box<T extends Fruit> {
    private ArrayList<T> content;

    public Box() {
        content=new ArrayList<>();
    }

    public Box(T... ts) {
        content=new ArrayList<>();
        content.addAll(Arrays.asList(ts));
    }

    public void add(T t) {
        content.add(t);
    }

    public void addAll(ArrayList<T> tArrayList) {
        content.addAll(tArrayList);
    }

    public float getWeight(){
        float sum=0.0f;
        for (T t : content) {
            sum+=t.getWeight();
        }
        return sum;
    }

    public int getNumOfFruits(){
        return content.size();
    }

    public boolean compare(Box<?> box){
        return this.getWeight() - box.getWeight() < 0.000001f;
    }

    public void intersperse(Box<T> box) {
//        int numOfFruits=this.getNumOfFruits();
//        for (int i = 0; i < numOfFruits; i++) {
//            box.add(this.content.remove(this.content.size()-1));
//        }
        box.addAll(this.content);
        this.content.clear();
    }

}
