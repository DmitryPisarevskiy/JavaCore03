package Lesson01;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
//    3. Большая задача:
//    Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
//    Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
//    Для хранения фруктов внутри коробки можно использовать ArrayList;
//    Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
//    Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
//    Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
//    Не забываем про метод добавления фрукта в коробку.
    public static void main(String[] args) {
        // 1.Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
        System.out.println("Задача 1. Метод меняющий элементы массива местами");
        String[] strArr= {"aaaa","bbbb","cccc"};
        System.out.println("Начальный массив строк - " + Arrays.asList(strArr));
        int firstElement=1;
        int secondElement=2;
        System.out.printf("Меняемые элементы - %d %d\n", firstElement, secondElement);
        System.out.println("Конечный массив строк - " + Arrays.asList(swingElements(strArr,firstElement, secondElement)));

        // 2. Написать метод, который преобразует массив в ArrayList;
        System.out.println("\nЗадача 2. Преобразование массива в ArrayList");
        Integer[] intArr={1,2,3,4,5,6,7};
        System.out.println("Начальный массив - " + Arrays.asList(intArr));
        ArrayList<Integer> intList=convertToArrayList(intArr);
        intList.add(78);
        System.out.println("Конечный ArrayList - " + intList);
        System.out.println(intList.getClass());

        // 3. Большая задача:
        System.out.println("\nЗадача 3. Фрукты и боксы");
        Box<Apple> box1 = new Box<>(new Apple(), new Apple(), new Apple());
        System.out.printf("Создан бокс box1 с яблоками. В ней находится %d яблок\n",
                box1.getNumOfFruits());

        Box<Orange> box2 = new Box<>(new Orange(), new Orange());
        System.out.printf("Создан бокс box2 с апельсинами. В ней находится %d апельсин\n",
                box2.getNumOfFruits());

        Box<Apple> box3 = new Box<>(new Apple(), new Apple(), new Apple(), new Apple());
        System.out.printf("Создан бокс box3 с яблоками. В ней находится %d яблок\n",
                box3.getNumOfFruits());

        System.out.println("Сравнение box1 и box2 - " + box1.compare(box2));
        System.out.println("Сравнение box2 и box3 - " + box2.compare(box3));

        int numOfFruits=3;
        box1.intersperse(box3);
        System.out.printf("Из box1 в box3 пересыпали %d яблок. В box1 %d яблок, в box3 %d яблок\n",
                numOfFruits, box1.getNumOfFruits(), box3.getNumOfFruits());
    }

    public static <T> T[] swingElements(T[] arr, int firstElement, int secondElement) {
        T buf=arr[firstElement];
        arr[firstElement]=arr[secondElement];
        arr[secondElement]=buf;
        return arr;
    }

    public static <T> ArrayList<T> convertToArrayList(T[] arr){
        return new ArrayList<>(Arrays.asList(arr));
    }
}
