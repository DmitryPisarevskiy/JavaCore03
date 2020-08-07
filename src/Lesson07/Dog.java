package Lesson07;

public class Dog {
    private static final int NUM_OF_PAWS=4;
    private static final int NUM_OF_EYES=2;
    private static final int NUM_OF_EARS=2;

    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @BeforeSuite
    static void mainInfo(){
        System.out.println("First Info: Это класс собак. Собака - друг человека!");
    }

    @Test (priority = 7)
    static void info1(){
        System.out.printf("Info1: У каждой собаки %d лапы\n", NUM_OF_PAWS);
    }

    @Test (priority = 2)
    static void info2(){
        System.out.printf("Info2: У каждой собаки %d глаза\n", NUM_OF_EYES);
    }

    @Test (priority = 5)
    static void info3(){
        System.out.printf("Info3: У каждой собаки %d уха\n", NUM_OF_EARS);
    }

    @AfterSuite
    static void lastInfo(){
        System.out.println("Last Info: Собаки - наши четвероногие друзья!");
    }

    @Test (priority = 1)
    static void doYouLikeDogs(boolean b){
        System.out.print("Вы любите собак? - ");
        System.out.println(b? "Конечно!": "Нет");
    }

    @Test (priority = 9)
    public void gav(int times) {
        for (int i = 0; i < times; i++) {
            System.out.print("gav ");
        }
        System.out.println("!");
    }

    @Test (priority = 5)
    public void info(){
        System.out.printf("Собаку зовут %s, ей %d лет", name, age);
    }

    @Test (priority = 7)
    public void givePaw() {
        System.out.println(name +" подала тебе лапу)");
    }
}
