package exercise;

import java.util.LinkedHashMap;
import java.util.Map;

// BEGIN
public class Tag {
    private String tag;
    private Map<String, String> attributes;

    public Tag(String tag, Map<String, String> attributes) {
        this.tag = tag;
        this.attributes = new LinkedHashMap<>(attributes);
    }

    public String getTag() {
        return tag;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
// END
