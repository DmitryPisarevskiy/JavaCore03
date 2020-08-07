package Lesson07;

import javafx.fxml.Initializable;

import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class MyTest {
    static Integer intVar=4;
    static String stringVar="HELLO!";
    static Boolean booleanVar=true;
    public static final String ANSI_RESET="\u001B[0m";
    public static final String ANSI_GREEN="\u001B[32m";

    public static void start(Class c) {
        startWithClass(c);
    }

    public static void start(String nameOfClass) throws ClassNotFoundException {
        startWithClass(Class.forName(nameOfClass));
    }


    public static void start(Object object) {
        System.out.printf(ANSI_GREEN + "Тестирование методов объекта класса %s\n"+ ANSI_RESET, object.getClass().getName());
        Method[] methods = object.getClass().getDeclaredMethods();
        TreeSet<Method> setOfMethods = new TreeSet(new Comparator<Method>() {
            public int compare(Method m1, Method m2) {
//                int mInt1;
//                int mInt2;
//                if (m1.isAnnotationPresent(BeforeSuite.class)) {
//                    mInt1=11;
//                } else if (m1.isAnnotationPresent(AfterSuite.class)) {
//                    mInt1=0;
//                } else if (m1.isAnnotationPresent(Test.class)) {
//                    mInt1=m1.getAnnotation(Test.class).priority();
//                } else {
//                    mInt1=-1;
//                }
//                if (m2.isAnnotationPresent(BeforeSuite.class)) {
//                    mInt2=11;
//                } else if (m2.isAnnotationPresent(AfterSuite.class)) {
//                    mInt2=0;
//                } else if (m2.isAnnotationPresent(Test.class)) {
//                    mInt2=m2.getAnnotation(Test.class).priority();
//                } else {
//                    mInt2=-1;
//                }
//                return mInt2-mInt1;
                return getPriority(m2)-getPriority(m1);
            }
        });

        int numOfBefore=0;
        int numOfAfter=0;

        for (Method method : methods) {
            if ((method.isAnnotationPresent(Test.class)
                    || method.isAnnotationPresent(BeforeSuite.class)
                    || method.isAnnotationPresent(AfterSuite.class))
                    && !Modifier.isStatic(method.getModifiers())) {
                setOfMethods.add(method);
            }
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                numOfBefore++;
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                numOfAfter++;
            }
        }

        if (numOfAfter>1 || numOfBefore>1) {
            throw new RuntimeException();
        }

        for (Method method : setOfMethods) {
            Parameter[] parameters = method.getParameters();
            Object[] paramObjects = new Object[parameters.length];
            for (int i = 0; i <parameters.length; i++) {
                if (parameters[i].getType().equals(String.class)) {
                    paramObjects[i] = stringVar;
                } else if (parameters[i].getType().equals(int.class)) {
                    paramObjects[i] = intVar;
                } else if (parameters[i].getType().equals(boolean.class)) {
                    paramObjects[i] = booleanVar;
                }
            }
            try {
                System.out.printf("Название метода - %s, параметры - %s\n", method.getName(),Arrays.toString(parameters));
                method.invoke(object,paramObjects);
                System.out.println();
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static void startWithClass(Class c) {
        System.out.printf(ANSI_GREEN + "Тестирование статичных методов класса %s\n" + ANSI_RESET, c.getName());
        Method[] methods = c.getDeclaredMethods();
        TreeSet<Method> setOfMethods = new TreeSet(new Comparator<Method>() {
            public int compare(Method m1, Method m2) {
//                int mInt1;
//                int mInt2;
//                if (m1.isAnnotationPresent(BeforeSuite.class)) {
//                    mInt1=11;
//                } else if (m1.isAnnotationPresent(AfterSuite.class)) {
//                    mInt1=0;
//                } else if (m1.isAnnotationPresent(Test.class)) {
//                    mInt1=m1.getAnnotation(Test.class).priority();
//                } else {
//                    mInt1=-1;
//                }
//                if (m2.isAnnotationPresent(BeforeSuite.class)) {
//                    mInt2=11;
//                } else if (m2.isAnnotationPresent(AfterSuite.class)) {
//                    mInt2=0;
//                } else if (m2.isAnnotationPresent(Test.class)) {
//                    mInt2=m2.getAnnotation(Test.class).priority();
//                } else {
//                    mInt2=-1;
//                }
//                return mInt2-mInt1;
                return getPriority(m2)-getPriority(m1);
            }
        });

        int numOfBefore=0;
        int numOfAfter=0;

        for (Method method : methods) {
            if ((method.isAnnotationPresent(Test.class)
                    || method.isAnnotationPresent(BeforeSuite.class)
                    || method.isAnnotationPresent(AfterSuite.class))
                    && Modifier.isStatic(method.getModifiers())) {
                setOfMethods.add(method);
            }
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                numOfBefore++;
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                numOfAfter++;
            }
        }

        if (numOfAfter>1 || numOfBefore>1) {
            throw new RuntimeException();
        }

        for (Method method : setOfMethods) {
            Parameter[] parameters = method.getParameters();
            Object[] paramObjects = new Object[parameters.length];
            for (int i = 0; i <parameters.length; i++) {
                if (parameters[i].getType().equals(String.class)) {
                    paramObjects[i] = stringVar;
                } else if (parameters[i].getType().equals(int.class)) {
                    paramObjects[i] = intVar;
                } else if (parameters[i].getType().equals(boolean.class)) {
                    paramObjects[i] = booleanVar;
                }
            }
            try {
                System.out.printf("Название метода - %s, параметры - %s\n", method.getName(),Arrays.toString(parameters));
                method.invoke(null,paramObjects);
                System.out.println();
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static int getPriority(Method method) {
        int mInt2;
        if (method.isAnnotationPresent(BeforeSuite.class)) {
            return 11;
        } else if (method.isAnnotationPresent(AfterSuite.class)) {
            return 0;
        } else if (method.isAnnotationPresent(Test.class)) {
            return method.getAnnotation(Test.class).priority();
        } else {
            return -1;
        }
    }
}
