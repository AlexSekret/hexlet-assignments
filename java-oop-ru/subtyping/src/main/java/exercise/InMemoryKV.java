package exercise;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private Map<String, String> data;

    public InMemoryKV(Map<String, String> value) {
        data = new HashMap<>();
        var tmp = value.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> new String(e.getValue()), (oldValue, newValue) -> oldValue, HashMap::new));
        data.putAll(tmp);
    }

    @Override
    public void set(String key, String value) {
        data.put(key, value);
    }

    @Override
    public void unset(String key) {
        data.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        var tmp = data.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> new String(e.getValue()), (oldValue, newValue) -> oldValue, HashMap::new));
        return  tmp;
    }
}
// END
