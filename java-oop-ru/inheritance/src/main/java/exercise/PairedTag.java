package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag {
    private String bodyTag;
    private List<Tag> childrens;

    public PairedTag(String tag, Map<String, String> attributes, String bodyTag, List<Tag> childrens) {
        super(tag, attributes);
        this.bodyTag = bodyTag;
        this.childrens = new ArrayList<>(childrens);
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
        result.append(bodyTag);
        for (var e : childrens) {
            result.append(e.toString());
        }
        result.append(String.format("</%s>", super.getTag()));
        return result.toString();
    }
}
// END
