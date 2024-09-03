package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        var map = storage.toMap();
        for (var e : map.entrySet()) {
            var key = e.getKey();
            var value = e.getValue();
            storage.unset(key);
            storage.set(value, key);
        }
    }
}
// END
