package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {
    public SingleTag(String tag, Map<String, String> attributes) {
        super(tag, attributes);
    }

    @Override
    public String toString() {
        var attr = super.getAttributes().entrySet();
        var result = new StringBuilder("<");
        result.append(String.format("%s", super.getTag()));
        for (var e : attr) {
            result.append(String.format(" %s=\"%s\"", e.getKey(), e.getValue()));
        }
        result.append(">");
        return result.toString();
    }
}
// END
