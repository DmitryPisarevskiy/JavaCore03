package Lesson07;

public class Main {
    public static void main(String[] args) {
        MyTest.start(Dog.class);

        Dog zhuchka = new Dog("Жучка", 7);
        MyTest.start(zhuchka);
    }
}
