package exercise;

import jdk.jshell.execution.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class FileKV implements KeyValueStorage {
    private Map<String, String> data;
    private String pathFile;

    public FileKV(String path, Map<String, String> storage) {
        pathFile = path;
        data = storage.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> new String(e.getValue()), (oldValue, newValue) -> oldValue, HashMap::new));
        var content = Utils.serialize(data);
        Utils.writeFile(this.pathFile, content);
    }

    @Override
    public void set(String key, String value) {
        data.put(key, value);
        var content = Utils.serialize(data);
        Utils.writeFile(this.pathFile, content);
    }

    @Override
    public void unset(String key) {
        data.remove(key);
        var content = Utils.serialize(data);
        Utils.writeFile(this.pathFile, content);
    }

    @Override
    public String get(String key, String defaultValue) {
        String content = Utils.readFile(this.pathFile);
        data = Utils.deserialize(content);
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        var tmp = data.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> new String(e.getValue()), (oldValue, newValue) -> oldValue, HashMap::new));
        return tmp;
    }
}
// END
