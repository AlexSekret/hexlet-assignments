package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Object obj) throws RuntimeException {
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        List<String> annotatedFields = new ArrayList<>();
        for (var field : fields) {
            field.setAccessible(true);
            try {
                if (field.isAnnotationPresent(NotNull.class) && (field.get(obj) == null)) {
                    annotatedFields.add(field.getName());
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return annotatedFields;
    }

    public static Map<String, List<String>> advancedValidate(Object obj) {
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        HashMap<String, List<String>> annotatedFields = new HashMap<>();
        for (var field : fields) {
            field.setAccessible(true);
            try {
                if (field.isAnnotationPresent(NotNull.class) && (field.get(obj) == null)) {
                    List<String> errList = new ArrayList<>();
                    errList.add("can not be null");
                    annotatedFields.put(field.getName(), errList);
                } else if (field.isAnnotationPresent(MinLength.class)) {
                    var minLength = field.getAnnotation(MinLength.class).minLength();
                    if (String.valueOf(field.get(obj)).length() < minLength) {
                        List<String> errList = new ArrayList<>();
                        errList.add(String.format("length less than %s", minLength));
                        annotatedFields.put(field.getName(), errList);
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return annotatedFields;
    }
}
// END
