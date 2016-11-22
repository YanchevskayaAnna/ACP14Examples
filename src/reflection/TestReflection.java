package reflection;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by serhii on 04.09.16.
 */

public class TestReflection {

    public static void main(String[] args) {
        Test test = new Test(1, "Some test");
        getAllInfo(test);
    }


    public static void getAllInfo(Object object){
        Class cl = object.getClass();

        // new
        try {
            Object newInstance = cl.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field[] fields = cl.getDeclaredFields();

        System.out.println("FIELDS:");
        Arrays.stream(fields).forEach(System.out::println);

        System.out.println("METHODS:");
        Arrays.stream(cl.getDeclaredMethods()).forEach(System.out::println);

        System.out.println("CONSTRUCTORS:");
        Arrays.stream(cl.getDeclaredConstructors()).forEach(System.out::println);

    }

}
