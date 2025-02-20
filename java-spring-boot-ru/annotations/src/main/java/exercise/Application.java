package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;

import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        var methods = address.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Inspect.class)) {
                System.out.println("Method " + method.getName()
                        + " returns a value of type " + method.getReturnType().getSimpleName());
            }
        }
        // END
    }
}
